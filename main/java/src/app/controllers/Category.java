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
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parser.Html;

import java.io.IOException;

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
        Site site = sitesRepositories.findById(2);
        CategoriesSite category = categoriesSitesRepositories.findById(2);
        Proxy proxy = proxyRepositories.findAll(new Sort(Sort.Direction.ASC, "timeout")).iterator().next();

        String url = site.getPath() + category.getPathUrl(); // url страницы без пагинации
        System.out.println(url);

        Html html = new Html();
        // проъодимся по всем страницам
        for (int i = 1, countErrro=0; i < 3000; i++) {
            String urlInPage = url;
            if(i!=1) {
                // added number page for url
                String page = site.getPage().replace("{n}", String.valueOf(i));
                urlInPage = url.contains("?") ? url + "&" + page : url + "?" + page;
            }

            Document doc = html.getHtmlDocument(urlInPage, proxy.getIp(), proxy.getPort());
            if(doc==null) {
                System.out.println("Document is NULL");
                continue;
            }
            Elements items = doc.select(category.getPageIteratorSelector());
            if(items.size()==0) {
                System.out.println("Items is empty");
                countErrro++;
                if(countErrro > 3) break;
            }
            items.forEach(item->{
                Elements a = item.select(category.getASelector());
                if(a!=null) {
                    String aHref = a.attr("href");
                    final String urlHash = md5PasswordEncoder.encodePassword(aHref, null);

                    Product product = productsRepositories.findByPathHash(urlHash);
                    product = product!=null?product:new Product();

                    product.setPathUrl(aHref);
                    product.setSite(site);
                    product.setPathHash(urlHash);

                    productsRepositories.save(product);
                }
            });
        }
    }
}
