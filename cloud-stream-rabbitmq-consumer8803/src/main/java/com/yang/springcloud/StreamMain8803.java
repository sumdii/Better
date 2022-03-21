package com.yang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xuyang
 * @description StreamMain8803 class
 * @date 2022/3/8
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StreamMain8803 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMain8803.class, args);
    }
}
