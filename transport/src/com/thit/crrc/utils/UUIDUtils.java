package com.thit.crrc.utils;

import java.util.UUID;

import org.junit.Test;

/**
 *@filename : UUIDUtils.java
 *@description:
 *
 *@author : sf
 *@time:2017年9月14日 上午9:23:41
 */
public class UUIDUtils {
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	@Test
	public void func(){
		
		System.out.println(UUIDUtils.getUUID());
		
	}

}
