package com.xuyang.springcloud.service;

import com.xuyang.springcloud.common.entity.Payment;
import com.xuyang.springcloud.common.util.Response;
import com.xuyang.springcloud.service.impl.PaymentFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xuyang
 * @description PaymentHystrixService interface
 * @date 2022/2/23
 */
@FeignClient(name = "cloud-provider-payment", path = "/payment", fallback = PaymentFallbackServiceImpl.class)
@Service
public interface PaymentHystrixService {
    /**
     * 新增支付记录
     *
     * @param payment 支付记录
     * @return 影响行数
     */
    @PostMapping(value = "/createTimeout")
    Response<String> create(Payment payment);

    /**
     * 根据id查询支付记录
     *
     * @param id id
     * @return 支付记录
     */
    @GetMapping(value = "/selectTimeout")
    Response<String> getPaymentById(@RequestParam("id") Long id);
}
