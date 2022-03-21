package com.yang.springcloud.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author xuyang
 * @description MySink interface
 * @date 2022/3/21
 */
public interface MySink {
    String MY_INPUT = "myInput";

    @Input(MySink.MY_INPUT)
    SubscribableChannel input();
}
