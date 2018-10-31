package com.jola.shengfan.skills.ui_adapter;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.bumptech.glide.load.engine.Resource;
import com.jola.shengfan.skills.R;

public class UiAdapterActivity extends AppCompatActivity {

    private  static Application application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_adapter);
    }

    private void touTiaoAdapterSolution(){
//        px = dp * density = dp * (dpi / 160)
//        宽度：1080px;匹配 xxhdpi 1dp = 3px; 宽度 1080px/3(pd/dp) = 360dp(屏幕宽度一般范围 300-460)
//         设计图px,android使用dp，为保持px 与 dp 对应关系不变，只有动态修改density（系统默认density = dpi/160）
    }


    // 1、加载了webView ,解决方法：重写webview onOverScrollMode() 中再次调用 adapterScreen()方法
//    2、如何使系统view 不受影响： 调用系统相关view 前 调用取消适配 cancelAdapter；调用完成后 再次调用 adapterScreen()方法
    public static void adapterScreen(Activity activity ,int sizeInPix,boolean isVerticalSlide){

        DisplayMetrics systemDisplayMetrics = Resources.getSystem().getDisplayMetrics();
        DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();

//        修改activity density
        if (isVerticalSlide){
            activityDisplayMetrics.density = activityDisplayMetrics.widthPixels / (float)sizeInPix;
        }else{
            activityDisplayMetrics.density = activityDisplayMetrics.heightPixels / (float)sizeInPix;
        }
//     按system比例修改 activity scaleDensity
        activityDisplayMetrics.scaledDensity = activityDisplayMetrics.density / systemDisplayMetrics.density * systemDisplayMetrics.scaledDensity;
//        修改activity densityDpi
        activityDisplayMetrics.densityDpi = (int)(activityDisplayMetrics.density * 160);

//        修改对应Application值
        appDisplayMetrics.density = activityDisplayMetrics.density;
        appDisplayMetrics.scaledDensity = activityDisplayMetrics.scaledDensity;
        appDisplayMetrics.densityDpi = activityDisplayMetrics.densityDpi;

    }




    public static void cancelAdapte(Activity activity){
        DisplayMetrics systemDm = Resources.getSystem().getDisplayMetrics();
        DisplayMetrics appDm = application.getResources().getDisplayMetrics();
        DisplayMetrics activityDm = activity.getResources().getDisplayMetrics();

        appDm.density = systemDm.density;
        appDm.scaledDensity = systemDm.scaledDensity;
        appDm.densityDpi = systemDm.densityDpi;

        activityDm.density = systemDm.density;
        activityDm.scaledDensity = systemDm.scaledDensity;
        activityDm.densityDpi = systemDm.densityDpi;

    }

    public static boolean isScreenAdapted(){
        DisplayMetrics systemDm = Resources.getSystem().getDisplayMetrics();
        DisplayMetrics appDm = application.getResources().getDisplayMetrics();
        return  systemDm.density != appDm.density;
    }









}
