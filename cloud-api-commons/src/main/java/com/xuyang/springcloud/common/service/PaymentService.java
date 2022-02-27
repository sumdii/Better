package com.xuyang.springcloud.common.service;


import com.xuyang.springcloud.common.dao.PaymentDao;
import com.xuyang.springcloud.common.entity.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description PaymentService class
 * @Date 2022/1/25
 * @Author xuyang
 */
@Service
public class PaymentService {
    @Resource
    private PaymentDao paymentDao;

    /**
     * 查询支付信息
     *
     * @param id 支付id
     * @return 支付信息
     */
    public Payment selectPaymentById(@Param("id") Long id) {
        return paymentDao.selectPaymentById(id);
    }

    /**
     * 新增支付信息
     *
     * @param payment 支付信息
     * @return 是否成功
     */
    public boolean insert(Payment payment) {
        return paymentDao.insert(payment) > 0;
    }

    /**
     * 修改支付信息
     *
     * @param payment 支付信息
     * @return 是否成功
     */
    public boolean update(Payment payment) {
        return paymentDao.update(payment) > 0;
    }

    /**
     * 删除支付信息
     *
     * @param payment 支付信息
     * @return 是否成功
     */
    public boolean delete(Payment payment) {
        return paymentDao.delete(payment) > 0;
    }
}
