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

    HttpHelper mHttpHelper;
    DBHelper mDBHelper;
    PreferencesHelper mPreferenceHelper;
    MyApis myApis;


    public DataManager(HttpHelper mHttpHelper, DBHelper mDBHelper, PreferencesHelper mPreferenceHelper) {
        this.mHttpHelper = mHttpHelper;
        this.mDBHelper = mDBHelper;
        this.mPreferenceHelper = mPreferenceHelper;
    }

    public DataManager(MyApis myApis,HttpHelper mHttpHelper, DBHelper mDBHelper, PreferencesHelper mPreferenceHelper) {
        this.myApis = myApis;
        this.mHttpHelper = mHttpHelper;
        this.mDBHelper = mDBHelper;
        this.mPreferenceHelper = mPreferenceHelper;
    }


    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo() {
        return mHttpHelper.fetchWelcomeInfo();
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
    public void insertNewsId(int id) {
        mDBHelper.insertNewsId(id);
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
