package com.jola.newnews.base;

/**
 * Created by lenovo on 2018/7/17
 * 公用的一些视图操作
 */

public interface IBaseView {

    void showErrorMsg(String errorMsg);

    void useNightMode(boolean isNight);

    void stateError();
    void stateEmpty();
    void stateLoading();
    void stateMain();

}
