package com.thit.crrc.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.thit.crrc.bean.Employee;
import com.thit.crrc.bean.StorageBean;
import com.thit.crrc.constants.TransportConstants;

/**
 * @filename: DecodeUtils.java
 * @description: 单个解析方法，把属性值通过反射赋给属性名
 * 
 * @author: sf
 * @time: 2017年1月19日 上午9:01:30
 * @version:1.0
 **/
public class DecodeUtils {
	
	private static Logger logger = Logger.getLogger(DecodeUtils.class.getName());

	/**
	 * 传入JavaBean propertyName propertyValue 使用该方法为该字段赋值
	 * 
	 * @param clazz
	 *            泛型传入对象类
	 * @param propertyName
	 *            类的属性字段
	 * @param propertyValue
	 *            字段赋予的值--十进制
	 * @throws Exception
	 */
//使用employee 进行测试
	public static void setProperty(Employee employee, String propertyName,
			int propertyValue) throws Exception {
		PropertyDescriptor descriptor = new PropertyDescriptor(propertyName,
				Employee.class);
		Method setMethod = descriptor.getWriteMethod();// 获取set方法
		setMethod.invoke(employee, propertyValue);
	}

	public static void setProperty(Employee emp, String propertyName,
			String propertyValue) throws Exception {
		PropertyDescriptor descriptor = new PropertyDescriptor(propertyName,
				emp.getClass());
		Method setMethod = descriptor.getWriteMethod();// 获取set方法
		setMethod.invoke(emp, propertyValue);
	}
//反射，获取set方法，把String类型的属性值赋给属性名 
	public static void setProperty(StorageBean storageBean,
			String propertyName, String propertyValue) throws Exception {
		PropertyDescriptor descriptor = new PropertyDescriptor(propertyName,
				storageBean.getClass());
		Method setMethod = descriptor.getWriteMethod();// 获取set方法
		setMethod.invoke(storageBean, propertyValue);
	}
	//反射，获取set方法，把int类型的属性值赋给属性名 
	public static void setProperty(StorageBean storageBean,
			String propertyName, int propertyValue) throws Exception {
		PropertyDescriptor descriptor = new PropertyDescriptor(propertyName,
				storageBean.getClass());
		Method setMethod = descriptor.getWriteMethod();// 获取set方法
		setMethod.invoke(storageBean, propertyValue);
	}

