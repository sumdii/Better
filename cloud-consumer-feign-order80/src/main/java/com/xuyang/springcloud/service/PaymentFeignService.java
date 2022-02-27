package com.xuyang.springcloud.service;

import com.xuyang.springcloud.common.entity.Payment;
import com.xuyang.springcloud.common.util.Response;
import com.xuyang.springcloud.config.LogConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xuyang
 * @description PaymentFeignService interface
 * @date 2022/2/21
 */
@FeignClient(name = "cloud-provider-payment", path = "/payment",
        configuration = {LogConfig.class})
@Service
public interface PaymentFeignService {
    /**
     * 新增支付记录
     *
     * @param payment 支付记录
     * @return 影响行数
     */
    @PostMapping(value = "/create")
    Response<String> create(Payment payment);

    /**
     * 根据id查询支付记录
     *
     * @param id id
     * @return 支付记录
     */
    @GetMapping(value = "/select")
    Response<Payment> getPaymentById(@RequestParam("id") Long id);
}
