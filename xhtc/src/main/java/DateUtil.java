
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 */
public class DateUtil {

    public static final String TIMESTAMP_FORMAT = "d+";

    /**
     * 时间格式 : yyyy-MM-dd HH:mm:ss
     */
    public static final String TIME_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式 : yyyyMMddHHmmss
     */
    public static final String TIME_FORMAT_2 = "yyyyMMddHHmmss";

    /**
     * 时间格式 : yyyyMMddHHmmssSSS
     */
    public static final String TIME_FORMAT_3 = "yyyyMMddHHmmssSSS";

    /**
     * 年-月-日
     */
    public static final String TIME_FORMAT_4 = "yyyy-MM-dd";

    /**
     * 年月日
     */
    public static final String TIME_FORMAT_5 = "yyyyMMdd";

    public static final String TIME_FORMAT_6 = "yyyy-MM-dd HH:mm";

    public static final String TIME_FORMAT_7 = "yyyy-MM-dd";

    public static final String TIME_FORMAT_8 = "yyyyMM";


    /**
     * 时间格式 : yyyy-MM-dd HH:mm:ss
     */
    public static final String TIME_FORMAT_9 = "HH:mm";

    /**
     * UTC时间。yyyy-MM-dd'T'HH:mm:ss'Z'
     */
    public static final String TIME_UTC_FORMAT_1 = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /**
     * UTC时间。yyyy-MM-dd'T'HH:mm'Z'
     */
    public static final String TIME_UTC_FORMAT_2 = "yyyy-MM-dd'T'HH:mm'Z'";

    /**
     * 每天时间毫秒
     */
    private static final long millSencondPerDay = 24 * 3600 * 1000;

    /**
     * 一天的秒数
     */
    public static final int SECOND_PRE_DAY = 86400;

    /**
     * 每天的起始时间
     */
    private static final String formateBeginDay = " 00:00:00";

    /**
     * 每天的结束时间
     */
    private static final String formateEndDay = " 23:59:59";

    /**
     * 时间格式 : yyyyMMddHHmmssSSS
     */
    public static final String TIME_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * 格式化时间 date
     *
     * @param date    date
     * @param pattern pattern
     * @return 格式化时间
     */
    public static String parseDate(Date date, String pattern) {
        if (date == null || pattern == null)
            return null;
        DateFormat ft = new SimpleDateFormat(pattern);
        return ft.format(date);
    }

