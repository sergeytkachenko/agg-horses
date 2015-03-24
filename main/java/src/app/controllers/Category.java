package app.controllers;

import app.crud.CategoriesSitesRepositories;
import app.crud.ProxyRepositories;
import app.crud.SitesRepositories;
import app.model.CategoriesSite;
import app.model.Proxy;
import app.model.Site;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @RequestMapping("/get-products-from-category")
    public void ping () throws IOException {
        Site sites = sitesRepositories.findById(1);
        CategoriesSite category = categoriesSitesRepositories.findById(1);
        Proxy proxy = proxyRepositories.findAll(new Sort(Sort.Direction.ASC, "timeout")).iterator().next();

        String url = sites.getPath() + category.getPathUrl();
        System.out.println(url);

        Html html = new Html();
        Document doc = html.getHtmlDocument(url, proxy.getIp(), proxy.getPort());
        doc.select(category.getPageIteratorSelector()).forEach(item->{
            System.out.println(item.select(category.getASelector()));
        });
    }
}
