package com.thit.crrc.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 *@filename : OriDataBean.java
 *@description:
 *
 *@author : sf
 *@time:2017年9月4日 上午11:49:54
 */
@Component
public class OriDataBean {
	
	
	private String eafId;
	private String msgId;
	
	private String msgFrom;	 //00 实时，01日志
	private String msgContent;
	private String msgStatus;//正常00，异常01,未处理-1
	
	private Date storagetime;

	public String getEafId() {
		return eafId;
	}

	public void setEafId(String eafId) {
		this.eafId = eafId;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public Date getStoragetime() {
		return storagetime;
	}

	public void setStoragetime(Date storagetime) {
		this.storagetime = storagetime;
	}

	@Override
	public String toString() {
		return "OriDataBean [eafId=" + eafId + ", msgId=" + msgId
				+ ", msgFrom=" + msgFrom + ", msgContent=" + msgContent
				+ ", msgStatus=" + msgStatus + ", storagetime=" + storagetime
				+ "]";
	}	
	
	
	
}
