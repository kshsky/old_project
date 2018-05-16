package com.thit.crrc.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 *@filename : ExtBean.java
 *@description:
 * 1、用于辅助和扩展的类
 * 	把重连状态的采集时间和车号进行存储(至存储最新的采集时间)
 *  把最近的采集时间和车号与入库的心跳信息进行匹配，把采集时间赋给心跳的采集时间
 * 
 *@author : sf
 *@time:2017年8月28日 上午9:55:38
 */

@Component
public class ExtBean {
	
	private String eafId; 
	
	private String jcid;       
 	
	private Date collectiontime;
	
	private String msgFrom;
	
	private String tableName;
	
	private Date storagetime;

	
	public ExtBean() {}

	public ExtBean(Object object, String string, Date parse, String string2,
			String string3, Date date) {
		
	}

	public String getEafId() {
		return eafId;
	}

	public void setEafId(String eafId) {
		this.eafId = eafId;
	}

	public String getJcid() {
		return jcid;
	}

	public void setJcid(String jcid) {
		this.jcid = jcid;
	}

	public Date getCollectiontime() {
		return collectiontime;
	}

	public void setCollectiontime(Date collectiontime) {
		this.collectiontime = collectiontime;
	}

	public String getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getStoragetime() {
		return storagetime;
	}

	public void setStoragetime(Date storagetime) {
		this.storagetime = storagetime;
	}

	@Override
	public String toString() {
		return "ExtBean [eafId=" + eafId + ", jcid=" + jcid
				+ ", collectiontime=" + collectiontime + ", msgFrom=" + msgFrom
				+ ", tableName=" + tableName + ", storagetime=" + storagetime
				+ "]";
	}
	
	
}
