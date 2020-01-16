package com.hsj.hotel.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>Title: 日期时间处理工具</p>
 * <p>Description: 提供大量日期时间格式转换、处理的相关方法</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: AI(NanJing)</p>
 * @author ZhouZh
 * @date 2015-10-20
 * @version 1.0
 */

public class TimeUtil {
	
	/**
	 * 获取系统时间
	 * @return Timestamp	Timestamp对象
	 * @throws Exception
	 */
	public static Timestamp getSysDateTime() throws Exception {
		return new Timestamp(System.currentTimeMillis());
	}
	/**
	 * 获取系统最大过期时间
	 * @return Timestamp	Timestamp对象
	 * @throws Exception
	 */
	public static Timestamp getMaxExpireDate() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set(2099, 11, 31, 0, 0, 0);
		Timestamp expireTime = new Timestamp(cal.getTimeInMillis());
		return expireTime;
	}
	
	/**
	 * 通过常规日期格式转换日期数据 
	 * @param text			字符串
	 * @return Date			Date对象
	 * @throws Exception
	 */
	public static Date getDate(String text) throws Exception {
		String[] formats = new String[]{"yyyy-MM-dd", "yyyy/MM/dd", "yyyyMMdd", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss SSS", "yyyy/MM/dd HH:mm:ss SSS","yyyy-MM-dd HH:mm:ss.F"};
		return DateUtils.parseDate(text, formats);
	}

	/**
	 * 通过常规日期格式转换日期数据 
	 * @param text			字符串
	 * @return Timestamp	Timestamp对象
	 * @throws Exception
	 */
	public static Timestamp getTimestamp(String text) throws Exception {
		Date date = getDate(text);
		return new Timestamp(date.getTime());
	}
		
	/**
	 * 根据指定的格式将字符串转换成Date对象，如果没有指定格式则采用默认语言环境的格式
	 * @param text			字符串
	 * @param pattern		日期格式
	 * @return Date			Date对象
	 * @throws Exception
	 */
	public static Date getDate(String text, String pattern) throws Exception {
		Date date = null;
		
		if (StringUtils.isNotBlank(text)) {
			SimpleDateFormat format = null;
			if (StringUtils.isBlank(pattern)) {
				format = new SimpleDateFormat();
			} else {
				format = new SimpleDateFormat(pattern);
			}
			date = format.parse(text);
		}
		
		return date;
	}
		
	/**
	 * 根据指定的格式将字符串转换成Timestamp对象，如果没有指定格式则采用默认语言环境的格式
	 * @param text			字符串
	 * @param pattern		日期格式
	 * @return Timestamp	Timestamp对象
	 * @throws Exception
	 */
	public static Timestamp getTimestamp(String text, String pattern)	throws Exception {
		Date date = getDate(text, pattern);
		return new Timestamp(date.getTime());
	}
		
	/**
	 * 按指定的格式将日期型数据格式化输出，如果没有指定格式则采用默认语言环境的格式
	 * @param Date			日期对象
	 * @param pattern		日期格式
	 * @return String		格式化后的字符串
	 * @throws Exception
	 */
	public static String format(Date date, String pattern) throws Exception {
		String text = null; 
		
		if (date != null) {
			SimpleDateFormat format = null;
			if (StringUtils.isBlank(pattern)) {
				format = new SimpleDateFormat();
			} else {
				format = new SimpleDateFormat(pattern);
			}
			text = format.format(date);
		}

		return text;
	}
		
	/**
	 * 按指定的格式将Timestamp型数据格式化输出，如果没有指定格式则采用默认语言环境的格式
	 * @param timestamp		Timestamp对象
	 * @param pattern		日期格式
	 * @return String		格式化后的字符串
	 * @throws Exception
	 */
	public static String format(Timestamp timestamp, String pattern) throws Exception {
		return format((Date) timestamp, pattern);
	}

	/**
	 * 按指定域调整时间，时间域同Calendar类中定义的YEAR、MONTH、DATE、DAY_OF_MONTH、HOUR、MINUTE、SECOND、MILLISECOND等
	 * @param date			Date日期对象
	 * @param field			日期格式
	 * @param value			调整的幅度
	 * @return Date			返回调整后的时间
	 * @throws Exception
	 */
	public static Date add(Date date, int field, int value) throws Exception {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(field, value);
		return calendar.getTime();
	}

	/**
	 * 按指定域调整时间，时间域同Calendar类中定义的YEAR、MONTH、DATE、DAY_OF_MONTH、HOUR、MINUTE、SECOND、MILLISECOND等
	 * @param timestamp		Timestamp日期对象
	 * @param field			日期格式
	 * @param value			调整的幅度
	 * @return Timestamp	返回调整后的时间
	 * @throws Exception
	 */
	public static Timestamp add(Timestamp timestamp, int field, int value) throws Exception {
		Date date = add((Date)timestamp, field, value);
		return new Timestamp(date.getTime());
	}

	/**
	 * 按指定时间间隔调整时间，时间隔各式为：调整数量 + 调整单位，其中：调整数量可正负数，整数表示增加，负数表示减少；调整单位为:Y(年)/M(月)/W(周)/D(天)/h(时)/m(分)/s(秒)/S(毫秒)，如："3D"表示向后3天，"-3D"表示向前3天
	 * @param date			Date日期对象
	 * @param period		时间间隔
	 * @return Date			返回调整后的时间
	 * @throws Exception
	 */
	public static Date add(Date date, String period) throws Exception {
		int value = Integer.valueOf(period.substring(0, period.length() - 1));
		char unit = period.charAt(period.length() - 1);
		int field = 0;
		switch (unit) {
		case 'Y':
			field = Calendar.YEAR;
			break;
		case 'M':
			field = Calendar.MONTH;
			break;
		case 'W':
			field = Calendar.DAY_OF_MONTH;
			value *= 7;
			break;
		case 'D':
			field = Calendar.DAY_OF_MONTH;
			break;
		case 'h':
			field = Calendar.HOUR;
			break;
		case 'm':
			field = Calendar.MINUTE;
			break;
		case 's':
			field = Calendar.SECOND;
			break;
		case 'S':
			field = Calendar.MILLISECOND;
			break;
		default:
			throw new Exception("不支持的时间调整单位(" + unit + ").");
		}
		return add(date, field, value);
	}
	
	/**
	 * 按指定时间间隔调整时间，时间隔各式为：调整数量 + 调整单位，其中：调整数量可正负数，整数表示增加，负数表示减少；调整单位为:Y(年)/M(月)/W(周)/D(天)/h(时)/m(分)/s(秒)/S(毫秒)，如："3D"表示向后3天，"-3D"表示向前3天
	 * @param timestamp		Timestamp日期对象
	 * @param period		时间间隔
	 * @return Timestamp	返回调整后的时间
	 * @throws Exception
	 */
	public static Timestamp add(Timestamp timestamp, String period) throws Exception {
		Date date = add((Date)timestamp, period);
		return new Timestamp(date.getTime());
	}

	
	/**
	 * 时间格式转换，即字符串表示的时间从一种格式转换成另一种格式
	 * @param text			字符串时间
	 * @param pattern1		当前时间格式
	 * @param pattern2		转换时间格式
	 * @return String 		返回转换后的字符串时间
	 * @throws Exception
	 */
	public static String dateFormatConvert(String text, String pattern1, String pattern2) throws Exception {
		if (StringUtils.isBlank(text)) {
			return null;
		} else {
			Date date = getDate(text, pattern1);
			return format(date, pattern2);
		}
	}
	 
    

    /**
     * 计数两个日期之间的相差的日期部分，日期部分可以为年（Calendar.YEAR）、月（Calendar.MONTH）、日（Calendar.DAY_OF_MONTH）、时（Calendar.HOUR）、分（Calendar.MINUTE）、秒（Calendar.SECOND）
     * @param startDate		开始日期
     * @param endDate		结束日期
     * @param fieldType		日期部分类型
     * @return long			日期部分差额
     */
    public static long countDateField(Date startDate, Date endDate, int fieldType) throws Exception {
        long count = 0;
        
        if (startDate == null || endDate == null) {
        	throw new Exception("日期参数为空！");
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startDate);
        c2.setTime(endDate);
        switch (fieldType) {
        case Calendar.YEAR:
        	count = Math.abs(c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR));
        	break;
        case Calendar.MONTH:
        	count = Math.abs((c1.get(Calendar.YEAR)*12 + c1.get(Calendar.MONTH)) - (c2.get(Calendar.YEAR)*12 + c2.get(Calendar.MONTH)));
        	break;
        case Calendar.DAY_OF_MONTH:
        	count = Math.abs(startDate.getTime()/86400000 - endDate.getTime()/86400000);
        	break;
        case Calendar.HOUR:
        	count = Math.abs(startDate.getTime()/3600000 - endDate.getTime()/3600000);
        	break;
        case Calendar.MINUTE:
        	count = Math.abs(startDate.getTime()/60000 - endDate.getTime()/60000);
        	break;
        case Calendar.SECOND:
        	count = Math.abs(startDate.getTime()/1000 - endDate.getTime()/1000);
        	break;
        default:
        	throw new Exception("不支持的日期部分类型！");
        }
        return count;
    }

    /**
     * 计数两个日期之间的相差的日期部分，日期部分可以为年（Calendar.YEAR）、月（Calendar.MONTH）、日（Calendar.DAY_OF_MONTH）、时（Calendar.HOUR）、分（Calendar.MINUTE）、秒（Calendar.SECOND）
     * @param startDate		开始日期
     * @param endDate		结束日期
     * @param fieldType		日期部分类型
     * @return long			日期部分差额
     */
    public static long countDateField(Timestamp startDate, Timestamp endDate, int fieldType) throws Exception {
    	return countDateField((Date)startDate, (Date)endDate, fieldType);
    }

	/**
	 * 列表两个日期之间的涵盖的日期部分，日期部分可以为年（Calendar.YEAR）、月（Calendar.MONTH）、日（Calendar.DAY_OF_MONTH）、时（Calendar.HOUR）、分（Calendar.MINUTE）
	 * @param startDate		开始日期
	 * @param endDate		结束日期
	 * @param fieldType		日期部分类型
	 * @return int
	 */
	public static String[] listDateField(Date startDate, Date endDate, int fieldType) throws Exception {
		List list = null;
		
		if (startDate == null || endDate == null) {
			throw new Exception("日期参数为空！");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		String pattern = null;
		switch (fieldType) {
		case Calendar.YEAR:
			pattern = "yyyy";
			break;
		case Calendar.MONTH:
			pattern = "yyyyMM";
			break;
		case Calendar.DAY_OF_MONTH:
			pattern = "yyyyMMdd";
			break;
		case Calendar.HOUR:
			pattern = "yyyyMMddHH";
			break;
		case Calendar.MINUTE:
			pattern = "yyyyMMddHHmm";
			break;
		default:
			throw new Exception("不支持的日期部分类型！");
		}
		int amount = 0;
		if (startDate.before(endDate)) {
			amount = 1;
		}
		else {
			amount = -1;
		}
		String e = format(endDate, pattern);
		list = new ArrayList();
		while (true) {
			String s = format(calendar.getTime(), pattern);
			list.add(s);
			if (s.equals(e)) {
				break;
			}
			calendar.add(fieldType, amount);
        }
		
        return (String[])list.toArray(new String[0]);
    }
	
	/**
	 * 列表两个日期之间的涵盖的日期部分，日期部分可以为年（Calendar.YEAR）、月（Calendar.MONTH）、日（Calendar.DAY_OF_MONTH）、时（Calendar.HOUR）、分（Calendar.MINUTE）
	 * @param startDate		开始日期
	 * @param endDate		结束日期
	 * @param fieldType		日期部分类型
	 * @return int
	 */
	public static String[] listDateField(Timestamp startDate, Timestamp endDate, int fieldType) throws Exception {
		return listDateField((Date)startDate, (Date)endDate, fieldType);
	}

	/**
	 * 根据制定的类型对日期部分进行截取，截取类型可以为年（Calendar.YEAR）、月（Calendar.MONTH）、日（Calendar.DAY_OF_MONTH）、时（Calendar.HOUR）、分（Calendar.MINUTE）、秒（Calendar.SECOND），如:日期 "2015-08-16 12:28:32"按年截取为
	 * "1970-01-01 00:00:00"；按月截取为"2015-01-01 00:00:00"；按日截取为"2015-08-01 00:00:00"；按时截取为"2015-08-16 00:00:00"；按分钟 截取为"2015-08-01 12:00:00"；按秒截取为"2015-08-01 12:28:00"
	 * @param date		开始日期
	 * @param fieldType		日期部分类型
	 * @return int
	 */
	public static Date truncate(Date date, int fieldType) throws Exception {
		if (date == null) {
			throw new Exception("日期参数为空！");
		}
		String pattern = null;
		switch (fieldType) {
		case Calendar.YEAR:
			pattern = "1970";
			break;
		case Calendar.MONTH:
			pattern = "yyyy";
			break;
		case Calendar.DAY_OF_MONTH:
			pattern = "yyyyMM";
			break;
		case Calendar.HOUR:
			pattern = "yyyyMMdd";
			break;
		case Calendar.MINUTE:
			pattern = "yyyyMMddHH";
			break;
		case Calendar.SECOND:
			pattern = "yyyyMMddHHmm";
			break;
		default:
			throw new Exception("不支持的日期部分类型！");
		}
		return getDate(format(date, pattern), pattern);
    }

	/**
	 * 根据制定的类型对日期部分进行截取，截取类型可以为年（Calendar.YEAR）、月（Calendar.MONTH）、日（Calendar.DAY_OF_MONTH）、时（Calendar.HOUR）、分（Calendar.MINUTE）、秒（Calendar.SECOND），如:日期 "2015-08-16 12:28:32"按年截取为
	 * "1970-01-01 00:00:00"；按月截取为"2015-01-01 00:00:00"；按日截取为"2015-08-01 00:00:00"；按时截取为"2015-08-16 00:00:00"；按分钟 截取为"2015-08-01 12:00:00"；按秒截取为"2015-08-01 12:28:00"
	 * @param timestamp		开始日期
	 * @param fieldType		日期部分类型
	 * @return int
	 */
	public static Timestamp truncate(Timestamp timestamp, int fieldType) throws Exception {
		Date time = truncate((Date)timestamp, fieldType);
		return new Timestamp(time.getTime());
	}
	
}
