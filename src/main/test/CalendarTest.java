import org.junit.Test;
import utils.CalendarHelper;

import java.util.Calendar;

/**
 * Created by Cookie on 2016/5/4.
 */
public class CalendarTest {

    //@Test
    public void getDayOfWeek(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,0,13);
        //一周星期，星期日从1开始
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int date = calendar.get(Calendar.DATE);
        System.out.println(dayOfWeek);
        System.out.println(dayOfMonth);
        System.out.println(date);
    }

    @Test
    public void calendarHelperTest(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,4,2);
        boolean isFestival = CalendarHelper.isFestivalDay(calendar);
        System.out.println(isFestival);
    }
}
