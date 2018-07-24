package com.jola.newnews.contract.main;

import com.jola.newnews.base.IBasePresenter;
import com.jola.newnews.base.IBaseView;
import com.jola.newnews.mode.bean.WelcomeBean;

/**
 * Created by lenovo on 2018/7/18.
 */

public interface IWelcomeContract {

    interface IWelcomeView extends IBaseView {
        void showContent(WelcomeBean welcomeBean);
        void jumpToMain();
    }

    interface IWelcomePresenter extends IBasePresenter<IWelcomeView> {
        void getWelcomeData();
    }


}
