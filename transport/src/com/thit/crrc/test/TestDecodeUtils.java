package com.thit.crrc.test;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.thit.crrc.bean.Employee;
import com.thit.crrc.utils.DecodeUtils;

/**
 * @filename: TestDecodeUtils.java
 * @description: 
 *
 * @author: sf
 * @time: 2017年1月16日 下午4:37:57
 * @version:1.0
 **/
public class TestDecodeUtils {
	
	@Test
	public void testDecodeUtils() throws Exception{
		
		Employee employee = new Employee();
		
		DecodeUtils.setProperty(employee, "mgr", "123");
		DecodeUtils.setProperty(employee, "job", "789");
		DecodeUtils.setProperty(employee, "deptno", 88);
		
		System.out.println(employee.getMgr());
		
		System.out.println(employee);
	}

	
	@Test
	public void testReflection() throws Exception{
		
		Employee emp = new Employee();
		
		PropertyDescriptor property = new PropertyDescriptor("mgr",Employee.class);
		Method setMethod = property.getWriteMethod();//获取set方法
		Method getMethod = property.getReadMethod();//获取get方法
		
		//public void com.thit.crrc.bean.Employee.setMgr(java.lang.String)
		System.out.println(setMethod.toString());
		
		//public java.lang.String com.thit.crrc.bean.Employee.getMgr()
		System.out.println(getMethod.toString());
		
		setMethod.invoke(emp, "123");
		
		String mgr = (String) getMethod.invoke(emp);
		System.out.println(mgr);//123
		System.out.println(emp.getMgr());
		
	}
	

	// ==============================================================
	// test single function
	public  void func0() throws Exception {

		int x = Integer.valueOf("97", 16);
		String y = Integer.toBinaryString(x);

		System.out.println(y);

		String[] binArr = y.split("");
		for (String string : binArr) {
			System.out.println(string);
		}
		System.out.println(getResult());

		System.out.println("--------------------------");

		System.out.println(new Date().getTime());
		String yy = Integer.toHexString(Integer.valueOf((int) new Date()
				.getTime()));
		System.out.println(yy);
		System.out.println("======================");

		System.out.println("===========>>>>>>");
		System.out.println(Integer.valueOf("00", 16));
		System.out.println(Integer.toBinaryString(Integer.valueOf("01", 16)));
		System.out.println(Integer.toBinaryString(Integer.valueOf("97", 16)));
		System.out.println(Integer.toBinaryString(Integer.valueOf("22", 16)));

		System.out.println((char) (int) Integer.valueOf("4e", 16));
	}

	public static String getResult() throws Exception {
		// String result = String.valueOf(Integer.parseInt("0x82"));
		String result = Integer.valueOf("82", 16).toString();// 16进制转为10进制

		String[] hexArr = { "58", "36", "ff", "28" };
		
		StringBuilder sb = new StringBuilder();
		for (String string : hexArr) {
			sb.append(string);
		}
		System.out.println(sb.toString());
		int y = Integer.valueOf(sb.toString(), 16);
		System.out.println(y);
		Date date = new Date(y);

		long sec = new SimpleDateFormat("yyyy").parse("2000").getTime();
		System.out.println("2000年是：" + sec);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(date.getTime());
		long sec_now = sec + date.getTime();

		System.out.println("=============>");
		System.out.println(sec_now);

		System.out.println(sdf.format(new Date(y)));

		return result;

	}

