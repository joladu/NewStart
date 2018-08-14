package com.jola.onlineedu.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jola.onlineedu.app.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/8/10.
 * 使用butterknife ,方法抽象化，统一管理activity
 */

public abstract class SimpleActivity extends AppCompatActivity {

    private Unbinder mUnbind;
    protected Activity mContext;

    protected abstract int getLayout();

    protected  void onViewCreated(){};

    protected abstract void initEventAndData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnbind = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivy(this);
        mUnbind.unbind();
    }
}