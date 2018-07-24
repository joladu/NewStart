package com.jola.newnews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.jola.newnews.app.App;
import com.jola.newnews.di.component.DaggerFragmentComponent;
import com.jola.newnews.di.component.FragmentComponent;
import com.jola.newnews.di.module.FragmentModule;
import com.jola.newnews.util.CommonUtil;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/7/24.
 */

public abstract class BaseFragment<T extends IBasePresenter> extends SimpleFragment implements IBaseView{

    @Inject
    protected T mPresenter;

    protected abstract void initInject();

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(App.getInstance().getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        if (null != mPresenter){
            mPresenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (null != mPresenter){
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        CommonUtil.showSnack(((ViewGroup)getActivity().findViewById(android.R.id.content)).getChildAt(0),errorMsg);
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
