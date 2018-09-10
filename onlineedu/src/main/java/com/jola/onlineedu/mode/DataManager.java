package com.jola.onlineedu.mode;

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
import com.jola.onlineedu.mode.db.DBHelper;
import com.jola.onlineedu.mode.http.HttpHelper;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.mode.prefs.PreferencesHelper;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;

/**
 * Created by lenovo on 2018/8/14
 * http db preference
 */

public class DataManager implements MyApis ,DBHelper,PreferencesHelper{

//    HttpHelper mHttpHelper;
    DBHelper mDBHelper;
    PreferencesHelper mPreferenceHelper;
    MyApis myApis;


//    public DataManager(HttpHelper mHttpHelper, DBHelper mDBHelper, PreferencesHelper mPreferenceHelper) {
//        this.mHttpHelper = mHttpHelper;
//        this.mDBHelper = mDBHelper;
//        this.mPreferenceHelper = mPreferenceHelper;
//    }

    public DataManager(MyApis myApis, DBHelper mDBHelper, PreferencesHelper mPreferenceHelper) {
        this.myApis = myApis;
        this.mDBHelper = mDBHelper;
        this.mPreferenceHelper = mPreferenceHelper;
    }


//    @Override
//    public Flowable<WelcomeBean> fetchWelcomeInfo() {
//        return myApis.getWelcomeInfo();
//    }
//
//    @Override
//    public Flowable<ResponseGetQiLiuBean> fetchQiLiuInfo() {
//        return myApis.getQiLiuInfo();
//    }
//
//    @Override
//    public Flowable<ResponseSimpleResult> fetchMsgCheckCode(String mobilePhone) {
//        return myApis.getMsgCheckCode(mobilePhone);
//    }
//
//    @Override
//    public Flowable<ResGetImageCode> fetchImageCode() {
//        return myApis.getImageCode();
//    }
//
//    @Override
//    public Flowable<ResUserLogin> fetchUserLoginInfo(String userName, String userPassword) {
//        return myApis.getUserLoginInfo(userName,userPassword);
//    }
//
//    @Override
//    public Flowable<ResUserRegister> fetchUserRegisterInfo(String userName, String mobileNum, String checkCode, String imageCode, String captcha, String password, String passwordConfirm) {
//        return myApis.getUserRegisterInfo(userName,mobileNum,checkCode,imageCode,captcha,password,passwordConfirm);
//    }
//
//    @Override
//    public Flowable<ResponseSimpleResult> fetchForgetPassword(String mobilePhone, String password, String msgCode, String captchaKey, String captcha) {
//        return myApis.getUserForgetPasswrod(mobilePhone,msgCode,captchaKey,captcha,password);
//    }



    @Override
    public Flowable<ResponseSimpleResult> getMsgCheckCode(String mobile) {
        return myApis.getMsgCheckCode(mobile);
    }

    @Override
    public Flowable<ResGetImageCode> getImageCode() {
        return myApis.getImageCode();
    }

    @Override
    public Flowable<ResponseGetQiLiuBean> getQiLiuInfo() {
        return myApis.getQiLiuInfo();
    }

    @Override
    public Flowable<ResUserLogin> getUserLoginInfo(String userName, String userPassword) {
        return myApis.getUserLoginInfo(userName,userPassword);
    }

    @Override
    public Flowable<ResUserRegister> getUserRegisterInfo(String userName, String mobileNum, String checkCode, String captcha_key, String captcha, String password, String passwordConfirm) {
        return myApis.getUserRegisterInfo(userName,mobileNum,checkCode,captcha_key,captcha,password,passwordConfirm);
    }

    @Override
    public Flowable<ResponseSimpleResult> getUserForgetPasswrod(String mobilePhone, String msgCode, String imageCodeKey, String imageCode, String password) {
        return myApis.getUserForgetPasswrod(mobilePhone,msgCode,imageCodeKey,imageCode,password);
    }

    @Override
    public Flowable<ResUserInfoBean> getUserInfo() {
        return myApis.getUserInfo();
    }

    @Override
    public Flowable<ResUploadUserImageBean> uploadUserImage(MultipartBody.Part file) {
        return myApis.uploadUserImage(file);
    }

