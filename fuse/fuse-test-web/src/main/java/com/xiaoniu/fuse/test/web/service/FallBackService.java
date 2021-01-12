package com.xiaoniu.fuse.test.web.service;

import org.springframework.stereotype.Service;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/9/26 11:14
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
@Service
public class FallBackService {

    public String say() {
        return "fuck";
    }
}