	/**
	 * 单个解析方法
	 * ------------------------------- 
	 * 0 直接存储 
	 * 1 StringToInt  字符串转十进制数
	 * 2 StringToASCII  字符串拼接ascii码
	 * 3 StringArrayToDateTime   转时间 （并保存为collectiontime）
	 * 4 StringToBoolArray  
	 * 5 StringArrayToFloat 转浮点数（经纬度）  0.0置为空
	 * 6 StringArrayToStringByAdd 获得车号  反向拼接
	 * 7 getJCIDType 获得车型 (2个字节) 反向拼接
	 * 8  温度解析  
	 * 9  获得车号(5个字节,拼ascii)
	 * 10 获得车型  顺序拼接
	 * 11 获得车号  顺序拼接
	 * 
	 * 12	获得机车工况（直接存储）
	 * 13	获得协议版本（字符串转十进制数然后除以10）
	 * 14	获得AB车标识（直接存储）
	 * 15        获得ip：16进制转成10进制，再转成ip
	 * 16        获得网络状态   
	         * 3G：01；
			 * WIFI：10；
			 * 卫星：11；
	 * 
	 * 17        温度解析，同8，增加判断：-126时，保存为“异常温度”，0保存为“-”
	 * 18   16进制转化为10进制，并进行判断（0:无报警；1:有报警）
	 * 19   16进制转化为10进制（获得报警类型）（保存格式==>代码+：+文字）
	 * 20   16进制转时间,同3，但不保存为collectiontime，0保存为“-”（走行步报警时间）
	 * 21        直接存储 00-->0 FF-->故障 
	 * 
	 * 22        获得车型(5个字节,拼ascii把结果转10进制,再转16进制)
	 * 
	 * ----------------------------------
	 */
	// 把小数组根据方法编号进行解析，并把解析结果封装成map
	public static Map<String, String> storeMap(String[] singleHexArr,
			String gkCode, Integer functionNO) throws Exception {

		Map<String, String> result = null;

		if (functionNO == 0) {
			result = functionNO0(singleHexArr, gkCode);
		} else if (functionNO == 1) {
			result = functionNO1(singleHexArr, gkCode);
		} else if (functionNO == 2) {
			result = functionNO2(singleHexArr, gkCode);
		} else if (functionNO == 3) {
			result = functionNO3(singleHexArr, gkCode);
		} else if (functionNO == 4) {
			result = functionNO4(singleHexArr, gkCode);
		} else if (functionNO == 5) {
			result = functionNO5(singleHexArr, gkCode);
		} else if (functionNO == 6) {
			result = functionNO6(singleHexArr, gkCode);
		} else if (functionNO == 7) {
			result = functionNO7(singleHexArr, gkCode);
		} else if (functionNO == 8) {
			result = functionNO8(singleHexArr, gkCode);
		} else if (functionNO == 9) {
			result = functionNO9(singleHexArr, gkCode);
		} else if (functionNO == 10) {
			result = functionNO10(singleHexArr, gkCode);
		} else if (functionNO == 11) {
			result = functionNO11(singleHexArr, gkCode);
		} else if (functionNO == 12) {
			result = functionNO12(singleHexArr, gkCode);
		} else if (functionNO == 13) {
			result = functionNO13(singleHexArr, gkCode);
		} else if (functionNO == 14) {
			result = functionNO14(singleHexArr, gkCode);
		} else if (functionNO == 15) {
			result = functionNO15(singleHexArr, gkCode);
		} else if (functionNO == 16) {
			result = functionNO16(singleHexArr, gkCode);
		} else if (functionNO == 17) {
			result = functionNO17(singleHexArr, gkCode);
		} else if (functionNO == 18) {
			result = functionNO18(singleHexArr, gkCode);
		} else if (functionNO == 19) {
			result = functionNO19(singleHexArr, gkCode);
		} else if (functionNO == 20) {
			result = functionNO20(singleHexArr, gkCode);
		} else if (functionNO == 21) {
			result = functionNO21(singleHexArr, gkCode);
		} else if (functionNO == 22) {
			result = functionNO22(singleHexArr, gkCode);
		} else {
			System.out.println("方法选择错误。。。");
		}
	// 打印map集合
	/*	for (String resultKey : result.keySet()) {
			System.out.println(resultKey + "===" + result.get(resultKey));
		}*/
		return result;

	}

	// ///////////////////////格式转化////////////////////////////

	// 把接收到的信息分为消息类型和消息内容两部分
	public static String[] getTypeContent(String msg) {
		String[] typeContent = new String[2];
		typeContent[0] = msg.substring(0, 4);
		System.out.println("msgType===>" + typeContent[0]);
		typeContent[1] = msg.substring(8);
		// typeContent[1] = msg.substring(4);
		System.out.println("msgContent===>" + typeContent[1]);
		return typeContent;
	}

	/**
	 * 把消息内容转化为16进制字符串数组
	 * @param string
	 * @return
	 */
	public static String[] getHexStringArr(String string) {
		StringBuffer buffer = new StringBuffer();
		// 加空格
		for (int x = 0; x < string.length(); x++) {
			if (x % 2 == 1) {
				buffer.append(string.charAt(x)).append(" ");
			} else {
				buffer.append(string.charAt(x));
			}
		}
		System.out.println("字节数组的长度是======================>"
				+ (buffer.toString().split(" ").length+1));
		return buffer.toString().split(" ");

	}

	// ===============================单个解析方法==============================
	// ////////////////////////////////////////////////////////////

	/**
	 * 0 directSave
	 * ff --->ff 直接存储
	 * @param hexArray
	 * @param gkCode
	 * @return
	 */
	public static Map<String, String> functionNO0(String[] hexArray,
			String gkCode) {
		Map<String, String> storeMap = new HashMap();
		//顺序拼接
		String mapValue = "";
		for (String string : hexArray) {
			mapValue += string;
		}
		storeMap.put(gkCode, mapValue);

		for (String resultKey : storeMap.keySet()) {
			logger.debug("functionNO0.....................");
			logger.debug(resultKey + "===" + storeMap.get(resultKey));
		}
		return storeMap;

	}

	/**
	 *  1 StringToInt  字符串直接换算成十进制数
	 *  32---130  默认为0
	 * @param hexArray
	 * @param gkCode
	 * @return
	 */
	public static Map<String, String> functionNO1(String[] hexArray,
			String gkCode) {
		Map<String, String> storeMap = new HashMap();
		// string 不带0x
		// String result = Integer.valueOf("82",16).toString();//130
		String hexValue = "";
		for (String string : hexArray) {
			hexValue += string;
		}
			storeMap.put(gkCode, Integer.valueOf(hexValue, 16).toString());
		for (String resultKey : storeMap.keySet()) {
			logger.debug("functionNO1.....................");
			logger.debug(resultKey + "===" + storeMap.get(resultKey));
		}
		return storeMap;
	}

