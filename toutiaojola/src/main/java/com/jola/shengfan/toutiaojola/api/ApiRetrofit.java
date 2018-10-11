package com.jola.shengfan.toutiaojola.api;

import com.jola.shengfan.toutiaojola.BuildConfig;
import com.jola.shengfan.toutiaojola.app.MyApp;
import com.jola.shengfan.toutiaojola.util.SystemUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2018/10/10.
 */

public class ApiRetrofit {

    private static volatile ApiRetrofit instance;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private ApiService apiService;

    public static ApiRetrofit getInstance(){
        if (null == instance){
            synchronized (ApiRetrofit.class){
                instance = new ApiRetrofit();
            }
        }
        return instance;
    }

    public ApiService getApiService(){
        return apiService;
    }

    private ApiRetrofit(){
        initApiRetrofit();
    }

    private Interceptor mHeadInterceptor = chain -> {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.108 Safari/537.36 2345Explorer/8.0.0.13547");
        builder.addHeader("Cache-Control", "max-age=0");
        builder.addHeader("Upgrade-Insecure-Requests", "1");
        builder.addHeader("X-Requested-With", "XMLHttpRequest");
        builder.addHeader("Cookie", "uuid=\"w:f2e0e469165542f8a3960f67cb354026\"; __tasessionId=4p6q77g6q1479458262778; csrftoken=7de2dd812d513441f85cf8272f015ce5; tt_webid=36385357187");
        return chain.proceed(builder.build());
    };

    private Interceptor mCacheInterceptor = chain -> {
        Request request = chain.request();
        boolean netWorkConnected = SystemUtil.isNetWorkConnected();
        if (!netWorkConnected){
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        if (netWorkConnected){
            response.newBuilder()
                    .header("Cache-Control","public,max_age=0")
                    .build();
        }else{
            response.newBuilder()
                    .header("Cache-Control","public,only-if-cached,max-stale="+30*24*60*60)
                    .build();
        }
        return response;
    };

    private void initApiRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(mHeadInterceptor);
        if (BuildConfig.DEBUG){
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        builder.cache(new Cache(new File(MyApp.getAppContext().getCacheDir(), "network"), 50 * 1024 * 1024));
        builder.addInterceptor(mCacheInterceptor);
        builder.addNetworkInterceptor(mCacheInterceptor);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20,TimeUnit.SECONDS);
        builder.writeTimeout(20,TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_SERVER_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }


}
