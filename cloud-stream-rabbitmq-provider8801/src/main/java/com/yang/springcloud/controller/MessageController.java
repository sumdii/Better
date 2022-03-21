package com.yang.springcloud.controller;

import com.yang.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xuyang
 * @description MessageController class
 * @date 2022/3/8
 */
@RestController
public class MessageController {
    @Resource
    private IMessageProvider messageProvider;

    @PostMapping("/message")
    public String sendMessage() {
        return messageProvider.send();
    }
}
