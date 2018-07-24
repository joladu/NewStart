package com.jola.newnews.mode.http;

import com.jola.newnews.mode.bean.DailyListBean;
import com.jola.newnews.mode.bean.VersionBean;
import com.jola.newnews.mode.bean.WelcomeBean;
import com.jola.newnews.mode.http.api.IMyApis;
import com.jola.newnews.mode.http.api.IZhiHuApis;
import com.jola.newnews.mode.http.response.MyHttpResponse;
import com.jola.newnews.util.LogUtil;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by lenovo on 2018/7/18
 * 使用Retrofit 封装 http请求
 */

public class RetrofitHelper implements IHttpHelper {

    public static final String TAG = "RetrofitHelper.class";

    private IZhiHuApis mIZhiHuApis;
    private IMyApis mIMyApis;

    /**
     * 哪里需要RetrofitHelper 就会自动向哪里注入，IZhiHuApis 已经由HttpModule提供注入
     */
    @Inject
    public RetrofitHelper(IZhiHuApis mIZhiHuApis,IMyApis mIMyApis) {
        LogUtil.logInteresting(TAG+"：创建RetrofitHelper");
        this.mIZhiHuApis = mIZhiHuApis;
        this.mIMyApis = mIMyApis;
    }

    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        LogUtil.logInteresting(TAG+"：fetchWelcomeInfo");
        return mIZhiHuApis.getWelcomInfo(res);
    }

    @Override
    public Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo() {
        LogUtil.logInteresting(TAG+"：fetchVersionInfo");
        return mIMyApis.getVersionInfo();
    }

    @Override
    public Flowable<DailyListBean> fetchDailyListBean() {
        return mIZhiHuApis.getDailyList();
    }


}
