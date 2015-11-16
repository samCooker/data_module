/*
 * FileName:    Tools.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月28日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author LLM
 *
 */
public abstract class Tools {
    /** 按 yyyy-MM-dd HH:mm:ss 格式化时间. */
    public static final SimpleDateFormat DATE_TIME_FORMAT    = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** 按 yyyyMMddHHmmss 格式化时间. */
    public static final SimpleDateFormat DATE_TIME_FORMAT2   = new SimpleDateFormat("yyyyMMddHHmmss");
    /** 按 yyyy-MM-dd HH:mm 格式化时间. */
    public static final SimpleDateFormat DATE_MINUTE_FORMAT  = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /** 按 yyyy-MM-dd 格式化日期. */
    public static final SimpleDateFormat DATE_FORMAT         = new SimpleDateFormat("yyyy-MM-dd");
    /** 按 yyyy/MM/dd 格式化日期. */
    public static final SimpleDateFormat DATE_FORMAT2        = new SimpleDateFormat("yyyy/MM/dd");
    /** 按 yyyy年MM月dd日 格式化日期. */
    public static final SimpleDateFormat DATE_FORMAT3        = new SimpleDateFormat("yyyy年MM月dd日");
    /** 按 yyyyMMdd 格式化日期. */
    public static final SimpleDateFormat DATE_FORMAT4        = new SimpleDateFormat("yyyyMMdd");
    /** 按 yyyyMMdd 格式化日期. */
    public static final SimpleDateFormat DATE_FORMAT5        = new SimpleDateFormat("HH:mm");
    /** 数值格式化 */
    public static final DecimalFormat    DECIMAL_FORMAT      = new DecimalFormat("#0.######");
    /** 两位小数标准格式化 */
    public static final DecimalFormat    STANDARD_DEC_FORMAT = new DecimalFormat("#0.00");

    /**
     * 将字符串数组转换成List
     *
     * @param strAry
     *            字符串数组
     * @return List strAry中的元素
     */
    public static List getListFromArray(Object[] strAry) {
        List tmpList = new ArrayList();
        for (int i = 0; i < strAry.length; i++) {
            tmpList.add(strAry[i]);
        }
        return tmpList;
    }

    /**
     * 将列表中对象的部分属性数据转换成数组
     *
     * @param src
     *            源列表
     * @param property
     *            对象属性
     * @return 数组
     */
    public static Object[] changeListToArray(List src, String property) {
        if (property == null) {
            return src.toArray();
        }
        BeanWrapper infoWrapper;
        Object[] result = new Object[src.size()];
        for (int i = 0; i < src.size(); ++i) {
            infoWrapper = new BeanWrapperImpl(src.get(i));
            result[i] = infoWrapper.getPropertyValue(property);
        }
        return result;
    }

    /**
     * 将字符数组拼凑成字符串
     *
     * @param src
     *            源数组
     * @param splitChr
     *            元素之间的分隔符
     * @param quote
     *            是否用单引号括起元素
     * @return 拼凑成的字符串
     */
    public static String changeArrayToString(Object[] src, String splitChr, boolean quote) {
        List tmpList = getListFromArray(src);
        return changeArrayToString(tmpList, splitChr, quote);
    }

    /**
     * 将字符数组拼凑成字符串,要去空格
     *
     * @param src
     *            源数组
     * @param splitChr
     *            元素之间的分隔符
     * @param quote
     *            是否用单引号括起元素
     * @return 拼凑成的字符串
     */
    public static String changeArrayToStringTrim(Object[] src, String splitChr, boolean quote) {
        List tmpList = getListFromArray(src);
        return changeArrayToStringTrim(tmpList, splitChr, quote);
    }

