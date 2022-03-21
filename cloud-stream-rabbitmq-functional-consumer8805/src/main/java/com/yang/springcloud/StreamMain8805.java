package com.yang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.function.Consumer;

/**
 * @author xuyang
 * @description StreamMain8805 class
 * @date 2022/3/21
 */
@SpringBootApplication
public class StreamMain8805 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMain8805.class, args);
    }

    @Bean
    public Consumer<String> sink1() {
        return System.out::println;
    }
}