	/**
	 * 2 StringToASCII
	 * 默认为0
	 * @param hexArray
	 * @param gkCode
	 * @return
	 */
	public static Map<String, String> functionNO2(String[] hexArray,
			String gkCode) {
		Map<String, String> storeMap = new HashMap();
		String asciiValue = "";
		for (String string : hexArray) {
			if (0 == (int) Integer.valueOf(string, 16)) {
				asciiValue += "";
			} else {
				asciiValue += (char) (int) Integer.valueOf(string, 16);
			}
		}
		//System.err.println(asciiValue);
		if ("".equals(asciiValue)) {
			storeMap.put(gkCode, "0");
		} else {
			storeMap.put(gkCode, asciiValue);
		}
		for (String resultKey : storeMap.keySet()) {
			logger.debug("functionNO2.....................");
			logger.debug(resultKey + "===" + storeMap.get(resultKey));
		}
		return storeMap;
	}

	/**
	 * 3 StringArrayToDateTime
	 *  58 36 ff 28 -->1479999272
	 *  2016-11-24 14:54:32 是英国格林尼治时间，比北京时间晚8小时
	 *  2016-11-24 22:54:32 北京时间
	 * @param hexArray
	 * @param gkCode
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> functionNO3(String[] hexArray,
			String gkCode) throws Exception {
		
		Map<String, String> storeMap = new HashMap();
		
		StringBuilder sb = new StringBuilder();
		for (String string : hexArray) {
			sb.append(string);
		}
		String unixStampStr = sb.toString();
	
		// 北京所在时区的世界协调时间
		long unixTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 08:00:00").getTime();
		// 接收数据的Unix时间戳
		//long unixStamp = new Date(Integer.valueOf(unixStampStr, 16)).getTime() * 1000;//Integer数据范围过小
		long unixStamp = new Date(Long.parseLong(unixStampStr, 16)).getTime() * 1000;
		
		// 两者确定北京时间的毫秒值，然后转化为北京时间字符串
		String beiJingTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(unixTime + unixStamp));
		
		storeMap.put(gkCode, beiJingTime);
		storeMap.put("recTime", beiJingTime);
		
		for (String resultKey : storeMap.keySet()) {
			logger.debug("functionNO3.....................");
			logger.debug(resultKey + "===" + storeMap.get(resultKey));
		}
		return storeMap;
	}

	/**
	 * 4 StringToBoolArray
	 * 97 --- 10010111
	 * 98 --- 10011000
	 * @param hexArray
	 * @param gkCode
	 * @return
	 */
	public static Map<String, String> functionNO4(String[] hexArray,
			String gkCode) {
		// int x = Integer.valueOf("97",16);
		// String y = Integer.toBinaryString(x);
		Map<String, String> storeMap = new HashMap();
		String binString = "";
		for (String string : hexArray) {
			//System.out.println(string);
			// 97-->10010111
			binString = Integer.toBinaryString(Integer.valueOf(string, 16));
			// 不足8位后补0
			String prefix = "";
			for (int x = 0; x < 8 - binString.length(); x++) {
				prefix += "0";
			}
			binString = prefix + binString;
		}
		//System.out.println(binString);
		// c0 --> 11000000
		String[] gkMap = gkCode.split(",");
		for (int i = 0; i < binString.length(); i++) {
			storeMap.put(gkMap[i], binString.charAt(i) + "");
		}

		for (String resultKey : storeMap.keySet()) {
			logger.debug("functionNO4.....................");
			logger.debug(resultKey + "===" + storeMap.get(resultKey));
		}
		return storeMap;
	}

	/**
	 * 5 StringArrayToFloat
	 * 42f25811 GPS经度 //121.172005 <--121.17200469970703125
	 * @param hexArray
	 * @param gkCode
	 * @return
	 */
	public static Map<String, String> functionNO5(String[] hexArray,
			String gkCode) {
		Map<String, String> storeMap = new HashMap();
		String gk = "";
		for (String string : hexArray) {
			gk += string;
		}
		//System.out.println(Float.intBitsToFloat((int) Long.parseLong("BFAF5C29",16)));//-1.37
        //System.out.println("--->"+gk);
		String gkValue=String.valueOf(Float.intBitsToFloat((int) Long.parseLong(gk, 16)));
		
		if("0.0".equals(gkValue)){			
			storeMap.put(gkCode, " ");
		}else{
			storeMap.put(gkCode, gkValue);
		}
		for (String resultKey : storeMap.keySet()) {
			logger.debug("functionNO5.....................");
			logger.debug(resultKey + "===" + storeMap.get(resultKey));
		}
		return storeMap;
	}

