package app.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 1000*60*7)
    public void reportCurrentTime() throws IOException {
        System.out.println("The time is now " + dateFormat.format(new Date()));

        String url = "http://localhost:8081/get-proxies";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        //con.getResponseCode();
        con.getContent();
        System.out.println("reportCurrentTime !!!!");
    }


    @Scheduled(fixedRate = 1000*60*120)
    public void getProducts() throws IOException {
        System.out.println("The time is now " + dateFormat.format(new Date()));

        String url = "http://localhost:8081/get-products-from-category";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.getContent();
        System.out.println("getProducts !!!!");
    }

    @Scheduled(fixedRate = 1000*60*20)
    public void getProductInfo() throws IOException {
        System.out.println("The time is now " + dateFormat.format(new Date()));

        String url = "http://localhost:8081/get-products-info";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.getContent();
        System.out.println("getProductInfo !!!!");
    }


    @Scheduled(fixedRate = 1000*60*60)
    public void getPhotos() throws IOException {
        System.out.println("The time is now " + dateFormat.format(new Date()));

        String url = "http://localhost:8081/get-photos";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.getContent();
        System.out.println("getPhotos !!!!");
    }

    @Scheduled(fixedRate = 1000*60*60)
    public void downloadPhotos() throws IOException {
        System.out.println("The time is now " + dateFormat.format(new Date()));

        String url = "http://localhost:8081/download-photos";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.getContent();
        System.out.println("downloadPhotos !!!!");
    }

    @Scheduled(fixedRate = 1000*60*60)
    public void phash() throws IOException {
        System.out.println("The time is now " + dateFormat.format(new Date()));

        String url = "http://localhost:8081/phash";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.getContent();
        System.out.println("phash !!!!");
    }


    @Scheduled(fixedRate = 1000*60*60)
    public void distance() throws IOException {
        System.out.println("The time is now " + dateFormat.format(new Date()));

        String url = "http://localhost:8081/distance";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.getContent();
        System.out.println("distance !!!!");
    }


    @Scheduled(fixedRate = 1000*60*60)
    public void comments() throws IOException {
        System.out.println("The time is now " + dateFormat.format(new Date()));

        String url = "http://localhost:8081/comments";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.getContent();
        System.out.println("comments !!!!");
    }
}
