package com.jola.newnews.app;

import android.app.Activity;
import android.app.Application;

import com.jola.newnews.di.component.AppComponent;
import com.jola.newnews.di.component.DaggerAppComponent;
import com.jola.newnews.di.module.AppModule;
import com.jola.newnews.di.module.HttpModule;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lenovo on 2018/7/17.
 */

public class App extends Application {

    private static App mInstance;


    private AppComponent mAppComponent;

    public  AppComponent getAppComponent(){
        if (null == mAppComponent){
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(mInstance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return mAppComponent;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }


    public static synchronized App getInstance(){
        return mInstance;
    }

    private Set<Activity> mAllActivitiesSet;

    public void addActivity(Activity activity){
        if (null == mAllActivitiesSet){
            mAllActivitiesSet = new HashSet<>();
        }
        mAllActivitiesSet.add(activity);
    }

    public void removeActivity(Activity activity){
        if (null != mAllActivitiesSet){
            mAllActivitiesSet.remove(activity);
        }
    }


}
