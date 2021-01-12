package com.xiaoniu.fuse.core.aop;

import com.xiaoniu.fuse.core.command.FuseHystrixCommand;
import com.xiaoniu.fuse.core.config.FuseHystrixConfig;
import com.xiaoniu.fuse.core.context.FuseContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @类描述：方法拦截器
 * @创建人：林继丰
 * @创建时间：2017/9/15 17:45
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class FuseMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String fullMethodName = methodInvocation.getThis().getClass().getName() + "." + methodInvocation.getMethod().getName();
        FuseHystrixConfig config = FuseContext.methodNameMapConfig.get(fullMethodName);
        FuseHystrixCommand command = new FuseHystrixCommand(methodInvocation, config);
        return command.execute();
    }
}
