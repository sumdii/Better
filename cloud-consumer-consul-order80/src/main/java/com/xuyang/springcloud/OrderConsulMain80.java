package com.xuyang.springcloud;

import com.xuyang.myRule.MyRandomRule;
import com.xuyang.myRule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author xuyang
 * @description OrderConsulMain80 class
 * @date 2022/2/20
 */
@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name = "cloud-payment-service", configuration = MyRandomRule.class)
public class OrderConsulMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsulMain80.class, args);
    }
}
