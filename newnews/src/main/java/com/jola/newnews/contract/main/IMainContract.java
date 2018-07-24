package com.jola.newnews.contract.main;

import com.jola.newnews.base.IBasePresenter;
import com.jola.newnews.base.IBaseView;

/**
 * Created by lenovo on 2018/7/20.
 */

public interface IMainContract {

    interface IMainView extends IBaseView{
        void showUpdateDialog(String updateInfo);
    }

    interface IMainPresenter extends IBasePresenter<IMainView>{
        void checkVersion(String currentVersion);
    }

}
