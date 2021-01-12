package com.xiaoniu.fuse.test;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ConfigurationBuilderEvent;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.event.EventListener;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/10/12 11:52
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    private static final String UTF_8 = "UTF-8";
    private volatile CompositeConfiguration compositeConfiguration = new CompositeConfiguration();
    private Map<String, PropertiesConfiguration> builderMapConfiguration = new ConcurrentHashMap<>();

    public void initializeConfig(List<File> files) {
        try {
            logger.debug("LOADING PROPERTIES........");
            for (File file : files) {
                logger.debug("loading properties File:{}........", file.getName());
                Parameters params = new Parameters();
                final ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration> reloadbuilder = new ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration>(
                        PropertiesConfiguration.class);

                reloadbuilder.configure(params.fileBased().setEncoding(UTF_8).setFile(file));

                logger.debug("SETTING PROPERTIES TO AUTO SAVE........");
                reloadbuilder.setAutoSave(true);
                PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(reloadbuilder.getReloadingController(),
                        null, 4, TimeUnit.SECONDS);
                trigger.start();
                PropertiesConfiguration propertiesConfiguration = (PropertiesConfiguration) reloadbuilder.getConfiguration();
                builderMapConfiguration.put(reloadbuilder.toString(), propertiesConfiguration);
                compositeConfiguration.addConfiguration(propertiesConfiguration);
                reloadbuilder.addEventListener(ConfigurationBuilderEvent.RESET, new EventListener<ConfigurationBuilderEvent>() {
                    public void onEvent(ConfigurationBuilderEvent event) {
                        try {
                            logger.info("------------Event change:-------------" + event);
                            String builderName = event.getSource().toString();
                            PropertiesConfiguration configuration = builderMapConfiguration.get(builderName);
                            if (configuration != null) {
                                compositeConfiguration.removeConfiguration(configuration);
                                configuration.clear();
                                configuration = (PropertiesConfiguration) event.getSource().getConfiguration();
                                compositeConfiguration.addConfiguration(configuration);
                                builderMapConfiguration.put(builderName, configuration);
                            }
                        } catch (ConfigurationException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            logger.debug("LOADING SUCCESS........");
        } catch (ConfigurationException e) {
            logger.error("ConfigurationException in AbstractConfigUtil.initializeConfig()", e);
        }
    }

    public String getProperties(String key) {
        return String.valueOf(compositeConfiguration.getProperty(key));
    }
}
