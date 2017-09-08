package com.open.free.eastmoney.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *author liuchsh
 */
public class DateUtil {
	public static final String FORMAT_yyyyMMdd = "yyyyMMdd";
	public static final String FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static final String FORMAT_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	public static final String FORMAT_HHmm = "HH:mm";
	public static final String FORMAT_HHmmss = "HH:mm:ss";
	public static final String FORMAT_yyyy_MM_ddHHmmssSSS = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MM月dd日 = "M月d日";
	public static final String FORMAT_yyyyMd = "yyyyMd";
	public static final String FORMAT_yyyy年MM月dd日hh时mm分ss秒SSS = "yyyy年M月d日 H时mm分s秒SSS";
	public static final String FORMAT_M月d日H时mm分 = "M月d日 H时mm分";
	public static final String FORMAT_yyyy_MM_dd = "yyyy_M_d";


	/**
	 * Date类转化为字符格式(如：yyyyMMddHHmmss)的字符串
	 */
	public static String date2Str(Date d, String pattern) {
		return new SimpleDateFormat(pattern).format(d);
	}
	public static String currentTime(String pattern){
		return date2Str(new Date(), pattern);
	}
	/**
	 * 字符串格式（如：yyyyMMddHHmmss）转化为Date类
	 */
	public static Date str2Date(String dateStr, String pattern)
			throws ParseException {
		return new SimpleDateFormat(pattern).parse(dateStr);
	}


	/**
	 * 日期转换成字符串，格式：yyyyMMdd
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String format(Date date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}
	public static Date parse(String dateStr)
			throws ParseException {
		return new SimpleDateFormat("yyyyMMdd").parse(dateStr);
	}


	/**
	 * day 为负数则表示 获取day天前的日期
	 * day 为正数则表示 获取day天后的日期
	 * @return
	 */
	public static String addDay(int day){
		return addDay(new Date(), day);
	}
	public static String addDay(Date date, int day){
		long time = date.getTime()+day*24*60*60*1000;
		Date d = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMAT_yyyyMMdd);
		return sdf.format(d) ;
	}

	/**
	 * @param date
	 * @param timeStr 格式：HH:mm:ss
	 * @return
	 */
	public static Date addDatetime(Date date, String timeStr){
		String[] tmp = timeStr.split(":");
		int hour = Integer.valueOf(tmp[0]);
		int minute = Integer.valueOf(tmp[1]);
		int second = Integer.valueOf(tmp[2]);
		long time = date.getTime() + ((hour*60+minute)*60+second)*1000;
		return new Date(time);
	}


	/**
	 * 将一个格式的日期转换成另一个格式的日期
	 * @return 返回toPattern形式的日期，如转换不成功，返回长度为0的字符串
	 */
	public static String formatDateFomPattern(String fromPattern,String toPattern,String dateStr){
		try{
			SimpleDateFormat sdf1 = new SimpleDateFormat(fromPattern);
			SimpleDateFormat sdf2 = new SimpleDateFormat(toPattern);
			Date date = sdf1.parse(dateStr);
			return sdf2.format(date);
		} catch(Exception e){
			return null;
		}
	}
	public static Date parseDateToPattern(String toPattern,String dateStr){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(toPattern);
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}
}
