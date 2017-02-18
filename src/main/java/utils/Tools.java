/*
 * FileName:    Tools.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月28日 (LLM) 1.0 Create
 */

package utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author LLM
 *
 */
public abstract class Tools {
    /** 按 yyyy-MM-dd HH:mm:ss 格式化时间. */
    public static final SimpleDateFormat DATE_TIME_FORMAT    = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** 按 yyyy-MM-dd HH:mm 格式化时间. */
    public static final SimpleDateFormat DATE_MINUTE_FORMAT  = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /** 数值格式化 */
    public static final DecimalFormat    DECIMAL_FORMAT      = new DecimalFormat("#0.######");
    /** 两位小数标准格式化 */
    public static final DecimalFormat    STANDARD_DEC_FORMAT = new DecimalFormat("#0.00");

}
