
package com.xiaoniu.fuse.test.service;

/**
 * Created by fengjx.
 *
 * @Date：2015/1/5 0005
 */
public interface HelloService {

    /**
     * 暴露的接口
     *
     * @param name
     * @return
     */
    public String sayHello(String name) throws InterruptedException;

}
