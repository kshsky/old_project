package com.thit.crrc.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import com.thit.crrc.bean.StorageConfigBean;

/**
 * @filename : StorageService.java
 * @description : 
 * 
 * @author : sf
 * @time : 2017年1月11日 下午9:46:10
 */

public interface StorageService {

	// 获取入库配置
	public List<StorageConfigBean> getStorageConfigBeanList(String msgType)throws Exception; 
	
	//入库	
	public void store(String msgFrom,String msgType,Date storagetime,Map<String, String> map) throws Exception;

}
