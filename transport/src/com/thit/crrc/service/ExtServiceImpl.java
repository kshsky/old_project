package com.thit.crrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thit.crrc.bean.ExtBean;
import com.thit.crrc.dao.ExtDao;

/**
 *@filename : ExtServiceImpl.java
 *@description:
 * 1、用于辅助和扩展的类
 * 
 *@author : sf
 *@time:2017年8月28日 上午9:52:45
 */

@Service
@Transactional
public class ExtServiceImpl implements ExtService {
	
	
	@Autowired
	private ExtDao extDao;

	@Override
	public void saveExtBean(ExtBean extBean) {
		extDao.saveExtBean(extBean);
		
	}	

	@Override
	public void updateExtBean(ExtBean extBean) {
		extDao.updateExtBean(extBean);
			}

	@Override
	public ExtBean queryExtBean(ExtBean extBean) {
		return extDao.queryExtBean(extBean);		
	}



}
