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
 * Created by Chris on 2017/9/24.
 */
public class FuseWebUrlConfigBeanDefinitionParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        FuseHystrixConfig config = ConfigParseHelper.buildBaseConfig(element, null);
        if (FuseContext.urlMapConfig.containsKey(config.getUrl())) {
            throw new FuseConfigException("fuse:Url had exist!");
        }
        FuseContext.urlMapConfig.put(config.getUrl(), config);
        return null;
    }
}
