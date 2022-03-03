package com.yang.springcloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xuyang
 * @description GitAutoRefreshConfig class
 * @date 2022/3/3
 */
@Component
@Data
@ConfigurationProperties(prefix = "data")
public class GitAutoRefreshConfig {
    @Data
    public static class UserInfo {
        private String username;
        private String password;
    }
    private String env;
    private UserInfo user;
}
