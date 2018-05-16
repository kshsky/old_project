package com.thit.crrc.dao;

import java.util.List;

import com.thit.crrc.bean.StorageBean;
import com.thit.crrc.bean.StorageConfigBean;

/**
 * 
 * @filename:StorageDao.java
 * @description:
 *
 * @author: sf
 * @time: 2017年1月11日 下午9:36:39
 * @version: 1.0.0
 */

public interface StorageDao {
	
	// 获取入库配置
	public List<StorageConfigBean> getStorageConfigBeanList(String msgType); 
	

	//入库
	public void store(StorageBean storageBean);
	


}
