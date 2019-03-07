package com.project.arielfaridja.Operator.model.Entities;

/**
 * Created by c plus on 05/12/2016.
 */

public class Date {
    int year;
    int month;
    int day;

    public Date() {
        year = 0;
        month = 0;
        day = 0;
    }

    public Date(int day, int month, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    static public Date parse(String s) {
        Date d = new Date();
        String[] parts = s.split("/");
        d.setDay(Integer.parseInt(parts [0]));
        d.setMonth(Integer.parseInt(parts [1]));
        d.setYear(Integer.parseInt(parts [2]));
        return d;
    }

    @Override
    public String toString() {
        return (String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
    }
}
