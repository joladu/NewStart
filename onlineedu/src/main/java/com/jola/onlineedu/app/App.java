package com.jola.onlineedu.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.jola.onlineedu.R;
import com.jola.onlineedu.di.component.AppComponent;
import com.jola.onlineedu.di.component.DaggerAppComponent;
import com.jola.onlineedu.di.module.AppModule;
import com.jola.onlineedu.di.module.HttpModule;
//import com.kk.taurus.ijkplayer.IjkPlayer;
import com.kk.taurus.playerbase.config.PlayerConfig;
import com.kk.taurus.playerbase.config.PlayerLibrary;
import com.kk.taurus.playerbase.entity.DecoderPlan;
import com.kk.taurus.playerbase.log.PLog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.HashSet;
import java.util.Set;

import io.realm.Realm;

/**
 * Created by jola on 2018/8/7.
 */

public class App extends Application {

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

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
