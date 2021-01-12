package com.xiaoniu.fuse;

import org.junit.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/12/5 13:59
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class JedisLockTest {

    public static void main(String[] args) throws InterruptedException {
        JedisLockTest test = new JedisLockTest();
        test.testAcquire();
    }

    private static final String JEDIS_HOST = System.getProperty("jedis.host", "10.17.2.197");
    private static final Integer JEDIS_PORT = Integer.getInteger("jedis.port", Protocol.DEFAULT_PORT);
    private static final String JEDIS_AUTH = System.getProperty("jedis.auth");

    public static void setup() {

        String hostinfo = String.format("endpoint=%s:%d, auth=%s", JEDIS_HOST, JEDIS_PORT, JEDIS_AUTH);

        System.out.println("Using redis at " + hostinfo);
        Jedis jedis = new Jedis(JEDIS_HOST, JEDIS_PORT);
        try {
            connect(jedis);
        } catch (Exception ex) {
            System.err.println("Unable to connect to Jedis - " + hostinfo);
        } finally {
            disconnect(jedis);
        }
    }

    public void testAcquire() throws InterruptedException {
        Jedis jedis = connect();

        JedisLock lock = new JedisLock(jedis, "testlock2", 100000);
        System.out.println(lock.acquire());
        JedisLock lock2 = new JedisLock(jedis, "testlock2", 1000);
        System.out.println(lock2.acquire());
        lock.release();

        lock2 = new JedisLock(jedis, "testlock2", 1000);
        System.out.println(lock2.acquire());
        lock2.release();
    }

    public void testRenew() throws InterruptedException {
        Jedis jedis = connect();

        JedisLock lock = new JedisLock(jedis, "testlock2");

        Thread.sleep(2000l);


        lock.release();

        JedisLock lock2 = new JedisLock(jedis, "testlock2", 1000);
        lock2.release();
    }

    public void testConcurrency() throws InterruptedException {
        final int count = 10;

        ConcurrentLocker[] lockers = new ConcurrentLocker[]{
                new ConcurrentLocker(count),
                new ConcurrentLocker(count),
                new ConcurrentLocker(count)};

        for (ConcurrentLocker locker : lockers) {
            locker.start();
        }

        for (ConcurrentLocker locker : lockers) {
            locker.join();
        }

        for (ConcurrentLocker locker : lockers) {
            Assert.assertEquals(count, locker.count());
        }
    }

    private class ConcurrentLocker extends Thread {

        private final int times;
        private int counter;

        public ConcurrentLocker(int times) {
            this.times = times;
            this.counter = 0;
        }

        public void run() {
            Jedis jedis = connect();
            try {
                for (int i = 0; i < times; i++) {
                    JedisLock lock = new JedisLock(jedis, "testlock", 15000, 200);
                    try {
                        if (lock.acquire()) {
                            counter++;
                            Thread.sleep(250);
                            lock.release();
                        }
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            } finally {
                disconnect(jedis);
            }
        }

        public int count() {
            return counter;
        }
    }


    private Jedis connect() {
        Jedis jedis = new Jedis(JEDIS_HOST, JEDIS_PORT);
        connect(jedis);
        return jedis;
    }

    private static void connect(Jedis jedis) {
        jedis.connect();
//        jedis.auth(JEDIS_AUTH);
    }

    private static void disconnect(Jedis jedis) {
        try {
            jedis.disconnect();
        } catch (Throwable ignore) {
        }
    }
}
