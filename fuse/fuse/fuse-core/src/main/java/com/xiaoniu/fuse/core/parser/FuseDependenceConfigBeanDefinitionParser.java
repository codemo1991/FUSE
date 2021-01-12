package com.xiaoniu.fuse.core.parser;

import com.xiaoniu.fuse.core.config.FuseHystrixConfig;
import com.xiaoniu.fuse.core.context.FuseContext;
import com.xiaoniu.fuse.core.exception.FuseConfigException;
import com.xiaoniu.fuse.core.helper.ConfigParseHelper;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/9/22 14:30
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class FuseDependenceConfigBeanDefinitionParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        FuseHystrixConfig config = ConfigParseHelper.buildBaseConfig(element, null);
        if (FuseContext.idMapConfig.containsKey(config.getId())) {
            throw new FuseConfigException("dependence:id had exist!");
        }
        FuseContext.idMapConfig.put(config.getId(), config);
        return null;
    }
}
