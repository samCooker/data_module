/*
 * FileName:    FlowTypeConverter.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年4月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docex.reference;

import javax.persistence.AttributeConverter;

import cn.com.chaochuang.common.dictionary.EnumDictConverter;

/**
 * @author LLM
 *
 */
public class DocexFlowTypeConverter extends EnumDictConverter<DocexFlowType> implements AttributeConverter<DocexFlowType, String> {

}
