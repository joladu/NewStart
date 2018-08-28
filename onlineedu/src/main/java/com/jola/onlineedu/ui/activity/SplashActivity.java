package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.view.View;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.StatusBarUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by jola on 2018/8/21.
 */

public class SplashActivity extends SimpleActivity {



    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEventAndData() {

//        ImmersionBar.with(this)
//                .statusBarDarkFont(true, 0.2f)
//                .init();

//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        StatusBarUtil.setStatusBarBlack(this);

        addSubscribe(Flowable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                       startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                       SplashActivity.this.finish();
                    }
                })
        );
    }

//    @Override
//    protected void onDestroy() {
//        ImmersionBar.with(this).destroy();
//        super.onDestroy();
//    }






}
