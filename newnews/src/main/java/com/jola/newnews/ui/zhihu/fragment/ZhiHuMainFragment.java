package com.jola.newnews.ui.zhihu.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jola.newnews.R;
import com.jola.newnews.base.SimpleFragment;
import com.jola.newnews.ui.zhihu.adapter.ZhiHuMainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/7/23.
 */

public class ZhiHuMainFragment extends SimpleFragment {

    @BindView(R.id.tablayout_zhihu_main)
    TabLayout mTablayout;
    @BindView(R.id.viewpager_zhihu_main)
    ViewPager mViewPager;

    String[] tabTitle = new String[]{"日报","主题","专栏","热门"};
//    String[] tabTitle = new String[]{"日报"};
    List<Fragment> fragments = new ArrayList<Fragment>();
    private ZhiHuMainAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_main;
    }

    @Override
    protected void initEventAndData() {
        fragments.add(new DailyFragment());
        fragments.add(new DailyFragment());
        fragments.add(new DailyFragment());
        fragments.add(new DailyFragment());
//        mAdapter = new ZhiHuMainAdapter(getChildFragmentManager(), fragments);
        mAdapter = new ZhiHuMainAdapter(getChildFragmentManager(), fragments,tabTitle);
        mViewPager.setAdapter(mAdapter);

        mTablayout.addTab(mTablayout.newTab().setText(tabTitle[0]));
        mTablayout.addTab(mTablayout.newTab().setText(tabTitle[1]));
        mTablayout.addTab(mTablayout.newTab().setText(tabTitle[2]));
        mTablayout.addTab(mTablayout.newTab().setText(tabTitle[3]));
        mTablayout.setupWithViewPager(mViewPager);
//        mTablayout.getTabAt(0).setTag(tabTitle[0]);
//        mTablayout.getTabAt(1).setTag(tabTitle[1]);
//        mTablayout.getTabAt(2).setTag(tabTitle[2]);
//        mTablayout.getTabAt(3).setTag(tabTitle[3]);

    }


}
