package app.controllers;

import app.crud.CategoriesSitesRepositories;
import app.crud.ProductsRepositories;
import app.crud.ProxyRepositories;
import app.crud.SitesRepositories;
import app.model.CategoriesSite;
import app.model.Product;
import app.model.Proxy;
import app.model.Site;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parser.Html;

import java.io.IOException;
import java.util.Date;

@RestController
public class Category {

    @Autowired
    SitesRepositories sitesRepositories;
    @Autowired
    ProxyRepositories proxyRepositories;
    @Autowired
    CategoriesSitesRepositories categoriesSitesRepositories;
    @Autowired
    ProductsRepositories productsRepositories;
    @Autowired
    Md5PasswordEncoder md5PasswordEncoder;

    @RequestMapping("/get-products-from-category")
    public void ping () throws IOException {
        Site site = sitesRepositories.findById(1);
        CategoriesSite categorySite = categoriesSitesRepositories.findById(1);
        Proxy proxy = proxyRepositories.findByBest();
        System.out.println(proxy);
        String url = categorySite.getPathUrl();
        url = url.startsWith("http") ? url : site.getPath() + categorySite.getPathUrl(); // url страницы без пагинации
        System.out.println(url);

        Html html = new Html();
        // проходимся по всем страницам
        for (int i = 1, countErrors=0; i < 3000; i++) {
            String urlInPage = url;
            if(i!=1) {
                // added number page for url
                String page = site.getPage().replace("{n}", String.valueOf(i));
                if(url.contains("?")) {
                    if(!page.startsWith("/")) {
                        urlInPage = url + "&" + page;
                    } else {
                        urlInPage = url + page;
                    }
                } else {
                    if(!page.startsWith("/")) {
                        urlInPage = url + "?" + page;
                    } else {
                        urlInPage = url + page;
                    }
                }
            }
            System.out.println(urlInPage);
            Document doc = html.getHtmlDocument(urlInPage, proxy.getIp(), proxy.getPort());
            if(doc==null) {
                System.out.println("Document is NULL");
                countErrors++;
                if(countErrors > 5) break;
                continue;
            }
            Elements items = doc.select(categorySite.getPageIteratorSelector());
            if(items.size()==0) {
                System.out.println("Items is empty");
                countErrors++;
                if(countErrors > 5) break;
            }
            items.forEach(item->{
                Elements a = item.select(categorySite.getASelector());
                if(a!=null) {
                    String aHref = a.attr("href");
                    final String urlHash = md5PasswordEncoder.encodePassword(aHref, null);

                    Product product = productsRepositories.findByPathHash(urlHash);
                    product = product!=null?product:new Product();

                    product.setPathUrl(aHref);
                    product.setSite(site);
                    product.setPathHash(urlHash);
                    product.setTitle(a.text());
                    product.setCategory(categorySite.getCategory());
                    product.setDateCreate(new Date());

                    productsRepositories.save(product);
                }
            });
        }
    }
}