    /**
     * 将列表拼凑成字符串,要去空格
     *
     * @param src
     *            源数组
     * @param splitChr
     *            元素之间的分隔符
     * @param quote
     *            是否用单引号括起元素
     * @return 拼凑成的字符串
     */
    public static String changeArrayToStringTrim(List src, String splitChr, boolean quote) {
        if (!Tools.isNotEmptyList(src)) {
            return "";
        }
        StringBuffer buffer = new StringBuffer("");
        Object obj;
        for (int i = 0; i < src.size(); ++i) {
            obj = (src.get(i) == null) ? src.get(i) : src.get(i).toString().trim();
            buffer.append(quote ? "'" : "").append(obj).append(quote ? "'" : "")
                            .append((splitChr != null && i < src.size() - 1) ? splitChr : "");
        }
        return buffer.toString();
    }

    /**
     * 将字符数组拼凑成字符串
     *
     * @param src
     *            源数组
     * @param splitChr
     *            元素之间的分隔符
     * @param quote
     *            是否用单引号括起元素
     * @return 拼凑成的字符串
     */
    public static String changeArrayToString(List src, String splitChr, boolean quote) {
        if (!Tools.isNotEmptyList(src)) {
            return "";
        }
        StringBuffer buffer = new StringBuffer("");
        for (int i = 0; i < src.size(); ++i) {
            buffer.append(quote ? "'" : "").append(src.get(i)).append(quote ? "'" : "")
                            .append((splitChr != null && i < src.size() - 1) ? splitChr : "");
        }
        return buffer.toString();
    }

    /**
     * 将对象列表指定的属性拼凑成字符串
     *
     * @param src
     *            对象数组
     * @param property
     *            要组装的对象属性
     * @param splitChr
     *            元素之间的分隔符
     * @param quote
     *            是否用单引号括起元素
     * @return 拼凑成的字符串
     */
    public static String changeArrayToString(List src, String property, String splitChr, boolean quote) {
        StringBuffer buffer = new StringBuffer("");
        BeanWrapper infoWrapper;
        for (int i = 0; i < src.size(); ++i) {
            infoWrapper = new BeanWrapperImpl(src.get(i));
            buffer.append(quote ? "'" : "").append(infoWrapper.getPropertyValue(property)).append(quote ? "'" : "")
                            .append((splitChr != null && i < src.size() - 1) ? splitChr : "");
        }
        return buffer.toString();
    }

    /**
     * 将Map指定的属性拼凑成字符串
     *
     * @param src
     *            Map
     * @param splitChr
     *            元素之间的分隔符
     * @param key
     *            是否取Map的key来组装
     * @param quote
     *            是否用单引号括起元素
     * @return 拼凑成的字符串
     */
    public static String changeArrayToString(Map src, String splitChr, boolean key, boolean quote) {
        StringBuffer buffer = new StringBuffer("");
        for (Iterator iter = src.entrySet().iterator(); iter.hasNext();) {
            Map.Entry element = (Map.Entry) iter.next();
            if (key) {
                buffer.append(quote ? "'" : "").append(element.getKey()).append(quote ? "'" : "")
                                .append((splitChr != null && iter.hasNext()) ? splitChr : "");
            } else {
                buffer.append(quote ? "'" : "").append(element.getValue()).append(quote ? "'" : "")
                                .append((splitChr != null && iter.hasNext()) ? splitChr : "");
            }
        }
        return buffer.toString();
    }

    /**
     * 判断字符串是否为空（null和零长度的字符串都是空）
     *
     * @param str
     *            源字符串
     * @return true：空字符串 false：非空
     */
    public static boolean isEmptyString(String str) {
        boolean result = true;

        if (str == null || str.trim().length() == 0) {
            return result;
        }
        return false;
    }

    /**
     * 判断字符串是否为空（null和零长度的字符串都是空）并且要等于equ指定的字符串
     *
     * @param str
     *            源字符串
     * @param equ
     *            要相等的字符串
     * @return true：等于equ字符串 false：空或不等于equ
     */
    public static boolean isNotEmptyString(String str, String equ) {
        if (isEmptyString(str) || isEmptyString(equ)) {
            return false;
        }
        return str.equals(equ);
    }

    /**
     * 判断list是否为非空列表（null和零长度的字符串都是空）
     *
     * @param list
     *            源列表
     * @return true：非空列表 false：空列表
     */
    public static boolean isNotEmptyList(List list) {
        boolean result = true;

        if (list != null && !list.isEmpty()) {
            return result;
        }
        return false;
    }

