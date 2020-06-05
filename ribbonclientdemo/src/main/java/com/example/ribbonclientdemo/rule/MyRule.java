package com.example.ribbonclientdemo.rule;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

/**
 *  ribbon负载均衡规则
 */
public class MyRule implements IRule {

    private ILoadBalancer lb;

    @Override
    public Server choose(Object o) {
        Random ran = new Random();
        int rNum = ran.nextInt(10);




        return null;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.lb = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.lb;
    }
}