    public static Date formatDateByPattern(Date date, String pattern) throws ParseException {
        if (date == null || pattern == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateStr = sdf.format(date);
        return sdf.parse(dateStr);

    }

    public static void main(String[] args) throws ParseException {
//		List<String> result = new ArrayList<String>();
//
//		Long currentTime = System.currentTimeMillis();
//		String startTime = "立即送达(最晚"+DateUtil.getTimeAfterOneHalfHours(currentTime) +")" ;
//		result.add(startTime);
//		Date businessEndTime = DateUtil.getSetDate("16:00:00");
//		if (businessEndTime.getTime() - currentTime <= 60 * 60 * 1000) {
//			System.out.println(result);
//		} else {
//			//配送前一刻时间
//			Calendar c = Calendar.getInstance();
//			c.setTime(new Date(currentTime));
//			c.set(Calendar.MINUTE, 0);
//			c.set(Calendar.SECOND, 0);
//			c.add(Calendar.HOUR, 1);
//			c.set(Calendar.MILLISECOND, 0);
//			//配送后一刻时间
//			Calendar c1 = Calendar.getInstance();
//			c1.setTime(businessEndTime);
//
//			if (c1.get(Calendar.MINUTE) != 0) {
//				c1.add(Calendar.HOUR, 1);
//			}
//			c1.set(Calendar.MINUTE, 0);
//			c1.set(Calendar.SECOND, 0);
//			c1.set(Calendar.MILLISECOND, 0);
//
//			//System.out.println(c1.getTime());
//
//			// 间隔毫秒
//			long jiangeHaomiao = c1.getTime().getTime() - c.getTime().getTime();
//			// 中间间隔多少小时
//			long jiangeJixiaoshi = jiangeHaomiao / 60 / 60 / 1000;
//			long temp = c.getTime().getTime();
//			for (long i = 0; i < jiangeJixiaoshi; i++) {
//				if (i != jiangeJixiaoshi - 1) {
//					result.add(new Date(temp).getHours()+":00" + "-" + new Date(temp + 60 * 60 * 1000).getHours()+":00");
//				} else {
//					if(businessEndTime.getMinutes()<10){
//						result.add(new Date(temp).getHours()+":00" + "-" + businessEndTime.getHours()+":0"+businessEndTime.getMinutes());
//					}else{
//						result.add(new Date(temp).getHours()+":00" + "-" + businessEndTime.getHours()+":"+businessEndTime.getMinutes());
//					}
//				}
//				temp = temp + 60 * 60 * 1000;
//			}
//		}
//		System.out.println(result);
    }

    public static Date parseDate(String dateStr) {
        if (StringUtils.isEmpty(dateStr))
            return null;
        // if(Pattern.matches("", ""));
        Date date = parseDate2(dateStr, TIME_FORMAT_1);
        if (date != null)
            return date;
        date = parseDate2(dateStr, TIME_FORMAT_6);
        if (date != null)
            return date;
        date = parseDate2(dateStr, TIME_FORMAT_7);
        if (date != null)
            return date;
        date = parseDate2(dateStr, TIME_FORMAT_5);
        if (date != null)
            return date;
        return null;
    }

    /**
     * 批量导入上下架时间使用
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parseDate4ExcelImport(String dateStr) {
        if (StringUtils.isEmpty(dateStr))
            return null;
        Date date = parseDate2(dateStr, TIME_FORMAT_1);
        if (date != null)
            return date;
        date = parseDate2(dateStr, TIME_FORMAT_6);
        if (date != null)
            return date;
        return null;
    }


    /**
     * 格式化时间转为date
     *
     * @param time    格式化时间
     * @param pattern pattern
     * @return 时间
     * @throws ParseException ParseException
     */
    public static Date parseFormatTime(String time, String pattern) throws ParseException {
        DateFormat ft = new SimpleDateFormat(pattern);
        return ft.parse(time);
    }

    /**
     * 获取当前UTC时间
     *
     * @return 当前UTC时间
     */
    public static Date getNowUtcTime() {
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();

        // 2、取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);

        // 3、取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);

        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

        return cal.getTime();
    }

    /**
     * 获取当前本地时间
     *
     * @return 当前本地时间
     */
    public static Date getNowLocalTime() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    /**
     * 将指定本地时间转换为UTC时间
     *
     * @param localDate 指定时间
     * @return UTC时间
     */
    public static Date convertToUtcTime(Date localDate) {
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        cal.setTime(localDate);

        // 2、取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);

        // 3、取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);

        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

        return cal.getTime();
    }

    /**
     * 将指定UTC时间转换为本地时间
     *
     * @param utcDate 指定UTC时间
     * @return 本地时间
     */
    public static Date convertToLocalTime(Date utcDate) {
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        cal.setTime(utcDate);

        // 2、取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);

        // 3、取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);

        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, (zoneOffset + dstOffset));

        return cal.getTime();
    }

    /**
     * 将date偏移(增加或减少)N天
     *
     * @param date
     * @param n
     * @return
     */
    public static Date addDays(Date date, int n) {
        long millSeconds = date.getTime();
        return new Date(millSeconds + n * millSencondPerDay);
    }

    /**
     * @return Date
     * @Title: getCurrentDate
     * @Description: 获得当前的系统时间
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 获取时间对应的：yyyyMM
     *
     * @return 时间对应的：yyyyMM
     */
    public static String getMonth(Date date) {
        return formatDate(date, TIME_FORMAT_8);
    }

