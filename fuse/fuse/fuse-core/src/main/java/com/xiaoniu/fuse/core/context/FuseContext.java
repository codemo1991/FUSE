package com.xiaoniu.fuse.core.context;

import com.xiaoniu.fuse.core.config.FuseHystrixConfig;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @类描述：Fuse上下文类
 * @创建人：林继丰
 * @创建时间：2017/9/21 19:18
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class FuseContext {
    //全路径方法名:限流配置
    public static final ConcurrentHashMap<String, FuseHystrixConfig> methodNameMapConfig = new ConcurrentHashMap<String, FuseHystrixConfig>();
    //Id:限流配置
    public static final ConcurrentHashMap<String, FuseHystrixConfig> idMapConfig = new ConcurrentHashMap<>();

    public static final ConcurrentHashMap<String, FuseHystrixConfig> urlMapConfig = new ConcurrentHashMap<>();

    public static void cleanAllContext() {
        FuseContext.idMapConfig.clear();
        FuseContext.methodNameMapConfig.clear();
        FuseContext.urlMapConfig.clear();
    }
}
