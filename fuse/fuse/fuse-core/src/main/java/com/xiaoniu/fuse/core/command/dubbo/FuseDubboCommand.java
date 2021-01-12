package com.xiaoniu.fuse.core.command.dubbo;

/**
 * Created by Chris on 2017/9/24.
 */

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcResult;
import com.xiaoniu.fuse.core.command.BaseHystrixCommand;
import com.xiaoniu.fuse.core.config.FuseHystrixConfig;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FuseDubboCommand extends BaseHystrixCommand<Result> {

    private static final int DEFAULT_THREADPOOL_CORE_SIZE = 30;
    private Invoker<?> invoker;
    private Invocation invocation;
    private FuseHystrixConfig hystrixConfig;


    public FuseDubboCommand(Invoker<?> invoker, Invocation invocation, FuseHystrixConfig hystrixConfig) {
        super(hystrixConfig);//线程池为30
        this.invoker = invoker;
        this.invocation = invocation;
        this.hystrixConfig = hystrixConfig;
    }


    @Override
    protected Result run() throws Exception {
        return invoker.invoke(invocation);
    }

    @Override
    protected Result getFallback() {
        if (StringUtils.isNotBlank(hystrixConfig.getFallBackRef())) {
            try {
                Class clazz = hystrixConfig.getFallBackService().getClass();
                if (hystrixConfig.getFullParam()) {

                    Class[] argumentClazzes = new Class[invocation.getArguments().length];
                    for (int i = 0; i < invocation.getArguments().length; i++) {
                        argumentClazzes[i] = invocation.getArguments()[i].getClass();
                    }
                    Method fallBackMethod = clazz.getDeclaredMethod(hystrixConfig.getFallBackMethod(), argumentClazzes);
                    return (Result) fallBackMethod.invoke(hystrixConfig.getFallBackService(), invocation.getArguments());
                } else {
                    Class[] argumentClazzes = new Class[0];
                    Method fallBackMethod = clazz.getDeclaredMethod(hystrixConfig.getFallBackMethod(), argumentClazzes);
                    Object fallBackResult = fallBackMethod.invoke(hystrixConfig.getFallBackService());
                    RpcResult result = new RpcResult();
                    result.setValue(fallBackResult);
                    return result;
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