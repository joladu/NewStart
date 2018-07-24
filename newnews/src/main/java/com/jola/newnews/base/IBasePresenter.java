package com.jola.newnews.base;

/**
 * Created by lenovo on 2018/7/17.
 */

public interface IBasePresenter<T extends IBaseView> {
    void attachView(T view);
    void detachView();
}
