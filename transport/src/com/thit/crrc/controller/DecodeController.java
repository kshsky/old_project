package com.thit.crrc.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.thit.crrc.bean.DecodeConfigBean;
import com.thit.crrc.service.DecodeService;
import com.thit.crrc.utils.DecodeUtils;
import com.thit.crrc.utils.XMLUtils;

/**
 * @filename: DecodeController.java
 * @description:
 * 
 * @author: sf
 * @time: 2017年1月16日 下午2:25:07
 * @version:1.0
 **/
@Controller
public class DecodeController {
	private static Logger logger = Logger.getLogger(DecodeController.class.getName());
	@Autowired
	private DecodeService decodeService;

	// 从数据库中读取解析配置：用于生成xml文件
	public List<DecodeConfigBean> getDecodeConfigBeanList(String msgType) {
		return decodeService.getDecodeConfigBeanList(msgType);
	}

	// 读取字符串解析结果封装到map中
	public Map<String, String> getStorageMap(String msgType,
			String[] hexStringArray) throws Exception  {
		// logger.info("读取xml文件生成list集合。。。。。。。。。。。。。。。。。。。。。");
		List<DecodeConfigBean> decodeConfigList=null;
		
			decodeConfigList = XMLUtils
					.getDecodeConfigBeanList(msgType);
	
		Map<String, String> gkMap = new HashMap<String, String>();

		logger.debug("===> 遍历decodeConfigList");
		for (DecodeConfigBean decodeConfigBean : decodeConfigList) {

			int index = decodeConfigBean.getLocation() - 1;
			int offset = decodeConfigBean.getOffset();
			String gkCode = decodeConfigBean.getGkCode();
			Integer functionNO = decodeConfigBean.getFunctionNO();

			logger.debug(index + "---" + offset + "---" + gkCode + "---"	+ functionNO);
			
			// 根据index和offset切割成小的数组
			String[] singleHexArr = Arrays.copyOfRange(hexStringArray, index,
					index + offset);
			
			// 解析后放到小map中
			
			Map<String, String> singleMap= DecodeUtils.storeMap(singleHexArr,gkCode, functionNO);
		
			// 遍历每个解析后的singleMap集合封装成到gkMap中
			for (String key : singleMap.keySet()) {

				gkMap.put(key, singleMap.get(key));

			}

		}
		if (gkMap.containsKey("jcType") && gkMap.containsKey("jcNo")) {
			gkMap.put("jcid", gkMap.get("jcType")+ "-" + gkMap.get("jcNo"));// 车型车号单独处理
			logger.debug("jcid 拼接后------------------------>"
					+ gkMap.get("jcid"));
		}

		// 打印map集合
		Set<String> set = gkMap.keySet();
		System.out.println("========================> print map");
		for (String string : set) {
			logger.debug(string + "---" + gkMap.get(string));
		}

		logger.info("\r\n\r\n 封装成 big map集合完毕。。。\r\n\r\n\r\n");

		return gkMap;
	}

}
