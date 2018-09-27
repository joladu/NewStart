package com.jola.onlineedu;

import com.jola.onlineedu.util.TimeFormatUtil;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);


//        String timeStamp = "2018-09-27T20:41:29.907";
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
//        try {
//            Date publishDate = simpleDateFormat.parse(timeStamp);
//            long offsetTime = date.getTime() - publishDate.getTime();
//            System.out.println(offsetTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        String result = TimeFormatUtil.formatTime("2018-09-28T00:05:55.407");
                    System.out.println(result);


    }
}