package com.xiaoniu.fuse.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/12/25 14:29
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class Subscriber {
    public static void main(String[] args) {
        /*  JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
            Jedis jedis1 = pool.getResource();
            jedis1.psubscribe(new NotificationListener(), "__key*__:RT*");  */
        Jedis jedis = new Jedis("10.17.2.197");
        jedis.psubscribe(new JedisPubSub() {
            @Override
            public void onPSubscribe(String pattern, int subscribedChannels) {
                System.out.println("onPSubscribe " + pattern + " " + subscribedChannels);
            }

            @Override
            public void onPMessage(String pattern, String channel, String message) {
                System.out.print("[Pattern:" + pattern + "]");
                System.out.print("[Channel: " + channel + "]");
                System.out.println("[Message: " + message + "]");
            }
        }, "__key*__:RT*:expired");

    }
}
