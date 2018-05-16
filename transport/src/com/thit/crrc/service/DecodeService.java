package com.thit.crrc.service;

import java.util.List;

import com.thit.crrc.bean.DecodeConfigBean;

/**
 * @filename: DecodeService.java
 * @description: 
 *
 * @author: sf
 * @time: 2017年1月16日 下午1:56:43
 * @version:1.0
 **/

public interface DecodeService {
	
	public List<DecodeConfigBean> getDecodeConfigBeanList(String msgType);
}