	/**
	 * 6 StringArrayToStringByAdd 获取车号  反向拼接
	 * @param hexArray
	 * @param gkCode
	 * @return
	 */
	//B9 14 -->14 B9 -->5305
	public static Map<String, String> functionNO6(String[] hexArray,
			String gkCode) {
		Map<String, String> storeMap = new HashMap();
		
		//小端存储，直接拼接
		String gcNo_String = "";
	/*	for (String string : hexArray) {
			gcNo_String += string;
		}*/

		//大端存储，反向拼接
		for(int i=hexArray.length-1;i>=0;i--){
			gcNo_String += hexArray[i].toUpperCase();
		}
		
		//车号转换为十进制数据，不满4位，左边补0
		String gcNo = "" + (int) Integer.valueOf(gcNo_String, 16);
		for (int i = 0; gcNo.length() < 4; i++) {
			gcNo = "0" + gcNo;
		}
		//判断，如果解析后
		//System.err.println(gcNo);
		if ("".equals(gcNo)) {
			storeMap.put(gkCode, "0");
			storeMap.put("jcNo", gcNo);// 方法6 获得车号
		} else {
			storeMap.put("jcNo", gcNo);// 方法6 获得车号
			storeMap.put(gkCode, gcNo);
		}

		for (String resultKey : storeMap.keySet()) {
			logger.debug("functionNO6.....................");
			logger.debug(resultKey + "===" + storeMap.get(resultKey));
		}
		return storeMap;

	}

	/**
	 * 7 getJCIDType
	 * 获得车型   
	 * @param hexArray
	 * @param gkCode
	 * @return
	 */
	public static Map<String, String> functionNO7(String[] hexArray,
			String gkCode) {
		Map<String, String> storeMap = new HashMap();
		String gcType_string = "";
		//小端存储，直接拼接
		/*	
		 * for (String string : hexArray) {
				asciiValue += "" + string.toUpperCase();
			}
		*/
		//大端存储，反向拼接
		for(int i=hexArray.length-1;i>=0;i--){
			gcType_string +=hexArray[i].toUpperCase();
		}
		
		storeMap.put(gkCode,TransportConstants.PREFIX_JC_TYPE+ gcType_string.substring(2).toUpperCase());
		storeMap.put("jcType", TransportConstants.PREFIX_JC_TYPE+ gcType_string.substring(2).toUpperCase());// 方法7 获得车型

		for (String resultKey : storeMap.keySet()) {
			logger.debug("functionNO7.....................");
			logger.debug(resultKey + "===" + storeMap.get(resultKey));
		}
		return storeMap;
	}

	/**
	 * 8 getTemperature
	 * 处理温度  一个字节  -128 ~ 127
	 * @param hexArray
	 * @param gkCode
	 * @return
	 */
	public static Map<String, String> functionNO8(String[] hexArray,
			String gkCode) {
		Map<String, String> storeMap = new HashMap();

		String hexData = "";
		for (String string : hexArray) {
			hexData += "" + string;
		}
		// 16进制转2进制,最高位为零时省去
		String binData = Integer.toBinaryString(Integer.valueOf(hexData, 16));
		logger.debug(binData);
		// 82 --- 130 --- 10000010
		//88 -120
		//80 -128
		//83 -125
		//12 18
		String result = "";
		// 温度为 -128 ~ 127 即 1111 1111 ~ 0111 1111
		if (binData.length() == 8) {
			// result = Integer.valueOf(binData, 2).toString();
			StringBuilder sb = new StringBuilder();
			
			for (int x = 1; x < binData.length(); x++) {
				if ("0".equals(String.valueOf(binData.charAt(x)))) {
					sb.append("1");
				} else if ("1".equals(String.valueOf(binData.charAt(x)))) {
					sb.append("0");
				}
			}
			//System.out.println(sb.toString());
			int antiCode = Integer.valueOf(sb.toString(), 2);// 反码
			//System.out.println(antiCode + 1);
			result = "-" + (antiCode + 1);//补码
			//System.out.println(result);
			storeMap.put(gkCode, result);
		} else {
			//正数的原反补都是自己
			result = Integer.valueOf(binData, 2).toString();
			storeMap.put(gkCode, result);
		}

		for (String resultKey : storeMap.keySet()) {
			logger.debug("functionNO8.....................");
			logger.debug(resultKey + "===" + storeMap.get(resultKey));
		}
		return storeMap;
	}

