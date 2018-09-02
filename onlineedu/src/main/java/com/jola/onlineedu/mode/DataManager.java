package com.jola.onlineedu.mode;

import com.jola.onlineedu.mode.bean.WelcomeBean;
import com.jola.onlineedu.mode.bean.response.ResUserLogin;
import com.jola.onlineedu.mode.bean.response.ResUserRegister;
import com.jola.onlineedu.mode.bean.response.ResponseGetQiLiuBean;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.mode.db.DBHelper;
import com.jola.onlineedu.mode.http.HttpHelper;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.mode.prefs.PreferencesHelper;

import io.reactivex.Flowable;

/**
 * Created by lenovo on 2018/8/14
 * http db preference
 */

public class DataManager implements HttpHelper ,DBHelper,PreferencesHelper{

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


    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo() {
        return myApis.getWelcomeInfo();
    }

    @Override
    public Flowable<ResponseGetQiLiuBean> fetchQiLiuInfo() {
        return myApis.getQiLiuInfo();
    }

    @Override
    public Flowable<ResponseSimpleResult> fetchMsgCheckCode(String mobilePhone) {
        return myApis.getMsgCheckCode(mobilePhone);
    }

    @Override
    public Flowable<ResUserLogin> fetchUserLoginInfo(String userName, String userPassword) {
        return myApis.getUserLoginInfo(userName,userPassword);
    }

    @Override
    public Flowable<ResUserRegister> fetchUserRegisterInfo(String userName, String mobileNum, String checkCode, String imageCode, String captcha, String password, String passwordConfirm) {
        return myApis.getUserRegisterInfo(userName,mobileNum,checkCode,imageCode,captcha,password,passwordConfirm);
    }

    @Override
    public Flowable<ResponseSimpleResult> fetchForgetPassword(String mobilePhone, String password, String msgCode, String captchaKey, String captcha) {
        return myApis.getUserForgetPasswrod(mobilePhone,msgCode,captchaKey,captcha,password);
    }

    @Override
    public void insertNewsId(int id) {
        mDBHelper.insertNewsId(id);
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
