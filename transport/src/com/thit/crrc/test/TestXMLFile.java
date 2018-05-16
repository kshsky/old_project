package com.thit.crrc.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thit.crrc.bean.DecodeConfigBean;
import com.thit.crrc.bean.XmlList;
import com.thit.crrc.controller.DecodeController;
import com.thit.crrc.utils.ConfigUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 
 *@filename: TestXMLFile.java
 *@description: 生成相应xml文件
 *
 *@author: sf
 *@time: 2017年5月12日 下午2:40:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class TestXMLFile {
	
	Configuration config = ConfigUtils.getConfiguration();

	@Autowired
	private DecodeController decodeController;

	//读取数据库中的解析配置生成xml文件
	@Test
	public void createXML() throws Exception {
		
		//1.从db中获取相应的list集合
		
		String mspType = "1000";

		List<DecodeConfigBean> list = decodeController.getDecodeConfigBeanList(mspType);
		
		
		//打印
		for (DecodeConfigBean decodeConfigBean : list) {
			System.out.println(decodeConfigBean);
		}

		System.out.println("---------------------------");
		XStream xStream = new XStream();
		xStream.alias("gk-config", java.util.List.class);
		xStream.alias("gk", com.thit.crrc.bean.DecodeConfigBean.class);
		String xml = xStream.toXML(list);

		System.out.println(xml);
		System.out.println("---------------------------");
		System.out.println("---------------------------");
        
		String xmlFilePath=null;
		
		if("0301".equals(mspType)){
			xmlFilePath = config.getString("decode_xml.ZXB");
		}else if("0401".equals(mspType)){
			xmlFilePath = config.getString("decode_xml.ZLZT");
		}else if("0701".equals(mspType)){
			xmlFilePath = config.getString("decode_xml.ZLGZ");
		}else if("0801".equals(mspType)){
			xmlFilePath = config.getString("decode_xml.ZLLJ");
		}else if("1000".equals(mspType)){
		xmlFilePath = config.getString("decode_xml.XT");
	}
		
		String xmlHeader = "<?xml version='1.0' encoding='UTF-8'?>\r\n";

		byte[] buff = (xmlHeader + xml).getBytes();

		FileOutputStream out = new FileOutputStream(new File(xmlFilePath));

		out.write(buff, 0, buff.length);
		out.flush();
		out.close();
		System.out.println("\r\n ok... \r\n\r\n\r\n");
	}

	
	//把xml读取成configbean 的list集合
	@Test
	public void readXml() throws Exception {

		String xmlFilePath_zxb = config.getString("decode_xml.ZXB");
		FileReader reader = new FileReader(new File(xmlFilePath_zxb));
		StringBuffer sb = new StringBuffer();
		int ch = 0;

		while ((ch = reader.read()) != -1) {
			sb.append((char) ch);
			System.out.print((char) ch + "");

		}
		reader.close();

		XStream xStream = new XStream(new DomDriver());
		xStream.processAnnotations(com.thit.crrc.bean.XmlList.class);
		xStream.alias("gk-config", com.thit.crrc.bean.XmlList.class);
		xStream.alias("gk", com.thit.crrc.bean.DecodeConfigBean.class);

		String xmlString = sb.toString();

		XmlList list = (XmlList) xStream.fromXML(xmlString);

		System.out.println(list);
		System.out.println(list.getClass());
		System.out.println("---------");
		for (DecodeConfigBean decodeConfigBean : list.getDecodeConfigBeanList()) {
			System.err.println(decodeConfigBean);

		}

	}

}
