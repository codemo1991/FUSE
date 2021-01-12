package com.xiaoniu.fuse.core.helper;

import com.xiaoniu.fuse.core.config.FuseHystrixConfig;
import com.xiaoniu.fuse.core.enums.HystrixStrategyEnums;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/9/22 14:31
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class ConfigParseHelper {

    /**
     * 构建基础的Config类
     *
     * @param element
     * @return
     */
    public static FuseHystrixConfig buildBaseConfig(Element element, FuseHystrixConfig config) {
        String id = element.getAttribute("id");
        String targetClass = element.getAttribute("targetClass");
        String groupKey = element.getAttribute("groupKey");
        String commandKey = element.getAttribute("commandKey");
        String threadPoolKey = element.getAttribute("threadPoolKey");
        String strategy = element.getAttribute("strategy");
        String timeoutInMilliseconds = element.getAttribute("timeoutInMilliseconds");
        String circuitBreakerEnabled = element.getAttribute("circuitBreakerEnabled");
        String circuitBreakerRequestVolumeThreshold = element.getAttribute("circuitBreakerRequestVolumeThreshold");
        String fallBackRef = element.getAttribute("fallBackRef");
        String fallBackMethod = element.getAttribute("fallBackMethod");
        String accordance = element.getAttribute("accordance");
        String coreSize = element.getAttribute("coreSize");
        String maxQueueSize = element.getAttribute("maxQueueSize");
        String url = element.getAttribute("url");
        String fullParam = element.getAttribute("fullParam");
        String circuitBreakerSleepInMSec = element.getAttribute("circuitBreakerSleepInMSec");
        String circuitBreakerErrorThresholdPercentage = element.getAttribute("circuitBreakerErrorThresholdPercentage");
        String maxSemaphoreSize = element.getAttribute("maxSemaphoreSize");

        if (config == null)
            config = new FuseHystrixConfig();

        if (StringUtils.isNotBlank(circuitBreakerEnabled))
            config.setCircuitBreakerEnabled(circuitBreakerEnabled);
        if (StringUtils.isNotBlank(circuitBreakerRequestVolumeThreshold))
            config.setCircuitBreakerRequestVolumeThreshold(circuitBreakerRequestVolumeThreshold);
        if (StringUtils.isNotBlank(commandKey))
            config.setCommandKey(commandKey);
        if (StringUtils.isNotBlank(coreSize))
            config.setCoreSize(coreSize);
        if (StringUtils.isNotBlank(fallBackRef))
            config.setFallBackRef(fallBackRef);
        if (StringUtils.isNotBlank(fallBackMethod))
            config.setFallBackMethod(fallBackMethod);
        if (StringUtils.isNotBlank(groupKey))
            config.setGroupKey(groupKey);
        if (StringUtils.isNotBlank(circuitBreakerEnabled))
            config.setCircuitBreakerEnabled(circuitBreakerEnabled);
        if (StringUtils.isNotBlank(strategy))
            config.setStrategy(HystrixStrategyEnums.getHystrixStrategy(strategy));
        if (StringUtils.isNotBlank(threadPoolKey))
            config.setThreadPoolKey(threadPoolKey);
        if (StringUtils.isNotBlank(timeoutInMilliseconds))
            config.setTimeoutInMilliseconds(timeoutInMilliseconds);
        if (StringUtils.isNotBlank(maxQueueSize))
            config.setMaxQueueSize(maxQueueSize);
        if (StringUtils.isNotBlank(accordance))
            config.setAccordance(Boolean.parseBoolean(accordance));
        if (StringUtils.isNotBlank(targetClass))
            config.setTargetClass(targetClass);
        if (StringUtils.isNotBlank(id))
            config.setId(id);
        if (StringUtils.isNotBlank(url))
            config.setUrl(url);
        if (StringUtils.isNotBlank(fullParam))
            config.setFullParam(Boolean.parseBoolean(fullParam));
        if (StringUtils.isNotBlank(circuitBreakerSleepInMSec))
            config.setCircuitBreakerSleepInMSec(Integer.parseInt(circuitBreakerSleepInMSec));
        if (StringUtils.isNotBlank(circuitBreakerErrorThresholdPercentage))
            config.setCircuitBreakerSleepInMSec(Integer.parseInt(circuitBreakerErrorThresholdPercentage));
        if (StringUtils.isNotBlank(maxSemaphoreSize))
            config.setMaxSemaphoreSize(Integer.parseInt(maxSemaphoreSize));
        return config;
    }


}
