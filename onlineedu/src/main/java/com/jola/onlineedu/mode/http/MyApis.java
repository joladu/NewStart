package com.jola.onlineedu.mode.http;

import com.jola.onlineedu.mode.bean.WelcomeBean;
import com.jola.onlineedu.mode.bean.response.ResCourseCapterDetail;
import com.jola.onlineedu.mode.bean.response.ResCourseCapterList;
import com.jola.onlineedu.mode.bean.response.ResCourseDetail;
import com.jola.onlineedu.mode.bean.response.ResCourseList;
import com.jola.onlineedu.mode.bean.response.ResCouserCommentList;
import com.jola.onlineedu.mode.bean.response.ResExamsDetail;
import com.jola.onlineedu.mode.bean.response.ResExamsList;
import com.jola.onlineedu.mode.bean.response.ResForumDetailBean;
import com.jola.onlineedu.mode.bean.response.ResForumListByTypeBean;
import com.jola.onlineedu.mode.bean.response.ResForumTypeBean;
import com.jola.onlineedu.mode.bean.response.ResGetImageCode;
import com.jola.onlineedu.mode.bean.response.ResLiveCourseDetail;
import com.jola.onlineedu.mode.bean.response.ResLiveCourseList;
import com.jola.onlineedu.mode.bean.response.ResQuestionTypeBean;
import com.jola.onlineedu.mode.bean.response.ResTeacherCourseDetail;
import com.jola.onlineedu.mode.bean.response.ResTeacherList;
import com.jola.onlineedu.mode.bean.response.ResUploadFourmImageBean;
import com.jola.onlineedu.mode.bean.response.ResUploadUserImageBean;
import com.jola.onlineedu.mode.bean.response.ResUserInfoBean;
import com.jola.onlineedu.mode.bean.response.ResUserLogin;
import com.jola.onlineedu.mode.bean.response.ResUserRegister;
import com.jola.onlineedu.mode.bean.response.ResponseGetQiLiuBean;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;

import java.util.List;

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
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jola on 2018/8/14.
 */

public interface MyApis {


    String DOMAIN = "http://yunketang.dev.attackt.com";
    String HOST = DOMAIN + "/api/";


    // *****************   begin base api     ***************
    @POST("v1/code/vcode/")
    @FormUrlEncoded
    Flowable<ResponseSimpleResult> getMsgCheckCode(@Field("mobile") String mobile);

    @GET("v1/code/captcha/")
    Flowable<ResGetImageCode> getImageCode();

    @GET("qiniu/settings/")
    Flowable<ResponseGetQiLiuBean> getQiLiuInfo();

//  *****************  end base api   *****************






    //  *****************  begin user api *****************
    @POST("v1/user/login/")
    @FormUrlEncoded
    Flowable<ResUserLogin> getUserLoginInfo(@Field("user_name") String userName,
                                            @Field("password") String userPassword);

    @POST("v1/user/register/")
    @FormUrlEncoded
    Flowable<ResUserRegister> getUserRegisterInfo(@Field("user_name") String userName,
                                                  @Field("mobile") String mobileNum,
                                                  @Field("v_code") String checkCode,
                                                  @Field("captcha_key") String captcha_key,
                                                  @Field("captcha") String captcha,
                                                  @Field("password") String password,
                                                  @Field("re_password") String passwordConfirm);


    @FormUrlEncoded
    @POST("v1/user/resetpwd/")
    Flowable<ResponseSimpleResult> getUserForgetPasswrod(@Field("mobile") String mobilePhone,
                                                         @Field("v_code") String msgCode,
                                                         @Field("captcha_key") String imageCodeKey,
                                                         @Field("captcha") String imageCode,
                                                         @Field("password") String password);


    @GET("v1/user/myprofile/")
    Flowable<ResUserInfoBean> getUserInfo();



    /**
     * 1、根据地址拿到File
     File file = new File(path);

     //        2、创建RequestBody，其中`multipart/form-data`为编码类型
     RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

     //        3、创建`MultipartBody.Part`，其中需要注意第一个参数`fileUpload`需要与服务器对应,也就是`键`    avatar_binary
     MultipartBody.Part part = MultipartBody.Part.createFormData("fileUpload", file.getName(), requestFile);
     */
    @POST("v1/user/avatar/")
    @Multipart
    Flowable<ResUploadUserImageBean> uploadUserImage(@Part MultipartBody.Part file);


//   ***************** end user api  *****************





