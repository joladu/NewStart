package com.jola.onlineedu.mode.http;

import com.jola.onlineedu.mode.bean.WelcomeBean;
import com.jola.onlineedu.mode.bean.response.ResUserLogin;
import com.jola.onlineedu.mode.bean.response.ResUserRegister;
import com.jola.onlineedu.mode.bean.response.ResponseGetQiLiuBean;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;

import io.reactivex.Flowable;

/**
 * Created by lenovo on 2018/8/14
 * 所有网络请求接口
 */

public interface HttpHelper {

    Flowable<WelcomeBean> fetchWelcomeInfo();

    Flowable<ResponseGetQiLiuBean> fetchQiLiuInfo();

    Flowable<ResponseSimpleResult> fetchMsgCheckCode();

    Flowable<ResUserLogin> fetchUserLoginInfo(String userName,String userPassword);

    Flowable<ResUserRegister> fetchUserRegisterInfo(String userName,String mobileNum,String checkCode,String imageCode,String captcha,String password,String passwordConfirm);

}
