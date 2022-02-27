package com.xuyang.springcloud.controller;

import com.xuyang.springcloud.common.entity.Payment;
import com.xuyang.springcloud.common.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description ZkOrderController class
 * @Date 2022/1/28
 * @Author xuyang
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class ZkOrderController {
    // public static final String PAYMENT_URL = "http://localhost:8001";

    /**
     * 与提供者的application name 保持一致
     */
    public static final String PAYMENT_URL = "http://cloud-provider-payment";
    public static final String CREATE_PAYMENT = "/payment/create";
    public static final String SELECT_PAYMENT = "/payment/select?id=";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/create")
    public Response<Payment> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + CREATE_PAYMENT, payment, Response.class);
    }

    @GetMapping("/select")
    public Response<Payment> getPayment(@RequestParam("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + SELECT_PAYMENT + id, Response.class);
    }
}