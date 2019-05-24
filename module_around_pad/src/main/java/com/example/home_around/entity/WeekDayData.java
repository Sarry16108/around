package com.example.home_around.entity;

import com.example.lib_generic.utils.LogUtils;

public class WeekDayData {
    private String weekDay;
    private String day;
    private String month;

    public WeekDayData(String weekDay, String day, String month) {
        this.weekDay = weekDay;
        this.day = day;
        this.month = month;
    }

    public WeekDayData(int weekDay, String day, String month) {
        this.weekDay = getWeekday(weekDay);
        this.day = day;
        this.month = month;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    private String getWeekday(int weekday) {
        switch (weekday) {
            case 1:
                return "七";
            case 2:
                return "一";
            case 3:
                return "二";
            case 4:
                return "三";
            case 5:
                return "四";
            case 6:
                return "五";
            case 7:
                return "六";
        }

        return "";
    }
}
