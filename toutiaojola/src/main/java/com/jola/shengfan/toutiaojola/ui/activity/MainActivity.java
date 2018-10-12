package com.jola.shengfan.toutiaojola.ui.activity;

import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.jola.shengfan.toutiaojola.R;
import com.jola.shengfan.toutiaojola.mode.event.TabRefreshEvent;
import com.jola.shengfan.toutiaojola.ui.adapter.MainTabAdapter;
import com.jola.shengfan.toutiaojola.ui.base.BaseActivity;
import com.jola.shengfan.toutiaojola.ui.base.BaseFragment;
import com.jola.shengfan.toutiaojola.ui.base.BasePresenter;
import com.jola.shengfan.toutiaojola.ui.fragment.MineFragment;
import com.jola.shengfan.toutiaojola.widget.NoScrollViewPager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/10/11.
 */

public class MainActivity extends BaseActivity {


    @BindView(R.id.vp_content)
    NoScrollViewPager mVpContent;
    @BindView(R.id.bottom_bar)
    BottomBarLayout mBottomBarLayout;

    private ArrayList<BaseFragment> mFragments;
    private MainTabAdapter mTabAdapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>(4);
        mFragments.add(new MineFragment());
        mFragments.add(new MineFragment());
        mFragments.add(new MineFragment());
        mFragments.add(new MineFragment());
    }

    @Override
    protected void initListener() {
        mTabAdapter = new MainTabAdapter(getSupportFragmentManager(), mFragments);
        mVpContent.setAdapter(mTabAdapter);
        mVpContent.setOffscreenPageLimit(mFragments.size());
        mBottomBarLayout.setViewPager(mVpContent);
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int position) {
//                setStatusBarColor(position);

//                if (position == 0 || position ==1){
//                    if (mBottomBarLayout.getCurrentItem() == position){
//                        String channelCode = "";
//                        postTabRefreshEvent(bottomBarItem,position,channelCode);
//                    }
//                    return;
//                }

//                BottomBarItem bottomItem = mBottomBarLayout.getBottomItem(0);
//                bottomItem.setIconSelectedResourceId(R.mipmap.tab_home_selected);//更换为原来的图标

            }
        });

    }


    private void setStatusBarColor(int position) {
        if (position == 3){
//            Eyes.translucentStatusBar(MainActivity.this,true);
        }else{
//            Eyes.setStatusBarColor(MainActivity.this, com.chaychan.news.utils.UIUtils.getColor(mStatusColors[position]));

        }
    }


    private void postTabRefreshEvent(BottomBarItem bottomBarItem, int position, String channelCode) {
        TabRefreshEvent event = new TabRefreshEvent();
        event.setChannelCode(channelCode);
        event.setBottomBarItem(bottomBarItem);
        event.setHomeTab(position == 0);
        EventBus.getDefault().post(event);//发送下拉刷新事件
    }



}
