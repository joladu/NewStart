package com.jola.onlineedu.base;

import android.support.v7.app.AppCompatDelegate;
import android.view.ViewGroup;

import com.jola.onlineedu.app.App;
import com.jola.onlineedu.di.component.ActivityComponent;
import com.jola.onlineedu.di.component.DaggerActivityComponent;
import com.jola.onlineedu.di.module.ActivityModule;
import com.jola.onlineedu.util.SnackbarUtil;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2018/8/10
 * 基于SimpleActivity ，注入Presenter extends IBasePresenter
 */

public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView{



    @Inject
    protected T mPresenter;

    protected abstract void initInject();

    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(App.getAppComponent())
                .build();
    }







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
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }


}
