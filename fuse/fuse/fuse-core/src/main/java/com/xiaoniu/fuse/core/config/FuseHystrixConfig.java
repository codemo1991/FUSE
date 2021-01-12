package com.xiaoniu.fuse.core.config;

import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/9/21 19:20
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class FuseHystrixConfig {

    private String id;
    private String groupKey = "defaultGroupKey";
    private String commandKey = "defaultCommandKey";
    private String threadPoolKey = "defaultThreadKey";
    private HystrixCommandProperties.ExecutionIsolationStrategy strategy = HystrixCommandProperties.ExecutionIsolationStrategy.THREAD;
    private String timeoutInMilliseconds = "1000";
    private String circuitBreakerEnabled = "true";
    private String circuitBreakerRequestVolumeThreshold = "20";
    private String fallBackRef;
    private String fallBackMethod;
    private String coreSize = "10";
    private String maxQueueSize = "-1";
    private Integer maxSemaphoreSize = 10;
    private Object fallBackService;
    //是否开启全类方法拦截
    private Boolean accordance;
    //目标类
    private String targetClass;

    private String url;

    private Boolean fullParam;

    //熔断多少秒后重试
    private Integer circuitBreakerSleepInMSec = 5000;

    //失败比例多少的时候熔断
    private Integer circuitBreakerErrorThresholdPercentage = 50;

    //方法阈值
    private Long limitNums;

    //方法名称
    private String limitParam;

    public Object getFallBackService() {
        return fallBackService;
    }

    public void setFallBackService(Object fallBackService) {
        this.fallBackService = fallBackService;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getCommandKey() {
        return commandKey;
    }

    public void setCommandKey(String commandKey) {
        this.commandKey = commandKey;
    }

    public String getThreadPoolKey() {
        return threadPoolKey;
    }

    public void setThreadPoolKey(String threadPoolKey) {
        this.threadPoolKey = threadPoolKey;
    }

    public HystrixCommandProperties.ExecutionIsolationStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(HystrixCommandProperties.ExecutionIsolationStrategy strategy) {
        this.strategy = strategy;
    }

    public String getTimeoutInMilliseconds() {
        return timeoutInMilliseconds;
    }

    public void setTimeoutInMilliseconds(String timeoutInMilliseconds) {
        this.timeoutInMilliseconds = timeoutInMilliseconds;
    }

    public String getCircuitBreakerEnabled() {
        return circuitBreakerEnabled;
    }

    public void setCircuitBreakerEnabled(String circuitBreakerEnabled) {
        this.circuitBreakerEnabled = circuitBreakerEnabled;
    }

    public String getCircuitBreakerRequestVolumeThreshold() {
        return circuitBreakerRequestVolumeThreshold;
    }

    public void setCircuitBreakerRequestVolumeThreshold(String circuitBreakerRequestVolumeThreshold) {
        this.circuitBreakerRequestVolumeThreshold = circuitBreakerRequestVolumeThreshold;
    }

    public String getFallBackRef() {
        return fallBackRef;
    }

    public void setFallBackRef(String fallBackRef) {
        this.fallBackRef = fallBackRef;
    }

    public String getFallBackMethod() {
        return fallBackMethod;
    }

    public void setFallBackMethod(String fallBackMethod) {
        this.fallBackMethod = fallBackMethod;
    }

    public String getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(String coreSize) {
        this.coreSize = coreSize;
    }

    public String getMaxQueueSize() {
        return maxQueueSize;
    }

    public void setMaxQueueSize(String maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    public Boolean getAccordance() {
        return accordance;
    }

    public void setAccordance(Boolean accordance) {
        this.accordance = accordance;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getFullParam() {
        return fullParam;
    }

    public void setFullParam(Boolean fullParam) {
        this.fullParam = fullParam;
    }

    public Integer getCircuitBreakerSleepInMSec() {
        return circuitBreakerSleepInMSec;
    }

    public void setCircuitBreakerSleepInMSec(Integer circuitBreakerSleepInMSec) {
        this.circuitBreakerSleepInMSec = circuitBreakerSleepInMSec;
    }

    public Integer getCircuitBreakerErrorThresholdPercentage() {
        return circuitBreakerErrorThresholdPercentage;
    }

    public void setCircuitBreakerErrorThresholdPercentage(Integer circuitBreakerErrorThresholdPercentage) {
        this.circuitBreakerErrorThresholdPercentage = circuitBreakerErrorThresholdPercentage;
    }

    public Integer getMaxSemaphoreSize() {
        return maxSemaphoreSize;
    }

    public void setMaxSemaphoreSize(Integer maxSemaphoreSize) {
        this.maxSemaphoreSize = maxSemaphoreSize;
    }

    public Long getLimitNums() {
        return limitNums;
    }

    public void setLimitNums(Long limitNums) {
        this.limitNums = limitNums;
    }

    public String getLimitParam() {
        return limitParam;
    }

    public void setLimitParam(String limitParam) {
        this.limitParam = limitParam;
    }
}
