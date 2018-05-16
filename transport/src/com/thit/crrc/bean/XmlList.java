package com.thit.crrc.bean;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @filename: XmlList.java
 * @description:  用于接收从xml转化而来的list集合
 *
 *
 * @author: sf
 * @time: 2017年1月17日 下午10:34:51
 * @version: 1.0.0
 */
@XStreamAlias("gk-config")
public class XmlList {
	@XStreamImplicit(itemFieldName="gk")
	private List<DecodeConfigBean> decodeConfigBeanList;

	public List<DecodeConfigBean> getDecodeConfigBeanList() {
		return decodeConfigBeanList;
	}

	public void setDecodeConfigBeanList(List<DecodeConfigBean> decodeConfigBeanList) {
		this.decodeConfigBeanList = decodeConfigBeanList;
	}

	@Override
	public String toString() {
		return "XmlList [decodeConfigBeanList=" + decodeConfigBeanList + "]";
	}

}
