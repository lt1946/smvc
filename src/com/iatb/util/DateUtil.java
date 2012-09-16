package com.iatb.util;


import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Class DateUtil
 *
 * @author <a href="mailto:yangzhenguo@ufstone.com">Yang Zhenguo</a>
 * @version $Revision:1.0.0, $Date:2009-3-19 上午11:51:51 $
 */

public class DateUtil {
	static java.text.SimpleDateFormat sdfShort = new java.text.SimpleDateFormat(
			"yyyyMMdd");
	static java.text.SimpleDateFormat sdfLong = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");
	static java.text.SimpleDateFormat sdfLongTime = new java.text.SimpleDateFormat(
			"yyyyMMddHHmmss");
	static java.text.SimpleDateFormat sdfLongTimePlus = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	static java.text.SimpleDateFormat sdfLongTimePlusMill = new java.text.SimpleDateFormat(
			"yyyyMMddHHmmssSSSS");

	public DateUtil() {
	}

	/**
	 * 取得当前日期,格式为:yyyy-MM-dd HH:mm:ss
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getPlusTime(Date date) {
		if (date == null)
			return null;
		String nowDate = sdfLongTimePlus.format(date);
		return nowDate;
	}
	/**
	 * 取得当前日期,格式为:yyyy-MM-dd HH:mm:ss
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getPlusTimeDate() {
		String nowDate = sdfLongTimePlus.format(new Date());
		return nowDate;
	}

	/**
	 * 格式化日期,格式为:yyyy-MM-dd
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getDatePlusTime(Date date) {
		if (date == null)
			return null;
		String nowDate = sdfLong.format(date);
		return nowDate;
	}

	/**
	 * 当前日期,格式为:yyyy-MM-dd
	 * @author：Dingding Zhang
	 * @return: String
	 * @date: Jan 31, 2010
	 */
	public static String getCurrentDate() {
		String nowDate = sdfLong.format(new Date());
		return nowDate;
	}

	/**
	 * pablo 根据毫秒数返回时分秒毫秒
	 *
	 * @param _second
	 *            秒数
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getTimeBySecond(long ms_second) {
		String returnTime = "";
		long longHour = 0;
		long longMinu = 0;
		long longSec = 0;
		long longMs = ms_second;
		if (longMs == 0) {
			returnTime = "0时0分0秒0毫秒";
			return returnTime;
		}
		longHour = longMs / 3600000; // 取得小时数
		longMs = longMs % 3600000; // 取得余下的毫秒
		longMinu = longMs / 60000; // 取得分数
		longMs = longMs % 60000; // 取得余下的毫秒
		longSec = longMs / 1000; // 取得余下的秒
		longMs = longMs % 1000; // 取得余下的毫秒
		returnTime = longHour + "时" + longMinu + "分" + longSec + "秒" + longMs
				+ "毫秒";
		return returnTime;
	}

	/**
	 * 获取当月的上下限字符串数组yyyy-MM-dd
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String[] getMonth() {
		String mm[] = { "", "" };
		Date d = new Date();
		d = new Date(d.getYear(), d.getMonth(), 1);
		mm[0] = sdfLong.format(d);
		if ((d.getMonth() + 1) == 12) {
			d = new Date(d.getYear() + 1, 0, 1);
			mm[1] = sdfLong.format(d);
		} else {
			d = new Date(d.getYear(), d.getMonth() + 1, 1);
			mm[1] = sdfLong.format(d);
		}
		return mm;
	}

	/**
	 *  获得时间的第2天
	 * @param date
	 * @param day
	 * @return
	 */
	public static java.util.Date addDate(java.util.Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 获取月份最大天数
	 *
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static int getMaxDate(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c.getActualMaximum(c.DAY_OF_MONTH);
	}

	/**
	 * 1.0.获取前一天yyyy-MM-dd
	 *
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getYestoday(Date d) {
		try {
			d.setDate(d.getDate() - 1);
			return getDatePlusTime(d);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 1.1.获取前一天yyyy-MM-dd
	 *
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getYestoday(String ssd) {
		try {
			Date ysd = sdfLong.parse(ssd);
			ysd.setDate(ysd.getDate() - 1);
			return getDatePlusTime(ysd);
		} catch (ParseException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 1.0.获取前一天yyyy-MM-dd
	 *
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getYestoday() {
		try {
			Date today = new Date();
			today.setDate(today.getDate() - 1);
			return getDatePlusTime(today);
		} catch (Exception e) {
			return null;
		}
	}
}
