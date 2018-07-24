package com.jola.newnews.presenter.main;

import com.jola.newnews.base.RxPresenter;
import com.jola.newnews.contract.main.IWelcomeContract;
import com.jola.newnews.mode.DataManage;
import com.jola.newnews.mode.bean.WelcomeBean;
import com.jola.newnews.util.CommonUtil;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/7/18.
 */

public class WelcomePresenter extends RxPresenter<IWelcomeContract.IWelcomeView>  implements IWelcomeContract.IWelcomePresenter{

    private static final String RES = "1080*1776";

    private static final int COUNT_DOWN_TIME = 2200;

    private DataManage mDataManage;

    @Inject
    public WelcomePresenter(DataManage dataManage) {
        mDataManage = dataManage;
    }

    @Override
    public void getWelcomeData() {

//        WelcomeBean welcomeBean = new WelcomeBean();
//        welcomeBean.setText("nfc手持机");
//        welcomeBean.setImg("http://p2.zhimg.com/10/7b/107bb4894b46d75a892da6fa80ef504a.jpg");
//        mView.showContent(welcomeBean);
//        startCountDown();

        addSubscribe(mDataManage.fetchWelcomeInfo(RES)
                .compose(CommonUtil.<WelcomeBean>rxSchedulerHelper())
                .subscribe(new Consumer<WelcomeBean>() {
                    @Override
                    public void accept(WelcomeBean welcomeBean) {
                        mView.showContent(welcomeBean);
                        startCountDown();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        startCountDown();
//                        mView.jumpToMain();
                    }
                })
        );


    }

    private void startCountDown(){
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(new FlowableTransformer<Long, Long>() {
                    @Override
                    public Publisher<Long> apply(Flowable<Long> observable) {
                        return observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                mView.jumpToMain();
            }
        }));
    }




}
