package com.api.tools;

public class RandomNoTools {
	/** 
	* 方法说明 :返回6位随机整数
	* @author  joker 
	* 创建时间：2015-02-27
	* 网址：http://www.ihuyi.com/
	* <p>@param SchoolBaseInfo</p>
	*/
	public static int getRandomNo(){
		int mobile_code = (int)((Math.random()*9+1)*100000);
		return mobile_code;
	}
	
	/** 
	* 方法说明 :返回数组中随机一位
	* @author  joker 
	* 创建时间：2015-02-27
	* 网址：http://www.ihuyi.com/
	* <p>@param SchoolBaseInfo</p>
	*/
	public static String getRandomArrayValue(String[] args){
		String arrayValue = null;
		int index;
		index = (int)(Math.random() * args.length);
		arrayValue = args[index];
		return arrayValue; 
	}
	
	/** 
	* 方法说明 :返回1位随机整数
	* @author  joker 
	* 创建时间：2015-02-27
	*/
	public static int getRandomNo1(){
		int mobile_code = (int)((Math.random()*9+1)*1);
		return mobile_code;
	}
	
	/** 
	* 方法说明 :返回2位随机整数
	* @author  joker 
	* 创建时间：2015-02-27
	*/
	public static int getRandomNo2(){
		int mobile_code = (int)((Math.random()*9+1)*10);
		return mobile_code;
	}
	
}
