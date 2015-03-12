package parser;


import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Html {
    int error = 0;
    public Document getHtmlDocument (String url, String ip, String port) throws IOException {
        HttpHost proxy = new HttpHost(ip, Integer.valueOf(port), "http");
        System.out.println("proxy ip="+ip+", port="+port);
        CloseableHttpClient httpClient = new Browser().get();

        Document doc = null;
        try {

            HttpGet httpget = new HttpGet(url);

            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectTimeout(1000*8)
                    //.setConnectionRequestTimeout(2000)
                    //.setSocketTimeout(1000)
                    .build();
            httpget.setConfig(config);

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    System.out.println("status =" + status);
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity, HTTP.UTF_8) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpClient.execute(httpget, responseHandler);
            doc = Jsoup.parse(responseBody);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        } finally {
            System.out.println("close "+url);
            httpClient.close();
        }

        return doc;
    }

}
