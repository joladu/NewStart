package com.jola.onlineedu.mode.http;

import com.jola.onlineedu.mode.bean.response.ResBannerHomepage;
import com.jola.onlineedu.mode.bean.response.ResCommentListBean;
import com.jola.onlineedu.mode.bean.response.ResCourseCapterDetail;
import com.jola.onlineedu.mode.bean.response.ResCourseCapterList;
import com.jola.onlineedu.mode.bean.response.ResCourseDetail;
import com.jola.onlineedu.mode.bean.response.ResCourseList;
import com.jola.onlineedu.mode.bean.response.ResCouserCommentList;
import com.jola.onlineedu.mode.bean.response.ResDownloadsBean;
import com.jola.onlineedu.mode.bean.response.ResExamsDetail;
import com.jola.onlineedu.mode.bean.response.ResExamsList;
import com.jola.onlineedu.mode.bean.response.ResForumComments;
import com.jola.onlineedu.mode.bean.response.ResForumDetailBean;
import com.jola.onlineedu.mode.bean.response.ResForumListByTypeBean;
import com.jola.onlineedu.mode.bean.response.ResForumTypeBean;
import com.jola.onlineedu.mode.bean.response.ResFriendDetailBean;
import com.jola.onlineedu.mode.bean.response.ResFriendList;
import com.jola.onlineedu.mode.bean.response.ResGetImageCode;
import com.jola.onlineedu.mode.bean.response.ResInteresListBean;
import com.jola.onlineedu.mode.bean.response.ResLiveCourseDetail;
import com.jola.onlineedu.mode.bean.response.ResLiveCourseList;
import com.jola.onlineedu.mode.bean.response.ResMessageDetailBean;
import com.jola.onlineedu.mode.bean.response.ResMessageListBean;
import com.jola.onlineedu.mode.bean.response.ResQuestionTypeBean;
import com.jola.onlineedu.mode.bean.response.ResSelectionListBean;
import com.jola.onlineedu.mode.bean.response.ResStudyListBean;
import com.jola.onlineedu.mode.bean.response.ResTeacherAttestation;
import com.jola.onlineedu.mode.bean.response.ResTeacherBannerBean;
import com.jola.onlineedu.mode.bean.response.ResTeacherCourseDetail;
import com.jola.onlineedu.mode.bean.response.ResTeacherList;
import com.jola.onlineedu.mode.bean.response.ResUpdatepersonalInfoBean;
import com.jola.onlineedu.mode.bean.response.ResUploadFourmImageBean;
import com.jola.onlineedu.mode.bean.response.ResUploadUserImageBean;
import com.jola.onlineedu.mode.bean.response.ResUserInfoBean;
import com.jola.onlineedu.mode.bean.response.ResUserLogin;
import com.jola.onlineedu.mode.bean.response.ResUserRegister;
import com.jola.onlineedu.mode.bean.response.ResponseGetQiLiuBean;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by jola on 2018/8/14.
 */

public interface MyApis {


    String DOMAIN = "http://yunketang.dev.attackt.com";
    String HOST = DOMAIN + "/api/";
    String TAG_AUTHORIZATION = "authorization";


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
    Flowable<ResUserInfoBean> getUserInfo(@Header("authorization") String token);




    @POST("v1/user/avatar/")
    @FormUrlEncoded
    Flowable<ResUploadUserImageBean> uploadUserImage(@Header("authorization") String token,@Field("avatar_binary") String imageBase64Str);

    @PUT("v1/user/change/profile/")
    Flowable<ResUpdatepersonalInfoBean> updateUserProfile(@QueryMap Map<String,String> map);


//   ***************** end user api  *****************





    //  *****************  begin forum api *****************

    @GET("v1/bbs/types/")
    Flowable<ResForumTypeBean> getForumTypeInfo();



//    @GET("v1/bbs/posts/")
//    Flowable<ResForumListByTypeBean> getForumListByType(@Query("type") String forumType);

    @GET("v1/bbs/posts/")
    Flowable<ResForumListByTypeBean> getForumListByType(@QueryMap Map<String,String> map);

    @GET("v1/bbs/posts/{id}/")
    Flowable<ResForumDetailBean> getForumDetail(@Path("id") String id);

    @GET("v1/bbs/posts/{id}/commments/")
    Flowable<ResForumComments> getForumComments(@Path("id") String id, @Query("page") int page);

    @POST("v1/bbs/posts/{id}/comments/")
    @FormUrlEncoded
    Flowable<ResponseSimpleResult> commentForum(@Path("id") String id,@Field("content") String content);


    @PUT("v1/bbs/comments/{id}/praise/")
    Flowable<ResponseSimpleResult> praiseComment(@Path("id") String id);


    /**
     * base64 字符串
     * @return
     */
    @POST("v1/bbs/images/")
    @FormUrlEncoded
    Flowable<ResUploadFourmImageBean> uploadForumImage(@Field("img")String img);

