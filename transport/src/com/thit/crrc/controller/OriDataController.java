package com.thit.crrc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.thit.crrc.bean.OriDataBean;
import com.thit.crrc.service.OriDataService;

/**
 *@filename : OriDataController.java
 *@description:
 *
 *@author : sf
 *@time:2017年9月13日 下午6:12:54
 */
@Controller
public class OriDataController {
	
	
	@Autowired
	private OriDataService oriDataService;
	
	public List<OriDataBean> queryList() throws Exception{
		return oriDataService.queryList();
	}
	
	public void updateMsgStatus(OriDataBean oriDataBean) throws Exception {
		
		oriDataService.updateMsgStatus(oriDataBean);
		
	}

}
