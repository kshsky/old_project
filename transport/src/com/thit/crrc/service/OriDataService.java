package com.thit.crrc.service;

import java.util.List;

import com.thit.crrc.bean.OriDataBean;


/**
 *@filename : OriDataService.java
 *@description:
 *
 *@author : sf
 *@time:2017年9月4日 下午2:31:29
 */
public interface OriDataService {
	
	public void saveOriDataBean(OriDataBean oriDataBean)throws Exception;
	
	public List<OriDataBean> queryList() throws Exception;
	
	public void updateMsgStatus(OriDataBean oriDataBean)throws Exception;
}
