package com.xuyang.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xuyang.springcloud.common.entity.Payment;
import com.xuyang.springcloud.common.util.Response;
import com.xuyang.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description ZkOrderController class
 * @Date 2022/1/28
 * @Author xuyang
 */
@RestController
@RequestMapping("/order")
@Slf4j
@DefaultProperties(defaultFallback = "defaultTimeoutHandler")
public class HystrixOrderController {

    /**
     * 与提供者的application name 保持一致
     */
    public static final String PAYMENT_URL = "http://cloud-provider-payment";
    public static final String CREATE_PAYMENT = "/payment/create";
    public static final String SELECT_PAYMENT = "/payment/select?id=";

    @Resource
    private PaymentHystrixService feignService;

    @PostMapping("/create")
    @HystrixCommand(fallbackMethod = "createTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
    })
    public Response<String> create(@RequestBody Payment payment) {
        return feignService.create(payment);
    }

    @GetMapping("/select")
//    @HystrixCommand(fallbackMethod = "selectTimeoutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
//    })
    @HystrixCommand
    public Response<String> getPayment(@RequestParam("id") Long id) {
        return feignService.getPaymentById(id);
    }

    public Response<String> createTimeoutHandler(@RequestBody Payment payment) {
        return Response.fail("local create payment unsuccessfully");
    }

    public Response<String> selectTimeoutHandler(@RequestParam("id") Long id) {
        return Response.fail("local select payment unsuccessfully");
    }

    public Response<String> defaultTimeoutHandler() {
        return Response.fail("operation unsuccessfully");
    }
}