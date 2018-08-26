package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.util.ToastUtil;

import butterknife.OnClick;

public class LoginActivity extends SimpleActivity {


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {

    }

    @OnClick(R.id.tv_login)
    public void login(View view){
        ToastUtil.toastShort("登录");
//        startActivity(new Intent(LoginActivity.this,VideoViewActivity.class));
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


}
