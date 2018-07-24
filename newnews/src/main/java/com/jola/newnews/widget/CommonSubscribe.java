package com.jola.newnews.widget;

import android.text.TextUtils;

import com.jola.newnews.base.IBaseView;
import com.jola.newnews.mode.http.exception.ApiException;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * Created by lenovo on 2018/7/23.
 */

public abstract class CommonSubscribe<T> extends ResourceSubscriber<T> {
    private IBaseView mIBaseView;
    private String mErrorMsg;
    private boolean mIsShowErrorState = true;

    protected CommonSubscribe(IBaseView iBaseView){
        mIBaseView = iBaseView;
    }

    protected CommonSubscribe(IBaseView iBaseView,String errorMsg){
        mIBaseView = iBaseView;
        mErrorMsg = errorMsg;
    }

    protected CommonSubscribe(IBaseView iBaseView,boolean isShowErrorState){
        mIBaseView = iBaseView;
        mIsShowErrorState = isShowErrorState;
    }

    protected CommonSubscribe(IBaseView iBaseView,String errorMsg ,boolean isShowErrorState){
        mIBaseView = iBaseView;
        mErrorMsg = errorMsg;
        mIsShowErrorState = isShowErrorState;
    }


    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable t) {
        if (mIBaseView == null){
            return;
        }
        if (!TextUtils.isEmpty(mErrorMsg)){
            mIBaseView.showErrorMsg(mErrorMsg);
        }else if (t instanceof ApiException){
            mIBaseView.showErrorMsg(t.getMessage());
        }else if (t instanceof HttpException){
            mIBaseView.showErrorMsg("数据加载失败！");
        }else{
            mIBaseView.showErrorMsg("未知错误！");
        }
        if (mIsShowErrorState){
            mIBaseView.stateError();
        }
    }
}
