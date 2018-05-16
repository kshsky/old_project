package com.thit.crrc.dao;

import java.util.List;

import com.thit.crrc.bean.DecodeConfigBean;

/**
 * @filename: DecodeDao.java
 * @description: 
 *
 * @author: sf
 * @time: 2017年1月16日 下午1:59:55
 * @version:1.0
 **/
public interface DecodeDao {
	
		public List<DecodeConfigBean> getDecodeConfigBeanList(String msgType);
		
}
