package com.thit.crrc.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @filename: DecodeConfigBean.java
 * @description: 解析表，对应解析协议，将来使用xml存储
 *
 * @author: sf
 * @time: 2017年1月16日 下午1:07:35
 * @version:1.0
 **/
public class DecodeConfigBean {
	
	@XStreamAlias("id")
	private Integer location;  //协议中指标的字节位置（开始位置）
	
	@XStreamAlias("offset")
	private Integer offset;   //偏移量，指标所占字节个数
	
	//gkCode 解析和入库的连接字段
	@XStreamAlias("gkCode")
	private String gkCode;
	
	//方法号
	@XStreamAlias("functionNO")
	private Integer functionNO;

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getGkCode() {
		return gkCode;
	}

	public void setGkCode(String gkCode) {
		this.gkCode = gkCode;
	}

	public Integer getFunctionNO() {
		return functionNO;
	}

	public void setFunctionNO(Integer functionNO) {
		this.functionNO = functionNO;
	}

	@Override
	public String toString() {
		return "DecodeConfigBean [location=" + location + ", offset=" + offset
				+ ", gkCode=" + gkCode + ", functionNO=" + functionNO + "]";
	}


	
	
}
