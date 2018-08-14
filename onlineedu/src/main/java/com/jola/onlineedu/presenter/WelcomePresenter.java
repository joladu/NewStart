package com.jola.onlineedu.presenter;

import android.util.Log;


import com.jola.onlineedu.base.RxPresenter;
import com.jola.onlineedu.contract.WelcomeContract;
import com.jola.onlineedu.util.RxUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by codeest on 16/8/15.
 */

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter{

    private static final String RES = "1080*1776";

    private static final int COUNT_DOWN_TIME = 2200;

//    private DataManager mDataManager;
//
//    @Inject
//    public WelcomePresenter(DataManager mDataManager) {
//        this.mDataManager = mDataManager;
//    }

    @Override
    public void getWelcomeData() {
//        addSubscribe(mDataManager.fetchWelcomeInfo(RES)
//                .compose(RxUtil.<WelcomeBean>rxSchedulerHelper())
//                .subscribe(new Consumer<WelcomeBean>() {
//                    @Override
//                    public void accept(WelcomeBean welcomeBean) {
//                        String img = welcomeBean.getImg();
//                        String text = welcomeBean.getText();
//                        Log.e("jola","WelcomPresenter:"+img+"  "+text);
//                        mView.showContent(welcomeBean);
//                        startCountDown();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) {
//                        mView.jumpToMain();
//                    }
//                })
//        );
    }

    private void startCountDown() {
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        mView.jumpToMain();
                    }
                })
        );
    }

}
