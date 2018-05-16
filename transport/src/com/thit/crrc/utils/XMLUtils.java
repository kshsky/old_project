package com.thit.crrc.utils;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;

import com.thit.crrc.bean.DecodeConfigBean;
import com.thit.crrc.bean.XmlList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 
 * @filename: XmlUtils.java
 * @description: 读取xml文件，生成xml文件的工具类
 *
 *
 * @author: sf
 * @time: 2017年1月17日 下午8:25:20
 * @version: 1.0.0
 */
public class XMLUtils {
	
	private static Logger log = Logger.getLogger(XMLUtils.class.getName());
	/**
	 * 读取xml配置文件（根据车地协议生成的配置文件），获取DecodeConfigBean的list集合
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<DecodeConfigBean> getDecodeConfigBeanList(String msgType) throws Exception {

		Configuration config = ConfigUtils.getConfiguration();
		
		String filePath=null;
		
		if("0301".equals(msgType)){
			filePath = config.getString("decode_xml.ZXB");			
			log.info(filePath);
		}else if("0401".equals(msgType)){
			filePath =config.getString("decode_xml.ZLZT");
			log.info(filePath);
		}else if("0701".equals(msgType)){
			filePath = config.getString("decode_xml.ZLGZ");
			log.info(filePath);
		}else if("0801".equals(msgType)){
			filePath = config.getString("decode_xml.ZLLJ");
			log.info(filePath);
		}else if("1000".equals(msgType)){
			filePath = config.getString("decode_xml.XT");
			log.info(filePath);
		}else{
			System.out.println("消息类型不存在。。。");
		}
		
		FileReader reader = new FileReader(new File(filePath));
		StringBuffer sb = new StringBuffer();
		int ch = 0;

		while ((ch = reader.read()) != -1) {
			sb.append((char) ch);
			//System.out.print((char) ch + "");

		}

		reader.close();// 读取xml文件结束

		XStream xStream = new XStream(new DomDriver());

		xStream.processAnnotations(com.thit.crrc.bean.XmlList.class);
		
		xStream.alias("gk-config", com.thit.crrc.bean.XmlList.class);
		xStream.alias("gk", com.thit.crrc.bean.DecodeConfigBean.class);

		String xmlString = sb.toString();

		XmlList list = (XmlList) xStream.fromXML(xmlString);
		//log.info("读取xml文件结束。。。");
		return list.getDecodeConfigBeanList();
	}
}
