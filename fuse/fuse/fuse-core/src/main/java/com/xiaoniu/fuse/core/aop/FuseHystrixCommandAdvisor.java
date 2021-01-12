package com.xiaoniu.fuse.core.aop;

import com.xiaoniu.fuse.core.config.FuseHystrixConfig;
import com.xiaoniu.fuse.core.constant.GlobalConstant;
import com.xiaoniu.fuse.core.context.FuseContext;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/9/15 17:45
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */

public class FuseHystrixCommandAdvisor extends StaticMethodMatcherPointcutAdvisor implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public FuseHystrixCommandAdvisor() {
        setAdvice(new FuseMethodInterceptor());
    }

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        String fullMethodName = method.getDeclaringClass().getName() + GlobalConstant.CLASS_PATH_DECOLLATOR + method.getName();
        FuseHystrixConfig methodMapConfig = FuseContext.methodNameMapConfig.get(fullMethodName);
        if (methodMapConfig != null) {
            Object fallBackService = applicationContext.getBean(methodMapConfig.getFallBackRef());
            FuseContext.methodNameMapConfig.get(fullMethodName).setFallBackService(fallBackService);
            return true;
        }
        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
