package com.jola.shengfan.toutiaojola.ui.fragment;

import com.jola.shengfan.toutiaojola.mode.entity.News;
import com.jola.shengfan.toutiaojola.ui.base.BaseFragment;
import com.jola.shengfan.toutiaojola.ui.base.BasePresenter;
import com.jola.shengfan.toutiaojola.ui.presenter.NewsListPresenter;
import com.jola.shengfan.toutiaojola.views.lNewsListView;

import java.util.List;

/**
 * Created by lenovo on 2018/10/16.
 */

public class NewsListFragment extends BaseFragment implements lNewsListView {

    @Override
    protected BasePresenter createPresenter() {
        return new NewsListPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onGetNewsListSuccess(List<News> newList, String tipInfo) {

    }

    @Override
    public void onError() {

    }
}
