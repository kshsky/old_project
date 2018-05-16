package com.thit.crrc.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thit.crrc.bean.ExtBean;
import com.thit.crrc.service.ExtService;

/**
 *@filename : TestExt.java
 *@description: 测试扩展类
 *
 *@author : sf
 *@time:2017年8月28日 上午11:46:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class TestExt {
/*	
	public ExtBean(String jcid, Date collectiontime, String msgFrom,
			String tableName) {
		this.jcid = jcid;
		this.collectiontime = collectiontime;
		this.msgFrom = msgFrom;
		this.tableName = tableName;
	}*/
	
	@Autowired
	private ExtService extService;
	
	@Test
	public void testAddExtService() throws Exception{
		
		ExtBean extBean = new ExtBean(
				"123",
				"5301",
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-08-28 12:24:22"),
				"00",
				"BOM_RT_ZLZT_TMP",
				new Date());
		
		extService.saveExtBean(extBean);
		System.out.println("ok...");
	}
	@Test
	public void testQueryExtService() throws Exception{
		ExtBean impExtBean = new ExtBean(
				null,
				"5301",
				null,
				"00",
				"BOM_RT_ZLZT_TMP",
			    new Date());
		
		ExtBean expExtBean = extService.queryExtBean(impExtBean);
		System.err.println(expExtBean);
		expExtBean.setCollectiontime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("9919-08-28 12:24:22"));
		expExtBean.setTableName(impExtBean.getTableName());
		System.err.println(expExtBean);
		extService.updateExtBean(expExtBean);
	}
	
	

}
