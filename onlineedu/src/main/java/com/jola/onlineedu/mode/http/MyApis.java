package com.jola.onlineedu.mode.http;

import com.jola.onlineedu.mode.bean.WelcomeBean;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by jola on 2018/8/14.
 */

public interface MyApis {

//    String HOST = "http://news-at.zhihu.com/api/4/";
    String HOST = "http://192.168.7.153:8080/AnXinPay/AXPay/";


//    @GET("AXPay/testJola/{res}")
//    Flowable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

//    /**
//     * 表单提交： username=name;age=age
//     */
//    @POST("/form")
//    @FormUrlEncoded
//    Call<ResponseBody> testFormUrlEncoded1(@Field("username") String name, @Field("age") int age);
//    Flowable<WelcomeBean> testFormUrlEncoded(@Field("username") String name,@Field("age") int age);

    @GET("testJola/")
    Flowable<WelcomeBean> getWelcomeInfo();


//    /**
//     * {@link Part} 后面支持三种类型，{@link RequestBody}、{@link okhttp3.MultipartBody.Part} 、任意类型
//     * 除 {@link okhttp3.MultipartBody.Part} 以外，其它类型都必须带上表单字段({@link okhttp3.MultipartBody.Part} 中已经包含了表单字段的信息)，
//     */
//    @POST("/form")
//    @Multipart
//    Call<ResponseBody> testFileUpload1(@Part("name") RequestBody name, @Part("age") RequestBody age, @Part MultipartBody.Part file);
    Flowable<ResponseBody> testFileUpload(@Part("name")RequestBody name, @Part("age") RequestBody age, @Part MultipartBody.Part file);





}
