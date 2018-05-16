package com.thit.crrc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thit.crrc.bean.DecodeConfigBean;
import com.thit.crrc.dao.DecodeDao;

/**
 * @filename: DecodeServiceImpl.java
 * @description: 
 *
 * @author: sf
 * @time: 2017年1月16日 下午2:23:04
 * @version:1.0
 **/
@Service("decodeService")
public class DecodeServiceImpl implements DecodeService {
	
	@Autowired
	private DecodeDao decodeDao;

	@Override
	public List<DecodeConfigBean> getDecodeConfigBeanList(String msgType) {
		
		return decodeDao.getDecodeConfigBeanList(msgType);
	}


}
