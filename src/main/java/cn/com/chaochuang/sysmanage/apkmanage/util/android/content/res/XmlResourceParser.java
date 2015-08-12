/*
 * FileName:    XmlResourceParser.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.apkmanage.util.android.content.res;

import cn.com.chaochuang.sysmanage.apkmanage.util.android.util.AttributeSet;
import cn.com.chaochuang.sysmanage.apkmanage.util.org.xmlpull.v1.XmlPullParser;

/**
 * @author LLM
 *
 */
public abstract interface XmlResourceParser extends XmlPullParser, AttributeSet {
    public abstract void close();
}
