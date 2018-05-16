package com.thit.crrc.dao;

import com.thit.crrc.bean.ExtBean;

/**
 *@filename : ExtDao.java
 *@description:
 *
 *@author : sf
 *@time:2017年8月28日 上午10:13:15
 */
public interface ExtDao {
	
	//保存ZLZT的最新collectiontime
	public void saveExtBean(ExtBean extBean);
	
	//更新ZLZT的最新collectiontime
	public void updateExtBean(ExtBean extBean);
	
	//查询ZLZT的最新collectiontime
	public ExtBean queryExtBean(ExtBean extBean);

}
