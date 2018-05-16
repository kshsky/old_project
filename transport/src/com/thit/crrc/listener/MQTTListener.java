package com.thit.crrc.listener;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.thit.crrc.bean.OriDataBean;
import com.thit.crrc.service.OriDataService;
import com.thit.crrc.utils.ConfigUtils;
import com.thit.crrc.utils.UUIDUtils;

/**
 * @filename: MQTTListener.java
 * @description:
 * 
 * @author: sf
 * @time: 2017年4月28日 上午9:55:02
 */
@Component
public class MQTTListener implements ServletContextListener {

	private static Logger logger = Logger.getLogger(MQTTListener.class.getName());
	static Configuration config = ConfigUtils.getConfiguration();

	@Autowired
	private OriDataService oriDataService;
	
	OriDataBean oriDataBean = new OriDataBean();
	
	public static String HOST = null;
	public static String USER = null;
	public static String PASSWORD = null;
	public static String TOPIC = null;
	private static String CLIENTID = null;

	private MqttClient client;
	private MqttConnectOptions options;
	private MqttTopic topic;

	//private String record="";
	
	// 使用静态块初读取配置文件
	static {
		HOST = config.getString("mqtt.host");
		USER = config.getString("mqtt.user");
		PASSWORD = config.getString("mqtt.password");
		TOPIC = config.getString("mqtt.topic");
		CLIENTID = config.getString("mqtt.clientid");
		
	}

	// mqtt加载驱动--初始化
	public void init() throws MqttException {
		client = new MqttClient(HOST, CLIENTID, new MemoryPersistence());
		options = new MqttConnectOptions();
		options.setCleanSession(false);
		//options.setCleanSession(false);
		options.setUserName(USER);
		options.setPassword(PASSWORD.toCharArray());
		options.setConnectionTimeout(100);
		options.setKeepAliveInterval(200);
		// topic = client.getTopic(TOPIC);
		options.setAutomaticReconnect(true);
		options.setWill("client/close", "0".getBytes(), 2, false);
	}

	// mqtt 监听
	public void listener() {

		class PushCallback implements MqttCallback {

			@Override
			public void connectionLost(Throwable arg0) {
				// 连接服务器，进行监听
				if (client.isConnected()) {
					try {
						client.disconnect();
					} catch (MqttException e) {
						System.out.println("重连异常");
						logger.info("重连异常");
					}
				}
				try {
					init();
					listener();
				} catch (MqttSecurityException e) {
					e.printStackTrace();
				} catch (MqttException e) {
					e.printStackTrace();
					logger.info("init异常");
				}
			}
			@Override
			public void deliveryComplete(IMqttDeliveryToken token) {
		
			}
			int p,q,x,y,z=0;
		
			@Override
			public void messageArrived(String topic, MqttMessage message){
				
				String msgFrom = null;
				
				// 区分不同topic，区分消息来源
				if ("topic/from/kenya/c".equals(topic)) {
					System.out.println(topic);
					msgFrom = "00";

				} else if ("topic/from/kenya/log".equals(topic)) {
					System.out.println(topic);
					msgFrom = "01";
				}

				//调用入库方法
				String msg = message.toString();
				
	//对各个消息类型进行计数			
	 if("0301".equals(msg.substring(0, 4))){
		 p++;
	 }else if("0401".equals(msg.substring(0, 4))){
		 q++;
	 }else if("0701".equals(msg.substring(0, 4))){
		 x++;
	 }else if("0801".equals(msg.substring(0, 4))){
		 y++;
	 }else if("1000".equals(msg.substring(0, 4))){
		 z++;
	 }
	 System.out.println("0301 "+p+
			 ",0401 "+q+
			 ",0701 "+x+
			 ",0801 "+y+
			 ",1000 "+z);
				logger.info(msg);
				try {
					//封装
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
					logger.error("--------------");
					logger.error(e.toString());
					logger.info("\r\n\r\n into database failed 。。。。。。。。。。\r\n\r\n");
				}
		
			}
		
		}
		// //////////内部类的方法
		client.setCallback(new PushCallback());// 调用回调函数
		
		try {
			client.connect(options);// 认证
		} catch (MqttSecurityException e) {
			e.printStackTrace();
			logger.info("authority failure ... ");
		} catch (MqttException e) {
			e.printStackTrace();
			logger.info("authority failure ..=. ");
		}
		int[] Qos = { 2 };
		String[] topics = { TOPIC };
		try {
			client.subscribe(topics, Qos);
		} catch (MqttException e) {
			e.printStackTrace();
			logger.info("authority failure ... ");
		}

	}
	// 在application-context加载之前注册bean
	private WebApplicationContext webApplicationContext;
	private ServletContext servletContext;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		servletContext = event.getServletContext();
		webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		
		//在spring初始化之前创建对象oriDataService
		oriDataService = (OriDataService) webApplicationContext.getBean("oriDataService");
		
		try {
			this.init();
			this.listener();
		} catch (MqttException e) {
			logger.info("初始化异常。。。");
		}
	}

}