	/**
	 * 9  获取车号 拼ascii码  5个字节
	 * @param hexArray
	 * @param gkCode
	 * @return
	 */
		public static Map<String, String> functionNO9(String[] hexArray,
				String gkCode) {
			
			Map<String, String> storeMap = new HashMap();
			
			String asciiValue="";
			
			for (String string : hexArray) {
				if(0==(int)Integer.valueOf(string,16)){
					asciiValue+="";
				}else{
					asciiValue+=(char)(int)Integer.valueOf(string,16);
				}
			}
			//两次存入jcNo，后者会覆盖前者
			//System.err.println(asciiValue);
			if ("".equals(asciiValue)) {
				storeMap.put(gkCode, "0");
			} else {
				storeMap.put(gkCode, asciiValue);
				storeMap.put("jcNo", asciiValue);// 方法6 获得车号
			}

			for (String resultKey : storeMap.keySet()) {
				logger.debug("functionNO9.....................");
				logger.debug(resultKey + "===" + storeMap.get(resultKey));
			}
			return storeMap;

		}

		/**
		 * 10 getJCIDType   // 获得车型   正向
		 *  正向拼接
		 * @param hexArray
		 * @param gkCode
		 * @return
		 */
		public static Map<String, String> functionNO10(String[] hexArray,
				String gkCode) {
			Map<String, String> storeMap = new HashMap();
			String gcType_string = "";
			//小端存储，直接拼接
			
			 for (String string : hexArray) {
				 gcType_string += string.toUpperCase();
				}
		
			//大端存储，反向拼接
		/*	for(int i=hexArray.length-1;i>=0;i--){
				gcType_string +=hexArray[i].toUpperCase();
			}*/
			System.out.println(gcType_string);
			storeMap.put(gkCode,TransportConstants.PREFIX_JC_TYPE+ gcType_string.substring(2).toUpperCase());
			storeMap.put("jcType", TransportConstants.PREFIX_JC_TYPE+ gcType_string.substring(2).toUpperCase());// 方法7 获得车型

			for (String resultKey : storeMap.keySet()) {
				logger.debug("functionNO10.....................");
				logger.debug(resultKey + "===" + storeMap.get(resultKey));
			}
			return storeMap;
		}

		// 11  获取车号  14 B9 == 5305    正向
			public static Map<String, String> functionNO11(String[] hexArray,
					String gkCode) {
				
				Map<String, String> storeMap = new HashMap();
				
				//小端存储，直接拼接
				String gcNo_String = "";
				for (String string : hexArray) {
					gcNo_String += string;
				}

				//大端存储，反向拼接
			/*	for(int i=hexArray.length-1;i>=0;i--){
					gcNo_String += hexArray[i].toUpperCase();
				}
				*/
				//车号转换为十进制数据，不满4位，左边补0
				String gcNo = "" + (int) Integer.valueOf(gcNo_String, 16);
				for (int i = 0; gcNo.length() < 4; i++) {
					gcNo = "0" + gcNo;
				}
		//判断，如果解析后
//				System.err.println(gcNo);
				if ("".equals(gcNo)) {
					storeMap.put(gkCode, "0");
					storeMap.put("jcNo", gcNo);// 方法6 获得车号
				} else {
					storeMap.put("jcNo", gcNo);// 方法6 获得车号
					storeMap.put(gkCode, gcNo);
				}

				for (String resultKey : storeMap.keySet()) {
					logger.debug("functionNO11.....................");
					logger.debug(resultKey + "===" + storeMap.get(resultKey));
				}
				return storeMap;

			}
		
		
			// 12 directSave 获得机车工况（直接存储）
			//惰转工况：50；牵引：52；电阻制动：54；自负荷：58
			// ff --->ff 直接存储 
			public static Map<String, String> functionNO12(String[] hexArray,
					String gkCode) {
				Map<String, String> storeMap = new HashMap();
				//顺序拼接
				String mapValue = "";
				for (String string : hexArray) {
					mapValue += string;
				}
				if("0050".equals(mapValue)){
					storeMap.put(gkCode, "惰转工况");
				}else if("0052".equals(mapValue)){
					storeMap.put(gkCode, "牵引");
				}else if("0054".equals(mapValue)){
					storeMap.put(gkCode, "电阻制动");
				}else if("0058".equals(mapValue)){
					storeMap.put(gkCode, "自负荷");
				}else{
					storeMap.put(gkCode,mapValue);
				}

				for (String resultKey : storeMap.keySet()) {
					logger.debug("functionNO12.....................");
					logger.debug(resultKey + "===" + storeMap.get(resultKey));
				}
				return storeMap;

			}
			// 13 	获得协议版本（字符串转十进制数然后除以10）
			public static Map<String, String> functionNO13(String[] hexArray,
					String gkCode) {
				Map<String, String> storeMap = new HashMap();

				String hexValue = "";
				for (String string : hexArray) {
					hexValue += string;
				}
				if (0 == Integer.valueOf(hexValue, 16)) {
					hexValue += "0";
				}
				storeMap.put(gkCode, String.valueOf(Integer.valueOf(hexValue, 16)/10));
			
				for (String resultKey : storeMap.keySet()) {
					logger.debug("functionNO13.....................");
					logger.debug(resultKey + "===" + storeMap.get(resultKey));
				}
				return storeMap;
				
			}

