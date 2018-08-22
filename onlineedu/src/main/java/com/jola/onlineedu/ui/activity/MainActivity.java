package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.ui.activity.VideoViewActivity;
import com.jola.onlineedu.util.RxUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class MainActivity extends SimpleActivity {

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar,"首页");


//        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
//                .compose(RxUtil.<Long>rxSchedulerHelper())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) {
//                        mView.jumpToMain();
//                    }
//                })
//        );

        Flowable.timer(2000,TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        startActivity(new Intent(MainActivity.this, VideoViewActivity.class));
                    }
                }).dispose();


    }





}
