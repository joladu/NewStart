package com.jola.onlineedu.contract;

import com.jola.onlineedu.base.BasePresenter;
import com.jola.onlineedu.base.BaseView;

/**
 * Created by jola on 2018/8/30.
 */

public interface RegisterContract {

    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter<View>{
        void doRegister();
    }
}
