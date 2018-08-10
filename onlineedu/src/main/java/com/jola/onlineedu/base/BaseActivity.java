package com.jola.onlineedu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lenovo on 2018/8/10
 * 基于SimpleActivity ，注入Presenter extends IBasePresenter
 */

public abstract class BaseActivity<T extends IBasePresenter> extends SimpleActivity implements IBaseView{


    protected T mPresenter;

    protected abstract void initInject();

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
//        完成注入
        initInject();
//        将View关联到Presenter
        if (null != mPresenter){
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (null != mPresenter){
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void stateErr() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void showErrMsg(String errMsg) {
        Snackbar.make(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), errMsg, Snackbar.LENGTH_LONG).show();
    }
}
