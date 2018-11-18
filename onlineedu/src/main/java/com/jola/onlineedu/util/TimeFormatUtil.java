package com.jola.onlineedu.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jola on 2018/9/27.
 */

public class TimeFormatUtil {



//    "created": "2018-09-27T20:41:29",

    //    刚刚：10分钟内
//    一小时内：xx分钟前
//    24小时内：xx小时前
//    其它：年-月-日
    public static String formatTime(String timeStamp){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss",Locale.CHINA);
        try {
            Date publishDate = simpleDateFormat.parse(timeStamp);
            long offsetTime = date.getTime() - publishDate.getTime();
            if (offsetTime < 10 * 60 * 1000){
                return "刚刚";
            }else if (offsetTime < 60 * 60 * 1000){
                return offsetTime / (60 * 1000)+"分钟前";
            }else if (offsetTime < 24 * 60 * 60 * 1000){
                return offsetTime / (60 * 60 * 1000)+"小时前";
            }else{
                return timeStamp.substring(0,10);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "时间错误";
    }


//    "created": "2018-09-27T20:41:29.907",

//    刚刚：10分钟内
//    一小时内：xx分钟前
//    24小时内：xx小时前
//    其它：年-月-日
    public static String formatTimeSSS(String timeStamp){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS",Locale.CHINA);
        try {
            Date publishDate = simpleDateFormat.parse(timeStamp);
            long offsetTime = date.getTime() - publishDate.getTime();
            if (offsetTime < 10 * 60 * 1000){
                return "刚刚";
            }else if (offsetTime < 60 * 60 * 1000){
                return offsetTime / (60 * 1000)+"分钟前";
            }else if (offsetTime < 24 * 60 * 60 * 1000){
                return offsetTime / (60 * 60 * 1000)+"小时前";
            }else{
                return timeStamp.substring(0,10);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "时间错误";
    }
}
