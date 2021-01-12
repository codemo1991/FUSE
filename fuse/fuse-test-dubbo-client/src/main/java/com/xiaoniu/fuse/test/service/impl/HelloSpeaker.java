package com.xiaoniu.fuse.test.service.impl;

import com.xiaoniu.fuse.test.service.HelloSpeakerService;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/9/19 14:25
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class HelloSpeaker implements HelloSpeakerService {

    public String speak(String abc) throws InterruptedException {
        Thread.sleep(2000);
        return "ifuck " + abc;
    }

    public String talk() throws InterruptedException {
        Thread.sleep(2000);
        return "sfuck";
    }
}
