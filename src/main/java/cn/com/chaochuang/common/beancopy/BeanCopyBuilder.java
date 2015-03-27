/*
 * FileName:    BeanCopyBuilder.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年1月8日 (guig) 1.0 Create
 */

package cn.com.chaochuang.common.beancopy;

import java.util.List;

/**
 * @author guig
 *
 */
public class BeanCopyBuilder {

    private BeanCopyBuilder() {
    }

    public static <ST, DT> List<DT> buildList(final List<ST> sourList, final Class<DT> targetClazz,
                    BeanCopyCallback<ST, DT> callback) {
        return new BeanCopyList(sourList, targetClazz, new BeanCopyCallbackBuilder<ST, DT>(callback));
    }

    public static <T> List<T> buildList(final List sourList, final Class<T> targetClazz) {
        return new BeanCopyList(sourList, targetClazz);
    }

    public static <T> T buildObject(final Object sourObject, final Class<T> targetClazz) {
        return BeanCopyUtil.copyBean(sourObject, targetClazz);
    }

}
