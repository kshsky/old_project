package com.thit.crrc.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thit.crrc.bean.ExtBean;
import com.thit.crrc.bean.StorageBean;
import com.thit.crrc.bean.StorageConfigBean;
import com.thit.crrc.constants.TransportConstants;
import com.thit.crrc.dao.DecodeDao;
import com.thit.crrc.dao.ExtDao;
import com.thit.crrc.dao.StorageDao;
import com.thit.crrc.utils.DecodeUtils;
import com.thit.crrc.utils.UUIDUtils;

/**
 * @filename : StorageServiceImp.java
 * @description : 把bigMap 封装到storageBean中
 * 
 * @author : sf
 * @time : 2017年1月11日 下午9:47:46
 */
@Service
@Transactional
public class StorageServiceImp implements StorageService {
	
	private static Logger logger = Logger.getLogger(StorageServiceImp.class.getName());
	@Autowired
	private StorageDao storageDao;

	@Autowired
	private DecodeDao decodeDao;
	
	@Autowired
	private ExtDao extDao;

	// ================================读取入库配置=======================
	@Override
	public List<StorageConfigBean> getStorageConfigBeanList(String msgType) {
		return storageDao.getStorageConfigBeanList(msgType);
	}

	// ==============================入库=================================
	@Override
	public void store(String msgFrom,String msgType,Date storagetime, Map<String, String> map) throws Exception {
		

		//读取入库配置
		List<StorageConfigBean> list = storageDao.getStorageConfigBeanList(msgType);

		for (StorageConfigBean storageConfigBean : list) {
			logger.debug("\r\n\r\n"+storageConfigBean+"\r\n\r\n\r\n");
		}
		
		Set<String> keySet = map.keySet();//big map
		
		StorageBean storageBean = new StorageBean();//创建入库bean
		
		for (String gkCode : keySet) {
			for (StorageConfigBean storageConfigBean : list) {
				if (gkCode.equals(storageConfigBean.getGkCode())) {

			logger.debug(storageConfigBean.getColumnName()+ "===" + map.get(gkCode));
					//把属性值赋给属性名		
		
				DecodeUtils.setProperty(storageBean, 
								storageConfigBean.getColumnName().toLowerCase(), 
								map.get(gkCode));
			
				}
			}
		}
		//设置消息类型
		storageBean.setMsgType(msgType);
		
		//设置消息来源
		storageBean.setMsgFrom(msgFrom);
		
		//设置eaf_id
		storageBean.setEafId(UUIDUtils.getUUID());
		
		// 设置jcid
		if(keySet.contains("jcid")){
			storageBean.setJcid(map.get("jcid"));// 车型和车号
		}
		//设置采集时间
		if(keySet.contains("recTime")){
			storageBean.setCollectiontime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("recTime")));
		}
		//设置入库时间--把接收到的入库时间设置为解析入库时间
		storageBean.setStoragetime(storagetime);
		
		logger.debug(msgType+"--"
				+msgFrom+"--"
				+storageBean.getJcid()+"--"
				+storageBean.getCollectiontime());	
		
		//storageBean 封装完成后进行其他业务
		logger.info("\r\n\r\n storageBean 封装完成后进行其他业务 。。。。。。。。。。\r\n\r\n");
		//封装 	ExtBean 
		ExtBean extBean = new ExtBean();
		extBean.setEafId(UUIDUtils.getUUID());
		extBean.setJcid(storageBean.getJcid());
		extBean.setMsgFrom(msgFrom);
		extBean.setTableName(TransportConstants.TABLE_ZLZT_TMP);

		if(TransportConstants.ZLZT.equals(storageBean.getMsgType())){
			
			//没有采集时间，插入
			if(null == extDao.queryExtBean(extBean)){
				extBean.setCollectiontime(storageBean.getCollectiontime());
				extBean.setStoragetime(storageBean.getStoragetime());
				
				logger.debug("\r\n\r\n" +extBean+ "\r\n\r\n");
				
				extDao.saveExtBean(extBean);				
			}else{
				//采集时间大于或等于临时表中的采集时间，则更新该采集时间
				Date dbTime = extDao.queryExtBean(extBean).getCollectiontime();
				if(dbTime == null ||storageBean.getCollectiontime().compareTo(dbTime)>=0){					
				//	if(storageBean.getCollectiontime().compareTo(extDao.queryExtBean(extBean).getCollectiontime())>=0){
						extBean.setCollectiontime(storageBean.getCollectiontime());
						extBean.setStoragetime(storageBean.getStoragetime());
						
						logger.debug("\r\n\r\n" +extBean+ "\r\n\r\n");
						
						extDao.updateExtBean(extBean);
					}
				}
			}
			
		
		if(TransportConstants.XT.equals(storageBean.getMsgType())){
			if(null!=extDao.queryExtBean(extBean) ){
				storageBean.setCollectiontime(extDao.queryExtBean(extBean).getCollectiontime());
			}
		}
		
		if(TransportConstants.ZXB.equals(storageBean.getMsgType())){
			if(null!=extDao.queryExtBean(extBean) ){
				storageBean.setCollectiontime(extDao.queryExtBean(extBean).getCollectiontime());
			}
		}
		
		///////////////////////////////////////////////

		// 调用入库
		storageDao.store(storageBean);
	}

}
