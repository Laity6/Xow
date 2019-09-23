package com.ol.xow.base;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

/**
 * 日期操作工具类，包括日期格式转化、特定日期获取
 */
public class DateUtils {
    public static String YEAR_MONTH_TIME = "yyyy-MM-dd HH:mm:ss";
    public static String YEAR_MONTH_HH_MM = "yyyy-MM-dd HH:mm";
    public static String YEAR_MONTH_HH_MM_1 = "yyyy/MM/dd HH:mm";

    public static String YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static String YEAR_MONTH_DAY_1 = "yyyy年MM月dd日";
    public static String YEAR_MONTH_DAY_2 = "yyyy/MM/dd";
    public static String YEAR_MONTH_DAY_3 = "yyyyMMdd";
    public static String YEAR_MONTH_TIME_1 = "yyyyMMddHHmmss";
    public static String HOUR_MINUTE = "HH:mm";

    private static String[] WEEK = new String[]{"日", "一", "二", "三", "四", "五", "六"};
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    private static SimpleDateFormat mDateFormat = new SimpleDateFormat();

    /**
     * 格式化输出时间字符串
     *
     * @param date    待格式化时间
     * @param pattern 输出时间字符串模式
     * @return 格式化后的时间字符串
     */
    public synchronized static String format(Date date, String pattern) {
        try {
            mDateFormat.applyPattern(pattern);
            return mDateFormat.format(date);
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 时间字符串转化为date
     *
     * @param date    待转化时间字符串
     * @param pattern 待转化时间字符串模式
     * @return
     * @throws ParseException
     */
    public synchronized static Date parse(String date, String pattern) throws ParseException {
        mDateFormat.applyPattern(pattern);
        return mDateFormat.parse(date);
    }

    /**
     * 获得指定字符串的date对象
     *
     * @param dateString 日期字符串
     * @return
     */
    public synchronized static Date getDate(String dateString) {
        Date date;
        try {
            mDateFormat.applyPattern(YEAR_MONTH_TIME);
            date = mDateFormat.parse(dateString);
            return date;
        } catch (Exception e) {
            HxLog.e(e.getMessage());
        }
        return null;
    }

    /**
     * 获取给定时间的上个月
     *
     * @param date 给定时间
     * @return 给定时间的上个月
     */
    public static Date getLastMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取给定时间的下个月
     *
     * @param date 给定时间
     * @return 给定时间的下个月
     */
    public static Date getNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取当前时间的未来num天的时间
     *
     * @param num
     * @return 给定时间的未来num天的时间
     */
    public static Date getNextDate(int num) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, num);
        return cal.getTime();
    }

