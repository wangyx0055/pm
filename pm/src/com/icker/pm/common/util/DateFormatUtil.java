package com.icker.pm.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;

public class DateFormatUtil {
	/**
	 * 统一转换 日期格式为  。。。
	 * @param date
	 * @return
	 */
	public static String DateToString(Date date){
		FastDateFormat dateFormat = FastDateFormat.getInstance("yyyyMMddHHmmss");
		String dateStr = dateFormat.format(date);
		return dateStr;
	}
	
	public static Date StringToDate(String pattern,String date){
		Date parDate = null;
		try {
			parDate =  new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parDate;
	}
}
