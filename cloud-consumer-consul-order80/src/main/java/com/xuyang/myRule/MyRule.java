package com.xuyang.myRule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuyang
 * @description MyRule: 永远获取可用列表中的第一个
 * @date 2022/2/20
 */
@Configuration
public class MyRule {

    @Bean
    public IRule myRuleMethod() {
        return new MyRandomRule();
    }
}
