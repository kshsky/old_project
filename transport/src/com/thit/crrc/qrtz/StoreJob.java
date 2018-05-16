package com.thit.crrc.qrtz;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thit.crrc.bean.OriDataBean;
import com.thit.crrc.controller.OriDataController;
import com.thit.crrc.controller.StorageController;
import com.thit.crrc.utils.DecodeUtils;

/**
 * @filename : StoreJob.java
 * @description:
 * 
 * @author : sf
 * @time:2017年9月13日 下午4:59:25
 */
@Component
public class StoreJob implements Job {
	
	private static Logger logger = Logger.getLogger(StoreJob.class.getName());

	@Autowired
	private StorageController storageController;

	@Autowired
	private OriDataController oriDataController;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// 默认执行的是execute的内容
		System.out.println("0000000000000000000000");
		// TODO Auto-generated method stub

	}

	public void transportStore() throws Exception {

		List<OriDataBean> oriList = oriDataController.queryList();

		for (OriDataBean oriDataBean : oriList) {
			System.out.println(oriDataBean);

			try {
				// 获得消息来源
				String msgFrom = oriDataBean.getMsgFrom();
				
				//获取入库时间
				Date storagetime = oriDataBean.getStoragetime();

				// 获得消息类型和消息内容
				String[] stringArr = DecodeUtils.getTypeContent(oriDataBean
						.getMsgContent());

				String msgType = stringArr[0];
				String msgContent = stringArr[1];

				// 获取16进制字符串数组
				String[] hexStringArr = DecodeUtils.getHexStringArr(msgContent);

				logger.debug("\r\n\r\n before。。。。。。。。。。。。。\r\n\r\n \r\n\r\n ");
				// 调用入库
				storageController.storeStorageBean(msgFrom, msgType,storagetime,hexStringArr);
				
				logger.debug("\r\n\r\n 2222222222222。。。。。。。。。。。。。\r\n\r\n \r\n\r\n ");
				
				// 解析、入库之后更新状态标识
				oriDataBean.setMsgStatus("00");
				
				oriDataController.updateMsgStatus(oriDataBean);
				
				logger.info("00 update successfully ...  ");
				
			} catch (Exception e) {
				
				logger.debug("\r\n\r\n \r\n\r\n 异常。。。。。。。。。。。。。\r\n\r\n \r\n\r\n ");
				
				//异常则把数据状态改为 01
				oriDataBean.setMsgStatus("01");
				
				oriDataController.updateMsgStatus(oriDataBean);
				
				logger.info("01 update successfully ...  ");
				
			}
		}

	}

}
