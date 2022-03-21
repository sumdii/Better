package com.yang.springcloud.service.impl;

import com.yang.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author xuyang
 * @description 定义消息的推送管道，需要使用@EnableBinding(Source.class)注解
 * @date 2022/3/8
 */
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderImpl implements IMessageProvider {
    /**
     * 消息发送管道
     */
    @Resource
    @Output(Source.OUTPUT)
    private MessageChannel outputChannel;

    @Override
    public String send() {
        String uid = UUID.randomUUID().toString();
        outputChannel.send(MessageBuilder.withPayload(uid)
                .build());
        log.info("Message send: {}", uid);
        return uid;
    }
}
