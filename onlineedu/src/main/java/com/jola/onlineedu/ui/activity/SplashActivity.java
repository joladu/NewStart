package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResUserLogin;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.StatusBarUtil;
import com.jola.onlineedu.util.SystemUtil;
import com.jola.onlineedu.util.ToastUtil;

import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by jola on 2018/8/21.
 */

public class SplashActivity extends SimpleActivity {

    @Inject
    DataManager dataManager;


    @BindView(R.id.iv_icon_class)
    ImageView iv_icon_class;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEventAndData() {

        getActivityComponent().inject(this);

//        ImmersionBar.with(this)
//                .statusBarDarkFont(true, 0.2f)
//                .init();

//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        StatusBarUtil.setStatusBarBlack(this);


        if (!SystemUtil.isNetworkConnected()) {
            ToastUtil.toastShort("当前无网络连接！");
            splashDelayMiliSecondsToLogin(2000);
            return;
        }

        String userPassword = dataManager.getUserPassword();
        String userName = dataManager.getUserName();

        if (null != userPassword && userPassword.length() > 0 && null != userName && userName.length() > 0){
            dataManager.getUserLoginInfo(userName,userPassword)
                    .compose(RxUtil.<ResUserLogin>rxSchedulerHelper())
                    .subscribe(new Consumer<ResUserLogin>() {
                        @Override
                        public void accept(ResUserLogin resUserLogin) throws Exception {
                            if (resUserLogin.getError_code() == 0){
                                dataManager.setUserPhone(resUserLogin.getData().getUser().getMobile());
                                dataManager.setUserId(resUserLogin.getData().getUser().getId()+"");
                                dataManager.setUserToken(resUserLogin.getData().getToken());
                                splashDelayMiliSecondsToMain(1000);
                            }else{
                                splashDelayMiliSecondsToLogin(1000);
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            splashDelayMiliSecondsToLogin(1000);
                        }
                    });
        }else{
            splashDelayMiliSecondsToLogin(2000);
        }

//        iv_icon_class.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
//                SplashActivity.this.finish();
//            }
//        },2000);

//        addSubscribe(Flowable.timer(2000, TimeUnit.MILLISECONDS)
//                .compose(RxUtil.<Long>rxSchedulerHelper())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) {
//                       startActivity(new Intent(SplashActivity.this,LoginActivity.class));
//                       SplashActivity.this.finish();
//                    }
//                })
//        );
    }

//    @Override
//    protected void onDestroy() {
//        ImmersionBar.with(this).destroy();
//        super.onDestroy();
//    }

    private void splashDelayMiliSecondsToLogin(int miliseconds){
        iv_icon_class.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                SplashActivity.this.finish();
            }
        },miliseconds);
    }

    private void splashDelayMiliSecondsToMain(int miliseconds){
        iv_icon_class.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                SplashActivity.this.finish();
            }
        },miliseconds);
    }

    private void startToMain(){
        startActivity(new Intent(SplashActivity.this,MainActivity.class));
        SplashActivity.this.finish();
    }

    private void startToLogin(){
        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        SplashActivity.this.finish();
    }






}
