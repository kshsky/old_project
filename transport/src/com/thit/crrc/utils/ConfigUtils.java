package com.thit.crrc.utils;

import java.net.URL;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 
 * @filename:ConfigUtils.java
 * @description: 读取/properties/文件夹中properties文件的配置信息
 *
 * @author: sf
 * @time: 2016年12月28日 下午9:33:24
 * @version: 1.0.0
 */
public class ConfigUtils {
	
    private static final Log logger = LogFactory.getLog(ConfigUtils.class);

    static {
        try {
            URL confiUrl = ConfigUtils.class.getResource("/properties/config.xml");
            DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder(confiUrl);
            config = builder.getConfiguration(true);
        } catch (ConfigurationException e) {
            logger.error(e,e);
        }

    }

    private static Configuration config;

    public static Configuration getConfiguration() {
        return config;
    }

}
