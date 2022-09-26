package com.prueba;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date extends java.util.Date {

    public static void main(String[] args) {
        // System.out.println(convertDate("2022-08-24 00:00:00"));
    }

    public static java.util.Date convertDate(String date) {
        java.util.Date newDate = new Date();
        SimpleDateFormat format = null;
        if (date.contains(":")) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            format = new SimpleDateFormat("yyyy-MM-dd");
        }
        try {
            newDate = format.parse(date);
        } catch (ParseException e) {
            newDate = addDay(new Date(), 15);
        }
        return newDate;
    }

    public static java.util.Date addDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        return c.getTime();
    }

}
