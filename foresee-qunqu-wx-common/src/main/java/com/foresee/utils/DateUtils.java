package com.foresee.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
	//private static Logger logger = Logger.getLogger(DateUtils.class);
	private static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";//日期
	private static String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";//时间
	private static String DEFAULT_TIME_FORMAT_STRING = "yyyyMMddHHmmss";//时间
	
	private static SimpleDateFormat DEFAULT_DATE_FORMATTER = new SimpleDateFormat(DEFAULT_DATE_FORMAT);//日期
	private static SimpleDateFormat DEFAULT_TIME_FORMATTER = new SimpleDateFormat(DEFAULT_TIME_FORMAT);//时间
	private static SimpleDateFormat DEFAULT_TIME_FORMATTER_STRING = new SimpleDateFormat(DEFAULT_TIME_FORMAT_STRING);//时间

	/**
	 * 格式化日期类型
	 * @return String （yyyy-MM-dd）
	 */
	public static String formatDate(Date d) {
		return DEFAULT_DATE_FORMATTER.format(d);
	}
	/**
	 * 格式化日期类型,参数为时间的整型表示
	 * @return String （yyyy-MM-dd）
	 */
	public static String formatDate(long d) {
		return DEFAULT_DATE_FORMATTER.format(d);
	}
	/**
	 * 格式化日期类型,若date为空则采用当前时间
	 * @return String 格式化后的日期字符串
	 */
	public static String formatDate(Date d,String fmt) {
		SimpleDateFormat simpleDateFormat = fmt==null?DEFAULT_TIME_FORMATTER:new SimpleDateFormat(fmt);
		return simpleDateFormat.format(d==null?new Date():d);
	}
	/**
	 * 格式化日期类型
	 * @return String 格式化后的日期字符串
	 */
	public static String formatDate(long d,String fmt) {
		SimpleDateFormat simpleDateFormat = fmt==null?DEFAULT_TIME_FORMATTER:new SimpleDateFormat(fmt);
		return simpleDateFormat.format(d);
	}
	/**
	 * 格式化长日期类型 带时分秒
	 * @return String （yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date d) {
		return DEFAULT_TIME_FORMATTER.format(d);
	}
	/**
	 * 格式化长日期类型 带时分秒,参数为时间的整型表示
	 * @return String （yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(long d) {
		return DEFAULT_TIME_FORMATTER.format(d);
	}
	/**
	 * 格式化长日期类型 带时分秒
	 * @return String （yyyyMMddHHmmss）
	 */
	public static String formatDateTimeString(Date d) {
		return DEFAULT_TIME_FORMATTER_STRING.format(d);
	}

	/**
	 * 字符串转换成日期对象
	 * @param str
	 * @return date （yyyy-MM-dd）
	 */
	public static Date getDate(String str) {
		try {
			return DEFAULT_DATE_FORMATTER.parse(str);
		} catch (ParseException e) {
			//logger.error("字符串转换日期格式异常：" + str);
			//logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 字符串转换成日期时间对象
	 * @param str 
	 * @return date（yyyy-MM-dd HH:mm:ss）
	 */
	public static Date getTime(String str) {
		try {
			return DEFAULT_TIME_FORMATTER.parse(str);
		} catch (ParseException e) {
			//logger.error("字符串转换日期格式异常：" + str);
			//logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 根据格式化信息得到日期对象
	 * @param str 需要转换的字符串
	 * @param parse 需要转换成的格式
	 * @return date
	 */
	public static Date getDate(String str, String parse) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parse);
		try {
			return simpleDateFormat.parse(str);
		} catch (ParseException e) {
			//logger.error("字符串转换日期格式异常：需要格式化的字符串=" + str + "，转化后的格式=" + parse);
			//logger.error(e.getMessage());
			return null;
		}

	}
	/**
	 * 采用给定的格式化方式返回当前日期的字符串表示,
	 * 若dateFormat为空则返回yyyy-MM-dd HH:mm:ss表示的时间
	 * @param dateFormat 格式化字符串(如:yyyy-MM-dd)
	 * @return 格式化后的当前日期字符串
	 */
	public static String getTodayDate(String dateFormat)
	{
		if(StringUtil.notBlank(dateFormat))
			return new SimpleDateFormat(dateFormat).format(new Date());
		else
			return getStringDate();
	}
	/**
	 * 获取当前日期对象,不包含时分秒
	 * @return
	 */
	public static Date getTodayDateNoSFM()
	{
		try {
			return DEFAULT_DATE_FORMATTER.parse(DEFAULT_DATE_FORMATTER.format(new Date()));
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 获取现在时间
	 * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		return DEFAULT_TIME_FORMATTER.format(new Date());
	}

	/**
	 * 获取现在时间
	 * @return 返回短时间字符串格式 yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		return DEFAULT_DATE_FORMATTER.format(new Date());
	}

	/**
	 * 当前时间 推移几天的日期
	 * @param day
	 *            需要推移的天数，正表示前移，负表示后移
	 * @return date
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 24 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	 * 判断是否润年
	 * @return true:是润年  false：不是润年
	 */
	public static boolean isLeapYear(String ddate) {

		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = getDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周
	 * @return String
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		// String year = Integer.toString(c.get(Calendar.YEAR));
		return week;
	}

	/**
	 * 根据传入的日期格式的字符串，判断是本年度的第几个周
	 * @param date
	 * @return string 
	 */
	public static String getSeqWeek(String date) {
		Date d = getDate(date);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		String week = Integer.toString(gc.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		return week;
	}
	public static String getbeforeDateByYear(int yearNum) {
		long days = yearNum * 365;
		return DEFAULT_DATE_FORMATTER.format(getLastDate(days));
	}
	public static String getDateByYear(String date, int yearNum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDate(date, "yyyy-MM-dd"));
		cal.add(Calendar.YEAR, yearNum);   
		return formatDate(cal.getTime(), "yyyy-MM-dd");
	}
	/**
	 * 获取两日日期月份差
	 * @param end 结束时间
	 * @param start 开始时间
	 * @return
	 */
	public static int getMonthSpace(String end, String start)throws ParseException {
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(sdf.parse(end));
        c2.setTime(sdf.parse(start));
        int y1 = c1.get(Calendar.YEAR),y2 = c2.get(Calendar.YEAR);
        int m1 = c1.get(Calendar.MONTH),m2 = c2.get(Calendar.MONTH);
        int d1 = c1.get(Calendar.DATE),d2 = c2.get(Calendar.DATE);
        result = (y1-y2)*12+(m1-m2);
        if(d1 >= d2){
        	result++;
        }
        return result == 0 ? 1 : Math.abs(result);
    }
	
	/**
	 * 获取前一个月的某天 当d=0设置为0号,当前日期既为前月最后一天,-1即为前月倒数第二天,依次类推 返回的格式是“yyyy-MM-dd”
	 * @param d
	 * @param fmt 为空时 默认返回 格式是“yyyy-MM-dd”
	 * @return
	 * 2018年7月4日-上午9:13:33
	 * YY
	 */
	public static String getSomeDay(int d,String fmt) {
		fmt =StringUtil.sNull(fmt);
		if("".equals(fmt))fmt = DEFAULT_DATE_FORMAT;
		SimpleDateFormat DEFAULT_DATE_FORMATTER = new SimpleDateFormat(fmt);
		//获取前月的最后一天
        Calendar cale = Calendar.getInstance();   
        cale.set(Calendar.DAY_OF_MONTH,2);//设置为0号,当前日期既为前月最后一天,-1即为前月倒数第二天,依次类推
        String lastDay = DEFAULT_DATE_FORMATTER.format(cale.getTime());
        return lastDay;
	}

	/**
	 * 获取上个季度的时间范围
	 * @return
	 */
	public static DateRange getLastQuarter() {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3 - 1) * 3);
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) / 3 - 1) * 3 + 2);
		endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		return new DateRange(startCalendar.getTime(), endCalendar.getTime());
	}

	/**
	 * 获取当前季度的时间范围
	 * @return
	 */
	public static DateRange getQuarter() {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3 ) * 3);
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) / 3 ) * 3 + 2);
		endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		return new DateRange(startCalendar.getTime(), endCalendar.getTime());
	}


	/**
	 * 获取当前日期的上一季度
	 * @param date
	 * @return
	 */
	public static String getSeason(Date date) {
		int season = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		switch (month) {
			case Calendar.JANUARY:
			case Calendar.FEBRUARY:
			case Calendar.MARCH:
				season = 1;
				break;
			case Calendar.APRIL:
			case Calendar.MAY:
			case Calendar.JUNE:
				season = 2;
				break;
			case Calendar.JULY:
			case Calendar.AUGUST:
			case Calendar.SEPTEMBER:
				season = 3;
				break;
			case Calendar.OCTOBER:
			case Calendar.NOVEMBER:
			case Calendar.DECEMBER:
				season = 4;
				break;
			default:
				break;
		}
		season--;
		if(season<=0){
			year--;
			season=4;
		}
		return year+"-"+season;
	}

     /**
     * 获取当月的 天数
     */
     public static int getCurrentMonthDay() {
		        Calendar a = Calendar.getInstance();
		        a.set(Calendar.DATE, 1);
		        a.roll(Calendar.DATE, -1);
		        int maxDate = a.get(Calendar.DATE);
		        return maxDate;
	 }

	public static List<String> getDayListOfMonth() {
		List<String> list = new ArrayList<String>();
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int year = aCalendar.get(Calendar.YEAR);//年份
		int month = aCalendar.get(Calendar.MONTH) + 1;//月份
		int day = aCalendar.getActualMaximum(Calendar.DATE);
		for (int i = 1; i <= day; i++) {
			String years=String.valueOf(year);
			String months=String.valueOf(month);
			String days = String.valueOf(i);
			if(months.length()==1){
				months="0"+months;
			}
			if(days.length()==1){
				days="0"+days;
			}
			String aDate = years+"-"+months+"-"+days;
			list.add(aDate);
		}
		return list;
	}

	//获取本月的第一天
	public static String getFitstDayOfMonth(){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		Date theDate=calendar.getTime();
		GregorianCalendar gcLast=(GregorianCalendar)Calendar.getInstance();
		gcLast.setTime(theDate);
		//设置为第一天
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first=sf.format(gcLast.getTime());
		return day_first;
	}

	//获取本月的最后一天
	public static String getLastDayOfMonth(){
		//获取Calendar
		Calendar calendar=Calendar.getInstance();
		//设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(calendar.DATE));
		//设置日期格式
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String ss=sf.format(calendar.getTime());
		return ss;
	}
}
