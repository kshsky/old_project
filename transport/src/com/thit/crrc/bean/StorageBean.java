package com.thit.crrc.bean;

import java.util.Date;

/**
 * 
 * @filename: StorageBean.java
 * @description: 根据17号讨论结果重新生成入库bean
 *
 *0825 加  msgFrom   消息来源
 *
 * @author: sf
 * @time: 2017年1月18日 上午12:01:03
 * @version: 1.0.0
 */
public class StorageBean extends BaseColumn{
	
	private String jcid;       
	private Date storagetime;     	
	private Date collectiontime; 
	private String msgFrom;//信息来源//0 实时 1 日志
	
	private String eafId;//主键
	private String msgType;//消息类型
	
	
	public String getJcid() {
		return jcid;
	}
	public void setJcid(String jcid) {
		this.jcid = jcid;
	}
	public Date getStoragetime() {
		return storagetime;
	}
	public void setStoragetime(Date storagetime) {
		this.storagetime = storagetime;
	}
	public Date getCollectiontime() {
		return collectiontime;
	}
	public void setCollectiontime(Date collectiontime) {
		this.collectiontime = collectiontime;
	}
	public String getEafId() {
		return eafId;
	}
	public void setEafId(String eafId) {
		this.eafId = eafId;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgFrom() {
		return msgFrom;
	}
	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}
	
	
}