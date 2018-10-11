package com.jola.shengfan.toutiaojola.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jola.shengfan.toutiaojola.app.MyApp;

/**
 * Created by lenovo on 2018/10/11.
 */

public class SystemUtil {

    public static boolean isNetWorkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)MyApp.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivityManager){
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (null != activeNetworkInfo && activeNetworkInfo.isConnected()){
                return true;
            }
        }
        return false;
    }

}
