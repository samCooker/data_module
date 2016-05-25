package utils;

import java.util.Calendar;

/**
 * Created by Cookie on 2016/5/3.
 */
public class CalendarHelper {

    /**
     * 节假日日期
     */
    public static int[][] DAY_OF_FESTIVAL= {{1,2,3},{7,8,9,10,11,12,13},{},{2,3,4,30},{1,2},{9,10,11},{},{},{15,16,17},{1,2,3,4,5,6,7},{},{}};
    /**
     * 加班日期
     */
    public static int[][] DAY_OF_EXTRA_WORK= {{},{6},{},{},{},{12},{},{},{19},{8,9},{},{}};

    /**
     * 判断该日期是否是周末
     * @param calendar 为null则默认当前时间
     * @return
     */
    public static boolean isWeekend(Calendar calendar){
        if(calendar==null){
            calendar = Calendar.getInstance();
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek==Calendar.SUNDAY||dayOfWeek==Calendar.SATURDAY){
            return true;
        }
        return false;
    }

    /**
     * 判断该日期是否是节假日
     * @param calendar 为null则默认当前时间
     * @return
     */
    public static boolean isFestivalDay(Calendar calendar){
        if(calendar==null){
            calendar = Calendar.getInstance();
        }
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        int[] festivalDateList = DAY_OF_FESTIVAL[month];
        for (int festivalDate : festivalDateList){
            if(festivalDate==date){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断该日期是否是需要加班
     * @param calendar 为null则默认当前时间
     * @return
     */
    public static boolean isExtraWorkDay(Calendar calendar){
        if(calendar==null){
            calendar = Calendar.getInstance();
        }
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        int[] extraWorkDate = DAY_OF_EXTRA_WORK[month];
        for (int workDate : extraWorkDate){
            if(workDate==date){
                return true;
            }
        }
        return false;
    }
}
