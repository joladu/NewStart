package com.jola.newnews.base;

import android.view.ViewGroup;

import com.jola.newnews.app.App;
import com.jola.newnews.di.component.ActivityComponent;
import com.jola.newnews.di.component.DaggerActivityComponent;
import com.jola.newnews.di.module.ActivityModule;
import com.jola.newnews.util.CommonUtil;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/7/17
 * 基于SimpleActivity (ButterKnife) 另外带mvp结构 完成presenter 的注入 供 子类使用
 */

public abstract class BaseActivity<T extends IBasePresenter> extends SimpleActivity implements IBaseView {

    public static final String TAG = "BaseActivity";

    @Inject
    protected  T mPresenter;

    private ActivityComponent mActivityComponent;

    protected ActivityComponent getActivityComponent(){
        if (null == mActivityComponent){
            mActivityComponent = DaggerActivityComponent
                    .builder()
                    .appComponent(App.getInstance().getAppComponent())
                    .activityModule(getActivityModule())
                    .build();
        }
        return mActivityComponent;
    }

    private ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }


    @Override
    protected void onViewCreated() {
        super.onViewCreated();
//        LogUtil.log("e","jola", TAG+"begin onViewCreated");
        initInject();
        if (null != mPresenter){
            mPresenter.attachView(this);
        }
//        LogUtil.log("e","jola", TAG+"end onViewCreated");
    }

    @Override
    protected void onDestroy() {
        if (null != mPresenter){
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    protected abstract void initInject();

    @Override
    public void showErrorMsg(String errorMsg) {
        CommonUtil.showSnack(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0),errorMsg);
    }

    @Override
    public void useNightMode(boolean isNight) {

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
