package com.demo.enums;

public enum WeekDay {
    MONDAY("星期一", 1);

    private String week;
    private Integer day;

    WeekDay(String week, Integer day) {
        this.week = week;
        this.day = day;
    }
}