    //  *****************  begin forum api *****************

    @GET("v1/bbs/types/")
    Flowable<ResForumTypeBean> getForumTypeInfo();

    @GET("v1/bbs/posts/")
    Flowable<ResForumListByTypeBean> getForumListByType(@Query("kw") String keyWords,@Query("type") String forumType);

    @GET("v1/bbs/posts/{:id}/")
    Flowable<ResForumDetailBean> getForumDetail(@Path("id") String id);

    @POST("v1/bbs/posts/{:id}/comments/")
    @FormUrlEncoded
    Flowable<ResponseSimpleResult> commentForum(@Path("id") String id,@Field("content") String content);


    @PUT("v1/bbs/comments/{:id}/praise/")
    Flowable<ResponseSimpleResult> praiseComment(@Path("id") String id);


    /**
     * //        1、根据地址拿到File
     File file = new File(path);

     //        2、创建RequestBody，其中`multipart/form-data`为编码类型
     RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

     //        3、创建`MultipartBody.Part`数组，多图片上传，其中需要注意第一个参数`fileUpload`需要与服务器对应,也就是`键`，多个也一样
     MultipartBody.Part[]  file = new MultipartBody.Part[2];
     file[0] = MultipartBody.Part.createFormData("file", file1.getName(), requestFile);
     file[1] = MultipartBody.Part.createFormData("file", file1.getName(), requestFile);
     * @param file   键值：img
     * @return
     */
    @POST("v1/bbs/images/")
    @Multipart
    Flowable<ResUploadFourmImageBean> uploadForumImage(@Part MultipartBody.Part[] file);

    @POST("v1/bbs/posts/")
    @FormUrlEncoded
    Flowable<ResponseSimpleResult> publishForumContent(@Header("authorization")String token,@Field("type") String type, @Field("title") String title, @Field("content") String content, @Field("imgs") List<String> imageUrlList);



    //   ***************** end forum api  *****************




//  *****************  begin  exams api *****************

    @GET("v1/questions/types/")
    Flowable<ResQuestionTypeBean> getQuestionType();

    @GET("v1/exams/")
    Flowable<ResExamsList> getExamsList();

    @GET("v1/exams/{:id}/")
    Flowable<ResExamsDetail> getExamsDetail(@Path("id") String id);

//  *****************  begin  exams api *****************





    //  *****************  begin course api *****************

    @GET("v1/course/")
    Flowable<ResCourseList> getCourseList(@Query("page") String page,@Query("page_size")String page_size);

    @GET("v1/course/{:id}/detail/")
    Flowable<ResCourseDetail> getCourseDetail(@Path("id") String id);

    @GET("v1/coursechapter/{:course_id}/")
    Flowable<ResCourseCapterList> getCourseCapterList(@Path("course_id")String course_id,@Query("page")String page,@Query("page_size")String page_size);

    @GET("v1/coursechapter/{:id}/detail/")
    Flowable<ResCourseCapterDetail> getCourseCapterDetail(@Path("id")String id);

    @GET("v1/coursecomment/{:course_id}/")
    Flowable<ResCouserCommentList> getCourseCommentList(@Path("course_id")String course_id,@Query("page")String page,@Query("page_size")String page_size);


//  *****************  begin  course api *****************






    //  *****************  begin live course api *****************

    @GET("v1/livecourse/")
    Flowable<ResLiveCourseList> getLiveCourseList(@Query("page")String page,@Query("page_size")String page_size);

    @GET("v1/livecourse/{:id}/detail/")
    Flowable<ResLiveCourseDetail> getLiveCourseDetail();

//  *****************  begin  live course api *****************




    //  *****************  begin teacher api *****************

    @GET("v1/teacher/")
    Flowable<ResTeacherList> getTeacherList(@Query("page")String page,@Query("page_size")String page_size);

    @GET("v1/teacher/{:id}/detail/")
    Flowable<ResTeacherCourseDetail> getTeacherCourseDetail(@Path("id")String id);

//  *****************  begin  teacher api *****************
//


// *****************  begin teacher api *****************

//  *****************  begin  teacher api *****************






    //
    @FormUrlEncoded
    @POST("v1/user/tes/t")
    Flowable<String> testHead(@Header("token") String token, String testName);





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
