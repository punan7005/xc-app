package com.api.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
public class DateUtils {

	private final static String[] MONTHS = { "Jan", "Feb", "Mar", "Apr", "May",
			"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	private final static DateFormat AFTER_SIX = new SimpleDateFormat(" yyyy");
	private final static DateFormat BEFORE_SIX = new SimpleDateFormat("HH:mm");
	private static Map<String,DateFormat> FormatterPool = new HashMap<String,DateFormat>();

	/**
	 * Get unix style date string.
	 */
	public static String getUnixDate(Date date) {
		long dateTime = date.getTime();
		if (dateTime < 0) {
			return "------------";
		}

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		String firstPart = MONTHS[cal.get(Calendar.MONTH)] + ' ';

		String dateStr = String.valueOf(cal.get(Calendar.DATE));
		if (dateStr.length() == 1) {
			dateStr = ' ' + dateStr;
		}
		firstPart += dateStr + ' ';

		long nowTime = System.currentTimeMillis();
		if (Math.abs(nowTime - dateTime) > 183L * 24L * 60L * 60L * 1000L) {
			return firstPart + AFTER_SIX.format(date);
		} else {
			return firstPart + BEFORE_SIX.format(date);
		}
	}

	/**
	 * Get the timezone specific string.
	 */
	public static String getString(Date dt, DateFormat df, TimeZone to) {
		df.setTimeZone(to);
		return df.format(dt);
	}

	/**
	 * Get the timezone specific calendar.
	 */
	public static Calendar getCalendar(Date dt, TimeZone to) {
		Calendar cal = Calendar.getInstance(to);
		cal.setTime(dt);
		return cal;
	}

	/**
	 * Get date object.
	 */
	public static Date getDate(String str, DateFormat df, TimeZone from)
			throws java.text.ParseException {
		df.setTimeZone(from);
		return df.parse(str);
	}

	/**
	 * Get date difference => d1 - d2.
	 */
	public static String getDifference(Date d1, Date d2) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d2);
		int year2 = calendar.get(Calendar.YEAR);
		int day2 = calendar.get(Calendar.DAY_OF_YEAR);
		int hour2 = calendar.get(Calendar.HOUR_OF_DAY);
		int min2 = calendar.get(Calendar.MINUTE);

		calendar.setTime(d1);
		int year1 = calendar.get(Calendar.YEAR);
		int day1 = calendar.get(Calendar.DAY_OF_YEAR);
		int hour1 = calendar.get(Calendar.HOUR_OF_DAY);
		int min1 = calendar.get(Calendar.MINUTE);

		int leftDays = (day1 - day2) + (year1 - year2) * 365;
		int leftHours = hour1 - hour2;
		int leftMins = min1 - min2;

		if (leftMins < 0) {
			leftMins += 60;
			--leftHours;
		}
		if (leftHours < 0) {
			leftHours += 24;
			--leftDays;
		}

