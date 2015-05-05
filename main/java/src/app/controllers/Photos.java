package app.controllers;

import app.crud.*;
import app.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parser.ImagePHash;
import parser.Images;

import java.io.*;
import java.util.Date;
import java.util.List;


@RestController
@PropertySource(value="classpath:photos-dev.properties")
public class Photos {

    @Value( "${photos.directory}")
    private String photosDirectory;

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

    ImagePHash phash = new ImagePHash();

    @RequestMapping("/get-photos")
    private void getPhotos () {
        productsRepositories.findByDateUpdateAndHtmlIsNotNull(null).forEach(product -> {
            Document document = Jsoup.parse(product.getHtml());
            String selector = product.getSite().getImagesIteratorSelector();
            Elements images = document.select(selector);
            System.out.println("images = "+images);
            if(images==null){return;}
            images.forEach(image -> {
                String imgSrc = null;
                if (image.tagName().contains("img")) {
                    imgSrc = image.attr("src");
                } else if(image.tagName().contains("a")) {
                    imgSrc = image.attr("href");
                }
                String hash = md5PasswordEncoder.encodePassword(imgSrc, null);

                ProductPhoto productPhoto = productPhotosRepositories.findByOutHash(hash);
                productPhoto = productPhoto==null? new ProductPhoto() : productPhoto;
                productPhoto.setProduct(product);
                productPhoto.setOutImgSrc(imgSrc);
                productPhoto.setOutHash(hash);
                productPhotosRepositories.save(productPhoto);
            });
        });
    }

    @RequestMapping("/download-photos")
    public void downloadPhotos() {
        System.out.println(photosDirectory);
        productPhotosRepositories.findByIsDownloaded(false).forEach(photo -> {
            Proxy proxy = proxyRepositories.findByBest();
            String photoSrc = photo.getOutImgSrc();
            photoSrc = photoSrc.replaceAll("medium_", "");
            try {
                String filename =
                        Images.download(
                                photoSrc,
                                photo.getProduct().getSite().getPath(),
                                photo.getId(),
                                photosDirectory,
                                proxy
                        );
                if(null==filename){return;}
                photo.setImgSrc(filename);
                photo.setIsDownloaded(true);
                InputStream is = new FileInputStream(new File(photosDirectory + "/" + filename).getPath());
                String hash = phash.getHash(is);
                photo.setPhash(hash);
                productPhotosRepositories.save(photo);
                System.out.println(photo);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @RequestMapping("/phash")
    public void phash() {
        productPhotosRepositories.findByPhashIsNull().forEach(photo -> {
            InputStream is = null;
            try {
                is = new FileInputStream(new File(photosDirectory + "/" + photo.getImgSrc()).getPath());
                String hash = phash.getHash(is);
                photo.setPhash(hash);
                productPhotosRepositories.save(photo);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    @RequestMapping("/distance")
    public void distance() {
        List<ProductPhoto> photos = productPhotosRepositories.findAll();
        photos.forEach(p1 -> {
            String ph1 = p1.getPhash();
            if(ph1==null){return;}
            photos.forEach(p2 -> {
                if (p1.getId() == p2.getId()) {
                    return;
                }
                String ph2 = p2.getPhash();
                if (ph2 == null) {
                    return;
                }
                int distance = phash.distance(ph1, ph2);
                if (distance < 10) {
                    PhashPhoto phashPhoto = new PhashPhoto();
                    phashPhoto.setPhotoIdX(p1.getId());
                    phashPhoto.setPhotoIdY(p2.getId());
                    phashPhoto.setDistance(distance);

                    phashPhotoRepositories.save(phashPhoto);
                }
            });

            productPhotosRepositories.save(p1);
        });
    }

    private boolean needUpdate (Date time) {
        if(time == null) return true;
        Date date = new Date();
        long different = (date.getTime() - time.getTime())/1000/60;
        System.out.println("last min updated = "+different);
        return ( different > 800*60  ); // 20 min
    }

}
