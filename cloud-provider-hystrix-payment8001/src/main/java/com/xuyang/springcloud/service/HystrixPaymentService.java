package com.xuyang.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xuyang.springcloud.common.entity.Payment;
import com.xuyang.springcloud.common.util.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author xuyang
 * @description HystrixPaymentService class
 * @date 2022/2/23
 */
@Service
public class HystrixPaymentService {
    @HystrixCommand(fallbackMethod = "createTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public Response<String> createTimeout(@RequestBody Payment payment) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Response.success("create payment successfully");
    }

    public Response<String> createTimeoutHandler(@RequestBody Payment payment) {
        return Response.success("create payment unsuccessfully");
    }

    @HystrixCommand(fallbackMethod = "selectTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public Response<String> selectPaymentByIdTimeout(@RequestParam("id") Long id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Response.success("Select payment successfully");
    }

    public Response<String> selectTimeoutHandler(@RequestParam("id") Long id) {
        return Response.fail("Select payment unsuccessfully");
    }

    /* ====????????????==== */


    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            // ?????????????????????
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // ????????????
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // ???????????????
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // ???????????????????????????????????????
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            // ???10s??????????????????10???????????????????????????60%?????????????????????
    })
    public Response<String> paymentCircuitBreaker(@RequestParam("id") Long id) {
        if (id < 0) {
            throw new RuntimeException("----id???????????????----");
        }
        String serialNo = IdUtil.simpleUUID();
        return Response.success("???????????????????????????" + serialNo);
    }

    public Response<String> paymentCircuitBreakerFallback(@RequestParam("id") Long id) {
        return Response.fail("????????????");
    }
}
