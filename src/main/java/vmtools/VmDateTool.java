package vmtools;

import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.config.ValidScope;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2017/1/30
 *
 * @author Shicx
 */
@DefaultKey("dateTool")
@ValidScope({"application"})
public class VmDateTool {

    /**
     * 按照指定格式，格式化日期
     * @param dateFormat
     * @param date
     * @return
     */
    public String format(String dateFormat, Date date) {
        if (date != null) {
            if(dateFormat==null||"".equals(dateFormat.trim())){
                //默认
                dateFormat="yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            return simpleDateFormat.format(date);
        }
        return null;
    }
}