			/**
			 * 14 获得AB车标识（直接存储）
			 * 非内重联： FF，		
			 * 内重联 A 车： 01，
			 * 内重联 B 车： 02
			 * @param hexArray
			 * @param gkCode
			 * @return
			 */
			public static Map<String, String> functionNO14(String[] hexArray,
					String gkCode) {
				Map<String, String> storeMap = new HashMap();
				//顺序拼接
				String mapValue = "";
				for (String string : hexArray) {
					mapValue += string;
				}
				if("FF".equals(mapValue)){
					storeMap.put(gkCode, "非内重联");
				}else if("01".equals(mapValue)){
					storeMap.put(gkCode, "内重联 A车");
				}else if("02".equals(mapValue)){
					storeMap.put(gkCode, "内重联 B车");
				}else{
					storeMap.put(gkCode, mapValue);
				}
				
				for (String resultKey : storeMap.keySet()) {
					logger.debug("functionNO14.....................");
					logger.debug(resultKey + "===" + storeMap.get(resultKey));
				}
				return storeMap;
				
			}
			/**
			 * 15 获得ip：16进制转成10进制，再转成ip
			 * @param hexArray
			 * @param gkCode
			 * @return
			 */
			public static Map<String, String> functionNO15(String[] hexArray,
					String gkCode) {
				Map<String, String> storeMap = new HashMap();

				String ip ="";
				for (String string : hexArray) {
					ip+=Integer.valueOf(string, 16)+".";
				}
				storeMap.put(gkCode, ip.substring(0, ip.length()-1));
			
				for (String resultKey : storeMap.keySet()) {
					logger.debug("functionNO15.....................");
					logger.debug(resultKey + "===" + storeMap.get(resultKey));
				}
				return storeMap;
			}
			
			/**
			 * 16 获得网络状态   心跳值    16进制转2进制，取前两位
			 * 3G：01；
			 * WIFI：10；
			 * 卫星：11；
			 * 状态不明：其他
			 * @param hexArray
			 * @param gkCode
			 * @return
			 */
			public static Map<String, String> functionNO16(String[] hexArray,
					String gkCode) {
				Map<String, String> storeMap = new HashMap();

				String hexStr = "";
				for (String string : hexArray) {
					hexStr += string;
				}
				//logger.debug("===================>" +hexStr);
				String midValue =Integer.toBinaryString(Integer.parseInt(hexStr, 16)).toString();
				if(8!= midValue.length()){
					
				}
				
				for(int i =0; i<8-midValue.length();i++){
					midValue="0"+midValue;
				}
				
				String binValue=midValue.substring(0, 2);
				
				if("01".equals(binValue)){
					storeMap.put(gkCode, "3G");
				}else if("10".equals(binValue)){
					storeMap.put(gkCode, "WIFI");
				}else if("11".equals(binValue)){
					storeMap.put(gkCode, "卫星");
				}else{
					storeMap.put(gkCode,"状态不明");
				}

				for (String resultKey : storeMap.keySet()) {
					logger.debug("functionNO16.....................");
					logger.debug(resultKey + "===" + storeMap.get(resultKey));
				}
				return storeMap;
			}
			
			
			
			
	// ============================================test

