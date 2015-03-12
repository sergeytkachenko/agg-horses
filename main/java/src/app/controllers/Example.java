package app.controllers;

import com.sergey.ping.Ping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Example {
    @RequestMapping("/ping")
    public void ping () throws IOException {
        Ping.pingTime("192.168.109.176", "80");
    }
}
