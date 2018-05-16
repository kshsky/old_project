package com.thit.crrc.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thit.crrc.bean.DecodeConfigBean;
import com.thit.crrc.bean.OriDataBean;
import com.thit.crrc.bean.StorageBean;
import com.thit.crrc.bean.StorageConfigBean;
import com.thit.crrc.controller.DecodeController;
import com.thit.crrc.controller.OriDataController;
import com.thit.crrc.controller.StorageController;
import com.thit.crrc.dao.StorageDao;
import com.thit.crrc.listener.MqListener;
import com.thit.crrc.service.StorageService;
import com.thit.crrc.utils.DecodeUtils;
import com.thit.crrc.utils.XMLUtils;

/**
 * @filename: TestStorage.java
 * @description: 入库的测试： 包括拆分测试和整合测试
 *
 * @author: sf
 * @time: 2017年1月19日 上午8:49:38
 * @version:1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class TestStorage {

	@Autowired
	private StorageDao storageDao;

	@Autowired
	private DecodeController decodeController;

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private StorageController storageController;
	
	@Autowired
	private MqListener mqListener;
	
	
	@Autowired
	private OriDataController oriDataController;

	
	
	///////////////////////////////////////////////////////////

	// 从数据库中读取入库配置
	@Test
	public void getStorageConfigBean() {
		List<StorageConfigBean> list = storageDao.getStorageConfigBeanList("0301");

		for (StorageConfigBean storageConfigBean : list) {
			System.out.println(storageConfigBean);

		}
	}

	// 从xml文件中读取解析配置
	@Test
	public void getDecodeConfigBean() throws Exception {

		// 使用XmlUtils获取 DecodeConfigBean 的list集合
		List<DecodeConfigBean> decodeConfigList = XMLUtils.getDecodeConfigBeanList("");
		for (DecodeConfigBean decodeConfigBean : decodeConfigList) {
			System.out.println(decodeConfigBean);
		}

	}

	// 测试把字符串转化为16进制形式的字符串数组
	@Test
	public void getHexArray() {
		String id301 = "30 30 30 31 00 02 82 09 0b 10 0d 82 82 82 82 82 82 82 82 82 82 82 82 82 82 82 82 82 82 06 06 05 05 07 06 06 06 05 06 06 07 06 06 05 06 07 06 07 2d 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 42 f2 58 11 45 42 1b 51 3d 4e 00 00 b1 00 01 00 00";

		String[] hexArray = DecodeUtils.getHexArray(id301);
		String[] realHexArray = {};
		for (String string : hexArray) {
			System.out.println(string);
		}
	}

	// 入库★★★★★★★★★
	@Test
	public void store() throws Exception {
		String id301 = "30 30 30 31 00 02 82 09 0b 10 0d 82 82 82 82 82 82 82 82 82 82 82 82 82 82 82 82 82 82 06 06 05 05 07 06 06 06 05 06 06 07 06 06 05 06 07 06 07 2d 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 42 f2 58 11 45 42 1b 51 3d 4e 00 00 b1 00 01 00 00";

		Integer x = (int) System.currentTimeMillis();
		// 转化为16进制字符串数组
		String[] hexArray = DecodeUtils.getHexArray(id301);

		// 读取解析配置
		List<DecodeConfigBean> decodeConfigList = XMLUtils.getDecodeConfigBeanList("0301");

		for (String hexSingleString : hexArray) {
			System.out.print("16进制字符串数组: " + hexSingleString + " ");
		}

		// 调用解析程序解析16进制字符串形式的数组，并装载到map集合中
		Map<String, String> gkMap = decodeController.getStorageMap("0301",hexArray);

		//入库
		storageService.store("00","0301",new Date(),gkMap);
		
		System.out.println(System.currentTimeMillis());

		Integer y = (int) System.currentTimeMillis();
		System.out.println(y - x);
	}



	//测试静态数据
	
	@Test
	public void storeStorageBean() {
		StorageBean storageBean = new StorageBean();

		storageBean.setJcid("JCID-DF8-1234");

		storageBean.setColumn001("001");
		storageBean.setColumn002("001");
		storageBean.setColumn003("001");
		storageBean.setColumn004("001");
		storageBean.setColumn005("001");

		storageBean.setColumn006("001");
		storageBean.setColumn007("001");
		storageBean.setColumn008("001");
		storageBean.setColumn009("001");
		storageBean.setColumn010("001");

		storageBean.setColumn011("001");
		storageBean.setColumn012("001");
		storageBean.setColumn013("001");
		storageBean.setColumn014("001");
		storageBean.setColumn015("001");

		storageBean.setColumn016("001");
		storageBean.setColumn017("001");
		storageBean.setColumn018("001");
		storageBean.setColumn019("001");
		storageBean.setColumn020("001");

		storageBean.setColumn021("001");
		storageBean.setColumn022("001");
		storageBean.setColumn023("001");
		storageBean.setColumn024("001");
		storageBean.setColumn025("001");

		storageBean.setColumn026("001");
		storageBean.setColumn027("001");
		storageBean.setColumn028("001");
		storageBean.setColumn029("001");
		storageBean.setColumn030("001");

		storageBean.setColumn031("001");
		storageBean.setColumn032("001");
		storageBean.setColumn033("001");
		storageBean.setColumn034("001");
		storageBean.setColumn035("001");

		storageBean.setColumn036("001");
		storageBean.setColumn037("001");
		storageBean.setColumn038("001");
		storageBean.setColumn039("001");
		storageBean.setColumn040("001");

		storageBean.setColumn041("001");
		storageBean.setColumn042("001");
		storageBean.setColumn043("001");
		storageBean.setColumn044("001");
		storageBean.setColumn045("001");

		storageBean.setColumn046("001");
		storageBean.setColumn047("001");
		storageBean.setColumn048("001");
		storageBean.setColumn049("001");
		storageBean.setColumn050("001");

		storageBean.setColumn051("001");
		storageBean.setColumn052("001");
		storageBean.setColumn053("001");
		storageBean.setColumn054("001");
		storageBean.setColumn055("001");

		storageBean.setColumn056("001");
		storageBean.setColumn057("001");
		storageBean.setColumn058("001");
		storageBean.setColumn059("001");

		storageDao.store(storageBean);
		System.out.println("------------------------->ok");
	}
	
	
	@Test
	public void func() throws Exception{
		List<OriDataBean> oriList = oriDataController.queryList();
		
		for (OriDataBean oriDataBean : oriList) {
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(
					new File("D:\\temp\\log\\log_db_3813.txt"),true)));

				bw.write(oriDataBean.getMsgContent()+"\r\n");
				bw.flush();	
			 if("0301".equals(oriDataBean.getMsgContent().substring(0, 4))){
					BufferedWriter bw1 = new BufferedWriter(
							new OutputStreamWriter(
							new FileOutputStream(
							new File("D:\\temp\\log\\log_db_3813_0301.txt"),true)));

						bw1.write(oriDataBean.getMsgContent()+"\r\n");
						bw1.flush();	
			 }else if("0401".equals(oriDataBean.getMsgContent().substring(0, 4))){
					BufferedWriter bw2 = new BufferedWriter(
							new OutputStreamWriter(
							new FileOutputStream(
							new File("D:\\temp\\log\\log_db_3813_0401.txt"),true)));

						bw2.write(oriDataBean.getMsgContent()+"\r\n");
						bw2.flush();	
			 }else if("0701".equals(oriDataBean.getMsgContent().substring(0, 4))){
					BufferedWriter bw3 = new BufferedWriter(
							new OutputStreamWriter(
							new FileOutputStream(
							new File("D:\\temp\\log\\log_db_3813_0701.txt"),true)));

						bw3.write(oriDataBean.getMsgContent()+"\r\n");
						bw3.flush();	
			 }else if("0801".equals(oriDataBean.getMsgContent().substring(0, 4))){
					BufferedWriter bw4 = new BufferedWriter(
							new OutputStreamWriter(
							new FileOutputStream(
							new File("D:\\temp\\log\\log_db_3813_0801.txt"),true)));

						bw4.write(oriDataBean.getMsgContent()+"\r\n");
						bw4.flush();	
			 }else if("1000".equals(oriDataBean.getMsgContent().substring(0, 4))){
					BufferedWriter bw5 = new BufferedWriter(
							new OutputStreamWriter(
							new FileOutputStream(
							new File("D:\\temp\\log\\log_db_3813_1000.txt"),true)));

						bw5.write(oriDataBean.getMsgContent()+"\r\n");
						bw5.flush();	
			 }
		
		}
		System.out.println("ok...........");
		
	}

}
