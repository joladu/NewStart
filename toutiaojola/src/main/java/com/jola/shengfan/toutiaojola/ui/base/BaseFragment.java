package com.jola.shengfan.toutiaojola.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.github.nukc.stateview.StateView;
import com.jola.shengfan.toutiaojola.R;

import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/10/12.
 */

public abstract class BaseFragment<T extends BasePresenter> extends LazyFragment {

    protected T mPresenter;
    protected StateView stateView;
    protected  View rootView;
    protected  Activity mActivity;

    protected abstract T createPresenter();

    protected abstract int getLayoutId();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (null == rootView){
            rootView =inflater.inflate(getLayoutId(),container,false);
            ButterKnife.bind(this,rootView);

            stateView = StateView.inject(getStateViewRoot());
            stateView.setLoadingResource(R.layout.page_loading);
            stateView.setRetryResource(R.layout.page_net_error);

            initView(rootView);
            initData();
            initListener();

        }else{
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent){
                parent.removeView(rootView);
            }
        }
        return rootView;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity)context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter){
            mPresenter.detachView();
        }
        rootView = null;
    }

    @Override
    protected void onFragmentFirstVisible() {
       loadData();
    }

    protected void loadData() {

    }

    protected void initView(View rootView) {

    }

    protected void initData() {

    }

    protected void initListener() {

    }


    private View getStateViewRoot() {
        return rootView;
    }

}
