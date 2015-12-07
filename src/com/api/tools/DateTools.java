package com.api.tools;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * 类说明 :时间工具
 * @author  joker 
 * 创建时间：2015.11.26
 */
public class DateTools {
	
	/**
	* 方法说明  根据生日获取年龄      
	* @author joker
	* @param @param birthday_
	* @param @return        
	 */
	public static String getAgeByBirthday(String birthday_){
		if(!StringUtils.isNullorBlank(birthday_)){
			String birthday = null;
			long days = DateUtils.getDatesOfOneDayToNow(birthday_);
			//整年
			long old = days / 365;
			birthday = String.valueOf(old) + " 岁";
			//整月
			if(0 == old){
				old = days / 30;
				birthday = String.valueOf(old) + " 月";
			}
			//不足月
			if(0 == old){
				birthday = "0 岁";
			}
			return birthday;
		}
		return "0 岁";
	}
	
	/**
	* 方法说明  获取当前月份      
	* @author joker
	* @param @param currMonth
	* @param @return        
	 */
	public static String getCurrMonth(){
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		return String.valueOf(month);
	}
	
	/**
	* 方法说明  获取当前年份    
	* @author joker
	* @param @param currMonth
	* @param @return        
	 */
	public static String getCurrYear(){
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		return String.valueOf(year);
	}
	
	/**
	* 方法说明  获取日期对应周天    
	* @author joker
	* @param @param currMonth
	* @param @return        
	 */
	public static String getWeekOfDate(Date date){
		String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
		Calendar calendar = Calendar.getInstance();
		if(date != null)
			calendar.setTime(date);
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1; 
		if(w < 0)
			w = 0;
		return weekOfDays[w];
	}
	
	/**
	* 方法说明  字符串转日期 yyyy年mm月dd日    
	* @author joker
	* @param @param currMonth
	* @param @return        
	 */
	public static Date StringToDate(String time, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static void main(String[] args){
		System.out.println(DateTools.StringToDate("2016年5月7日", "yyyy年mm月dd日"));
	}
}
