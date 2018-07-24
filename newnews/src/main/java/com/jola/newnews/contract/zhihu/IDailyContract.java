package com.jola.newnews.contract.zhihu;

import com.jola.newnews.base.IBasePresenter;
import com.jola.newnews.base.IBaseView;
import com.jola.newnews.mode.bean.DailyListBean;

/**
 * Created by lenovo on 2018/7/24.
 */

public interface IDailyContract {

    interface IDailyView extends IBaseView{
        void showContent(DailyListBean dailyListBean);
    }

    interface IDailyPresenter extends IBasePresenter<IDailyView>{
        void getDailyData();
    }

}
