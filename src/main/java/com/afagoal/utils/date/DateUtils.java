package com.afagoal.utils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by BaoCai on 18/4/17.
 * Description:
 */
public class DateUtils {

    private static final String DATETIME_FORMATTER = "yyyy-MM-dd hh:mm:ss";
    private static final String DATE_FORMATTER = "yyyy-MM-dd";
    private static final DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMATTER);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    public static LocalDateTime valueOfDateTime(String string) {
        checkNull(string);
        return LocalDateTime.parse(string, datetimeFormatter);
    }

    public static String valueOfString(LocalDateTime time) {
        checkNull(time);
        return time.format(datetimeFormatter);
    }

    public static LocalDate valueOfDate(String str) {
        checkNull(str);
        return LocalDate.parse(str, dateFormatter);
    }

    public static String valueOfString(LocalDate date) {
        checkNull(date);
        return date.format(dateFormatter);
    }

    public static void checkNull(Object object) {
        if (null == object) {
            throw new RuntimeException("parse object can't be null !");
        }
    }
}
