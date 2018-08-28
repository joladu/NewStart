package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.util.StatusBarUtil;
import com.jola.onlineedu.util.ToastUtil;

import butterknife.OnClick;

public class LoginActivity extends SimpleActivity {


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {

       StatusBarUtil.setStatusBarTranslucent(this);
//        ImmersionBar.with(this)
//                .transparentBar()
//                .init();

//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        StatusBarUtil.setStatusBarBlack(this);

    }

    @OnClick(R.id.tv_login)
    public void login(View view){
        ToastUtil.toastShort("登录");
//        startActivity(new Intent(LoginActivity.this,VideoViewActivity.class));
        startActivity(new Intent(LoginActivity.this,ForumListActivity.class));
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


//    @Override
//    protected void onDestroy() {
//        ImmersionBar.with(this).destroy();
//        super.onDestroy();
//    }
}
