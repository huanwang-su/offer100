package com.yiguo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

/**
 * date工具类
 *
 * @author wanghuan
 * @date 2018-01-08
 */
public class UtilDate {

	/** "yyyy-MM-dd HH:mm:ss" */
	public static final String defaultPattern = "yyyy-MM-dd HH:mm:ss";
	/** 格式:yyyy-MM-dd */
	public static final String patternDate = "yyyy-MM-dd";
	public static final String patternTime = "HH:mm:ss";

	private static final DateFormat defaultPatternDateFormat = new SimpleDateFormat(defaultPattern);
	private static final DateFormat patternDateDateFormat = new SimpleDateFormat(patternDate);
	private static final DateFormat patternTimeDateFormat = new SimpleDateFormat(patternTime);

	private static final String[] patternList = { defaultPattern, patternDate, patternTime };

	public static Date parse(String str) throws ParseException {
		return DateUtils.parseDate(str, patternList);
	}

	public static Date parse(long l) throws ParseException {
		return new Date(l);
	}

	public static String toDateString(Date date) {
		return defaultPatternDateFormat.format(date);
	}

	public static String toTimeString(Date date) {
		return patternTimeDateFormat.format(date);
	}

	public static String toCalendarString(Date date) {
		return patternDateDateFormat.format(date);
	}

}
