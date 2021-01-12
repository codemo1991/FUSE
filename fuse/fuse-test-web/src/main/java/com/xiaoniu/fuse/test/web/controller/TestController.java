package com.xiaoniu.fuse.test.web.controller;

import com.xiaoniu.fuse.test.web.service.SpeakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/9/26 11:11
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
@RestController
@RequestMapping("/index")
public class TestController {

    @Autowired
    private SpeakService speakService;

    @RequestMapping(value = "/say")
    @ResponseBody
    public String helloWorld(HttpServletRequest request) throws InterruptedException {


        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(RequestContextUtils.getWebApplicationContext(request).getServletContext());
        return speakService.say();
    }
}