    @POST("v1/bbs/posts/")
    @FormUrlEncoded
    Flowable<ResponseSimpleResult> publishForumContent(@Header("authorization")String token,@Field("type") String type, @Field("title") String title, @Field("content") String content, @Field("imgs") List<String> imageUrlList);



    //   ***************** end forum api  *****************




//  *****************  begin  exams api *****************

    @GET("v1/questions/types/")
    Flowable<ResQuestionTypeBean> getQuestionType();

    @GET("v1/exams/")
    Flowable<ResExamsList> getExamsList();

    @GET("v1/exams/{id}/")
    Flowable<ResExamsDetail> getExamsDetail(@Path("id") String id);

//  *****************  end  exams api *****************





    //  *****************  begin course api *****************

    @GET("v1/course/")
    Flowable<ResCourseList> getCourseList(@Query("page") String page,@Query("page_size")String page_size);

    @GET("v1/course/recommend/")
    Flowable<ResCourseList> getCourseRecommendList(@Query("page") String page,@Query("page_size")String page_size);

    @GET("v1/course/{id}/detail/")
    Flowable<ResCourseDetail> getCourseDetail(@Path("id") String id);

    @GET("v1/coursechapter/{course_id}/")
    Flowable<ResCourseCapterList> getCourseCapterList(@Path("course_id")String course_id,@Query("page")String page,@Query("page_size")String page_size);

    @GET("v1/coursechapter/{id}/detail/")
    Flowable<ResCourseCapterDetail> getCourseCapterDetail(@Path("id")String id);

    @GET("v1/coursecomment/{course_id}/")
    Flowable<ResCouserCommentList> getCourseCommentList(@Path("course_id")String course_id,@Query("page")String page,@Query("page_size")String page_size);

    @FormUrlEncoded
    @POST("v1/bbs/posts/{id}/comments/")
    Flowable<ResponseSimpleResult> publishCourseComment(@Path("id")String id,@Field("content")String content);

    @PUT("v1/bbs/comments/{id}/praise/")
    Flowable<ResponseSimpleResult> praiseCommentCourse(@Path("id")String id);


//  *****************  end  course api *****************






    //  *****************  begin live course api *****************

    @GET("v1/livecourse/")
    Flowable<ResLiveCourseList> getLiveCourseList(@Query("page")String page,@Query("page_size")String page_size);

    @GET("v1/livecourse/{id}/detail/")
    Flowable<ResLiveCourseDetail> getLiveCourseDetail(@Path("id")String id);

//  *****************  end  live course api *****************




    //  *****************  begin teacher api *****************


    @GET("v1/teacher/commend/")
    Flowable<ResTeacherBannerBean> getTeacherBanner();


    @GET("v1/teacher/")
    Flowable<ResTeacherList> getTeacherList(@Query("page")String page,@Query("page_size")String page_size);

    @GET("v1/teacher/{id}/detail/")
    Flowable<ResTeacherCourseDetail> getTeacherCourseDetail(@Path("id")String id);

//  *****************  end  teacher api *****************
//




// *****************  begin common api *****************

    @GET("v1/common/banner/")
    Flowable<List<ResBannerHomepage>> getBannerHomepage();

//  *****************  end  common api *****************



    // *****************  begin user api *****************

// no
//    application/json; charset=UTF-8
//    @Multipart
//    @PUT("v1/uc/chpwd/")
//    Flowable<ResponseSimpleResult> changePassword(
//            @Header("authorization") String token,
//            @Part("oldpwd") String oldpwd,
//            @Part("newpwd") String newpwd,
//            @Part("newpwd2") String newpwd2);


//    no
// Content-Type: application/x-www-form-urlencoded
//    oldpwd=654321&newpwd=123456&newpwd2=123456
    @FormUrlEncoded
    @PUT("v1/uc/chpwd/")
    Flowable<ResponseSimpleResult> changePassword(
            @Header("authorization") String token,
            @Field("oldpwd") String oldpwd,
            @Field("newpwd") String newpwd,
            @Field("newpwd2") String newpwd2);



//    no
//    Content-Disposition: form-data; name="oldpwd"
//    Content-Transfer-Encoding: binary
//    Content-Type: multipart/form-data; charset=utf-8
//    654321
    @Multipart
    @PUT("v1/uc/chpwd/")
    Flowable<ResponseSimpleResult> changePassword1(
            @Header("authorization") String token,
            @Part("oldpwd") RequestBody oldpwd,
            @Part("newpwd") RequestBody newpwd,
            @Part("newpwd2") RequestBody newpwd2);


//no
//    Content-Disposition: form-data; name="oldpwd"
//    Content-Transfer-Encoding: binary
//    Content-Type: multipart/form-data; charset=utf-8
//    654321

