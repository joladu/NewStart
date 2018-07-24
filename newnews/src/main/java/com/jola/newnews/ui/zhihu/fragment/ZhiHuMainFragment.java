package com.jola.newnews.ui.zhihu.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jola.newnews.R;
import com.jola.newnews.base.SimpleFragment;

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
    List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_main;
    }

    @Override
    protected void initEventAndData() {

    }


}
