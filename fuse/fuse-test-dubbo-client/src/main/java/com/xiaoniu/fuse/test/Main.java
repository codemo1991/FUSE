package com.xiaoniu.fuse.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/9/19 14:33
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        JedisPool pool = new JedisPool(new JedisPoolConfig(), "10.17.2.197");
        Jedis jedis = pool.getResource();
        jedis.set("RTnotify", "umq");
        jedis.set("notify", "umq");
        jedis.set("notify1", "umq");
        jedis.set("RTnotify1", "umq");
        jedis.expire("RTnotify", 2); //in Seconds
        jedis.expire("RT_abc_123", 2); //in Seconds
//        final ApplicationContext ctx =
//                new ClassPathXmlApplicationContext("applicationContext.xml");
//        final HelloServiceConsumer service = (HelloServiceConsumer) ctx.getBean("helloServiceConsumer");
//
//        for (int i = 0; i < 10; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.err.println(service.helloFjx());
//                }
//            }).start();
//        }

//        final HelloSpeaker speaker = (HelloSpeaker) ctx.getBean("helloSpeaker");
//        System.out.println(speaker.talk());
//        Thread.sleep(5000);
//        for (int i = 0; i < 1000; i++) {
//            final int finalI = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        System.out.println(finalI + " speak");
//                        System.out.println("speak_" + finalI + "_" + speaker.speak("ccd"));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        System.out.println(finalI + " talk");
//                        System.out.println("talk_" + finalI + "_" + speaker.talk());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();

//
//        Parameters params = new Parameters();
//        ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration> reloadbuilder = new ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration>(
//                PropertiesConfiguration.class);
//
//        File propertiesFile = new File("E:\\self-code\\fuse-test-dubbo-client\\target\\classes\\log4j.properties");
//        reloadbuilder.configure(params.fileBased().setEncoding("UTF-8").setFile(propertiesFile));
//
//        reloadbuilder.setAutoSave(true);
//        PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(reloadbuilder.getReloadingController(),
//                null, 4, TimeUnit.SECONDS);
//        trigger.start();
//
//        final PropertiesConfiguration compositeConfiguration = (PropertiesConfiguration) reloadbuilder.getConfiguration();
//        Test t = new Test();
////        t.initializeConfig(propertiesFile);
//
//        while (true) {
//            Thread.sleep(2000L);
//            System.out.println(t.getProperties("abc"));
//        }

    }

//        HystrixPlugins.getInstance().registerMetricsPublisher(HystrixMetricsPublisherDefault.getInstance());

    // when
    // ... we register (without throwing a "Another strategy was already registered" exception)


//        Thread.sleep(Integer.MAX_VALUE);


//}


}
