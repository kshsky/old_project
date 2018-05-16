package com.thit.crrc.bean;
/**
 * @filename: StorageConfigBean.java
 * @description: 入库配置信息==>从工况信息表中读取行列配置
 *
 *
 * @author: sf
 * @time: 2017年1月17日 下午11:54:17
 * @version: 1.0.0
 */
public class StorageConfigBean {
	
	
	private String gkCode;
	private String columnName;
	
	public String getGkCode() {
		return gkCode;
	}
	public void setGkCode(String gkCode) {
		this.gkCode = gkCode;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	@Override
	public String toString() {
		return "StorageConfigBean [gkCode=" + gkCode + ", columnName="
				+ columnName + "]";
	}

	
	
}
