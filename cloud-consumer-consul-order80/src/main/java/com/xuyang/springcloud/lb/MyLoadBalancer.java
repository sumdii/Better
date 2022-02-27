package com.xuyang.springcloud.lb;

import com.netflix.loadbalancer.Server;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuyang
 * @description MyLoadBalancer class
 * @date 2022/2/20
 */
@Component
public class MyLoadBalancer implements LoadBalancer {
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        // 自旋锁
        while (true) {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
            if (atomicInteger.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    @Override
    public Server instance(List<Server> serverInstances) {
        // 请求次数 模 实例总数
        int index = getAndIncrement() % serverInstances.size();
        return serverInstances.get(index);
    }
}
