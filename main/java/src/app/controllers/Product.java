package app.controllers;

import app.crud.*;
import app.model.CategoryProperty;
import app.model.ProductProperty;
import app.model.Proxy;
import app.model.Site;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parser.Html;

import java.io.IOException;
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
    CategoryPropertiesRepositories propertiesSiteRepositories;
    @Autowired
    Md5PasswordEncoder md5PasswordEncoder;

    @RequestMapping("/get-products-info")
    public void getProductsInfo () throws IOException {
        List<app.model.Product> products = productsRepositories.findAll();
        Proxy proxy = proxyRepositories.findAll(new Sort(Sort.Direction.ASC, "timeout")).iterator().next();

        Html html = new Html();

        for(app.model.Product product : products) {
            // TODO релаизовать нормально
            List<CategoryProperty> properties =
                    propertiesSiteRepositories.findByCategory(product.getCategory());

            Site site = product.getSite();

            String url = site.getPath() + product.getPathUrl();
            Document doc = html.getHtmlDocument(url, proxy.getIp(), proxy.getPort());

            for(CategoryProperty prop : properties) {
                String selector = prop.getValueSelector();
                String value = doc.select(selector).text();
                if(value==null || value.isEmpty()) {
                    // TODO правильная обработка ошибок
                    //throw new RuntimeException("Свойство не может быть пустым, url = "+url+", selector = "+selector);
                }
                ProductProperty productProperty =
                        productPropertiesRepositories.findByProductAndProperty(product, prop.getProperty());
                productProperty = productProperty!=null ? productProperty : new ProductProperty();
                productProperty.setProduct(product);
                productProperty.setProperty(prop.getProperty());
                productProperty.setValue(value);

                productPropertiesRepositories.save(productProperty);
            }

        }

    }
}
