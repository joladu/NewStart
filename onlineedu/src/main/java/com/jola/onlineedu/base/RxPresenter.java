//package com.jola.onlineedu.base;
//
//import io.reactivex.disposables.CompositeDisposable;
//import io.reactivex.disposables.Disposable;
//
///**
// * Created by lenovo on 2018/8/10.
// * 基于Rx 的Presenter
// */
//
//public class RxPresenter<T extends BaseView> implements BasePresenter<T>  {
//
//    protected T mView;
//    private CompositeDisposable mCompositeDisposable;
//
//    protected void addSubscribe(Disposable disposable){
//        if (null == mCompositeDisposable){
//            mCompositeDisposable = new CompositeDisposable();
//        }
//        mCompositeDisposable.add(disposable);
//    }
//
//    protected void unSubscribe(){
//        if (null != mCompositeDisposable){
//            mCompositeDisposable.clear();
//        }
//    }
//
//    @Override
//    public void attachView(T view) {
//        mView = view;
//    }
//
//    @Override
//    public void detachView() {
//        mView = null;
//    }
//
//
//}
