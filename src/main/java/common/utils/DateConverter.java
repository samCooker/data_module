package common.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2017/1/31
 *
 * @author Shicx
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if(source!=null&&!"".equals(source.trim())) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            try {
                return dateFormat.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
