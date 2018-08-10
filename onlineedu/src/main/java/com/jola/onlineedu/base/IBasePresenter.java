package com.jola.onlineedu.base;


/**
 * Created by lenovo on 2018/8/10.
 */

public interface IBasePresenter<T extends IBaseView> {
    /**
     * 将Presenter 关联到具体的View上
     * @param view
     */
    void attachView(T view);

    /**
     * presenter 取消关联时，销毁对象
     */
    void detachView();

}