	// 把接受到的字符串转化为0x23（16进制字符串形式）的字符串数组==加0x
	public static String[] getHexArray(String originalString) {

		String[] cStringArray = originalString.split(" ");

		StringBuffer sb = new StringBuffer("");
		for (int x = 0; x < cStringArray.length; x++) {
			if (x == cStringArray.length - 1) {
				sb.append("0x").append(cStringArray[x]);
			} else {
				sb.append("0x").append(cStringArray[x]).append(" ");
			}
		}
		String[] hexArray = sb.toString().split(" ");

		// 打印十六进制的字符串数组
		for (String string : hexArray) {
			System.out.print("\"" + string + "\", ");
		}
		return hexArray;
	}

	
	/**
	 * 17 getTemperature
	 * 处理温度  一个字节  -128 ~ 127
	 * -126时，保存为“异常温度”，0保存为“-”
	 * @param hexArray
	 * @param gkCode
	 * @return
	 */
	public static Map<String, String> functionNO17(String[] hexArray,
			String gkCode) {
		Map<String, String> storeMap = new HashMap();

		String hexData = "";
		for (String string : hexArray) {
			hexData += "" + string;
		}
		// 16进制转2进制,最高位为零时省去
		String binData = Integer.toBinaryString(Integer.valueOf(hexData, 16));
	
		String result = "";
		// 温度为 -128 ~ 127 即 1111 1111 ~ 0111 1111
		if (binData.length() == 8) {//8位，最高位符号位为1
			// result = Integer.valueOf(binData, 2).toString();
			StringBuilder sb = new StringBuilder();
			
			for (int x = 1; x < binData.length(); x++) {
				if ("0".equals(String.valueOf(binData.charAt(x)))) {
					sb.append("1");
				} else if ("1".equals(String.valueOf(binData.charAt(x)))) {
					sb.append("0");
				}
			}
			int antiCode = Integer.valueOf(sb.toString(), 2);// 反码
			result = "-" + (antiCode + 1);//补码
			if("-126".equals(result)){
				storeMap.put(gkCode, "异常温度");
			}else{
				storeMap.put(gkCode, result);
			}
		} else {
			//正数的原反补都是自己
			result = Integer.valueOf(binData, 2).toString();
			if("0".equals(result)){				
				storeMap.put(gkCode, "-");
			}else{
			    storeMap.put(gkCode, result);
			}
		}
		for (String resultKey : storeMap.keySet()) {
			logger.debug("functionNO17.....................");
			logger.debug(resultKey + "===" + storeMap.get(resultKey));
		}
		return storeMap;
	}
	
	/**
	 *  18 StringToInt  字符串直接换算成十进制数
	 *  0:无报警
        1:有报警
	 * @param hexArray
	 * @param gkCode
	 * @return
	 */
	public static Map<String, String> functionNO18(String[] hexArray,
			String gkCode) {
		Map<String, String> storeMap = new HashMap();

		String hexValue = "";
		for (String string : hexArray) {
			hexValue += string;
		}
		if (0 == Integer.valueOf(hexValue, 16)) {
			storeMap.put(gkCode,"无报警");
		}else if(1 == Integer.valueOf(hexValue, 16)){
			storeMap.put(gkCode, "有报警");
		}else{
			storeMap.put(gkCode, "报警数据异常");
		}
		for (String resultKey : storeMap.keySet()) {
			logger.debug("functionNO18.....................");
			logger.debug(resultKey + "===" + storeMap.get(resultKey));
		}
		return storeMap;
	}
	
