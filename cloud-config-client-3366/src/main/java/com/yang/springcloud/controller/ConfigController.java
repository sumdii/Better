package com.yang.springcloud.controller;

import com.yang.springcloud.config.GitAutoRefreshConfig;
import com.yang.springcloud.config.GitConfig;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xuyang
 * @description ConfigController class
 * @date 2022/3/2
 */
@RestController
@RefreshScope
public class ConfigController {

    @Resource
    private GitConfig gitConfig;

    @Resource
    private GitAutoRefreshConfig autoRefreshConfig;

    @GetMapping("/autoShow")
    public GitAutoRefreshConfig getGitAutoRefreshConfig() {
        return autoRefreshConfig;
    }

    @GetMapping("/show")
    public GitConfig getGitConfig() {
        return gitConfig;
    }
}
