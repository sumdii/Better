package com.xuyang.springcloud.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.xuyang.springcloud.common.entity.Payment;
import com.xuyang.springcloud.common.service.PaymentService;
import com.xuyang.springcloud.common.util.Response;
import com.xuyang.springcloud.service.HystrixPaymentService;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author xuyang
 * @description PaymentController class
 * @date 2022/2/22
 */
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
    @Resource
    private HystrixPaymentService hystrixPaymentService;

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/create")
    public Response<String> create(@RequestBody Payment payment) {

        if (ObjectUtil.isNotNull(paymentService.insert(payment))) {
            return Response.success("create payment successfully, serverPort: " + serverPort);
        } else {
            return Response.fail("create payment fail, serverPort: " + serverPort);
        }
    }

    @GetMapping(value = "/select")
    public Response<Payment> selectPaymentById(@RequestParam("id") Long id) {
        if (Objects.isNull(id)) {
            return Response.fail(null);
        }
        Payment payment = paymentService.selectPaymentById(id);
        if (payment != null) {
            return Response.success(payment);
        } else {
            return Response.fail(null);
        }
    }

    @PostMapping(value = "/createTimeout")
    public Response<String> createTimeout(@RequestBody Payment payment) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hystrixPaymentService.createTimeout(payment);

    }

    @GetMapping(value = "/selectTimeout")
    public Response<String> selectPaymentByIdTimeout(@RequestParam("id") Long id) {

        return hystrixPaymentService.selectPaymentByIdTimeout(id);
    }

    @GetMapping(value = "/selectCircuit")
    public Response<String> selectPaymentByIdCircuit(@RequestParam("id") Long id) {

        return hystrixPaymentService.paymentCircuitBreaker(id);
    }
}
