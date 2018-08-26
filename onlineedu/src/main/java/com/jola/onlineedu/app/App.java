package com.jola.onlineedu.app;

import android.app.Activity;
import android.app.Application;

import com.jola.onlineedu.di.component.AppComponent;
import com.jola.onlineedu.di.component.DaggerAppComponent;
import com.jola.onlineedu.di.module.AppModule;
import com.jola.onlineedu.di.module.HttpModule;
//import com.kk.taurus.ijkplayer.IjkPlayer;
import com.kk.taurus.playerbase.config.PlayerConfig;
import com.kk.taurus.playerbase.config.PlayerLibrary;
import com.kk.taurus.playerbase.entity.DecoderPlan;
import com.kk.taurus.playerbase.log.PLog;

import java.util.HashSet;
import java.util.Set;

import io.realm.Realm;

/**
 * Created by jola on 2018/8/7.
 */

public class App extends Application {

    /**
     * ijk 解码
     */
    public static final int PLAN_ID_IJK = 1;
    /**
     * exo 解码备用
     */
//    public static final int PLAN_ID_EXO = 2;

    private static App instance;

    /**
     * 是否忽略移动网络下播放警告
     */
    public static boolean ignoreMobile;

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

        PLog.LOG_OPEN = true;

        initVideoPlay();

        //初始化数据库
        Realm.init(this);

//        初始化Stetho
//        Stetho.initializeWithDefaults(this);


    }

    private void initVideoPlay() {
//        PlayerConfig.addDecoderPlan(new DecoderPlan(PLAN_ID_IJK, IjkPlayer.class.getName(), "IjkPlayer"));
//        PlayerConfig.addDecoderPlan(new DecoderPlan(PLAN_ID_EXO, ExoMediaPlayer.class.getName(), "ExoPlayer"));
//        PlayerConfig.setDefaultPlanId(PLAN_ID_IJK);

        //use default NetworkEventProducer.
        PlayerConfig.setUseDefaultNetworkEventProducer(true);

        PlayerLibrary.init(this);
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
