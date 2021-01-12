
package com.xiaoniu.fuse.test.impl;


import com.xiaoniu.fuse.test.service.HelloService;

/**
 * @author fengjx.
 * @date：2015/1/6 0006
 */
public class HelloServiceImpl implements HelloService {


    /**
     * 暴露的接口
     *
     * @param name
     * @return
     */
    @Override
    public String sayHello(String name) throws InterruptedException {
        System.out.println("call sayHello");
        int sleep = (int) (Math.random() * 2000L);
        Thread.sleep(sleep);
        return "Hello " + name;
    }
}
