package com.yang.springcloud.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author xuyang
 * @description MySource interface
 * @date 2022/3/21
 */
public interface MySource {
    String MY_OUTPUT = "myOutput";

    @Output(MySource.MY_OUTPUT)
    MessageChannel output();
}
