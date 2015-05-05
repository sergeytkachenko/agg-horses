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
import java.util.ArrayList;
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
        if(null==doc) {
            System.out.println("html is null");
            return;
        }

        Elements trList = doc.select("table#proxylisttable tr");
        if(trList.size() == 0) {
            System.out.println("Proxies list in html document is null");
        }
        for (Element tr : trList) {
            String ip = tr.select("td:eq(0)").text();
            String port = tr.select("td:eq(1)").text();

            if(ip==null || ip.isEmpty()) {continue;}
            Proxy proxy = new Proxy();
            if(proxyRepositories.findByIp(ip)!=null){
                proxy = proxyRepositories.findByIp(ip);
            }

            proxy.setIp(ip);
            proxy.setPort(port);

            proxyRepositories.save(proxy);
        }

        pingProxies();
    }

    @RequestMapping("/ping-proxies")
    public void pingProxies () throws IOException {
        // TODO обрабатывать exception, set limit for ping
        List<Proxy> proxies = proxyRepositories.findAll();
        List<Proxy> proxyList = new ArrayList<>();
        proxies.parallelStream().forEach(proxy -> {
            System.out.println(proxy.getIp());
            float time = Integer.MAX_VALUE;
            time = Ping.pingTime(proxy.getIp(), proxy.getPort());

            proxy.setTimeout(Math.round(time));
            int pingedCount = proxy.getPingedCount();
            proxy.setPingedCount(pingedCount + 1);
            proxyList.add(proxy);
        });
        System.out.println("Синхронизация скорости прокси прошла успешно");
        System.out.println(proxyList);
        proxyRepositories.save(proxyList);
    }

    @RequestMapping("/best-proxies")
    public Iterable<Proxy> bestProxies () {
        Iterable<Proxy> proxies = proxyRepositories.findAll(new Sort(Sort.Direction.ASC, "timeout"));
        return proxies;
    }

    @RequestMapping("/best-proxy")
    public Proxy bestProxy () {
        return proxyRepositories.findByBest();
    }
}
