//package com.jola.onlineedu.mode.http;
//
//import com.jola.onlineedu.mode.bean.WelcomeBean;
//import com.jola.onlineedu.mode.bean.response.ResUserLogin;
//import com.jola.onlineedu.mode.bean.response.ResUserRegister;
//import com.jola.onlineedu.mode.bean.response.ResponseGetQiLiuBean;
//import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
//
//import javax.inject.Inject;
//
//import io.reactivex.Flowable;
//
///**
// * Created by jola on 2018/8/14.
// * 有多个 ApiService 使用 RetrofitHelper 比较方便
// */
//
//public class RetrofitHelper implements HttpHelper {
//
//
//    private MyApis mMyApiService;
//
//    @Inject
//    public RetrofitHelper(MyApis mMyApiService) {
//        this.mMyApiService = mMyApiService;
//    }
//
//    @Override
//    public Flowable<WelcomeBean> fetchWelcomeInfo() {
//        return mMyApiService.getWelcomeInfo();
//    }
//
//    @Override
//    public Flowable<ResponseGetQiLiuBean> fetchQiLiuInfo() {
//        return mMyApiService.getQiLiuInfo();
//    }
//
//    @Override
//    public Flowable<ResponseSimpleResult> fetchMsgCheckCode(String phoneNum) {
//        return mMyApiService.getMsgCheckCode(phoneNum);
//    }
//
//    @Override
//    public Flowable<ResUserLogin> fetchUserLoginInfo(String userName, String userPassword) {
//        return mMyApiService.getUserLoginInfo(userName,userPassword);
//    }
//
//    @Override
//    public Flowable<ResUserRegister> fetchUserRegisterInfo(String userName, String mobileNum, String checkCode, String imageCode, String captcha, String password, String passwordConfirm) {
//        return mMyApiService.getUserRegisterInfo(userName,mobileNum,checkCode,imageCode,captcha,password,passwordConfirm);
//    }
//
//
//}