    /**
     * 判断map是否为非空Map（null和零长度的字符串都是空）
     *
     * @param map
     *            源map
     * @return true：非空map false：空map
     */
    public static boolean isNotEmptyMap(Map map) {
        boolean result = true;

        if (map != null && !map.isEmpty()) {
            return result;
        }
        return false;
    }

    /**
     * 判断set是否为非空Set（null和零长度的字符串都是空）
     *
     * @param set
     *            源set
     * @return true：非空set false：空set
     */
    public static boolean isNotEmptySet(Set set) {
        boolean result = true;

        if (set != null && !set.isEmpty()) {
            return result;
        }
        return false;
    }

    /**
     * 返回截断的字符串
     *
     * @param source
     *            源字符串
     * @param start
     *            截断起始位置
     * @param end
     *            截断终止位置
     * @return 截断的字符串
     */
    public static String substring(String source, int start, int end) {
        if (Tools.isEmptyString(source)) {
            return "";
        }
        if (start >= source.length()) {
            return "";
        }
        if (end >= source.length()) {
            return source.substring(start, source.length());
        }
        return source.substring(start, end);
    }

    /**
     * 获取类名（去掉包前缀）
     *
     * @param className
     *            原类名
     * @return 类名
     */
    public static String getClassName(String className) {
        String[] paths = className.split("\\.");
        String tmps = className;
        if (paths.length > 1) {
            tmps = paths[paths.length - 1];
        }
        return tmps;
    }

    /**
     * 字符转换 全角转成半角
     *
     * @param source
     *            全角字符串
     * @return 半角字符串
     */
    public static String transSBCToDBC(String source) {
        if (Tools.isEmptyString(source)) {
            return "";
        }
        String result = "";
        String temp = "";
        byte[] b = null;
        for (int i = 0; i < source.length(); i++) {
            try {
                temp = source.substring(i, i + 1);
                b = temp.getBytes("unicode");
            } catch (java.io.UnsupportedEncodingException e) {
                return source;
            }
            if (b[2] == -1) {
                b[3] = (byte) (b[3] + 32);
                b[2] = 0;
                try {
                    result += new String(b, "unicode");
                } catch (java.io.UnsupportedEncodingException e) {
                    return source;
                }
            } else {
                result += temp;
            }
        }
        return result;
    }

    /**
     * 日期相减 计算相差天数 endDate-startDate
     *
     * @param endDate
     *            结束日期
     * @param startDate
     *            开始日期
     * @return 天数
     */
    public static Integer subtractDate(Date endDate, Date startDate) {
        // date1和date2都不能为null 否则返回0
        if (endDate == null || startDate == null) {
            return new Integer(0);
        }
        Calendar temp = Calendar.getInstance();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        temp.setTime(endDate);
        end.set(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH), temp.get(Calendar.DATE), 23, 59, 59);
        start.setTime(startDate);

