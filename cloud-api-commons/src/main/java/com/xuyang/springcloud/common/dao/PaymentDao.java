package com.xuyang.springcloud.common.dao;

import com.xuyang.springcloud.common.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description PaymentDao interface
 * @Date 2022/1/25
 * @Author xuyang
 */
@Mapper
public interface PaymentDao {
    /**
     * 查询支付信息
     *
     * @param id 支付id
     * @return 支付信息
     */
    Payment selectPaymentById(@Param("id") Long id);

    /**
     * 新增支付信息
     *
     * @param payment 支付信息
     * @return 影响行数
     */
    int insert(Payment payment);

    /**
     * 修改支付信息
     *
     * @param payment 支付信息
     * @return 影响行数
     */
    int update(Payment payment);

    /**
     * 删除支付信息
     *
     * @param payment 支付信息
     * @return 影响行数
     */
    int delete(Payment payment);

}
