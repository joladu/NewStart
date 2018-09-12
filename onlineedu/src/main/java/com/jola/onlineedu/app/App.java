package com.jola.onlineedu.app;

import android.app.Activity;
import android.app.Application;

import com.jola.onlineedu.di.component.AppComponent;
import com.jola.onlineedu.di.component.DaggerAppComponent;
import com.jola.onlineedu.di.module.AppModule;
import com.jola.onlineedu.di.module.HttpModule;

import java.util.HashSet;
import java.util.Set;

import io.realm.Realm;


/**
 * Created by jola on 2018/8/7.
 */

public class App extends Application {



    private static App instance;


    private static AppComponent appComponent;

    public static AppComponent getAppComponent(){
        if (null == appComponent){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    private Set<Activity> mAllActivitySet;

    public static synchronized  App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化数据库
        Realm.init(this);
    }


    public void addActivity(Activity activity){
        if (null == mAllActivitySet){
            mAllActivitySet = new HashSet<>();
        }
        mAllActivitySet.add(activity);
    }

    public void removeActivy(Activity activity){
        if (null != mAllActivitySet){
            mAllActivitySet.remove(activity);
        }
    }

    public void exitApp(){
        if (null != mAllActivitySet){
            synchronized (mAllActivitySet){
                for (Activity activity : mAllActivitySet){
                    activity.finish();
                }
            }
        }
    }



}
