package com.jola.onlineedu.presenter;


import com.jola.onlineedu.base.RxPresenter;
import com.jola.onlineedu.contract.WelcomeContract;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.WelcomeBean;
import com.jola.onlineedu.util.RxUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by codeest on 16/8/15.
 */

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter{

    private static final String RES = "1080*1776";

    private static final int COUNT_DOWN_TIME = 6000;

    private DataManager mDataManager;

    @Inject
    public WelcomePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getWelcomeData() {

//        RequestBody nameBody = RequestBody.create(MediaType.parse("text"), "name");
//        RequestBody ageBody = RequestBody.create(MediaType.parse("text"), "age");
//        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), "filepath");
//
//        File filepath = new File("filepath");
//        MultipartBody.Part.createFormData("file","test.text",file)
//        MultipartBody.Part part = MultipartBody.Part.createFormData("file", "test.txt", fileBody);


//        addSubscribe(mDataManager.fetchWelcomeInfo()
//                .compose(RxUtil.<WelcomeBean>rxSchedulerHelper())
//                .subscribe(new Consumer<WelcomeBean>() {
//                    @Override
//                    public void accept(WelcomeBean welcomeBean) {
//                        String img = welcomeBean.getImg();
//                        String text = welcomeBean.getText();
//                        Log.e("jola","WelcomPresenter:"+img+"  "+text);
//                        mView.showContent(welcomeBean);
//                        startCountDown();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) {
//                        mView.jumpToMain();
//                    }
//                })
//        );


        WelcomeBean welcomeBean = new WelcomeBean();
        welcomeBean.setImg("https://www.baidu.com/img/xinshouye_034fec51df225fe8410f36ad3f2fccf6.png");
        welcomeBean.setText("jola");
        mView.showContent(welcomeBean);

        mDataManager.setNightModeState(false);
        mDataManager.insertNewsId(123321);

        startCountDown();

    }

    private void startCountDown() {
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        mView.jumpToMain();
                    }
                })
        );
    }

}
