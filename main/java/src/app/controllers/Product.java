package app.controllers;

import app.crud.*;
import app.model.*;
import app.model.Category;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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
    CategoriesSitesRepositories categoriesSitesRepositories;
    @Autowired
    ProductsRepositories productsRepositories;
    @Autowired
    PropertiesSiteRepositories propertiesSiteRepositories;
    @Autowired
    Md5PasswordEncoder md5PasswordEncoder;

    @RequestMapping("/get-products-info")
    public void getProductsInfo () throws IOException {
        List<app.model.Product> products = productsRepositories.findAll();
        Proxy proxy = proxyRepositories.findAll(new Sort(Sort.Direction.ASC, "timeout")).iterator().next();

        Html html = new Html();

        for(app.model.Product product : products) {
            // TODO релаизовать нормально
            List<PropertiesSite> properties =
                    propertiesSiteRepositories.findByCategorySites(categoriesSitesRepositories.findById(2));

            String url = product.getPathUrl();
            Document doc = html.getHtmlDocument(url, proxy.getIp(), proxy.getPort());

            for(PropertiesSite prop : properties) {
                String selector = prop.getValueSelector();
                Elements value = doc.select(selector);
                System.out.println(value);
            }

            return;
        }

    }
}
