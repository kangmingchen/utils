package com.x.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 时间工具类
 * 
 * @author Kenmy E-mail:6e8@163.com
 * @date 创建时间：2012-7-23 下午06:13:41
 */
public class TimeUtil {

	static Logger log = Logger.getLogger(TimeUtil.class);

	private static DateFormat DF_YYYY = new SimpleDateFormat("yyyy");
	private static DateFormat DF_YYYY_MM = new SimpleDateFormat("yyyy-MM");
	private static DateFormat DF_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat DF_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DateFormat DF_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 获取系统时间
	 * <ul>
	 * <li>sign=0 yyyy</li>
	 * <li>sign=1 yyyy-MM</li>
	 * <li>sign=2 yyyy-MM-dd</li>
	 * <li>sign=3 yyyy-MM-dd HH:mm:ss</li>
	 * </ul>
	 * 
	 * @param sign
	 * @return
	 */
	public static String getNow(int sign) {
		Calendar now = Calendar.getInstance();
		switch (sign) {
		case 0:
			return DF_YYYY.format(now.getTime());
		case 1:
			return DF_YYYY_MM.format(now.getTime());
		case 2:
			return DF_YYYY_MM_DD.format(now.getTime());
		case 3:
			return DF_YYYY_MM_DD_HH_MM_SS.format(now.getTime());
		default:
			return DF_YYYY_MM_DD.format(now.getTime());
		}
	}

	/**
	 * 获取系统时间
	 * <ul>
	 * <li>sign=0 yyyy</li>
	 * <li>sign=1 yyyy-MM</li>
	 * <li>sign=2 yyyy-MM-dd</li>
	 * <li>sign=3 yyyy-MM-dd HH:mm:ss</li>
	 * </ul>
	 * 
	 * @param sign
	 * @return string
	 */
	public static String dateFormat(Date dt, int sign) {
		switch (sign) {
		case 0:
			return DF_YYYY.format(dt);
		case 1:
			return DF_YYYY_MM.format(dt);
		case 2:
			return DF_YYYY_MM_DD.format(dt);
		case 3:
			return DF_YYYY_MM_DD_HH_MM_SS.format(dt);
		default:
			return DF_YYYY_MM_DD.format(dt);
		}
	}

	/**
	 * 获取系统时间
	 * <ul>
	 * <li>sign=0 yyyy</li>
	 * <li>sign=1 yyyy-MM</li>
	 * <li>sign=2 yyyy-MM-dd</li>
	 * <li>sign=3 yyyy-MM-dd HH:mm:ss</li>
	 * </ul>
	 * 
	 * @param sign
	 * @return date
	 */
	public static Date dateFormat1(String dt, int sign) {
		try {
			switch (sign) {
			case 0:
				return DF_YYYY.parse(dt);
			case 1:
				return DF_YYYY_MM.parse(dt);
			case 2:
				return DF_YYYY_MM_DD.parse(dt);
			case 3:
				return DF_YYYY_MM_DD_HH_MM_SS.parse(dt);
			default:
				return DF_YYYY_MM_DD.parse(dt);
			}
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * 系统时间相减得到新的时间
	 * 
	 * @param min
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getLowerTime(int min) {
		long l = new Date().getTime();// 当前时间的 毫秒数
		long s = min * 60 * 1000;// 300秒的 毫秒数
		Date date = new Date(l - s);// 减去300秒后 的 时间
		return DF_YYYY_MM_DD_HH_MM_SS.format(date.getTime());
	}

	/**
	 * 时间格式转换为yyyy-MM-dd
	 * 
	 * @param time
	 * @return
	 */
	public static String toDate(String time) {
		Calendar dt = Calendar.getInstance();
		try {
			dt.setTime(DF_YYYYMMDD.parse(time));
		} catch (ParseException e) {
			log.error(e);
		}
		return DF_YYYY_MM_DD.format(dt.getTime());
	}

	/**
	 * 当前时间相加或相减天数返回
	 * 
	 * @param day
	 *            /-day
	 * @return yyyy-MM-dd
	 */
	public static String getDay(int day) {
		Calendar time = Calendar.getInstance();
		time.add(Calendar.DATE, day); // 当前时间相减去天数返回最新日期
		return DF_YYYY_MM_DD.format(time.getTime());
	}
	
}
