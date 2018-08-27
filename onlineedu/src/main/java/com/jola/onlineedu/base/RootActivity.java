package com.jola.onlineedu.base;

import android.view.View;
import android.view.ViewGroup;

import com.jola.onlineedu.R;

/**
 * Created by lenovo on 2018/8/27.
 */

public abstract class RootActivity<T extends BasePresenter> extends BaseActivity<T> {

    private static final int STATE_MAIN = 0x001;
    private static final int STATE_ERROR = 0x010;
    private static final int STATE_LOADING = 0x100;


    private int currentState = STATE_MAIN;

    private boolean isErrorViewAdded = false;

    private ViewGroup viewMain;
    private ViewGroup mParent;
    private View viewLoading;
    private View viewError;

    @Override
    protected void initEventAndData() {
        viewMain = (ViewGroup) findViewById(R.id.view_main);
        if (null == viewMain){
            throw new IllegalStateException("The subclass of RootSimpleActivity must contain view named  'view_main' ");
        }
        if (!(viewMain.getParent() instanceof ViewGroup)){
            throw new IllegalStateException("view_main's parent should be a ViewGroup");
        }
        mParent = (ViewGroup) viewMain.getParent();
        View.inflate(mContext,R.layout.view_loading,mParent);
        viewLoading = mParent.findViewById(R.id.loading_view_ll);
        viewLoading.setVisibility(View.GONE);
        viewMain.setVisibility(View.VISIBLE);
    }


    @Override
    public void stateError() {
        if (currentState == STATE_ERROR){
            return;
        }
        if (!isErrorViewAdded){
            View.inflate(mContext,R.layout.view_error,mParent);
            viewError = mParent.findViewById(R.id.view_error_ll);
            if (null == viewError){
                throw new IllegalStateException("There is no view_error_ll in ErrorViewLayout");
            }
            hideCurrentView();
            viewError.setVisibility(View.VISIBLE);
            currentState = STATE_ERROR;
            isErrorViewAdded = true;
        }
    }


    @Override
    public void stateLoading() {
        if (currentState == STATE_LOADING){
            return;
        }
        hideCurrentView();
        viewLoading.setVisibility(View.VISIBLE);
        currentState = STATE_LOADING;
    }

    @Override
    public void stateMain() {
        if (currentState == STATE_MAIN){
            return;
        }
        hideCurrentView();
        viewMain.setVisibility(View.VISIBLE);
        currentState = STATE_MAIN;
    }


    private void hideCurrentView(){
        switch (currentState){
            case STATE_ERROR:
                if (null != viewError){
                    viewError.setVisibility(View.GONE);
                }
                break;
            case STATE_MAIN:
                if (null != viewMain){
                    viewMain.setVisibility(View.GONE);
                }
                break;
            case STATE_LOADING:
                if (null != viewLoading){
                    viewLoading.setVisibility(View.GONE);
                }
                break;
        }
    }


}
