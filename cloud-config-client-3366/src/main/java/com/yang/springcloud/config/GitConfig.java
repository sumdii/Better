package com.yang.springcloud.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xuyang
 * @description GitConfig class
 * @date 2022/3/3
 */
@Data
@Component
public class GitConfig {

    @Value("${data.env}")
    private String env;

    @Value("${data.user.username}")
    private String username;

    @Value("${data.user.password}")
    private String password;

}
