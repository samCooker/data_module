/*
 * FileName:    NullBeanUtils.java
 * Description:
 * Company:     ����������Ϣ�������޹�˾
 * Copyright:   ChaoChuang (c) 2005
 * History:     2005-5-19 (guig) 1.0 Create
 */
package utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author guig
 * @version 1.0 2005-5-19
 */
public class NullBeanUtils extends BeanUtils {

    private static BeanUtilsBean beanUtilsBean;

    private static void initBeanUtilsBean(BeanUtilsBean bean) {
        ConvertUtilsBean cu = bean.getConvertUtils();

        cu.register(new BigDecimalConverter(null), BigDecimal.class);
        cu.register(new BigIntegerConverter(null), BigInteger.class);
        cu.register(new BooleanConverter(null), Boolean.TYPE);
        cu.register(new BooleanConverter(null), Boolean.class);
        cu.register(new ByteConverter(null), Byte.TYPE);
        cu.register(new ByteConverter(null), Byte.TYPE);
        cu.register(new CharacterConverter(null), Character.TYPE);
        cu.register(new CharacterConverter(null), Character.class);
        cu.register(new DoubleConverter(null), Double.TYPE);
        cu.register(new DoubleConverter(null), Double.class);
        cu.register(new FloatConverter(null), Float.TYPE);
        cu.register(new FloatConverter(null), Float.class);
        cu.register(new IntegerConverter(null), Integer.TYPE);
        cu.register(new IntegerConverter(null), Integer.class);
        cu.register(new LongConverter(null), Long.TYPE);
        cu.register(new LongConverter(null), Long.class);
        cu.register(new ShortConverter(null), Short.TYPE);
        cu.register(new ShortConverter(null), Short.class);
        cu.register(new DateConverter(null), Date.class);
    }

    /**
     * @return Returns the beanUtilsBean.
     */
    public static BeanUtilsBean getBeanUtilsBean() {
        if (null == beanUtilsBean) {
            beanUtilsBean = new BeanUtilsBean();
            initBeanUtilsBean(beanUtilsBean);
        }
        return beanUtilsBean;
    }

    /**
     * <p>
     * Copy property values from the origin bean to the destination bean for all cases where the property names are the
     * same.
     * </p>
     *
     * <p>
     * For more details see <code>BeanUtilsBean</code>.
     * </p>
     *
     * @see BeanUtilsBean#copyProperties
     */
    public static void copyProperties(Object dest, Object orig) {
        try {
            getBeanUtilsBean().copyProperties(dest, orig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>
     * Copy the specified property value to the specified destination bean, performing any type conversion that is
     * required.
     * </p>
     *
     * <p>
     * For more details see <code>BeanUtilsBean</code>.
     * </p>
     *
     * @see BeanUtilsBean#copyProperty
     */
    public static void copyProperty(Object bean, String name, Object value) {

        try {
            getBeanUtilsBean().copyProperty(bean, name, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
