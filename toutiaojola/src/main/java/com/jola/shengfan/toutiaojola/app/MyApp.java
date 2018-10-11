package com.jola.shengfan.toutiaojola.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;


/**
 * Created by lenovo on 2018/10/11.
 */

public class MyApp extends Application {

    private static Context appContext;
    private static Thread mainThread;
    private static Handler mainHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
        mainThread = Thread.currentThread();
        mainHandler = new Handler();

    }

    public void restartApp(){
        Intent launchIntentForPackage = appContext.getPackageManager().getLaunchIntentForPackage(appContext.getPackageName());
        launchIntentForPackage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        appContext.startActivity(launchIntentForPackage);
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static Handler getMainHandler() {
        return mainHandler;
    }


}