		String interval = "";
		if (leftDays > 0) {
			interval = leftDays + " Days";
		} else if ((leftHours > 0) && (leftDays == 0)) {
			interval = leftHours + " Hours";
		} else if ((leftMins > 0) && (leftHours == 0) && (leftDays == 0)) {
			interval = leftMins + " Minutes";
		} else {
			interval = "";
		}
		return interval;
	}

	/**
	 * 取当前时间。
	 * <p>
	 * 默认格式为：yyyy-MM-dd HH:mm:ss。
	 * 
	 * @return 字符串表示的当前时间。
	 */
	public static String getDateTime() {
		return getDateTime("yyyy-MM-dd HH:mm:ss");
	}

	public static String getDateOtherTime() {
		return getDateTime("yyyy-MM-dd HH-mm-ss SSS");
	}

	/**
	 * 根据给定的格式取当前时间。
	 * <p>
	 * 如果给定的格式为空，则使用默认格式：yyyy-MM-dd HH:mm:ss。
	 * 
	 * @param pattern
	 *            指定格式
	 * @return 字符串表示的当前时间
	 */
	public static String getDateTime(String pattern) {
		if (null == pattern || "".equals(pattern)) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}		
		return getFormatter(pattern).format(Calendar.getInstance().getTime());
	} // end getDateTime()

	public static String fotmatDate1(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日 HH时mm分ss秒");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate2(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate3(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate4(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate5(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate6(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate7(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate8(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}
	
	public static String fotmatDate9(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		String strDate = formatter.format(myDate);
		return strDate;
	}
	//20100721  ---> 2010-07-21
	public static String fotmatDate10(int date) {
		String dateStr = String.valueOf(date);
		return fotmatDate4(getDate(dateStr));
	}
	
	public static String fotmatDate11(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		String strDate = formatter.format(myDate);
		return strDate;
	}
	//20100721   112030 ---> 2010-07-21 11:20:30
	public static String fotmatDate10(int date, int time) {
		String dateStr = String.valueOf(date);
		String timeStr = String.valueOf(time);
		int ch = 0;
		if((ch = 6 - timeStr.length()) != 0){
			for(int i = 0; i < ch;i++){
				timeStr = "0"+timeStr;
			}
		}
		return fotmatDate3(getDateAndTime(dateStr+" "+timeStr));
	}
	
	public static int getCurrentDate(){
		return Integer.parseInt(fotmatDate7(new Date()));
	}
	
	public static int getCurYMDate(){
		return Integer.parseInt(fotmatDate11(new Date()));
	}
	
	public static int getSpecialDate(String dateStr){// 2004-7-23
		long milltime = getDateLongTime(dateStr);
		return Integer.parseInt(fotmatDate7(new Date(milltime)));
	}
	
	public static int getCurrentTime(){
		return Integer.parseInt(fotmatDate9(new Date()));
	}
	
	public static long getLongTime() {
		Date aDate = new Date();
		long atime = aDate.getTime();
		// System.out.println(atime);
		return atime;
	}

	public static long getaLongTime() {
		return System.currentTimeMillis();
	}

	public static long getDateLongTime(int year, int month, int day) {
		Calendar myCalendar = Calendar.getInstance();
		myCalendar.set(year, month - 1, day);
		return myCalendar.getTime().getTime();
	}

	public static long getDateLongTime(String dateStr) { // 2004-7-23
		long value = 0;
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = myFormatter.parse(dateStr);
			value = date.getTime();
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return value;
		// Calendar myCalendar = Calendar.getInstance();
		// myCalendar.set(year, month - 1, day);
		// return myCalendar.getTime().getTime();
	}

	public static long getDateLongTimeStr(String dateStr) { // 2004-7-23
		// 18:40:40
		long value = 0;
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			java.util.Date date = myFormatter.parse(dateStr);
			value = date.getTime();
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return value;
	}
	
	public static Date getDate(String dateStr) { // 20040723
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
			java.util.Date date = myFormatter.parse(dateStr);
			return date;
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static Date getDateAndTime(String dateStr) { // 20040723 112033
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd HHmmss");
			java.util.Date date = myFormatter.parse(dateStr);
			return date;
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * <p>作者:lht</p>
	 * <p>功能描述:日期字符串转换成Date对象</p>
	 * <p>创建时间:2012-9-19下午4:04:45</p>
	 * <p>@param dateStr
	 * <p>@param pattern 
	 * <p>@return</p>
	 * <p>修改:</p>
	 */
	public static Date getDateFromString(String dateStr,String pattern) { // 20040723 112033
		try {
			java.util.Date date = getFormatter( pattern).parse(dateStr);
			return date;
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static long getDateLongTimeStr2(String dateStr) { // 2004-7-23
		// 18:40
		long value = 0;
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			java.util.Date date = myFormatter.parse(dateStr);
			value = date.getTime();
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return value;
	}

	/**
	 * 
	 * @param dateStr
	 *            String
	 * @param pattern
	 *            String "yyyy年MM月dd日 HH时mm分ss秒" or 2004-7-23 18:40 etc
	 * @return long
	 */
	public static long getCommonDateLongTimeStr(String dateStr, String pattern) { // 2004-7-23
		// 18:40
		long value = 0;
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat(pattern);
			java.util.Date date = myFormatter.parse(dateStr);
			value = date.getTime();
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return value;
	}

	// public static String fotmatDate1(Date myDate) {
	// SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日
	// HH时mm分ss秒");
	// String strDate = formatter.format(myDate);
	// return strDate;
	// }

	public static String getDateFormatStr(long val) {
		return fotmatDate1(new Date(val));
	}

	public static String getDateFormatStr2(long val) {
		return fotmatDate3(new Date(val));
	}

	public static String getDateFormatSimpleStr(long val) {
		return fotmatDate4(new Date(val));
	}

	public static String getDateFormatNow() {
		return fotmatDate4(new Date());
	}

	public static String getDateFormatNow7() {
		return fotmatDate7(new Date());
	}

	public static String getDateTimeStr(long val) {
		return fotmatDate8(new Date(val));
	}

	public static String getDateTimeStr2(long val) {
		return fotmatDate3(new Date(val));
	}

	public static String getDateTimeStrWithDate(long val) {
		return fotmatDate4(new Date(val));
	}

	/**
	 * 取不带年份的时间。
	 * 
	 * @param strTime
	 *            字符串格式的时间值
	 * @return 去除年份的时间
	 */
	public static String getTimeWithoutYear(final String strTime) {
		if (StringUtils.isNullorBlank(strTime) || 5 >= strTime.length()) {
			return strTime;
		}
		// todo:validat...
		return strTime.substring(5);
	} // end getTimeWithoutYear()

	public static String getTimeMonthsAgo(final int counts) {
		java.util.Date myDate = new java.util.Date();
		long myTime = (myDate.getTime() / 1000) - 60 * 60 * 24 * 30 * counts;
		myDate.setTime(myTime * 1000);
		String mDate = fotmatDate4(myDate); // this.getDateFormatSimpleStr()
		// formatter.format(myDate);
		return mDate;
	}

	public static String getTimeDaysAgo(final int counts) {
		java.util.Date myDate = new java.util.Date();
		long myTime = (myDate.getTime() / 1000) - 60 * 60 * 24 * counts;
		myDate.setTime(myTime * 1000);
		String mDate = fotmatDate4(myDate); // this.getDateFormatSimpleStr()
		// formatter.format(myDate);
		return mDate;
	}

	public static String getTimeDaysAfter(final int counts) {
		java.util.Date myDate = new java.util.Date();
		long myTime = (myDate.getTime() / 1000) + 60 * 60 * 24 * counts;
		myDate.setTime(myTime * 1000);
		String mDate = fotmatDate3(myDate); // this.getDateFormatSimpleStr()
		// formatter.format(myDate);
		return mDate;
	}

	public static long getTimeDaysAfterCount(final int counts) {
		java.util.Date myDate = new java.util.Date();
		long myTime = (myDate.getTime() / 1000) + 60 * 60 * 24 * counts;
		return myTime * 1000;
	}

	public static long getTimeDaysAfter(String dateStr, final int counts) { // dateStr
		// 2004-7-23
		java.util.Date myDate = new java.util.Date();

		long myTime = (getDateLongTime(dateStr) / 1000) + 60 * 60 * 24 * counts;
		myDate.setTime(myTime * 1000);
		// String mDate = fotmatDate3(myDate); //this.getDateFormatSimpleStr()
		// formatter.format(myDate);
		return myDate.getTime();
	}

	/**
	 * 判断指定时间是否是几天内
	 * 
	 * @param compareTime
	 * @param days
	 * @return
	 */
	public static boolean isTimeDaysRecent(long compareTime, int days) {
		long nowTime = getLongTime();
		long day = (nowTime - compareTime) / (24 * 60 * 60 * 1000);
		if (day > days) {
			return false;
		} else {
			return true;
		}
	}
	
	//指定时间，指定天数之后的日期与当前时间的差值
	public static Long getRangeOfDaysAddDate(String date,Integer days) throws Exception
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date myDate = formatter.parse(date);
		Long myTime = (myDate.getTime() / 1000) + 60 * 60 * 24 * days;
		myDate.setTime(myTime * 1000);
		String mDate = formatter.format(myDate);  //指定日期后的天数
		
		// 两个时间之间的天数
		java.util.Date nowdate = null;
		java.util.Date mydate = null;
		try {
			nowdate = formatter.parse(fotmatDate10(DateUtils.getCurrentDate()));  //当前时间
			mydate = formatter.parse(mDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long day = (mydate.getTime() - nowdate.getTime() )
				/ (24 * 60 * 60 * 1000);
		
		return day;
	}

	/**
	 * 取得2个时间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getTwoDateDifferentDay(String date1, String date2) {// 2003-05-1
		long day = -1;
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = myFormatter.parse(date1);
			java.util.Date mydate = myFormatter.parse(date2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return day;
	}
	
	/**
	 * 
	 * <p>作者:lht</p>
	 * <p>功能描述:获取某天距离今天的天数(今天-指定日期)</p>
	 * <p>创建时间:2012-8-10下午3:00:14</p>
	 * <p>@param oneDay
	 * <p>@return</p>
	 * <p>修改:</p>
	 */
	public static long getDatesOfOneDayToNow(String oneDay){
		return getTwoDateDifferentDay(getCurrentTime("yyyy-MM-dd"),oneDay);		
	}

	public static void timeProcessWaitSign() {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"M/dd/yyyy hh:mm:ss a", java.util.Locale.US);
			java.util.Date d = sdf.parse("5/13/2003 10:31:37 AM");
			System.out.println(d);
			System.out.println("<br>");
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String mDateTime1 = formatter.format(d);
			System.out.println(mDateTime1);
			System.out.println("<br>");
			System.out.println(d.getTime());
			System.out.println("<br>");
			// 当前时间
			Calendar cal = Calendar.getInstance();
			// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss");
			formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss G E D F w W a E F");
			String mDateTime = formatter.format(cal.getTime());
			System.out.println(mDateTime);
			System.out.println("<br>");
			// 1年前日期
			java.util.Date myDate = new java.util.Date();
			long myTime = (myDate.getTime() / 1000) - 60 * 60 * 24 * 365;
			myDate.setTime(myTime * 1000);
			String mDate = formatter.format(myDate);
			System.out.println(mDate);
			System.out.println("<br>");
			// 明天日期
			myDate = new java.util.Date();
			myTime = (myDate.getTime() / 1000) + 60 * 60 * 24;
			myDate.setTime(myTime * 1000);
			mDate = formatter.format(myDate);
			System.out.println(mDate);
			System.out.println("<br>");
			// 两个时间之间的天数
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = myFormatter.parse("2003-05-1");
			java.util.Date mydate = myFormatter.parse("1899-12-30");
			long day = (date.getTime() - mydate.getTime())
					/ (24 * 60 * 60 * 1000);
			System.out.println(day);
			System.out.println("<br>");
			// 加半小时
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			java.util.Date date1 = format.parse("2002-02-28 23:16:00");
			long Time = (date1.getTime() / 1000) + 60 * 30;
			date1.setTime(Time * 1000);
			String mydate1 = formatter.format(date1);
			System.out.println(mydate1);
			System.out.println("<br>");
			// 年月周求日期
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM F E");
			java.util.Date date2 = formatter2.parse("2003-05 5 星期五");
			SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
			String mydate2 = formatter3.format(date2);
			System.out.println(mydate2);
			System.out.println("<br>");
			// 求是星期几
			mydate = myFormatter.parse("2001-1-1");
			SimpleDateFormat formatter4 = new SimpleDateFormat("E");
			String mydate3 = formatter4.format(mydate);
			System.out.println(mydate3);
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
	}

	// 求出本月的第一天和最后一天
	public static String[] monthFix() {
		String[] dateMonth = new String[2];
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();
		// 本月第一天
		cpcalendar.set(Calendar.DAY_OF_MONTH, 1);
		dateMonth[0] = df.format(new Date(cpcalendar.getTimeInMillis()))
				.concat(" 00:00:01");

		cpcalendar.add(Calendar.MONTH, 1);
		cpcalendar.add(Calendar.DATE, -1);
		dateMonth[1] = df.format(new Date(cpcalendar.getTimeInMillis()))
				.concat(" 23:59:59");
		return dateMonth;
	}

	//// 求出本周的第一天和最后一天
	public static String[] dayOfWeekFix() {
		String[] dateDay = new String[2];
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();

		cpcalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		dateDay[0]=df.format(new Date(cpcalendar.getTimeInMillis())).concat(" 00:00:01");
		
		cpcalendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);		
		dateDay[1]=df.format(new Date(cpcalendar.getTimeInMillis())).concat(" 23:59:59");		
		return dateDay;
	}

	public static Integer parseInt(String dateStr){
		Integer date = new Integer(0);
		date = Integer.parseInt(dateStr.substring(0,4));
		date=date*100+Integer.parseInt(dateStr.substring(5,7));
		date=date*100+Integer.parseInt(dateStr.substring(8,10));
		return date;
	}
	public static Date getCurrentTimeForDate() {
		    return Calendar.getInstance().getTime();
		  }
	public static String getCurrentTime(String pattern) {
		    return formatTime(pattern, getCurrentTimeForDate());
		  }
	public static String formatTime(String pattern, Date time) {
		    return getFormatter(pattern).format(time);
		  }
	/**
	 * 
	 * <p>作者:lht</p>
	 * <p>功能描述:日期样式 对象放进静态变量，每次先从静态的Map对象中读取，如果没有就新建样式并放入静态对象，可以节省内存空间</p>
	 * <p>创建时间:2012-7-25下午2:19:01</p>
	 * <p>@param pattern
	 * <p>@return</p>
	 * <p>修改:</p>
	 */
	private static DateFormat getFormatter(String pattern) {
		    DateFormat df =  FormatterPool.get(pattern);
		    if (df == null) {
		      df = new SimpleDateFormat(pattern);
		      FormatterPool.put(pattern, df);
		    }
		    return df;
		  }

	  /**
	   * 判断两个日期是否同一天
	   * 如果日期都为空，则返回false
	   * @param d1 Date
	   * @param d2 Date
	   * @return boolean
	   */
	  public static boolean isSameDay(Date d1, Date d2) {
	    if (d1 == null || d2 == null) {
	      return false;
	    }
	    Calendar c1 = Calendar.getInstance();
	    c1.setTime(d1);
	    Calendar c2 = Calendar.getInstance();
	    c2.setTime(d2);
	    if (c1.get(Calendar.DAY_OF_YEAR) != c2.get(Calendar.DAY_OF_YEAR)) {
	      return false;
	    }
	    if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR)) {
	      return false;
	    }
	    return true;

	  }
	  /**
	   *    
	   * <p>作者:lht</p>
	   * <p>功能描述:比较给定日期是否大于当前日期</p>
	   * <p>创建时间:2012-10-11上午10:31:32</p>
	   * <p>@param dateA 日期格式：yyyy-MM-dd HH:mm:ss
	   * <p>@return
	   * <p>@throws ParseException</p>
	   * <p>修改:</p>
	   */
	  public static boolean compareDateABiggerThanCurrentDate(String dateA) throws ParseException{
	 	 Date a = getDateFromString(dateA, "yyyy-MM-dd HH:mm:ss");
	 	 return a.after(Calendar.getInstance().getTime());
	  }
	
	/**
	 在当前日期基础上增加月份数
	 @param month月份数
	 @return 添加后格式化的时间
	 @Date:   2012-09-14
	*/
	public static String addDate(int month) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		return getFormatter("yyyy-MM-dd HH:mm:ss").format(c.getTime());
	}
	
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
	/**		if(0 == old){
				old = days / 30;
				birthday = String.valueOf(old) + " 月";
			}
			//不足月
			if(0 == old){
				birthday = "0 岁";
			}
		*/
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
	
	public static void main(String[] argv) {
		// for(int i=0;i<1000;i++){
		// System.out.println(DateUtils.getDateOtherTime());
		// }
		// 1242122853859
		// 1242122702
		// System.out.println(DateUtils.getDateFormatStr2(1242123195000L));
		// System.out.println(DateUtils.getLongTime());
		// System.out.println(System.currentTimeMillis());
//		System.out.println(monthFix()[0]);
//		System.out.println(monthFix()[1]);
//		System.out.println(dayOfWeekFix()[0]);
//		System.out.println(dayOfWeekFix()[1]);
//		System.out.println(fotmatDate10(20100605));
//		System.out.println(fotmatDate10(20100605,4));
//		System.out.println(parseInt("2010-12-12"));
	//	System.out.println("==="+addDate(0));
//		try {
////			System.out.println(DateUtils.getRangeOfDaysAddDate("2010-6-20",0));
//			System.out.println("==="+getDatesOfOneDayToNow("2012-10-29"));
////			System.out.println("==="+getTwoDateDifferentDay("2012-8-21","2012-8-20"));
////			System.out.println("==="+getTwoDateDifferentDay("2012-8-20","2012-8-21"));
////			System.out.println("==="+getTwoDateDifferentDay("2012-8-20 10:00:00","2012-8-21 08:59:59"));
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
}
