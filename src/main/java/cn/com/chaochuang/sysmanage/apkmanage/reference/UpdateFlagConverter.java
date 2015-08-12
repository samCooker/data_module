/*
 * FileName:    UpdateFlagConverter.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.apkmanage.reference;

import javax.persistence.AttributeConverter;

import cn.com.chaochuang.common.dictionary.EnumDictConverter;

/**
 * @author LLM
 *
 */
public class UpdateFlagConverter extends EnumDictConverter<UpdateFlag> implements
                AttributeConverter<UpdateFlag, String> {

}
