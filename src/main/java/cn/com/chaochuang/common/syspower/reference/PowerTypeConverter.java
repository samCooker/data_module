/*
 * FileName:    SexConverter.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2014
 * History:     2014年10月28日 (guig) 1.0 Create
 */

package cn.com.chaochuang.common.syspower.reference;

import javax.persistence.AttributeConverter;

import cn.com.chaochuang.common.dictionary.EnumDictConverter;

/**
 * @author guig
 *
 */
public class PowerTypeConverter extends EnumDictConverter<PowerType> implements AttributeConverter<PowerType, String> {
}
