package com.jola.onlineedu.mode.http;

import com.jola.onlineedu.mode.bean.WelcomeBean;

import io.reactivex.Flowable;

/**
 * Created by lenovo on 2018/8/14
 * 所有网络请求接口
 */

public interface HttpHelper {

    Flowable<WelcomeBean> fetchWelcomInfo(String res);

}
