/*
 * FileName:    FordoSourceConverter.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月22日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docwork.reference;

import javax.persistence.AttributeConverter;

import cn.com.chaochuang.common.dictionary.EnumDictConverter;

/**
 * @author Shicx
 *
 */
public class SubmitActionConverter extends EnumDictConverter<SubmitAction> implements
                AttributeConverter<SubmitAction, String> {

}