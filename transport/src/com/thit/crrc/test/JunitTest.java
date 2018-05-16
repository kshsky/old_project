package com.thit.crrc.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 *@filename : JunitTest.java
 *@description:
 *
 *@author : sf
 *@time:2017年8月28日 下午2:46:08
 */
public class JunitTest {
	@Test
	public void testCompareTo() throws Exception{
		Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2019-08-28 12:24:22");
		Date date2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2019-08-28 12:44:22");
		Date date3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2019-08-28 12:44:22");
		Date date4 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2019-08-28 12:44:23");
		
		System.out.println(date1.compareTo(date2));//-1
		System.out.println(date2.compareTo(date1));//1
		System.out.println(date2.compareTo(date3));//0
		System.out.println(date4.compareTo(date3));//1
		System.out.println(date4.compareTo(null));//1
		
		
	}
}
