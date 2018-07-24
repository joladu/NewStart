package com.jola.newnews.ui.zhihu.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.jola.newnews.R;
import com.jola.newnews.base.BaseFragment;
import com.jola.newnews.contract.zhihu.IDailyContract;
import com.jola.newnews.mode.bean.DailyListBean;
import com.jola.newnews.presenter.zhihu.DailyPresenter;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/7/24.
 */

public class DailyFragment extends BaseFragment<DailyPresenter> implements IDailyContract.IDailyView{

    @BindView(R.id.swipe_refresh_daily_fragment)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view_daily_fragment)
    RecyclerView mRecyclerView;
    @BindView(R.id.floating_action_btn_daily_fragment)
    FloatingActionButton mFloatingActionBtn;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getDailyData();
    }


    @Override
    public void showContent(DailyListBean dailyListBean) {
//        dailyListBean
    }

}
