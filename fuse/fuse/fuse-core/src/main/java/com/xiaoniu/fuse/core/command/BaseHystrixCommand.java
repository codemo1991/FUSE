package com.xiaoniu.fuse.core.command;

import com.netflix.hystrix.*;
import com.xiaoniu.fuse.core.config.FuseHystrixConfig;

/**
 * Created by Chris on 2017/9/24.
 */
public class BaseHystrixCommand<R> extends HystrixCommand<R> {

    public BaseHystrixCommand(FuseHystrixConfig config) {
        super(setter(config));
    }

    @Override
    protected R getFallback() {
        return super.getFallback();
    }

    @Override
    protected R run() throws Exception {
        return null;
    }

    /**
     * 构建Hystrix命令的设置
     *
     * @param hystrixConfig
     * @return
     */
    public static HystrixCommand.Setter setter(FuseHystrixConfig hystrixConfig) {
        return HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(hystrixConfig.getGroupKey()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(hystrixConfig.getCommandKey()))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(hystrixConfig.getStrategy())
                        .withExecutionTimeoutInMilliseconds(Integer.parseInt(hystrixConfig.getTimeoutInMilliseconds()))
                        .withCircuitBreakerEnabled(Boolean.parseBoolean(hystrixConfig.getCircuitBreakerEnabled()))
                        .withCircuitBreakerRequestVolumeThreshold(Integer.parseInt(hystrixConfig.getCircuitBreakerRequestVolumeThreshold()))
                        .withCircuitBreakerSleepWindowInMilliseconds(hystrixConfig.getCircuitBreakerSleepInMSec())//熔断多少秒后重试请求
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(hystrixConfig.getMaxSemaphoreSize())
                        .withCircuitBreakerErrorThresholdPercentage(hystrixConfig.getCircuitBreakerErrorThresholdPercentage())
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(hystrixConfig.getMaxSemaphoreSize())
                )
                //ThreadPool配置
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(hystrixConfig.getThreadPoolKey()))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(Integer.parseInt(hystrixConfig.getCoreSize()))
                        .withMaxQueueSize(Integer.parseInt(hystrixConfig.getMaxQueueSize()))
                        .withQueueSizeRejectionThreshold(Integer.parseInt(hystrixConfig.getMaxQueueSize())))
                ;
    }
}
