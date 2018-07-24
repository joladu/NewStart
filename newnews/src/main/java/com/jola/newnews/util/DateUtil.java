package com.jola.newnews.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2018/7/24.
 */

public class DateUtil {
    public static String getTomorrowDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return String.valueOf(Integer.valueOf(df.format(new Date())) + 1);
    }
}
