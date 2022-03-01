package com.xuyang.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuyang
 * @description GatewayConfig class
 * @date 2022/2/27
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route",
                        r -> r.path("/payment/create")
                                .uri("lb://cloud-provider-payment"))
                .build();
    }
}
