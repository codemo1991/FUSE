package com.xiaoniu.fuse.core.nameSpaceHandler;

import com.xiaoniu.fuse.core.context.FuseContext;
import com.xiaoniu.fuse.core.parser.FuseBaseConfigBeanDefinitionParser;
import com.xiaoniu.fuse.core.parser.FuseDependenceConfigBeanDefinitionParser;
import com.xiaoniu.fuse.core.parser.FuseWebUrlConfigBeanDefinitionParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/9/19 14:10
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class FuseNameSpaceHandler extends NamespaceHandlerSupport {

    private final Logger logger = LoggerFactory.getLogger(FuseNameSpaceHandler.class);

    @Override
    public void init() {
        FuseContext.cleanAllContext();
        logger.info("FuseNameSpaceHandler is working....");
        logger.info("loading dependence...");
        registerBeanDefinitionParser("dependence", new FuseDependenceConfigBeanDefinitionParser());
        logger.info("dependence had loaded");
        logger.info("loading consumer...");
        registerBeanDefinitionParser("consumer", new FuseBaseConfigBeanDefinitionParser());
        logger.info("consumer had load");
        logger.info("loading url...");
        registerBeanDefinitionParser("url", new FuseWebUrlConfigBeanDefinitionParser());
        logger.info("url had load");
        logger.info("FuseNameSpaceHandler is done");

    }

}