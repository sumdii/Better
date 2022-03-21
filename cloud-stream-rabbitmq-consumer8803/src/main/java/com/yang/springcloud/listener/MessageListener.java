package com.yang.springcloud.listener;

import com.yang.springcloud.sink.MySink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author xuyang
 * @description MessageListener class
 * @date 2022/3/8
 */
@Component
@EnableBinding(MySink.class)
@Slf4j
public class MessageListener {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(MySink.MY_INPUT)
    public void receive(Message<String> message) {
        log.info("Message received: [{}] from port: [{}]", message.getPayload(), serverPort);
    }
}
