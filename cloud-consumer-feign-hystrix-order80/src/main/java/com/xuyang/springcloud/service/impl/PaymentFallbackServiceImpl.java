package com.xuyang.springcloud.service.impl;


import com.xuyang.springcloud.common.entity.Payment;
import com.xuyang.springcloud.common.util.Response;
import com.xuyang.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @author xuyang
 * @description PaymentFallbackServiceImpl class
 * @date 2022/2/24
 */
@Component
public class PaymentFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public Response<String> create(Payment payment) {
        return Response.fail("----PaymentFallbackServiceImpl fallback----");
    }

    @Override
    public Response<String> getPaymentById(Long id) {
        return Response.fail("----PaymentFallbackServiceImpl fallback----");
    }
}
