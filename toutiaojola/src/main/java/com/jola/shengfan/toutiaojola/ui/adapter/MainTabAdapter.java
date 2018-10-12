package com.jola.shengfan.toutiaojola.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.jola.shengfan.toutiaojola.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by lenovo on 2018/10/12.
 */

public class MainTabAdapter extends FragmentStatePagerAdapter {


    private List<BaseFragment> mFragmentList;

    public MainTabAdapter(FragmentManager fm,List<BaseFragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }

}
