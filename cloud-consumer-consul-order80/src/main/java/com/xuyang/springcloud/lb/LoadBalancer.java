package com.xuyang.springcloud.lb;

import com.netflix.loadbalancer.Server;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author xuyang
 * @description LoadBalance interface
 * @date 2022/2/20
 */
public interface LoadBalancer {
    /**
     * 获取请求实例
     *
     * @param serverInstances 实例列表
     * @return 请求实例
     */
    Server instance(List<Server> serverInstances);
}
