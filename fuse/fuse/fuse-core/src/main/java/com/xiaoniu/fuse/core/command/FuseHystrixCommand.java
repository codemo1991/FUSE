package com.xiaoniu.fuse.core.command;


import com.xiaoniu.fuse.core.config.FuseHystrixConfig;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/9/20 15:38
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class FuseHystrixCommand extends BaseHystrixCommand<Object> {

    //被调用方法
    private MethodInvocation invocation;
    //hystrix配置
    private FuseHystrixConfig hystrixConfig;

    public FuseHystrixCommand(MethodInvocation invocation, FuseHystrixConfig hystrixConfig) {
        super(hystrixConfig);//线程池为30
        this.invocation = invocation;
        this.hystrixConfig = hystrixConfig;
    }


    /**
     * 执行方法
     *
     * @return
     * @throws Exception
     */
    @Override
    protected Object run() throws Exception {
        try {
            return invocation.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("调用错误");
        }
    }

    /**
     * 降级方法
     *
     * @return
     */
    @Override
    protected Object getFallback() {
        if (StringUtils.isNotBlank(hystrixConfig.getFallBackRef())) {
            try {
                Class clazz = hystrixConfig.getFallBackService().getClass();
                if (hystrixConfig.getFullParam()) {
                    Class[] argumentClazzes = new Class[invocation.getArguments().length];
                    for (int i = 0; i < invocation.getArguments().length; i++) {
                        argumentClazzes[i] = invocation.getArguments()[i].getClass();
                    }
                    Method fallBackMethod = clazz.getDeclaredMethod(hystrixConfig.getFallBackMethod(), argumentClazzes);
                    fallBackMethod.setAccessible(true);
                    return fallBackMethod.invoke(hystrixConfig.getFallBackService(), invocation.getArguments());
                } else {
                    Class[] argumentClazzes = new Class[0];
                    Method fallBackMethod = clazz.getDeclaredMethod(hystrixConfig.getFallBackMethod(), argumentClazzes);
                    fallBackMethod.setAccessible(true);
                    return fallBackMethod.invoke(hystrixConfig.getFallBackService());
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}