        double t = ((end.getTimeInMillis() - start.getTimeInMillis()) / (1000 * 60 * 60 * 24D));// 化为天
        return new Integer((int) Math.floor(t)); // 取最大天数，向上取整
        // return new Integer(new Long(t).intValue());
    }

    /**
     * 判断天数是否为负值，如果为负数就返回0
     *
     * @param date
     *            被减日期
     * @param date2
     *            要减日期
     * @return 天数
     */
    public static Integer subtractDateNegative(Date date1, Date date2) {
        Integer result = subtractDate(date1, date2);
        return (result.intValue() < 0) ? new Integer(-1) : result;
    }

    /**
     * 日期的加减
     *
     * @param sourceDate
     *            源日期
     * @param diff
     *            与源日期相差的天数
     * @return 最终日期
     */
    public static Date diffDate(Date sourceDate, int diff) {
        if (sourceDate == null) {
            sourceDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.DAY_OF_YEAR, diff);
        return c.getTime();
    }

    /**
     * 计算从今天至endDate（含endDate）有多少双休日
     *
     * @param endDate
     * @return
     */
    public static int countRestDay(Date endDate) {
        Integer lastDate = Tools.subtractDateNegative(endDate, new Date());
        int restDay = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // endDate当天也算工作日
        for (int i = 0; i < lastDate.intValue() + 1; i++) {
            // 从今天开始计算双休日数量
            if ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
                            || (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
                restDay += 1;
            }
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return restDay;
    }

    /**
     * 获取一个月的日期范围。如：2013-01-01至2013-02-01
     *
     * @return 字符串数组，数组第一个元素起始日期，数组第二个元素结束日期
     */
    public static Date[] getMonthRange() {
        Date[] result = new Date[2];
        // 起始时间为当前月份的1日
        String date1 = DATE_FORMAT.format(new Date());
        date1 = date1.substring(0, date1.length() - 2) + "01";
        Calendar cal = Calendar.getInstance();
        try {
            result[0] = DATE_FORMAT.parse(date1);
            cal.setTime(DATE_FORMAT.parse(date1));
            cal.add(Calendar.MONTH, 1);
            // 结束日期为当前月的下一月的1日
            result[1] = cal.getTime();
        } catch (Exception ex) {
            return null;
        }
        return result;
    }

    /**
     * 格式化文本，在文本的头部加两个汉字空格
     *
     * @param source
     * @return
     */
    public static String formatTextWhitespace(CharSequence source) {
        if (Tools.isEmptyString(source.toString())) {
            return "";
        }
        int idx = 0, strLen = source.length();
        for (int i = 0; i < strLen; i++) {
            if (!(Character.isWhitespace(source.charAt(i)) || source.charAt(i) == '　')) {
                return "　　" + source.subSequence(idx, strLen).toString();
            }
            idx++;
        }
        return source.toString();
    }

    /**
     * 判断日期是否是周末
     *
     * @param source
     * @return
     */
    public static boolean checkWeekend(String source) {
        Date date = null;
        try {
            date = DATE_FORMAT.parse(source);
        } catch (Exception ex) {

        }
        if (date == null) {
            return false;
        }
        return checkWeekend(date);
    }

    /**
     * 判断日期是否是周末
     *
     * @param date
     * @return
     */
    public static boolean checkWeekend(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0 || week == 6) {
            return true;
        }
        return false;
    }

    /**
     * 判断日期是否在时指定间段内
     *
     * @param compareDate
     *            比较日期
     * @param betweenDateLow
     *            时间段1
     * @param betweenDateHi
     *            时间段2
     * @return 比较结果：true compareDate在betweenDateLow和betweenDateHi之间
     */
    public static boolean isBetweenDate(Date compareDate, Date betweenDateLow, Date betweenDateHi) {
        boolean result = false;
        String comps = DATE_FORMAT.format(compareDate), dateLow = DATE_FORMAT.format(betweenDateLow), dateHi = DATE_FORMAT
                        .format(betweenDateHi);
        if (comps.compareTo(dateLow) >= 0 && comps.compareTo(dateHi) <= 0) {
            result = true;
        }
        return result;
    }

    /**
     * 在指定字符串上补充num个空格
     *
     * @param source
     *            原字符串
     * @param num
     *            需要补充的空格数
     * @return 补充空格后的字符串
     */
    public static String addBlack(String source, int num) {
        if (num <= 0) {
            return source;
        }
        if (Tools.isEmptyString(source)) {
            source = "";
        }
        for (int i = 0; i < num; i++) {
            source += " ";
        }
        return source;
    }

    /**
     * 转换文件大小
     *
     * @param size
     *            文件大小
     * @return 转换后字符串
     */
    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }

    /**
     * 分解字符串例如：oa_address_info_id=1152
     *
     * @param source
     * @return
     */
    public static Map<String, String> splitData(String source) {
        Map<String, String> result = new HashMap();
        if (Tools.isEmptyString(source)) {
            return result;
        }
        String[] items = source.split(",");
        for (String item : items) {
            String[] keys = item.split("=");
            result.put(keys[0], keys[1]);
        }
        return result;
    }

}