    /**
     * 获取上月时间
     *
     * @param date 当前时间
     * @return 获取上月时间
     */
    public static Date getLastMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }

    /**
     * 返回一天的起始时间点YYYY-MM-DD 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getStartDate(Date date) {
        String dayStr = formatDate(date, TIME_FORMAT_4);
        String begingDayStr = dayStr + formateBeginDay;
        return parseDate2(begingDayStr, TIME_FORMAT_1);

    }

    /**
     * 返回这一天设置的时间 YYYY-MM-DD 自选
     *
     * @return
     */
    public static Date getSetDate(String formateEndTime) {
        Date current = getCurrentDate();
        String dayStr = formatDate(current, TIME_FORMAT_4);
        String begingDayStr = dayStr + " " + formateEndTime;
        return parseDate2(begingDayStr, TIME_FORMAT_1);
    }

    /**
     * 获取下月1号0点时间
     *
     * @param date 当前时间
     * @return 下月1号0点时间
     */
    public static Date getStartNextMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.MONTH, 1);
        return c.getTime();
    }

    /**
     * @param date
     * @return
     * @Title: getStartMonth
     * @Description: 获得此月的开始时间即X月1日00:00:00
     * @author SuXiaoJie(xjsu@800best.com)
     * @date 2016-6-23 下午12:52:53
     */
    public static Date getStartMonth(Date date) {
        date = getStartDate(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        return c.getTime();
    }

    /**
     * 返回偏移n天后的起始时间点YYYY-MM-DD 00:00:00
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getStartDate(Date date, int n) {
        Date newDay = addDays(date, n);
        String dayStr = formatDate(newDay, TIME_FORMAT_4);
        String begingDayStr = dayStr + formateBeginDay;
        return parseDate2(begingDayStr, TIME_FORMAT_1);

    }

    /**
     * @param date
     * @return
     * @Title: getEndMonth
     * @Description: 获得此月的结束时间即X月30/31日23:59:59
     * @author SuXiaoJie(xjsu@800best.com)
     * @date 2016-6-23 下午12:52:53
     */
    public static Date getEndMonth(Date date) {
        date = getEndDate(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        c.set(Calendar.DAY_OF_MONTH, lastDay);
        return c.getTime();
    }

    /**
     * 返回一天的结束时间点YYYY-MM-DD 23:59:59
     *
     * @param date
     * @return
     */
    public static Date getEndDate(Date date) {
        String dayStr = formatDate(date, TIME_FORMAT_4);
        String endDayStr = dayStr + formateEndDay;
        return parseDate2(endDayStr, TIME_FORMAT_1);
    }

    /**
     * 返回偏移n天后的结束时间点YYYY-MM-DD 23:59:59
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getEndDate(Date date, int n) {
        Date newDay = addDays(date, n);
        String dayStr = formatDate(newDay, TIME_FORMAT_4);
        String endDayStr = dayStr + formateEndDay;
        return parseDate2(endDayStr, TIME_FORMAT_1);
    }

    /**
     * 格式化时间
     *
     * @param date
     * @param fmt
     * @return
     */
    public static String formatDate(Date date, String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        if (date == null) {
            return "";
        }
        return sdf.format(date);
    }

    /**
     * 解析时间
     *
     * @param str
     * @param fmt
     * @return
     */
    public static Date parseDate2(String str, String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取当天的00:00:00
     *
     * @param time 时间
     * @return 当天的00:00:00
     */
    public static Date parseDayStart(Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当天的23:59:59
     *
     * @param time 时间
     * @return 当天的23:59:59
     */
    public static Date parseDayEnd(Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 获取当前时间指定秒数后的时间
     *
     * @param second 秒数
     * @return
     */
    public static Date addSecond(int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.SECOND, second);
        return c.getTime();
    }

    /**
     * 获取当前时间指定秒数后的时间
     *
     * @param second 秒数
     * @return
     */
    public static Date addSecond(Date date, int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, second);
        return c.getTime();
    }


    /**
     * 是否为指定偏移内时间
     *
     * @param date 时间
     * @param n
     * @return
     */
    public static boolean isNowday(Date date, int n) {
        if (date == null)
            return false;
        Date start = getStartDate(new Date(), n);
        return date.getTime() >= start.getTime();
    }

    /**
     * 获取小时数
     *
     * @param date
     * @return
     */
    public static String getHours(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return String.format("%02d", c.get(Calendar.HOUR_OF_DAY));
    }


    /**
     * 获取当前时间往后推1小时的时间
     *
     * @return
     */
    public static String getTimeAfterOneHours(Long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        time += 60 * 60 * 1000;
        return format.format(time);
    }


    /**
     * 获取当前时间往后推半小时的时间
     *
     * @return
     */
    public static String getTimeAfterOneHalfHours(Long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        time += 30 * 60 * 1000;
        return format.format(time);
    }

    /**
     * 计算时间相差毫秒数，时间若为空，返回相差0
     *
     * @param source
     * @param target
     * @return
     */
    public static long getDiffSeconds(Date source, Date target) {
        if (source == null || target == null) {
            return 0;
        }
        if (target.after(source)) {
            return (target.getTime() - source.getTime()) / 1000;
        } else {
            return (source.getTime() - target.getTime()) / 1000;
        }
    }

    public static int getQuarterByMonth(int month) {
        if (month >= 1 && month < 4) {
            return 1;
        } else if (month >= 4 && month < 7) {
            return 2;
        } else if (month >= 7 && month < 10) {
            return 3;
        } else {
            return 4;
        }

    }

    public static String getCurrentQuarterNumber() {
        Calendar cal = Calendar.getInstance();
        //当前月
        int month = cal.get(Calendar.MONTH) + 1;
        //当前年
        int year = cal.get(Calendar.YEAR);
        //当前季度
        int quarter;
        String quarterNumber = "";
        if (month == 1) {
            quarter = 4;
            year = year - 1;
        } else if (month == 4 || month == 7 || month == 10) {
            quarter = DateUtil.getQuarterByMonth(month) - 1;
        } else {
            quarter = DateUtil.getQuarterByMonth(month);
        }
        //年+季度
        quarterNumber = new Integer(quarter).toString();
        quarterNumber = new Integer(year).toString() + "0" + quarterNumber;
        return quarterNumber;
    }

    /**
     * 获取下一个星期的开始
     *
     * @return
     */
    public static Date getNextWeekBegin() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, 1);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 将date偏移(增加或减少)N个月
     *
     * @param index
     * @return
     */
    public static Date addMonth(Date date, int index) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, index);
        return cal.getTime();
    }

