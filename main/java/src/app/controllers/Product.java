package app.controllers;

import app.crud.*;
import app.model.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parser.Html;
import parser.ImagePHash;
import parser.Images;
import parser.Phone;

import java.io.*;
import java.util.Date;
import java.util.List;

@RestController

public class Product {

    @Autowired
    SitesRepositories sitesRepositories;
    @Autowired
    ProxyRepositories proxyRepositories;
    @Autowired
    CategoriesRepositories categoriesRepositories;
    @Autowired
    CategoriesSitesRepositories categoriesSitesRepositories;
    @Autowired
    ProductPropertiesRepositories productPropertiesRepositories;
    @Autowired
    ProductsRepositories productsRepositories;
    @Autowired
    ProductPhotosRepositories productPhotosRepositories;
    @Autowired
    CategoryPropertiesRepositories propertiesSiteRepositories;
    @Autowired
    ServiceRepositories serviceRepositories;
    @Autowired
    ProductServiceRepositories productServiceRepositories;
    @Autowired
    PhashPhotoRepositories phashPhotoRepositories;
    @Autowired
    Md5PasswordEncoder md5PasswordEncoder;

    @RequestMapping("/get-products-info")
    public void getProductsInfo () throws IOException {
        List<app.model.Product> products = productsRepositories.findAll();
        System.out.println(products);

        Html html = new Html();
        products.parallelStream().forEach(product -> {
            if(!needUpdate(product.getDateUpdate())) {return;}

            Proxy proxy = proxyRepositories.findByBest();
            List<CategoryProperty> properties =
                    propertiesSiteRepositories.findBySite(product.getSite());

            Site site = product.getSite();
            String productUrl = product.getPathUrl();
            String url = productUrl.startsWith("http")?productUrl : site.getPath() + productUrl;
            Document doc = null;
            try {
                doc = html.getHtmlDocument(url, proxy.getIp(), proxy.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(null==doc) {
                System.out.println("document is null... in url = "+url);
                return;
            }

            for(CategoryProperty prop : properties) {
                String selector = prop.getValueSelector();
                Elements tags = doc.select(selector);
                if(tags==null || tags.size()==0) {continue;}
                Elements tag = tags.eq(0);
                String value = tag.text();
                if(tags.iterator().next().tagName().contains("img")) {
                    value = "is image!";
                }
                if(value.isEmpty()) {value = "is empty";}
                ProductProperty productProperty =
                        productPropertiesRepositories.findByProductAndProperty (product, prop.getProperty());
                productProperty = productProperty != null ? productProperty : new ProductProperty();
                productProperty.setProduct(product);
                productProperty.setProperty (prop.getProperty());
                if(prop.getDeleteText()!=null) {
                    value = value.replaceAll(prop.getDeleteText(), "");
                }
                if(prop.getProperty().getIsInteger()) {
                    value = convertToInt(value); // convert to int
                }

                if(prop.getProperty().getIsPhone()) {
                    value = Phone.parse(value); // parse phone number
                    if(value==null) {continue;} // что-бы не перезаписать существующий номер
                }
                System.out.println(value);
                productProperty.setValue (value);
                productPropertiesRepositories.save(productProperty);
            }

            getServices(doc, product); // get services by products

            product.setHtml(doc.outerHtml());
            //product.setDateUpdate(new Date());
            productsRepositories.save(product); // update date_update in product
        });
    }

    private String convertToInt (String val) {
        return val.replaceAll("[^0-9]", "");
    }

    private void getServices (Document document, app.model.Product product) {
        String selector = product.getSite().getServicesIteratorSelector();
        Elements services = document.select(selector);
        if(services==null || product==null) return;
        services.forEach(service -> {
            String serviceTitle = service.text();

            serviceTitle = serviceTitle.toLowerCase().trim();

            Service serviceDb = serviceRepositories.findByTitleAndSite(serviceTitle, product.getSite());
            if (serviceDb == null) {
                serviceDb = new Service();
                serviceDb.setTitle(serviceTitle);
                serviceDb.setSite(product.getSite());
                System.out.println(product.getSite().getId());
                serviceDb = serviceRepositories.save(serviceDb);
            }
            ProductService ps =
                    productServiceRepositories.findByProductAndService(product, serviceDb);
            if(ps!=null){return;}
            ProductService productService = new ProductService();
            productService.setProduct(product);
            productService.setService(serviceDb);
            productService.setDate(new Date());

            productServiceRepositories.save(productService);
        });

    }

    private boolean needUpdate (Date time) {
        if(time == null) return true;
        Date date = new Date();
        long different = (date.getTime() - time.getTime())/1000/60;
        System.out.println("last min updated = "+different);
        return ( different > 30*60  ); // 30 min
    }
}
