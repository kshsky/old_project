package com.thit.crrc.dao;

import java.util.List;

import com.thit.crrc.bean.OriDataBean;

/**
 *@filename : OriDataDao.java
 *@description:
 *
 *@author : sf
 *@time:2017年9月4日 下午2:29:09
 */
public interface OriDataDao {
	
	public void saveOriDataBean(OriDataBean oriDataBean)throws Exception;
	
	public List<OriDataBean> queryList() throws Exception;
	
	public void updateMsgStatus(OriDataBean oriDataBean)throws Exception;

}
