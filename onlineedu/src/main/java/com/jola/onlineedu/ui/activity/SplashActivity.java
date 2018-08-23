package com.jola.onlineedu.ui.activity;

import android.content.Intent;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.util.RxUtil;

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
}
