/*
 * FileName:    XmlPullParserException.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.apkmanage.util.org.xmlpull.v1;

/**
 * @author LLM
 *
 */
public class XmlPullParserException extends Exception {
    protected Throwable detail;
    protected int       row    = -1;
    protected int       column = -1;

    public XmlPullParserException(String s) {
        super(s);
    }

    public XmlPullParserException(String msg, XmlPullParser parser, Throwable chain) {
        super((msg == null ? "" : new StringBuilder(String.valueOf(msg)).append(" ").toString())
                        + (parser == null ? "" : new StringBuilder("(position:")
                                        .append(parser.getPositionDescription()).append(") ").toString())
                        + (chain == null ? "" : new StringBuilder("caused by: ").append(chain).toString()));

        if (parser != null) {
            this.row = parser.getLineNumber();
            this.column = parser.getColumnNumber();
        }
        this.detail = chain;
    }

    public Throwable getDetail() {
        return this.detail;
    }

    public int getLineNumber() {
        return this.row;
    }

    public int getColumnNumber() {
        return this.column;
    }

    public void printStackTrace() {
        if (this.detail == null)
            super.printStackTrace();
        else
            synchronized (System.err) {
                System.err.println(super.getMessage() + "; nested exception is:");
                this.detail.printStackTrace();
            }
    }
}
