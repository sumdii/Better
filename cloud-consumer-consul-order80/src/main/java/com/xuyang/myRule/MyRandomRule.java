package com.xuyang.myRule;

import com.netflix.client.IClientConfigAware;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import com.xuyang.springcloud.lb.MyLoadBalancer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author xuyang
 * @description MyRandomRule class
 * @date 2022/2/20
 */
@Slf4j
public class MyRandomRule implements IRule {

    public static final int RETRY_TIMES = 10;
    private ILoadBalancer loadBalancer;

    @Resource
    private MyLoadBalancer myBalancer;

    @Override
    public Server choose(Object key) {
        Server server = null;
        int count = 0;
        while (count++ <= RETRY_TIMES) {
            server = myBalancer.instance(loadBalancer.getAllServers());

            if (server == null) {
                Thread.yield();
                continue;
            }
            if (server.isAlive() && server.isReadyToServe()) {
                return server;
            }
            server = null;
        }

        if (count >= RETRY_TIMES) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + loadBalancer);
        }
        return null;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.loadBalancer = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.loadBalancer;
    }
}
