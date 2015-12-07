package com.api.tools;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	
	public static String toPath(String date){
		StringBuffer result = new StringBuffer();
		result.append(File.separator);
		result.append(date.substring(0,4));
		result.append(File.separator);
		result.append(date.substring(4,6));
		result.append(File.separator);
		result.append(date.substring(6,8));
		result.append(File.separator);
		result.append(date.substring(8));
		result.append(File.separator);
		return result.toString();
	}
	
	public static boolean isNullorBlank(final String str) {
		boolean isNorB = false;
		if (null == str || 0 >= str.length() || str.equals("null")) {
			isNorB = true;
		}
		return isNorB;
	} 

	public static List<Integer> stringArrayToIntegerList(String[] source){
		List<Integer> list = new ArrayList<Integer>();
		if(source != null && source.length > 0){
			for(String s : source){
				list.add(new Integer(s));
			}
		}
		return list;
	}
	
	/**
	 * 判断字符串是否为数字
	 */
	public static boolean isNumeric(String str){
	     Pattern pattern = Pattern.compile("[0-9]*");
	     return pattern.matcher(str).matches();   
	}
	/**
	 * 判断字符串是否为数字(整数或者小数)
	 */
	public static boolean isNumericOrDouble(String str){
	     Pattern pattern = Pattern.compile("[0-9]*|[0-9]*\\.[0-9]*");
	     return pattern.matcher(str).matches();   
	}
	
	/** 
	 * 方法说明 :过滤文本内的换行符
	 * @author  jokerPu 
	 * 创建时间：2015-01-29
	 * <p>@param null</p>
	 */
	public static String valEnter(String str){
		String replacedString = null;
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()){
			replacedString = str.replaceAll("\\r|\\r\\n|\\n|\\t", "");
			return replacedString;
		}
		return str;
	}
	
	/** 
	 * 方法说明 :小数转百分比
	 * @author  jokerPu 
	 * 创建时间：2015-01-29
	 * <p>@param null</p>
	 */
	public static String getDecimalToFraction(String value){
		double temp = Double.valueOf(value);
		String fraction = null;
		double count = 1 / temp;
		fraction = "1:" + String.valueOf(count).substring(0, String.valueOf(count).lastIndexOf(".") + 2);
		fraction = fraction.substring(0, fraction.lastIndexOf("."));
		return fraction;
	}
	
	/** 
	 * 方法说明 :字符串逗号分隔从新拼接
	 * @author  jokerPu 
	 * 创建时间：2015-01-29
	 * <p>@param null</p>
	 */
	public static String getStringSplit(String string){
		if(!StringUtils.isNullorBlank(string)){
			String[] strings = string.split(",");
			StringBuffer stringBuffer = new StringBuffer();
			for(int i = 0; i < strings.length; i++){
				if(i == 0){
					stringBuffer.append("'" + strings[i] + "'");
				}else {
					stringBuffer.append(", '" + strings[i] + "'");
				}
			}
			return stringBuffer.toString();
		}
		return null;
	}
	
	/**
	 * 
	 * <p>作者:jokerPu</p> 
	 * <p>功能描述:是否包含传入的字符串</p>
	 * <p>创建时间:2015-02-03 11:25:00</p>
	 * <p>@param str
	 * <p>@return</p>
	 * <p>修改:</p>
	 */
	public static boolean isIncludeText(String text, String includeText){
		return text.contains(includeText);
	}
	
	/**
	 * 
	 * <p>作者:jokerPu</p> 
	 * <p>功能描述:获取6位时间的年份</p>
	 * <p>创建时间:2015-02-03 11:25:00</p>
	 * <p>@param str
	 * <p>@return</p>
	 * <p>修改:</p>
	 */
	public static String getYear(String time){
		String year = time.substring(0, 4);
		return year;
	}
	
	/**
	 * 
	 * <p>作者:jokerPu</p> 
	 * <p>功能描述:获取6位时间的月份，各位去0</p>
	 * <p>创建时间:2015-02-03 11:25:00</p>
	 * <p>@param str
	 * <p>@return</p>
	 * <p>修改:</p>
	 */
	public static String getMonth(String time){
		String month = null;
		String temp = time.substring(4, 6);
		System.out.println(temp.substring(0, 1));
		if(temp.substring(0, 1).equals("0")){
			month = temp.substring(1);
		}else{
			month = temp;
		}
		return month;
	}
	
	public static void main(String[] args){
		System.out.println(StringUtils.getDecimalToFraction("2.1"));
	}
	
	
}