    @Multipart
    @PUT("v1/uc/chpwd/")
    Flowable<ResponseSimpleResult> changePassword(
            @Header("authorization") String token,
            @PartMap Map<String,RequestBody> map
            );



    //    第3种
    @Multipart
    @POST("v1/uc/teacherverify/")
    Flowable<ResTeacherAttestation> teacherVerify(
            @Header("authorization") String token,
            @PartMap Map<String ,RequestBody> map
    );

    @Multipart
    @POST("v1/uc/teacherverify/")
    Observable<ResTeacherAttestation> teacherVerifyObserable(
            @Header("authorization")String token,
            @PartMap Map<String, RequestBody> param
    );


//
//    @Multipart
//    @POST("v1/uc/chpwd/")
//    Observable<ResponseBody> teacherVerifyObserable(
//            @Header("authorization")String token,
//            @PartMap Map<String, RequestBody> param
//    );







    @GET("v1/fridend/{id}/detail/")
    Flowable<ResFriendDetailBean> getFriendDetailInfo(
            @Header("authorization") String token,
            @Path("id") String id
    );

    @PUT("v1/uc/changemobile/")
    Flowable<ResponseSimpleResult> changeMobilePhoneNo(
            @Header("authorization") String token,
            @Field("mobile") String mobile,
            @Field("vcode") String vcode,
            @Field("captcha_key") String captcha_key,
            @Field("captcha") String captcha
    );


    @GET("v1/friend/")
    Flowable<ResFriendList> getFriendList(@Header("authorization") String token);

    @GET("v1/friend/")
    Flowable<ResFriendList> queryFriendByKey(@Header("authorization") String token, @Query("kw") String keyWord);

    @FormUrlEncoded
    @POST("v1/friend/add/")
    Flowable<ResponseSimpleResult> addFriend(@Header(TAG_AUTHORIZATION) String token,@Field("from_user_id") String from_user_id);

    @GET("v1/uc/message/")
    Flowable<ResMessageListBean> getMessageList(@Header(TAG_AUTHORIZATION) String token, @Query("page") int page, @Query("pagesize")int pagesize);


    @GET("v1/uc/message/{id}/detail/")
    Flowable<ResMessageDetailBean> getMessageDetailInfo(
            @Header(TAG_AUTHORIZATION) String token,
            @Path("id") String id
    );

    @FormUrlEncoded
    @POST("v1/uc/message/send/")
    Flowable<ResponseSimpleResult> sendMessage(
            @Header(TAG_AUTHORIZATION) String token,
            @Field("to_user_id") String to_user_id,
            @Field("content") String content
    );

    @FormUrlEncoded
    @POST("v1/uc/message/{id}/reply/")
    Flowable<ResponseSimpleResult> responseMessage(
            @Header(TAG_AUTHORIZATION) String token,
            @Path("id") String id,
            @Field("content") String content

    );

    @GET("v1/uc/record/favoritecourse/")
    Flowable<ResCommentListBean> getCommentsList(
            @Header(TAG_AUTHORIZATION) String token,
            @Query("page") int page,
            @Query("pageSize") int pagesize
    );


    @GET("v1/uc/record/favoritecourse/")
    Flowable<ResSelectionListBean> getSelectionList(
            @Header(TAG_AUTHORIZATION) String token,
            @Query("page") int  page,
            @Query("pageSize") int pagesize
    );

    @GET("v1/uc/resourcedownload/")
    Flowable<ResDownloadsBean> getDownloadList(
            @Header(TAG_AUTHORIZATION) String token,
            @Query("page") int page,
            @Query("pageSize") int pagesize
    );


    @GET("v1/uc/resourcedownload/")
    Flowable<ResInteresListBean> getInterestList(
            @Header(TAG_AUTHORIZATION) String token,
            @Query("page") int page,
            @Query("pageSize") int pagesize
    );


    @GET("v1/uc/mystudy/")
    Flowable<ResStudyListBean> getMyStudyList(
            @Header(TAG_AUTHORIZATION) String token,
            @Query("page") int page,
            @Query("pagesize")int pagesize);


//  *****************  end  user api *****************





// *****************  begin teacher api *****************

//  *****************  end  teacher api *****************



//    /**
//     * {@link Part} 后面支持三种类型，{@link RequestBody}、{@link okhttp3.MultipartBody.Part} 、任意类型
//     * 除 {@link okhttp3.MultipartBody.Part} 以外，其它类型都必须带上表单字段({@link okhttp3.MultipartBody.Part} 中已经包含了表单字段的信息)，
//     */
//    @POST("/form")
//    @Multipart
//    Call<ResponseBody> testFileUpload1(@Part("name") RequestBody name, @Part("age") RequestBody age, @Part MultipartBody.Part file);
//    Flowable<ResponseBody> testFileUpload(@Part("name")RequestBody name, @Part("age") RequestBody age, @Part MultipartBody.Part file);


}
