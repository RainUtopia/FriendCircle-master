package razerdp.friendcircle.utils;

/**
 * Created by 大灯泡 on 2016/2/25.
 * 时间工具类
 */
public class TimeUtil {
    static StringBuffer result = new StringBuffer();

    public static String getTimeString(long milliseconds) {
        result.delete(0, result.length());

        long time = System.currentTimeMillis() - (milliseconds * 1000);
        long mill = (long) Math.ceil(time / 1000);//秒前

        long minute = (long) Math.ceil(time / 60 / 1000.0f);// 分钟前

        long hour = (long) Math.ceil(time / 60 / 60 / 1000.0f);// 小时

        long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000.0f);// 天前

        if (day - 1 > 0 && day - 1 < 30) {
            result.append(day + "天");
        }
        else if (day - 1 >= 30) {
            result.append(Math.round((day - 1) / 30) + "个月");
        }
        else if (hour - 1 > 0) {
            if (hour >= 24) {
                result.append("1天");
            }
            else {
                result.append(hour + "小时");
            }
        }
        else if (minute - 1 > 0) {
            if (minute == 60) {
                result.append("1小时");
            }
            else {
                result.append(minute + "分钟");
            }
        }
        else if (mill - 1 > 0) {
            if (mill == 60) {
                result.append("1分钟");
            }
            else {
                result.append(mill + "秒");
            }
        }
        else {
            result.append("刚刚");
        }
        if (!result.toString().equals("刚刚")) {
            result.append("前");
        }
        return result.toString();
    }
}
