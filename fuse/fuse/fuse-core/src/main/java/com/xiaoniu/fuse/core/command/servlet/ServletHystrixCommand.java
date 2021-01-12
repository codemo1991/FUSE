package com.xiaoniu.fuse.core.command.servlet;


import com.xiaoniu.fuse.core.command.BaseHystrixCommand;
import com.xiaoniu.fuse.core.config.FuseHystrixConfig;
import com.xiaoniu.fuse.core.interceptor.FuseHystrixInterceptor;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/9/20 15:38
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class ServletHystrixCommand extends BaseHystrixCommand<Boolean> {

    private FuseHystrixInterceptor interceptor;
    private FuseHystrixConfig hystrixConfig;

    public ServletHystrixCommand(FuseHystrixInterceptor interceptor, FuseHystrixConfig hystrixConfig) {
        super(hystrixConfig);//线程池为30
        this.interceptor = interceptor;
        this.hystrixConfig = hystrixConfig;
    }


    /**
     * 执行方法
     *
     * @return
     * @throws Exception
     */
    @Override
    protected Boolean run() throws Exception {
        try {
            return interceptor.doPreHandle();
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
    protected Boolean getFallback() {
        if (StringUtils.isNotBlank(hystrixConfig.getFallBackRef())) {
            try {
                Class clazz = hystrixConfig.getFallBackService().getClass();
                Class[] argumentClazzes = new Class[1];
                argumentClazzes[0] = HttpServletRequest.class;
                Method fallBackMethod = clazz.getMethod(hystrixConfig.getFallBackMethod(), argumentClazzes);
                return (Boolean) fallBackMethod.invoke(hystrixConfig.getFallBackService(), interceptor.getRequest());
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