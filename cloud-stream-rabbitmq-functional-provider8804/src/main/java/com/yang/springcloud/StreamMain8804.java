package com.yang.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author xuyang
 * @description StreamMain8804 class
 * @date 2022/3/21
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class StreamMain8804 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMain8804.class, args);
    }

//    @Bean
//    public Supplier<Date> source1() {
//        return Date::new;
//    }

    @Autowired
    private StreamBridge streamBridge;

    @RequestMapping("/message1")
    public String message1(@RequestBody String body) {
        System.out.println("Sending " + body);
        streamBridge.send("source1-out-0", body);

        return "OK";
    }

    @RequestMapping("/message2")
    public String message2(@RequestBody String body) {
        System.out.println("Sending " + body);
        streamBridge.send("source2-out-0", body);

        return "OK";
    }
}
