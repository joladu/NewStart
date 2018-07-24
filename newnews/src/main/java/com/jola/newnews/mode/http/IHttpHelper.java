package com.jola.newnews.mode.http;

import com.jola.newnews.mode.bean.DailyListBean;
import com.jola.newnews.mode.bean.VersionBean;
import com.jola.newnews.mode.bean.WelcomeBean;
import com.jola.newnews.mode.http.response.MyHttpResponse;

import io.reactivex.Flowable;

/**
 * Created by lenovo on 2018/7/18.
 * http 接口
 */

public interface IHttpHelper {
    Flowable<WelcomeBean> fetchWelcomeInfo(String res);

    Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo();

    Flowable<DailyListBean> fetchDailyListBean();
}