	/**
	 *  19 StringToInt  字符串直接换算成十进制数
	 *   0：-
	 *   7：温升报警
		 8：超温报警
		 9：轴承外部1级报警
		10：齿轮外部1级报警
		11：踏面外部1级报警
		12：轴承外部2级报警
		13：齿轮外部2级报警
		14：踏面外部2级报警
	 * @param hexArray
	 * @param gkCode
	 * @return
	 */
	public static Map<String, String> functionNO19(String[] hexArray,
			String gkCode) {
		Map<String, String> storeMap = new HashMap();

		String hexValue = "";
		for (String string : hexArray) {
			hexValue += string;
		}
		
		if (0 == Integer.valueOf(hexValue, 16)) {
			storeMap.put(gkCode,"-");
		}else if(7 == Integer.valueOf(hexValue, 16)){
			storeMap.put(gkCode, "7：温升报警");
		}else if(8 == Integer.valueOf(hexValue, 16)){
			storeMap.put(gkCode, "8：超温报警");
		}else if(9 == Integer.valueOf(hexValue, 16)){
			storeMap.put(gkCode, "9：轴承外部1级报警");
		}else if(10 == Integer.valueOf(hexValue, 16)){
			storeMap.put(gkCode, "10：齿轮外部1级报警");
		}else if(11 == Integer.valueOf(hexValue, 16)){
			storeMap.put(gkCode, "11：踏面外部1级报警");
		}else if(12 == Integer.valueOf(hexValue, 16)){
			storeMap.put(gkCode, "12：轴承外部2级报警");
		}else if(13 == Integer.valueOf(hexValue, 16)){
			storeMap.put(gkCode, "13：齿轮外部2级报警");
		}else if(14 == Integer.valueOf(hexValue, 16)){
			storeMap.put(gkCode, "14：踏面外部2级报警");
		}else{
			storeMap.put(gkCode, "报警数据异常");
		}
		
		for (String resultKey : storeMap.keySet()) {
			logger.debug("functionNO19.....................");
			logger.debug(resultKey + "===" + storeMap.get(resultKey));
		}
		return storeMap;
	}
	/**
	 * 20 StringArrayToDateTime   走行步报警时间
	 *  58 36 ff 28 -->1479999272
	 *  2016-11-24 14:54:32 是英国格林尼治时间，比北京时间晚8小时
	 *  2016-11-24 22:54:32 北京时间
	 * @param hexArray
	 * @param gkCode
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> functionNO20(String[] hexArray,
			String gkCode) throws Exception {
		
		Map<String, String> storeMap = new HashMap();
		
		StringBuilder sb = new StringBuilder();
		for (String string : hexArray) {
			sb.append(string);
		}
		String unixStampStr = sb.toString();
	
		// 北京所在时区的世界协调时间
		long unixTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 08:00:00").getTime();
		// 接收数据的Unix时间戳
	    //long unixStamp = new Date(Integer.valueOf(unixStampStr, 16)).getTime() * 1000;
		long unixStamp = new Date(Long.parseLong(unixStampStr, 16)).getTime() * 1000;
		// 两者确定北京时间的毫秒值，然后转化为北京时间字符串
		String beiJingTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(unixTime + unixStamp));
		
		if("1970-01-01 08:00:00".equals(beiJingTime)){
			storeMap.put(gkCode, "-");
		}else{
			storeMap.put(gkCode, beiJingTime);
		}	
		for (String resultKey : storeMap.keySet()) {
			logger.debug("functionNO20.....................");
			logger.debug(resultKey + "===" + storeMap.get(resultKey));
		}
     	return storeMap;
	}
	
	
	/**
	 * 21  直接存储 00-->0 FF --> 故障
	 * @param hexArray
	 * @param gkCode
	 * @return
	 */
	public static Map<String, String> functionNO21(String[] hexArray,
			String gkCode) {
		Map<String, String> storeMap = new HashMap();
		
		String mapValue = "";
		for (String string : hexArray) {
			mapValue += string;
		}
		
		if("00".equals(mapValue)){
			storeMap.put(gkCode, "0");			
		}else{			
			storeMap.put(gkCode, "故障");
		}

		for (String resultKey : storeMap.keySet()) {
			logger.debug("functionNO21.....................");
			logger.debug(resultKey + "===" + storeMap.get(resultKey));
		}
		return storeMap;

	}
	
	/**
	 * 22 获取车号 
	 * 拼ascii码  3个字节  把ascii码结果string转10进制，再转16进制（心跳数据）
	 * @param hexArray
	 * @param gkCode
	 * @return
	 */
		public static Map<String, String> functionNO22(String[] hexArray,
				String gkCode) {
			
			Map<String, String> storeMap = new HashMap();
			
			String asciiValue="";
			
			for (String string : hexArray) {
				if(0==(int)Integer.valueOf(string,16)){
					asciiValue+="";
				}else{
					asciiValue+=(char)(int)Integer.valueOf(string,16);
				}
			}
			String gcType_string = null;
			if ("".equals(asciiValue)) {
				storeMap.put(gkCode, "0");
			} else {
				
				gcType_string = Integer.toHexString(Integer.parseInt(asciiValue));
				storeMap.put(gkCode,TransportConstants.PREFIX_JC_TYPE+ gcType_string.toUpperCase());
				storeMap.put("jcType", TransportConstants.PREFIX_JC_TYPE+ gcType_string.toUpperCase());// 方法22获得车型

			}
			for (String resultKey : storeMap.keySet()) {
				logger.debug("functionNO22.....................");
				logger.debug(resultKey + "===" + storeMap.get(resultKey));
			}
			return storeMap;

		}
}
	


