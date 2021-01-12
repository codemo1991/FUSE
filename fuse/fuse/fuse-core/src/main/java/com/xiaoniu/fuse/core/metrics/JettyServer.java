package com.xiaoniu.fuse.core.metrics;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/9/25 17:30
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class JettyServer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private int port;
    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    private Server server = null;

    public void init() {
        try {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        logger.info("hystrix metrics startup in port:{}", port);
                        //加载HystrixMetricsStreamServlet并映射url
                        server = new Server(port);
                        WebAppContext context = new WebAppContext();
                        context.setContextPath("/");
                        context.addServlet(HystrixMetricsStreamServlet.class, "/hystrix.stream");
                        context.setResourceBase(".");
                        server.setHandler(context);
                        server.start();
                        server.join();
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void destory() {
        if (server != null) {
            try {
                server.stop();
                server.destroy();
                logger.warn("jettyServer stop and destroy!");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}