package com.jola.shengfan.toutiaojola.api;

/**
 * Created by lenovo on 2018/10/10.
 */

public class ApiRetrofit {


    private static volatile ApiRetrofit instance;

    private ApiRetrofit(){}

    public ApiRetrofit getInstance(){
        if (null == instance){
            synchronized (ApiRetrofit.class){
                instance = new ApiRetrofit();
            }
        }
        return instance;
    }


}
