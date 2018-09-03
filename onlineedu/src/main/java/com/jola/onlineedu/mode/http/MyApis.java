package com.jola.onlineedu.mode.http;

import com.jola.onlineedu.mode.bean.WelcomeBean;
import com.jola.onlineedu.mode.bean.response.ResUserLogin;
import com.jola.onlineedu.mode.bean.response.ResUserRegister;
import com.jola.onlineedu.mode.bean.response.ResponseGetQiLiuBean;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by jola on 2018/8/14.
 */

public interface MyApis {

    String HOST = "http://yunketang.dev.attackt.com/api/";

    @GET("qiniu/settings")
    Flowable<ResponseGetQiLiuBean> getQiLiuInfo();

    @POST("v1/code/vcode")
    @FormUrlEncoded
    Flowable<ResponseSimpleResult> getMsgCheckCode(@Field("mobile") String mobile);

    @POST("v1/user/login")
    @FormUrlEncoded
    Flowable<ResUserLogin> getUserLoginInfo(@Field("user_name") String userName,
                                            @Field("password") String userPassword);

    @POST("v1/user/register")
    @FormUrlEncoded
    Flowable<ResUserRegister> getUserRegisterInfo(@Field("user_name") String userName,
                                                  @Field("mobile") String mobileNum,
//                                                  短信验证码
                                                  @Field("v_code") String checkCode,
                                                  @Field("captcha_key") String imgCode,
                                                  @Field("captcha") String captcha,
                                                  @Field("password") String password,
                                                  @Field("re_password") String passwordConfirm);

    @FormUrlEncoded
    @POST("v1/user/resetpwd")
    Flowable<ResponseSimpleResult> getUserForgetPasswrod(@Field("mobile") String mobilePhone,
                                                         @Field("v_code") String msgCode,
                                                         @Field("captcha_key") String imageCodeKey,
                                                         @Field("captcha") String imageCode,
                                                         @Field("password") String password);

    @FormUrlEncoded
    @POST("v1/user/test")
    Flowable<String> testHead(@Header("token") String token,String testName);





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
//    Flowable<ResponseBody> testFileUpload(@Part("name")RequestBody name, @Part("age") RequestBody age, @Part MultipartBody.Part file);





}
