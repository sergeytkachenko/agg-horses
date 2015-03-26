package app.controllers;

import app.crud.ProxyRepositories;
import app.model.Proxy;
import com.sergey.ping.Ping;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parser.Html;

import java.io.IOException;
import java.util.List;

@RestController
public class Proxies {

    @Autowired
    ProxyRepositories proxyRepositories;

    @RequestMapping("/get-proxies")
    public void getProxies () throws IOException {
        String url = "http://www.us-proxy.org/";
        Html html = new Html();
        Document doc = html.getHtmlDocument(url);

        Elements trList = doc.select("table#proxylisttable tr");
        for (Element tr : trList) {
            String ip = tr.select("td:eq(0)").text();
            String port = tr.select("td:eq(1)").text();

            if(ip==null || ip.isEmpty()) {continue;}
            if(proxyRepositories.findByIp(ip)!=null){continue;}

            Proxy proxy = new Proxy();
            proxy.setIp(ip);
            proxy.setPort(port);

            proxyRepositories.save(proxy);
        }
    }

    @RequestMapping("/ping-proxies")
    public void pingProxies () throws IOException {
        // TODO обрабатывать exception, set limit for ping
        List<Proxy> proxies = proxyRepositories.findAll();
        for(Proxy proxy : proxies) {
            float time = Integer.MAX_VALUE;
            Thread thread = new Thread("New Thread") {
                public void run(){
                    try {
                        time = Ping.pingTime(proxy.getIp(), proxy.getPort());
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            };

            thread.start();

            proxy.setTimeout(Math.round(time));
            if(time == Integer.MAX_VALUE) {
                proxyRepositories.delete(proxy);
                return;
            }
            int pingedCount = (int) proxy.getPingedCount();
            proxy.setPingedCount(pingedCount+1);

            proxyRepositories.save(proxy);
        }

    }

    @RequestMapping("/best-proxies")
    public Iterable<Proxy> bestProxies () {
        Iterable<Proxy> proxies = proxyRepositories.findAll(new Sort(Sort.Direction.ASC, "timeout"));
        return proxies;
    }

}
