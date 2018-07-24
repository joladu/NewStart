package com.jola.newnews.util;

import android.util.Log;

/**
 * Created by lenovo on 2018/7/19.
 */

public class LogUtil {

    private static boolean mIsDebug = true;
    private static final String mInterestingTag = "jola";

    public static void log(String logLevel, String tag, String logMsg) {
        if (!mIsDebug) {
            return;
        }
        switch (logLevel) {
            case "i":
                Log.i(tag, logMsg);
                break;
            case "d":
                Log.d(tag, logMsg);
                break;
            case "e":
                Log.e(tag, logMsg);
                break;
        }
    }

    public static void logInteresting(String logMsg) {
        if (!mIsDebug) {
            return;
        }
        Log.e(mInterestingTag, logMsg);
    }


}
