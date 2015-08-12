/*
 * FileName:    ChunkUtil.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.apkmanage.util.android.content.res;

import java.io.IOException;

/**
 * @author LLM
 *
 */
public class ChunkUtil {
    public static final void readCheckType(IntReader reader, int expectedType) throws IOException {
        int type = reader.readInt();
        if (type != expectedType)
            throw new IOException("Expected chunk of type 0x" + Integer.toHexString(expectedType) + ", read 0x"
                            + Integer.toHexString(type) + ".");
    }
}
