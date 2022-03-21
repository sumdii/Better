package com.yang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

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
public class StreamMain8804 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMain8804.class, args);
    }

    @Bean
    public Supplier<Date> source1() {
        return Date::new;
    }
}
