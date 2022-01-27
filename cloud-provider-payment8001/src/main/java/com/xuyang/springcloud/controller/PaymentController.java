package com.xuyang.springcloud.controller;

import com.xuyang.springcloud.entity.Payment;
import com.xuyang.springcloud.service.PaymentService;
import com.xuyang.springcloud.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Description PaymentController class
 * @Date 2022/1/25
 * @Author xuyang
 */
@RestController
@RequestMapping(value = "/payment")
@Slf4j
public class PaymentController {

    public static final String INSTANCE_NAME = "CLOUD-PAYMENT-SERVICE";

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/create")
    public Response<String> create(@RequestBody Payment payment) {
        if (paymentService.insert(payment)) {
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

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*************element:{}", element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances(INSTANCE_NAME);
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getPort());
        }
        return this.discoveryClient;
    }
}