    @Override
    public Flowable<ResForumTypeBean> getForumTypeInfo() {
        return myApis.getForumTypeInfo();
    }

    @Override
    public Flowable<ResForumListByTypeBean> getForumListByType(String keyWords, String forumType) {
        return myApis.getForumListByType(keyWords,forumType);
    }

    @Override
    public Flowable<ResForumDetailBean> getForumDetail(String id) {
        return myApis.getForumDetail(id);
    }

    @Override
    public Flowable<ResponseSimpleResult> commentForum(String id, String content) {
        return myApis.commentForum(id,content);
    }

    @Override
    public Flowable<ResponseSimpleResult> praiseComment(String id) {
        return myApis.praiseComment(id);
    }

    @Override
    public Flowable<ResUploadFourmImageBean> uploadForumImage(MultipartBody.Part[] file) {
        return myApis.uploadForumImage(file);
    }

    @Override
    public Flowable<ResQuestionTypeBean> getQuestionType() {
        return myApis.getQuestionType();
    }

    @Override
    public Flowable<ResExamsList> getExamsList() {
        return myApis.getExamsList();
    }

    @Override
    public Flowable<ResExamsDetail> getExamsDetail(String id) {
        return myApis.getExamsDetail(id);
    }

    @Override
    public Flowable<ResCourseList> getCourseList(String page, String page_size) {
        return myApis.getCourseList(page,page_size);
    }

    @Override
    public Flowable<ResCourseDetail> getCourseDetail(String id) {
        return myApis.getCourseDetail(id);
    }

    @Override
    public Flowable<ResCourseCapterList> getCourseCapterList(String course_id, String page, String page_size) {
        return myApis.getCourseCapterList(course_id,page,page_size);
    }

    @Override
    public Flowable<ResCourseCapterDetail> getCourseCapterDetail(String id) {
        return myApis.getCourseCapterDetail(id);
    }

    @Override
    public Flowable<ResCouserCommentList> getCourseCommentList(String course_id, String page, String page_size) {
        return myApis.getCourseCommentList(course_id,page,page_size);
    }

    @Override
    public Flowable<ResLiveCourseList> getLiveCourseList(String page, String page_size) {
        return myApis.getLiveCourseList(page,page_size);
    }

    @Override
    public Flowable<ResLiveCourseDetail> getLiveCourseDetail() {
        return myApis.getLiveCourseDetail();
    }

    @Override
    public Flowable<ResTeacherList> getTeacherList(String page, String page_size) {
        return myApis.getTeacherList(page,page_size);
    }

    @Override
    public Flowable<ResTeacherCourseDetail> getTeacherCourseDetail(String id) {
        return myApis.getTeacherCourseDetail(id);
    }

    @Override
    public Flowable<String> testHead(String token, String testName) {
        return myApis.testHead(token,testName);
    }

    @Override
    public Flowable<WelcomeBean> getWelcomeInfo() {
        return null;
    }

    @Override
    public void insertNewsId(int id) {
        mDBHelper.insertNewsId(id);
    }

    @Override
    public void setCurMainFragmentTag(int fragmentTag) {
        mPreferenceHelper.setCurMainFragmentTag(fragmentTag);
    }

    @Override
    public int getCurMainFragmentTag() {
        return mPreferenceHelper.getCurMainFragmentTag();
    }

    @Override
    public void setUserId(String userId) {
        mPreferenceHelper.setUserId(userId);
    }

    @Override
    public String getUserId() {
        return mPreferenceHelper.getUserId();
    }

    @Override
    public void setUserName(String userName) {
        mPreferenceHelper.setUserName(userName);
    }

    @Override
    public String getUserName() {
        return mPreferenceHelper.getUserName();
    }

    @Override
    public void setUserPhone(String phone) {
        mPreferenceHelper.setUserPhone(phone);
    }

    @Override
    public String getUserPhone() {
        return mPreferenceHelper.getUserPhone();
    }

    @Override
    public void setUserToken(String userToken) {
        mPreferenceHelper.setUserToken(userToken);
    }

    @Override
    public String getUserToken() {
        return mPreferenceHelper.getUserToken();
    }

    @Override
    public boolean getNightModeState() {
        return mPreferenceHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean state) {
        mPreferenceHelper.setNightModeState(state);
    }
}
