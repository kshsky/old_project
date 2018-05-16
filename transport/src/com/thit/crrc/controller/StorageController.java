package com.thit.crrc.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.thit.crrc.service.OriDataService;
import com.thit.crrc.service.StorageService;

/**
 * @filename: StorageController.java
 * @description:
 * 
 * @author: sf
 * @time: 2017年2月9日 下午4:12:59
 * @version:1.0
 **/
@Controller
public class StorageController {

	@Autowired
	private StorageService storageService;
	@Autowired
	private DecodeController decodeController;

	public void storeStorageBean(String msgFrom, String msgType,Date storagetime, String[] hexStringArray) throws Exception {

		// 解析并把解析后的数据封装到map集合中

		Map<String, String> gkMap = decodeController.getStorageMap(msgType,hexStringArray);
		// 入库
		storageService.store(msgFrom, msgType, storagetime,gkMap);

	}

}