	@Test
	public void getASCII() {

		System.out.println((char) (int) Integer.valueOf("50", 16));
		System.out.println(Integer.valueOf('a'));
		System.out.println((char) (int) Integer.valueOf("00", 16));
		System.out.println((char) (int) Integer.valueOf("b1", 16));

		System.out.println((char) (int) Integer.valueOf("00", 16));
		System.out.println((char) (int) Integer.valueOf("4e", 16));
		System.out.println("====================");
		System.out.println((char) (int) Integer.valueOf("00", 16));
		System.out.println("====================>>");
		if ("" == ((char) (int) Integer.valueOf("00", 16) + "")) {
			System.out.print("true");
		}
		System.out.println("====================");
		if (0 == (int) Integer.valueOf("00", 16)) {
			System.out.println("true");
		}
		// System.out.println((char)97);
		System.out.println("====================");
		System.out.println((char) (int) Integer.valueOf("2d", 16));
		System.out.println("====================");
		String hexString = "30 30 30 31 00";
		String[] hexArr = hexString.split(" ");
		String asciiValue = "";
		for (String s : hexArr) {
			asciiValue += (char) (int) Integer.valueOf(s, 16);
			// asciiValue +=(char)(int)Integer.valueOf(string,16);
			// System.out.print((char)(int)Integer.valueOf(s,16));
		}
		System.out.print(asciiValue);
		System.out.println("---------------------------");
		String hexString1 = "00 50";
		String[] hexArr1 = hexString1.split(" ");
		String asciiValue1 = "";
		for (String ss : hexArr1) {
			asciiValue1 += (char) (int) Integer.valueOf(ss, 16);

		}
		System.out.print(asciiValue1);
		System.out.println((char) (int) Integer.valueOf("50", 16));
	}

	
	 //42f25811 GPS经度 //121.172005 <--121.17200469970703125
	// BFAF5C29   -1.37
	@Test//经纬度测试
	public void getFloat() {
		// 42f25811 GPS经度
		
		System.out.println("-------42f25811----121.172005-----------");
		System.out.println(Float.intBitsToFloat(Integer.valueOf("42f25811", 16)));// 121.172005
		System.out.println(Float.intBitsToFloat((int) Long.parseLong("42f25811",16)));//121.172005

		System.out.println("------BFAF5C29---  -1.37 --------------");
		System.out.println(Float.intBitsToFloat((int) Long.parseLong("BFAF5C29",16)));//-1.37
		System.out.println("------4213A27A -----36.90867---------");
		System.out.println("------BFAF35EE ----- -1.3688333---------");
		System.out.println(Float.intBitsToFloat((int) Long.parseLong("4213A27A",16)));//36.90867
		System.out.println(Float.intBitsToFloat((int) Long.parseLong("BFAF35EE",16)));//-1.3688333


		
		
		
	}

	@Test
	public void UnixTime() throws Exception {
    //C92590A2
	//String[] hexArr = { "58", "36", "ff", "28" };
		//5836FF2A
		//5977ED03
		String[] hexArr = { "C9", "25", "90", "A2" };//2076-12-09 03:44:34
		// 584221b4
		StringBuilder sb = new StringBuilder();
		for (String string : hexArr) {
			sb.append(string);
		}
		System.out.println(sb.toString());

		long unixTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(
				"01/01/1970 08:00:00").getTime();
		long parse = new Date(Long.parseLong("C92590A2",16)).getTime();

		System.out.println(new Date(Long.parseLong("C92590A2",16)).getTime());

		String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date(parse * 1000 + unixTime));
		System.out.println(datetime);//2016-12-03 09:36:52
		System.out.println("===============");

