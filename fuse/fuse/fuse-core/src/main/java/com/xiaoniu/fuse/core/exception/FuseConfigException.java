package com.xiaoniu.fuse.core.exception;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/9/22 14:44
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class FuseConfigException extends RuntimeException {

    public FuseConfigException() {
    }

    public FuseConfigException(String message) {
        super(message);
    }

    public FuseConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
