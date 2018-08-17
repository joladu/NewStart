package com.jola.onlineedu.mode.http;

import com.jola.onlineedu.mode.bean.WelcomeBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jola on 2018/8/14.
 */

public interface MyApis {

//    String HOST = "http://news-at.zhihu.com/api/4/";
    String HOST = "http://192.168.7.153:8080/AnXinPay/AXPay/";


//    @GET("AXPay/testJola/{res}")
//    Flowable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

    @GET("testJola/")
    Flowable<WelcomeBean> getWelcomeInfo();

}
