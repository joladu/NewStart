package com.jola.newnews.mode.http.api;

import com.jola.newnews.mode.bean.DailyListBean;
import com.jola.newnews.mode.bean.VersionBean;
import com.jola.newnews.mode.bean.WelcomeBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lenovo on 2018/7/18.
 */

public interface IZhiHuApis {

    public static final String HOST = "http://news-at.zhihu.com/api/4/";

    @GET("start-image/{res}")
    Flowable<WelcomeBean> getWelcomInfo(@Path("res") String res);



    @GET("news/latest")
    Flowable<DailyListBean> getDailyList();



}
