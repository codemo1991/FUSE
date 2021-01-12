package com.xiaoniu.fuse.core.enums;

import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/9/22 11:40
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public enum HystrixStrategyEnums {


    Thread("Thread", HystrixCommandProperties.ExecutionIsolationStrategy.THREAD),
    Semaphore("Semaphore", HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE);

    private String name;

    private HystrixCommandProperties.ExecutionIsolationStrategy strategy;

    HystrixStrategyEnums(String name, HystrixCommandProperties.ExecutionIsolationStrategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    /**
     * 根据名称获取hystrix的策略
     *
     * @param name
     * @return
     */
    public static HystrixCommandProperties.ExecutionIsolationStrategy getHystrixStrategy(String name) {
        for (HystrixStrategyEnums enums : HystrixStrategyEnums.values()) {
            if (enums.name.equals(name)) {
                return enums.strategy;
            }
        }
        return null;
    }
}
