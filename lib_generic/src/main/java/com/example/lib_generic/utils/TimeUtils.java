package com.example.lib_generic.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class TimeUtils {

    //秒数返回：X小时X分钟X秒
    public static String getFormatTime(int seconds) {
        StringBuilder builder = new StringBuilder();

        int hour = seconds / 3600;
        if (hour != 0) {
            builder.append(hour).append("小时");
        }

        int min = seconds / 60 % 60;
        if (hour == 0 && min != 0) {
            builder.append(min).append("分钟");
        }

        int sec = seconds % 60;
        if (sec != 0) {
            builder.append(sec).append("秒");
        }

        return builder.toString();
    }

    //当前时间格式化：yyyy/MM/dd hh:mm:ss
    public static String getCurrentTimeFormat () {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date today = new Date();
        return simpleDateFormat.format(today);
    }
}
