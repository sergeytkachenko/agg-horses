package parser;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Browser {
    CloseableHttpClient httpClient;

    public Browser() {
        String userAgent = "Mozilla/5.0 (Windows NT x.y; rv:10.0) Gecko/20100101 Firefox/10.0";
        httpClient = HttpClients.custom().setUserAgent(userAgent).build();
        System.out.println("open HttpClients");
    }

    public CloseableHttpClient get () {
        return httpClient;
    }

    public void close() throws IOException {
        httpClient.close();
    }

    static Browser instance=null;

    public static Browser getInstance() {
        if(instance==null) {
            instance = new Browser();
        }
        return instance;
    }
}
