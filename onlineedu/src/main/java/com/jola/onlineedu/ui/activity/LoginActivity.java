package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;

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
        startActivity(new Intent(LoginActivity.this,VideoViewActivity.class));
    }


}
