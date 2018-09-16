package com.corkili.pa.dao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

    private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDateTime(Date datetime) {
        return dateTimeFormatter.format(datetime);
    }

    public static Date parseDateTime(String datetime) throws ParseException {
        return dateTimeFormatter.parse(datetime);
    }

}
