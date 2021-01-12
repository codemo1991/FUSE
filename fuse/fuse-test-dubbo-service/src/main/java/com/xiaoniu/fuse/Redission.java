package com.xiaoniu.fuse;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/12/5 15:08
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class Redission {

    public static void main(String[] args) throws IOException {
        Config config = Config.fromJSON(new File("config-file.json"));
        RedissonClient redisson = Redisson.create(config);
        RLock lock = redisson.getLock("anyLock");
// Most familiar locking method
        lock.lock();
    }
}