		long UTCTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
				"1970-01-01 08:00:00").getTime();
		System.out.println("584221b4");
		System.out.println(Integer.parseInt("584221b4", 16));
		long unixStamp = new Date(Integer.valueOf("584221b4", 16)).getTime();
		String beiJingTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date(unixTime + unixStamp * 1000));
		System.out.println(beiJingTime);
		
		//59 40 DD E5 
		//2017-06-14 14:55:33
		//5940DDE5

		//1497423333000
		System.out.println(System.currentTimeMillis());
		long end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-06-17 12:33:00").getTime();
		long start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 08:00:00").getTime();
		long exp = (end -start)/1000;
		System.out.println(Integer.toHexString(Integer.valueOf(String.valueOf(exp))));//5944b0fc
		
		long unixStamp2 = new Date(Integer.valueOf("584221b4", 16)).getTime();
		System.out.println(Integer.valueOf("584221b4", 16));//1480729012
		System.out.println(unixStamp2);//1480729012
		
		System.out.println(Integer.valueOf("5944b0fc", 16));
		System.out.println(Long.parseLong("5944b0fc", 16));
		
		

	}

	@Test
	public void getBooleanArr() {
		int x6 = Integer.valueOf("97", 16);
		String y = Integer.toBinaryString(x6);
		System.out.println(y);

		int x1 = Integer.valueOf("10", 16);
		String y1 = Integer.toBinaryString(x1);
		System.out.println(y1);

		int x2 = Integer.valueOf("c0", 16);
		String y2 = Integer.toBinaryString(x2);
		System.out.println(y2);

		String binString2 = Integer.toBinaryString(Integer.valueOf("10", 16));
		System.out.println(binString2.length());

		String binString = Integer.toBinaryString(Integer.valueOf("c0", 16));
		String prefix = "";
		for (int x = 0; x < 8 - binString.length(); x++) {
			prefix += "0";
		}
		binString = prefix + binString;
		System.out.println(binString);

		String gkCode = "ZLZT_GK049,ZLZT_GK050,ZLZT_GK051,ZLZT_GK052,ZLZT_GK053,ZLZT_GK054,ZLZT_GK055,ZLZT_GK056";
		String[] gkMap = gkCode.split(",");

		for (int i = 0; i < binString.length(); i++) {
			System.out.println(gkMap[i] + "--" + binString.charAt(i));
		}
		// ZLZT_GK049--1
		// ZLZT_GK050--1
		// ZLZT_GK051--0
		// ZLZT_GK052--0
		// ZLZT_GK053--0
		// ZLZT_GK054--0
		// ZLZT_GK055--0
		// ZLZT_GK056--0

	}

	@Test
	public void testTrue() {
		if ("".equals("")) {
			System.out.println("true");
		}
		System.out.println(Integer.valueOf("000009a7", 16));
		System.out.println(Integer.valueOf("64", 16));

		System.out.println((char) (int) Integer.valueOf("ff", 16));// ÿ
	}
	@Test
	//拼ascii码
	public void testJcNo() {
		
		//String hexString = "30 30 30 31 00";
		String hexString = "31 37 37";
		//String hexString = "31 39 33";

		//String hexString = "30 30 30 31 00";
		
		String[] hexArr = hexString.split(" ");
		String asciiValue = "";
	
		//System.out.println(Integer.valueOf("3030303100", 16));
		for (String string : hexArr) {
			if(0==(int)Integer.valueOf(string,16)){
				asciiValue +="";
			}else{
				asciiValue +=(char)(int)Integer.valueOf(string,16);
			}
		}
		System.err.println(asciiValue+"\r\n");//0001
		//System.out.println(Integer.toHexString(Integer.parseInt("")));//exception
	System.out.println(Integer.toHexString(Integer.parseInt(asciiValue)));
		}
	
	@Test
	//车型车号
	public void testBigSmallSave(){
		//C100B914
		String[] hexString_gcType={"C1","00"};
		String asciiValue="";
		
		for(int i = hexString_gcType.length-1;i>=0;i--){
			System.out.println(i);
			System.out.println(hexString_gcType.length);
			System.out.println(hexString_gcType[i]);
			asciiValue += hexString_gcType[i];
		}
		System.out.println(asciiValue);//00C1
		//车号测试
		String[] hexString_gcNo={"B7","14"};
		//String[] hexString_gcNo={"14","B7"};
	
		String ori_String="";
		
		for(int i = hexString_gcType.length-1;i>=0;i--){
			System.out.println(hexString_gcNo[i]);
			ori_String += hexString_gcNo[i];
		}
		System.out.println(ori_String);
		String asciiValue2 = "" + (int) Integer.valueOf(ori_String, 16);
		for (int i = 0; asciiValue.length() < 4; i++) {
			asciiValue2 = "0" + asciiValue;
		}
		System.err.println(asciiValue2);//5305
		
	}
	
	
	//ip地址
	@Test
	public void fun15(){
		Map<String, String> storeMap = new HashMap();

		String [] hexArray={"C0","A8","09","9A"};
		String ip = "";
		for (String string : hexArray) {
			System.out.println(Integer.valueOf(string, 16));
			ip+=Integer.valueOf(string, 16)+".";
		}
			System.out.println(ip.substring(0, ip.length()-1));
	
		
	}
	
	//二进制转十进制
	@Test
	public void fun16(){
		Map<String, String> storeMap = new HashMap();
		
		String [] hexArray={"01","10","11"};
		String binValue = "";
		for (String string : hexArray) {
			//System.out.println(Integer.valueOf(string, 16));
			binValue+=Integer.valueOf(string, 2)+".";
		}
		System.out.println(binValue.substring(0, binValue.length()-1));
		
		
	}
	
	@Test
	public void testXTValue() throws Exception{

		String dayStr = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
		System.out.println(dayStr);
		System.out.println(dayStr.substring(0,8));
		
	    String stdDateString= new SimpleDateFormat("yyyyMMdd").format(new Date());
		System.out.println("stdDateString==> "+stdDateString);
		
		
		Date yearMonthDayHourMinSec = new SimpleDateFormat("yyyyMMdd_hhmmss").parse(stdDateString+"_000000");
		System.out.println("yearMonthDayHourMinSec==> "+yearMonthDayHourMinSec);
	
		System.out.println(new SimpleDateFormat("yyyyMMdd_hhmmss").format(yearMonthDayHourMinSec));//20170901_120000
		System.out.println(new Date().compareTo(yearMonthDayHourMinSec));
		
		Date d1 = new SimpleDateFormat("yyyyMMdd_hhmmss").parse("20170901_102312");
		System.out.println(d1.compareTo(yearMonthDayHourMinSec));
		Date d2 = new SimpleDateFormat("yyyyMMdd_hhmmss").parse("20170901_000001");
		System.out.println(d2.compareTo(yearMonthDayHourMinSec));
	
		
	}
	@Test
	public void jwd(){
		System.out.println(String.valueOf(Float.intBitsToFloat((int) Long.parseLong("00000000", 16))));
	}
	
	

}
