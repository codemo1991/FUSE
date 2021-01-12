package com.xiaoniu.fuse.core.parser;

import com.xiaoniu.fuse.core.config.FuseHystrixConfig;
import com.xiaoniu.fuse.core.constant.GlobalConstant;
import com.xiaoniu.fuse.core.context.FuseContext;
import com.xiaoniu.fuse.core.enums.HystrixStrategyEnums;
import com.xiaoniu.fuse.core.exception.FuseConfigException;
import com.xiaoniu.fuse.core.helper.ConfigParseHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @类描述：配置文件解析类
 * @创建人：林继丰
 * @创建时间：2017/9/19 14:11
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class FuseBaseConfigBeanDefinitionParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        List<Element> childElements = DomUtils.getChildElementsByTagName(element, "method");
        String dependence = element.getAttribute("dependence");
        FuseHystrixConfig config = getDependenceFuseHystrixConfig(dependence);
        config = ConfigParseHelper.buildBaseConfig(element, config);
        if (!config.getAccordance()) {
            for (Element childElement : childElements) {
                String methodName = childElement.getAttribute("name");
                if (StringUtils.isBlank(methodName)) {
                    throw new RuntimeException("method:name不存在");
                }
                rebuildChildConfig(childElement, config);
                String fullMethodName = getFullMethodName(config, methodName);
                FuseContext.methodNameMapConfig.put(fullMethodName, config);
            }
        } else {
            try {
                Method[] methods = Class.forName(config.getTargetClass()).getDeclaredMethods();
                for (Method m : methods) {
                    String methodName = m.getName();
                    String fullMethodName = getFullMethodName(config, methodName);
                    FuseContext.methodNameMapConfig.put(fullMethodName, config);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取依赖的外部配置
     *
     * @param dependence
     * @return
     */
    private FuseHystrixConfig getDependenceFuseHystrixConfig(String dependence) {
        FuseHystrixConfig config = null;
        if (StringUtils.isNotBlank(dependence)) {
            if (!FuseContext.idMapConfig.containsKey(dependence))
                throw new FuseConfigException("dependence:id " + dependence + " is not found!");
            config = FuseContext.idMapConfig.get(dependence);
        }
        return config;
    }

    /**
     * 获取方法的全路径名
     *
     * @param config
     * @param methodName
     * @return
     */
    private String getFullMethodName(FuseHystrixConfig config, String methodName) {
        return config.getTargetClass() + GlobalConstant.CLASS_PATH_DECOLLATOR + methodName;
    }

    /**
     * 构建子个性config类
     *
     * @param childElement
     * @param config
     * @return
     */
    private FuseHystrixConfig rebuildChildConfig(Element childElement, FuseHystrixConfig config) {
        String innerFallBackClass = childElement.getAttribute("fallBackClass");
        String innerFallBackMethod = childElement.getAttribute("fallBackMethod");
        String innerTimeoutInMilliseconds = childElement.getAttribute("timeoutInMilliseconds");
        String innerCircuitBreakerRequestVolumeThreshold = childElement.getAttribute("circuitBreakerRequestVolumeThreshold");
        String innerCoreSize = childElement.getAttribute("coreSize");
        String innermaxQueueSize = childElement.getAttribute("maxQueueSize");
        String innerGroupKey = childElement.getAttribute("groupKey");
        String innerCommandKey = childElement.getAttribute("commandKey");
        String innerThreadPoolKey = childElement.getAttribute("threadPoolKey");
        String strategy = childElement.getAttribute("strategy");
        String fullParam = childElement.getAttribute("fullParam");
        String circuitBreakerSleepInMSec = childElement.getAttribute("circuitBreakerSleepInMSec");
        String circuitBreakerErrorThresholdPercentage = childElement.getAttribute("circuitBreakerErrorThresholdPercentage");
        String maxSemaphoreSize = childElement.getAttribute("maxSemaphoreSize");

        if (StringUtils.isNotBlank(innerCircuitBreakerRequestVolumeThreshold))
            config.setCircuitBreakerRequestVolumeThreshold(innerCircuitBreakerRequestVolumeThreshold);

        if (StringUtils.isNotBlank(innerCoreSize))
            config.setCoreSize(innerCoreSize);

        if (StringUtils.isNotBlank(innerFallBackMethod))
            config.setFallBackMethod(innerFallBackMethod);

        if (StringUtils.isNotBlank(innerFallBackClass))
            config.setFallBackRef(innerFallBackClass);

        if (StringUtils.isNotBlank(innermaxQueueSize))
            config.setMaxQueueSize(innermaxQueueSize);

        if (StringUtils.isNotBlank(innerTimeoutInMilliseconds))
            config.setTimeoutInMilliseconds(innerTimeoutInMilliseconds);

        if (StringUtils.isNotBlank(innerGroupKey))
            config.setGroupKey(innerGroupKey);

        if (StringUtils.isNotBlank(innerCommandKey))
            config.setCommandKey(innerCommandKey);

        if (StringUtils.isNotBlank(innerThreadPoolKey))
            config.setThreadPoolKey(innerThreadPoolKey);

        if (StringUtils.isNotBlank(strategy))
            config.setStrategy(HystrixStrategyEnums.getHystrixStrategy(strategy));

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
