/*
 * FileName:    VoiceEventHandleStatusConverter.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月15日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.reference;

import javax.persistence.AttributeConverter;

import cn.com.chaochuang.common.dictionary.EnumDictConverter;

/**
 * @author LLM
 *
 */
public class VoiceEventHandleStatusConverter extends EnumDictConverter<VoiceEventHandleStatus> implements
                AttributeConverter<VoiceEventHandleStatus, String> {

}
