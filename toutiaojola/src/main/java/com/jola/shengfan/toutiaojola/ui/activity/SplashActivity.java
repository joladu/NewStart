package com.jola.shengfan.toutiaojola.ui.activity;

import android.content.Intent;

import com.jola.shengfan.toutiaojola.R;
import com.jola.shengfan.toutiaojola.app.MyApp;
import com.jola.shengfan.toutiaojola.ui.base.BaseActivity;
import com.jola.shengfan.toutiaojola.ui.base.BasePresenter;

/**
 * Created by lenovo on 2018/10/11.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        MyApp.getMainHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },2000);
    }
}
