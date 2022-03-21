package com.yang.springcloud.service;

/**
 * @author xuyang
 * @description IMessageProvider interface
 * @date 2022/3/8
 */
public interface IMessageProvider {
    /**
     * 发送消息
     *
     * @return 发送结果
     */
    String send();
}
