package com.wfuhui.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

	public final static String DATE_PATTERN = "yyyy-MM-dd";

	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    
	public static Date add(Date date, Integer days) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		cld.add(Calendar.DATE, days);
		return cld.getTime();
	}
	
    public static Date parseDate(String date) {
    	SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);
        try {
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return null;
	}
}
