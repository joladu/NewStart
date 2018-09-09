package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResUserLogin;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.StatusBarUtil;
import com.jola.onlineedu.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class LoginActivity extends SimpleActivity {


    @Inject
    DataManager mDataManager;

    @BindView(R.id.et_account)
    EditText et_account;
    @BindView(R.id.et_password)
    EditText et_password;


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
       StatusBarUtil.setStatusBarTranslucent(this);
        getActivityComponent().inject(this);
    }

    @OnClick(R.id.tv_login)
    public void login(View view){
        String account = et_account.getText().toString();
        String password = et_password.getText().toString();
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)){
            ToastUtil.toastShort("请输入账号和密码后，再重试！");
            return;
        }
        showLoadingDialog();
        addSubscribe(mDataManager.fetchUserLoginInfo(account,password)
            .compose(RxUtil.<ResUserLogin>rxSchedulerHelper())
            .subscribe(new Consumer<ResUserLogin>() {
                @Override
                public void accept(ResUserLogin resUserLogin) {
                    hideLoadingDialog();
                    int error_code = resUserLogin.getError_code();
                    if (error_code == 0) {
                        ToastUtil.toastShort("登陆成功！");
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        ToastUtil.toastLong(resUserLogin.getError_msg());
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable){
                    hideLoadingDialog();
                    ToastUtil.toastLong(getString(R.string.error_server_message));
                }
            }));
    }

    @OnClick(R.id.tv_forget_password)
    public void forgetPassword(View view){
//        ToastUtil.shortShow("忘记密码");
        startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
    }

    @OnClick(R.id.tv_tip_register_green)
    public void register(View view){
//        ToastUtil.toastShort("注册");
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }

    @OnClick(R.id.iv_ali_login)
    public void aliLogin(View view){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//        startActivity(new Intent(LoginActivity.this, ForumListActivity.class));
    }

    @OnClick(R.id.iv_qq_login)
    public void qqLogin(View view){
//        startActivity(new Intent(LoginActivity.this, TestPoolActivity.class));
    }

    @OnClick(R.id.iv_weibo_login)
    public void weiboLogin(View view){

    }

    @OnClick(R.id.iv_wechat_login)
    public void wechatLogin(View view){

    }


}
