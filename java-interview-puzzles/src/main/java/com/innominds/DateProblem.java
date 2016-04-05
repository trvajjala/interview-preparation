package com.innominds;

import java.util.Calendar;

public class DateProblem {
    public static void main(String[] args) {

        final Calendar calendar = Calendar.getInstance();

        calendar.set(1999, 12, 31); // yyyy,MM,dd months are 0-11 not 12

        System.out.println(calendar.get(Calendar.YEAR));

        System.out.println(calendar.getTime());
    }
}