    /**
     * 获取给定时间的未来num天的时间
     *
     * @param date 给定时间
     * @param num
     * @return 给定时间的未来num天的时间
     */
    public static Date getNextDate(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, num);
        return cal.getTime();
    }

    /**
     * 获取给定时间的未来minute分钟的时间
     *
     * @param date   给定时间
     * @param minute
     * @return 获取给定时间的未来minute分钟的时间
     */
    public static Date getNextDateMinute(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    /**
     * 获取给定时间的未来seconds秒钟的时间
     *
     * @param date   给定时间
     * @param second
     * @return 获取给定时间的未来minute分钟的时间
     */
    public static Date getNextDateSecond(Date date, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * 获取两个时间之间的间隔天数
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @return 间隔天数
     */
    public static int getBetweenDay(Date begin, Date end) {
        double between = (end.getTime() - begin.getTime()) / 1000.0;// 除以1000是为了转换成秒
        int day = (int) (between / (24.0 * 3600.0));
        return day;
    }

    /**
     * 获取两个时间之间的年数
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @return 间隔天数
     */
    public static int getBetweenYear(Date begin, Date end) {
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(begin);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        int between = endCalendar.get(Calendar.YEAR) - beginCalendar.get(Calendar.YEAR);
        return between;
    }

    /**
     * 获得上周一日期
     *
     * @return
     */
    public synchronized static String getPreWeekMonday() {
        int mondayPlus = 0;
        Calendar calandar = Calendar.getInstance();
        int dayofWeek = calandar.get(Calendar.DAY_OF_WEEK);
        if (dayofWeek == Calendar.SUNDAY) {
            mondayPlus = -6;
        } else {
            mondayPlus = Calendar.MONDAY - dayofWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus - 7);
        Date monday = currentDate.getTime();
        mDateFormat.applyPattern(YEAR_MONTH_DAY);
        String preMonday = mDateFormat.format(monday);
        return preMonday;
    }

    /**
     * 获取上周日日期
     *
     * @return
     */
    public synchronized static String getPreWeekSunday() {
        int mondayPlus = 0;
        Calendar calandar = Calendar.getInstance();
        int dayofWeek = calandar.get(Calendar.DAY_OF_WEEK);
        if (dayofWeek == Calendar.SUNDAY) {
            mondayPlus = -6;
        } else {
            mondayPlus = Calendar.MONDAY - dayofWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus - 1);
        Date sunday = currentDate.getTime();
        mDateFormat.applyPattern(YEAR_MONTH_DAY);
        String preSunday = mDateFormat.format(sunday);
        return preSunday;
    }

    /**
     * 获得本周一日期
     *
     * @return
     */
    public synchronized static String getCurWeekMonday() {
        Date monday = getMondayForCurWeek();
        mDateFormat.applyPattern(YEAR_MONTH_DAY);
        String preMonday = mDateFormat.format(monday);
        return preMonday;
    }

    /**
     * 获得本周一日期
     *
     * @return
     */
    public static Date getMondayForCurWeek() {
        int mondayPlus = 0;
        Calendar calandar = Calendar.getInstance();
        int dayofWeek = calandar.get(Calendar.DAY_OF_WEEK);
        if (dayofWeek == Calendar.SUNDAY) {
            mondayPlus = -6;
        } else {
            mondayPlus = Calendar.MONDAY - dayofWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        return monday;
    }

    /**
     * 获得本周日的日期
     *
     * @return
     */

    public synchronized static String getCurWeekSunday() {
        int mondayPlus = 0;
        Calendar calandar = Calendar.getInstance();
        int dayofWeek = calandar.get(Calendar.DAY_OF_WEEK);
        if (dayofWeek == Calendar.SUNDAY) {
            mondayPlus = -6;
        } else {
            mondayPlus = Calendar.MONDAY - dayofWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date sunday = currentDate.getTime();
        mDateFormat.applyPattern(YEAR_MONTH_DAY);
        String thissunday = mDateFormat.format(sunday);
        return thissunday;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNowDateTime() {
        return format(new Date(), YEAR_MONTH_TIME);
    }

    public static String getNowDateTime(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getNowDate() {
        return format(new Date(), YEAR_MONTH_DAY);
    }

    public static String getNowDate(String format) {
        return format(new Date(), format);
    }


    /**
     * 获取显示日期时间字符串，去空格和横线
     *
     * @return
     */
    public static String getNowString() {
        String sDate = getNowDateTime();
        sDate = sDate.replace(" ", "").replace("-", "").replace(":", "");
        return sDate;
    }


    public static Date str2Date(String str) {
        return str2Date(str, null);
    }

    /**
     * 字符串转时间
     *
     * @param str
     * @param format
     * @return
     */
    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }

    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);

    }

    public static Calendar str2Calendar(String str, String format) {
        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;

    }

    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }

    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }

    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }

    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + "-"
                + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
    }

    /**
     * 获得当前日期的字符串格式
     *
     * @param format
     * @return
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);
    }

    /**
     * 格式到秒
     *
     * @param time
     * @return
     */
    public static String getMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

    }

    /**
     * 格式到天
     *
     * @param time
     * @return
     */
    public static String getDay(long time) {
        return new SimpleDateFormat("yyyy-MM-dd").format(time);

    }

    /**
     * 格式到毫秒
     *
     * @param time
     * @return
     */
    public static String getSMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

    }

    /**
     * 字符串转换到时间格式
     *
     * @param dateStr   需要转换的字符串
     * @param formatStr 需要格式的目标字符串 举例 yyyy-MM-dd
     * @return Date 返回转换后的时间
     * @throws ParseException 转换异常
     */
    public static Date StringToDate(String dateStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 转化时间输入时间与当前时间的间隔
     *
     * @param timestamp
     * @return
     */
    public static String converTime(long timestamp) {
        long currentSeconds = System.currentTimeMillis() / 1000;
        long timeGap = currentSeconds - timestamp;// 与现在时间相差秒数
        String timeStr = null;
        if (timeGap > 24 * 60 * 60) {// 1天以上
            timeStr = timeGap / (24 * 60 * 60) + "天前";
        } else if (timeGap > 60 * 60) {// 1小时-24小时
            timeStr = timeGap / (60 * 60) + "小时前";
        } else if (timeGap > 60) {// 1分钟-59分钟
            timeStr = timeGap / 60 + "分钟前";
        } else {// 1秒钟-59秒钟
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 把字符串转化为时间格式
     *
     * @param timestamp
     * @return
     */
    public static String getStandardTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
        Date date = new Date(timestamp * 1000);
        sdf.format(date);
        return sdf.format(date);
    }

    public static String getTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
        Date date = new Date(timestamp * 1000);
        return sdf.format(date);
    }

    public static String getTime(long timestamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(timestamp * 1000);
        return sdf.format(date);
    }

    public static Date getDateTime(long timestamp) {
        return new Date(timestamp * 1000);
    }


    /**
     * 获得当前日期时间 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String currentDatetime() {
        return datetimeFormat.format(now());
    }

    /**
     * 格式化日期时间 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String formatDatetime(Date date) {
        return datetimeFormat.format(date);
    }

    /**
     * 获得当前时间 时间格式HH:mm:ss
     *
     * @return
     */
    public static String currentTime() {
        return timeFormat.format(now());
    }

    /**
     * 格式化时间 时间格式HH:mm:ss
     *
     * @return
     */
    public static String formatTime(Date date) {
        return timeFormat.format(date);
    }

    /**
     * 获得当前时间的<code>java.util.Date</code>对象
     *
     * @return
     */
    public static Date now() {
        return new Date();
    }

    public static Calendar calendar() {
        Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal;
    }

    /**
     * 获得当前时间的毫秒数
     * <p>
     * 详见{@link System#currentTimeMillis()}
     *
     * @return
     */
    public static long millis() {
        return System.currentTimeMillis();
    }

    /**
     * 获得当前Chinese月份
     *
     * @return
     */
    public static int month() {
        return calendar().get(Calendar.MONTH) + 1;
    }

    /**
     * 获得月份中的第几天
     *
     * @return
     */
    public static int dayOfMonth() {
        return calendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 今天是星期的第几天
     *
     * @return
     */
    public static int dayOfWeek() {
        return calendar().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 今天是年中的第几天
     *
     * @return
     */
    public static int dayOfYear() {
        return calendar().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 判断原日期是否在目标日期之前
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isBefore(Date src, Date dst) {
        return src.before(dst);
    }

    /**
     * 判断原日期是否在目标日期之后
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isAfter(Date src, Date dst) {
        return src.after(dst);
    }

    /**
     * 判断两日期是否相同
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqual(Date date1, Date date2) {
        return date1.compareTo(date2) == 0;
    }

    /**
     * 判断某个日期是否在某个日期范围
     *
     * @param beginDate 日期范围开始
     * @param endDate   日期范围结束
     * @param src       需要判断的日期
     * @return
     */
    public static boolean between(Date beginDate, Date endDate, Date src) {
        return beginDate.before(src) && endDate.after(src);
    }

    /**
     * 获得当前月的最后一天
     * <p>
     * HH:mm:ss为0，毫秒为999
     *
     * @return
     */
    public static Date lastDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
        cal.set(Calendar.MINUTE, 0);// m置零
        cal.set(Calendar.SECOND, 0);// s置零
        cal.set(Calendar.MILLISECOND, 0);// S置零
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
        cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
        return cal.getTime();
    }

    /**
     * 获得当前月的第一天
     * <p>
     * HH:mm:ss SS为零
     *
     * @return
     */
    public static Date firstDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
        cal.set(Calendar.MINUTE, 0);// m置零
        cal.set(Calendar.SECOND, 0);// s置零
        cal.set(Calendar.MILLISECOND, 0);// S置零
        return cal.getTime();
    }

    private static Date weekDay(int week) {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_WEEK, week);
        return cal.getTime();
    }

    /**
     * 获得周五日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date friday() {
        return weekDay(Calendar.FRIDAY);
    }

    /**
     * 获得周六日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date saturday() {
        return weekDay(Calendar.SATURDAY);
    }

    /**
     * 获得周日日期 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date sunday() {
        return weekDay(Calendar.SUNDAY);
    }

    /**
     * 将字符串日期时间转换成java.util.Date类型 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @param datetime
     * @return
     */
    public static Date parseDatetime(String datetime) throws ParseException {
        return datetimeFormat.parse(datetime);
    }

    /**
     * 将字符串日期转换成java.util.Date类型 日期时间格式yyyy-MM-dd
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    /**
     * 将字符串日期转换成java.util.Date类型 时间格式 HH:mm:ss
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date parseTime(String time) throws ParseException {
        return timeFormat.parse(time);
    }

    /**
     * 根据自定义pattern将字符串日期转换成java.util.Date类型
     *
     * @param datetime
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseDatetime(String datetime, String pattern) throws ParseException {
        SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
        format.applyPattern(pattern);
        return format.parse(datetime);
    }

    /**
     * 把秒格式化为分种小时
     *
     * @param second
     * @return
     */
    public static String parseSecond(int second) {
        if (second >= 60) {
            return second / 60 + "分";
        } else if (second >= 60 * 60) {
            return second / 60 * 60 + "时";
        } else if (second >= 60 * 60 * 24) {
            return second / 60 * 60 + "天";
        } else {
            return second + "秒";
        }
    }


    /**
     * 比较时间大小
     *
     * @param begin
     * @param end
     * @return
     */
    public static int compareDate(String begin, String end) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date beginDate = df.parse(begin);
            Date endDate = df.parse(end);
            if (beginDate.getTime() < endDate.getTime()) {
                return 1;
            } else if (beginDate.getTime() > endDate.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获得年份
     *
     * @param date
     * @return
     */
    public int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获得月份
     *
     * @param date
     * @return
     */
    public int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得星期几
     *
     * @param date
     * @return
     */
    public int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获得日期
     *
     * @param date
     * @return
     */
    public int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DATE);
    }

    /**
     * 获得天数差
     *
     * @param begin
     * @param end
     * @return
     */
    public long getDayDiff(Date begin, Date end) {
        long day = 1;
        if (end.getTime() < begin.getTime()) {
            day = -1;
        } else if (end.getTime() == begin.getTime()) {
            day = 1;
        } else {
            day += (end.getTime() - begin.getTime()) / (24 * 60 * 60 * 1000);
        }
        return day;
    }

    /**
     * 24小时制 转换 12小时制
     *
     * @param time
     * @return
     */
    public static String time24To12(String time) {
        String str[] = time.split(":");
        int h = Integer.valueOf(str[0]);
        int m = Integer.valueOf(str[1]);
        String sx;
        if (h > 1) {
            h = 12;
            sx = "上午";
        } else if (h > 12) {
            sx = "上午";
        } else if (h > 13) {
            sx = "下午";
        } else {
            sx = "下午";
            h -= 12;
        }
        return String.format("%d:%02d%s", h, m, sx);
    }

    @SuppressLint("DefaultLocale")
    public static String time24For12(String time) {
        String str[] = time.split(":");
        int h = Integer.valueOf(str[0]);
        int m = Integer.valueOf(str[1]);
        String sx = null;
        if (h <= 12) {
            sx = "上午";
        } else if (h >= 13) {
            sx = "下午";
            h = h - 12;
        } else {
        }
        return sx + String.format("%02d:%02d%s", h, m, "");
    }

    /**
     * Date 转换 MM月dd日 星期
     *
     * @param date
     * @return
     */
    public static String date2MMddWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return new SimpleDateFormat("MM月dd日 星期").format(date) + WEEK[dayOfWeek - 1];
    }

    public static String dayOfWeek(int dayOfWeek) {
        dayOfWeek = dayOfWeek - 1;
        if (dayOfWeek < WEEK.length && dayOfWeek >= 0) {
            return WEEK[dayOfWeek];
        }
        return "";
    }

    /**
     * Date 转换 yyyy年MM月dd日 星期
     *
     * @param date
     * @return
     */
    public static String date2yyyyMMddWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return new SimpleDateFormat("yyyy年MM月dd日 星期").format(date) + WEEK[dayOfWeek - 1];
    }

    /**
     * Date 转换 yyyy/MM/dd HH:mm 星期
     *
     * @param date
     * @return
     */
    public static String date2yyyyMMddHHmmWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return new SimpleDateFormat("yyyy/MM/dd HH:mm 星期").format(date) + WEEK[dayOfWeek - 1];
    }

    /**
     * 判断两个时间是否是同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH);
        int day2 = calendar.get(Calendar.DAY_OF_MONTH);

        if (year1 == year2
                && month1 == month2
                && day1 == day2) {
            return true;
        }

        return false;
    }

    /**
     * 获得时间差
     *
     * @param endDate
     * @param nowDate
     * @return
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        String hr = "";
        String mu = "";
        String sc = "";
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        if (String.valueOf(hour).toString().length() == 1) {
            hr = "0" + hour;
        } else {
            hr = hour + "";
        }
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        if (String.valueOf(min).toString().length() == 1) {
            mu = "0" + min;
        } else {
            mu = min + "";
        }
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        if (String.valueOf(sec).toString().length() == 1) {
            sc = "0" + sec;
        } else {
            sc = sec + "";
        }
        return hr + ":" + mu + ":" + sc + "";
    }
}