//	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
//		Long time = System.currentTimeMillis();
//		time += 60*60*1000;
//		String d= format.format(time);
//		System.out.println("Format To String(Date):" + d);
//	}

    //判断时间段是否过期
    public static boolean ifPeriodOverdue(Date endDate) {
        return endDate.before(new Date()) ? true : false;
    }

    public static boolean ifDateIntegerMultipleFor5(Date date) {
        return date.getMinutes() % 5 == 0 ? true : false;
    }

    public static boolean ifDateIntegralPoint(Date date) {
        return (date.getMinutes() == 0 ? true : false) && (date.getSeconds() == 0 ? true : false);
    }

    public static boolean exitsSecondIsZero(Date date) {
        return date.getSeconds() == 0 ? true : false;
    }

    /**
     * 获取一周的第一天
     *
     * @param date 当天时间
     * @return 获取一周的第一天（yyyyMMdd）
     */
    public static String getFirstDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        // 中国的一周的开始是按照礼拜一计算
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            c.add(Calendar.DATE, -1);
        }

        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_5);
        return sdf.format(c.getTime());

    }

    /**
     * 获取一周的最后一天
     *
     * @param date
     * @return 获取一周的最后一天（yyyyMMdd）
     */
    public static String getEndDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 中国的一周的开始是按照礼拜一计算
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            c.add(Calendar.DATE, 1);
        } else {
            c.add(Calendar.DATE, 7);
        }

        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_5);
        return sdf.format(c.getTime());
    }

    /**
     * 获取一周的每一天
     *
     * @param date
     * @return 获取一周的最后一天（yyyyMMdd）
     */
    public static List<Long> getAllDaysOfWeek(Date date) {
        List<Long> weekDayList = new ArrayList<Long>();

        for (int i = 0; i < 7; i++) {
            if (i < 6) {
                Calendar c = Calendar.getInstance();
                c.setTime(date);

                // 中国的一周的开始是按照礼拜一计算
                if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    c.add(Calendar.DATE, -1);
                }

                int day = 0;
                if (i == 0) {
                    day = Calendar.MONDAY;
                } else if (i == 1) {
                    day = Calendar.TUESDAY;
                } else if (i == 2) {
                    day = Calendar.WEDNESDAY;
                } else if (i == 3) {
                    day = Calendar.THURSDAY;
                } else if (i == 4) {
                    day = Calendar.FRIDAY;
                } else if (i == 5) {
                    day = Calendar.SATURDAY;
                }
                c.set(Calendar.DAY_OF_WEEK, day);
                SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_5);
                weekDayList.add(Long.valueOf(sdf.format(c.getTime())));
            } else {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                // 中国的一周的开始是按照礼拜一计算
                if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    c.add(Calendar.DATE, 1);
                } else {
                    c.add(Calendar.DATE, 7);
                }

                c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_5);
                weekDayList.add(Long.valueOf(sdf.format(c.getTime())));
            }

        }

        return weekDayList;
    }

}
