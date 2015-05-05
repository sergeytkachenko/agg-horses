package app.controllers;

import app.crud.*;

import app.model.CategoriesSite;
import app.model.Comment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestController
public class Comments {

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
    CommentsRepositories commentsRepositories;
    @Autowired
    Md5PasswordEncoder md5PasswordEncoder;

    @RequestMapping("/comments")
    public void distance() throws IOException {
        List<app.model.Product> pList = productsRepositories.findByDateUpdate(null);
        for(app.model.Product p : pList) {
            CategoriesSite cs = categoriesSitesRepositories.findBySiteAndCategory(p.getSite(), p.getCategory());
            String ct = cs.getCommentIteratorSelector();

            if(p.getHtml() == null){continue;}

            Document doc = Jsoup.parse(p.getHtml());
            if(doc == null) {continue;}

            Elements comments = doc.select(ct);
            if(comments==null || comments.size()==0) {continue;}

            comments.forEach(comment-> {
                String author = comment.select(cs.getCommentAuthorSelector()).text();

                String text = comment.select(cs.getCommentTextSelector()).text();

                Comment c = new Comment();
                c.setProduct(p);
                c.setAuthor(author);
                c.setComment(text);
                System.out.println(comment);
                String dateSelector = cs.getCommentDateSelector();
                if(dateSelector!=null) {
                    c.setDate(parseDate(comment.select(dateSelector).text()));
                }

                commentsRepositories.save(c);
            });

            p.setDateUpdate(new Date());
            productsRepositories.save(p);
        }
    }

    private Date parseDate (String date) {
        return new Date();
    }

}
