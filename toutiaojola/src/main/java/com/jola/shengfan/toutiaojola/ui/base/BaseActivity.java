package com.jola.shengfan.toutiaojola.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/10/11.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity{

    protected  T mPresenter;

    protected abstract T createPresenter();

    protected abstract int provideLayoutId();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();

        setContentView(provideLayoutId());
        ButterKnife.bind(this);

        initView();
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter){
            mPresenter.detachView();
        }
    }



    protected void initView() {

    }


    protected void initData() {

    }


    protected void initListener() {

    }


//    public void requestPermission(String[] permissions, IPermissionListener iPermissionListener){
//
//    }


}
