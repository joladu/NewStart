package com.jola.newnews.ui.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by lenovo on 2018/7/25.
 */

public class ZhiHuMainAdapter extends FragmentStatePagerAdapter {


    private final List<Fragment> mList;
    private String[] mTitlesList;


    public ZhiHuMainAdapter(FragmentManager fm, List<Fragment> mList) {
        super(fm);
        this.mList = mList;
    }

    public ZhiHuMainAdapter(FragmentManager fm, List<Fragment> mList,String[] mTitlesList) {
        super(fm);
        this.mList = mList;
        this.mTitlesList = mTitlesList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (null != mTitlesList && mTitlesList.length > 0){
            if (position >= 0 && position < mTitlesList.length){
                return mTitlesList[position];
            }
        }
        return "未知";

    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }
}
