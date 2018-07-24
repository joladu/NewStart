package com.jola.newnews.mode.http.api;

import android.icu.util.VersionInfo;

import com.jola.newnews.mode.bean.VersionBean;
import com.jola.newnews.mode.http.response.MyHttpResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by lenovo on 2018/7/24.
 */

public interface IMyApis {

    String HOST = "http://codeest.me/api/geeknews/";

    String APK_DOWNLOAD_URL = "http://codeest.me/apk/geeknews.apk";

    @GET("version")
    Flowable<MyHttpResponse<VersionBean>> getVersionInfo();


}
