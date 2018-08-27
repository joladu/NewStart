package com.jola.onlineedu.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.jola.onlineedu.app.App;
import com.jola.onlineedu.di.component.DaggerFragmentComponent;
import com.jola.onlineedu.di.component.FragmentComponent;
import com.jola.onlineedu.di.module.FragmentModule;
import com.jola.onlineedu.util.SnackbarUtil;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/8/27.
 */

public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView{

    @Inject
    protected T mPresenter;

    protected abstract void initInject();

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    private FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
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
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
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
