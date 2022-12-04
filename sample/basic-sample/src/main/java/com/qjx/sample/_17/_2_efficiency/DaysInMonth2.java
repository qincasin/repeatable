package com.qjx.sample._17._2_efficiency;

import java.util.Calendar;

/**
 * @author: qinjiaxing
 * @Date: 2022/12/3 09:56
 * @Description:
 */
public class DaysInMonth2 {
    public static void main(String[] args) {
        Calendar today = Calendar.getInstance();
        int month = today.get(Calendar.MONTH);
        int year = today.get(Calendar.YEAR);

        int daysInMonth = switch (month) {
            case Calendar.JANUARY,
                Calendar.MARCH,
                Calendar.MAY,
                Calendar.JULY,
                Calendar.AUGUST,
                Calendar.OCTOBER,
                Calendar.DECEMBER -> 31;
            case Calendar.APRIL,
                Calendar.JUNE,
                Calendar.SEPTEMBER,
                Calendar.NOVEMBER -> 30;
            case Calendar.FEBRUARY -> {
                if (((year % 4 == 0) && (!(year % 100 == 0)) || (year % 400 == 0))) {
                    yield 29;
                } else {
                    yield 28;
                }
            }
            default -> throw new RuntimeException("error");
        };

        System.out.println("There are " + daysInMonth + " days in this month.");

    }
}
