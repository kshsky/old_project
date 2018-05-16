package com.thit.crrc.listener;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thit.crrc.bean.OriDataBean;
import com.thit.crrc.controller.StorageController;
import com.thit.crrc.service.OriDataService;
import com.thit.crrc.utils.DecodeUtils;
import com.thit.crrc.utils.UUIDUtils;

/**
 * @filename: MqListener.java
 * @description: 监听端口，接收数据，调用入库程序
 * 
 * @author: sf
 * @time: 2017年1月18日 下午4:39:18
 * @version:1.0
 **/
@Component
public class MqListener implements MessageListener {

	private static Logger logger = Logger.getLogger(MqListener.class.getName());

	@Autowired
	private OriDataService oriDataService;

	OriDataBean oriDataBean = new OriDataBean();

	@Override
	public void onMessage(Message message) {

		TextMessage mqMsg = (TextMessage) message;

		logger.info("\r\n\r\n\r\n\r\n\r\n\r\n================>\r\n\r\n\r\n");

		String msg = null;
		try {
			msg = mqMsg.getText();
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.info("接收消息异常。。。");
		}

		String msgFrom = "00";

		try {
			// 封装
			oriDataBean.setEafId(UUIDUtils.getUUID());
			oriDataBean.setMsgId(UUIDUtils.getUUID());
			oriDataBean.setMsgFrom(msgFrom);
			oriDataBean.setMsgContent(msg);
			oriDataBean.setMsgStatus("-1");
			oriDataBean.setStoragetime(new Date());

			logger.info("\r\n\r\n into database begin ... ... \r\n\r\n");

			oriDataService.saveOriDataBean(oriDataBean);

			logger.info("\r\n\r\n into database successfully 。。。。。。。。。。\r\n\r\n");

		} catch (Exception e) {

			logger.error("--------------");
			logger.error(msg);
			logger.error(e);
			logger.error(oriDataBean.toString());
		}

	}
}
