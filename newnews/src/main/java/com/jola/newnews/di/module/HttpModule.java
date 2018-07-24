package com.jola.newnews.di.module;

import com.jola.newnews.BuildConfig;
import com.jola.newnews.app.Constants;
import com.jola.newnews.di.qualifier.MyUrl;
import com.jola.newnews.di.qualifier.ZhiHuUrl;
import com.jola.newnews.mode.http.api.IMyApis;
import com.jola.newnews.mode.http.api.IZhiHuApis;
import com.jola.newnews.util.CommonUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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
 * Created by lenovo on 2018/7/18.
 */

@Module
public class HttpModule {

//  *************************************** begin 对外提供


    @Provides
    @Singleton
    IZhiHuApis provideIZhiHuApis(@ZhiHuUrl  Retrofit retrofit){
        return retrofit.create(IZhiHuApis.class);
    }

    @Provides
    @Singleton
    IMyApis provideIMyApis(@MyUrl Retrofit retrofit){
        return retrofit.create(IMyApis.class);
    }


//  ***************************************  end 对外提供





//  ***************************************  begin 对内提供

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuild(){
        return new Retrofit.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpClientBuilder(){
        return new OkHttpClient.Builder();
    }



    @Provides
    @Singleton
    @ZhiHuUrl
    Retrofit provideZhiHuRetrofit(Retrofit.Builder retrofitBuilder, OkHttpClient okHttpClient){
        return createRetrofit(retrofitBuilder,okHttpClient,IZhiHuApis.HOST);
    }

    @Provides
    @Singleton
    @MyUrl
    Retrofit provideMyRetrofit(Retrofit.Builder retrofitBuilder,OkHttpClient okHttpClient){
        return createRetrofit(retrofitBuilder,okHttpClient,IMyApis.HOST);
    }



    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder){
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!CommonUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (CommonUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
//        Interceptor apikey = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                request = request.newBuilder()
//                        .addHeader("apikey",Constants.KEY_API)
//                        .build();
//                return chain.proceed(request);
//            }
//        }
//        设置统一的请求头部参数
//        builder.addInterceptor(apikey);
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }





    private Retrofit createRetrofit(Retrofit.Builder retrofitBuilder,OkHttpClient okHttpClient,String url){
        return retrofitBuilder
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

//    ***************************************  end 对内提供



}
