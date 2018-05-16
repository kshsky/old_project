package com.thit.crrc.test;

import org.apache.commons.configuration.Configuration;
import org.junit.Test;

import com.thit.crrc.utils.ConfigUtils;

/**
 * 
 * @filename:TestConfigUtils.java
 * @description:
 *
 * @author: sf
 * @time: 2016年12月28日 下午9:44:47
 * @version: 1.0.0
 */
public class TestConfigUtils {
	@Test
	public void testConfigUtils(){
		
		Configuration config = ConfigUtils.getConfiguration();
		
		System.out.println(config.getString("oracle.classname"));
		System.out.println(config.getString("oracle.url"));
		System.out.println(config.getString("oracle.username"));
		System.out.println(config.getString("oracle.password"));
		
		System.out.println(config.getString("cloud.url.register"));
		System.out.println(config.getString("cloud.url.senddata"));
		System.out.println(config.getString("12010"));

		
		
		
	}

}
