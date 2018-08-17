package com.jola.onlineedu.mode.http;

import com.jola.onlineedu.mode.bean.WelcomeBean;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by jola on 2018/8/14.
 */

public class RetrofitHelper implements HttpHelper {


    private MyApis mMyApiService;

    @Inject
    public RetrofitHelper(MyApis mMyApiService) {
        this.mMyApiService = mMyApiService;
    }

    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo() {
        return mMyApiService.getWelcomeInfo();
    }
}
