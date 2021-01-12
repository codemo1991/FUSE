
package com.xiaoniu.fuse.test.service.impl;

import com.xiaoniu.fuse.test.service.HelloService;

/**
 * @author fengjx.
 * @date：2015/1/6 0006
 */
public class HelloServiceConsumer {

    private HelloService helloService;

    public String helloFjx() {
        return helloService.sayHello("fuck~123");
    }


    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
}
