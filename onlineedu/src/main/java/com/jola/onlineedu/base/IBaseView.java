package com.jola.onlineedu.base;

/**
 * Created by lenovo on 2018/8/10.
 */

public interface IBaseView {
    void stateErr();
    void stateLoading();
    void stateEmpty();
    void showErrMsg(String errMsg);
}
