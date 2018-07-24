package com.jola.newnews.presenter.zhihu;

import com.jola.newnews.base.RxPresenter;
import com.jola.newnews.contract.zhihu.IDailyContract;
import com.jola.newnews.mode.DataManage;
import com.jola.newnews.mode.bean.DailyListBean;
import com.jola.newnews.util.CommonUtil;
import com.jola.newnews.widget.CommonSubscribe;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/7/24.
 */

public class DailyPresenter extends RxPresenter<IDailyContract.IDailyView> implements IDailyContract.IDailyPresenter {


    private DataManage mDataManage;

    @Inject
    public DailyPresenter(DataManage dataManage) {
        mDataManage = dataManage;
    }

    @Override
    public void attachView(IDailyContract.IDailyView view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
//        addSubscribe(RxBus.getInstanceDefault().toFlowable());
    }

    @Override
    public void getDailyData() {
        addSubscribe(mDataManage.fetchDailyListBean()
        .compose(CommonUtil.<DailyListBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscribe<DailyListBean>(mView) {
                    @Override
                    public void onNext(DailyListBean dailyListBean) {
                        mView.showContent(dailyListBean);
                    }
                })
        );

    }




}
