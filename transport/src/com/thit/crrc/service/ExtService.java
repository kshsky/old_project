package com.thit.crrc.service;

import com.thit.crrc.bean.ExtBean;
import com.thit.crrc.bean.OriDataBean;

/**
 *@filename : ExtService.java
 *@description:
 * 1、用于辅助和扩展的类
 *@author : sf
 *@time:2017年8月28日 上午9:52:00
 */
public interface ExtService {
	
	//保存ZLZT的最新collectiontime
	public void saveExtBean(ExtBean extBean);
	
	//更新ZLZT的最新collectiontime
	public void updateExtBean(ExtBean extBean);
	
	//查询ZLZT的最新collectiontime
	public ExtBean queryExtBean(ExtBean extBean);

}
