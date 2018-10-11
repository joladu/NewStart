package com.jola.shengfan.toutiaojola.ui.base;

import com.jola.shengfan.toutiaojola.api.ApiRetrofit;
import com.jola.shengfan.toutiaojola.api.ApiService;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2018/10/10.
 */

public abstract class BasePresenter<T> {

    protected ApiService apiService = ApiRetrofit.getInstance().getApiService();


    protected T mView;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(T view){
        mView = view;
    }

    public void attachView(T view){
        mView = view;
    }

    public void detachView(){
        mView = null;
    }

    public void addSubscribe(Disposable disposable){
        if (null == mCompositeDisposable){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void onUnSubscribe(){
        if (null != mCompositeDisposable){
            mCompositeDisposable.clear();
        }
    }






}
