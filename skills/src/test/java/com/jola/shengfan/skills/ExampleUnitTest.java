package com.jola.shengfan.skills;

import org.junit.Test;

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
        String readInt = MoneyUtils.readInt(1004500813);
        System.out.println(readInt);

    }





    static class MoneyUtils {

        private static final char[] NUM = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        private static final char[] CHINESE_UNIT = {'元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿', '拾', '佰', '仟'};

        /**
         * 返回关于钱的中文式大写数字,支仅持到亿
         */
        public static String readInt(int moneyNum) {
            String res = "";
            int i = 0;
            if (moneyNum == 0) {
                return "0";
            }

            if (moneyNum == 10) {
                return "拾";
            }

            if (moneyNum > 10 && moneyNum < 20) {
                return "拾" + moneyNum % 10;
            }

            while (moneyNum > 0) {
                res = CHINESE_UNIT[i++] + res;
                res = NUM[moneyNum % 10] + res;
                moneyNum /= 10;
            }


            return res.replaceAll("0[拾佰仟]", "0")
                    .replaceAll("0+亿", "亿")
                    .replaceAll("0+万", "万")
                    .replaceAll("0+元", "元")
                    .replaceAll("0+", "0")
                    .replace("元", "");
        }
    }


}


