package com.jola.onlineedu.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.ui.fragment.FriendFragment;
import com.jola.onlineedu.ui.fragment.HomePageFragment;
import com.jola.onlineedu.ui.fragment.LiveFragment;
import com.jola.onlineedu.ui.fragment.MineFragment;
import com.jola.onlineedu.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends SimpleActivity {


    @Inject
    DataManager mDataManager;

    @BindView(R.id.tv_home_navi)
    TextView tv_home_navi;
    @BindView(R.id.tv_live_navi)
    TextView tv_live_navi;
    @BindView(R.id.tv_friend_navi)
    TextView tv_friend_navi;
    @BindView(R.id.tv_mine_navi)
    TextView tv_mine_navi;


    HomePageFragment mHomePageFragment;
    LiveFragment mLiveFragment;
    FriendFragment mFriendFragment;
    MineFragment mMineFragment;

    private static final int TYPE_HOME_PAGE = 101;
    private static final int TYPE_LIVE = 201;
    private static final int TYPE_FRIEND = 301;
    private static final int TYPE_MINE = 401;

    public static final String HomePageFragmentTag = "home_page";
    public static final String LiveFragmentTag = "live";
    public static final String FriendFragmentTag = "friend";
    public static final String MineFragmentTag = "mine";

    private long mLastOnBackPressedTime = 0;

    private int mCurPostion = TYPE_HOME_PAGE;

    private static final String Tag_Position = "cur_pos";

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != savedInstanceState) {
            resumeFragment(savedInstanceState.getInt(Tag_Position));
        }else{
            setHomePageFragment();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        保存位置
        outState.putInt(Tag_Position,mCurPostion);
    }

    private void setHomePageFragment() {
        mCurPostion = TYPE_HOME_PAGE;
        clearnSelectedStatus();
        tv_home_navi.setSelected(true);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (null == mHomePageFragment){
            mHomePageFragment = new HomePageFragment();
            fragmentTransaction.add(R.id.fl_main_content, mHomePageFragment,HomePageFragmentTag);
        }else{
            fragmentTransaction.show(mHomePageFragment);
        }
        fragmentTransaction.commit();
    }


    private void resumeFragment(int curPostion) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        switch (curPostion){
            case TYPE_HOME_PAGE:
                clearnSelectedStatus();
                tv_home_navi.setSelected(true);
                hideAllFragment(transaction);
                if (null == mHomePageFragment) {
                    mHomePageFragment = new HomePageFragment();
                    transaction.add(R.id.fl_main_content,mHomePageFragment,HomePageFragmentTag);
                }else{
                    transaction.show(mHomePageFragment);
                }
                break;
            case TYPE_LIVE:
                clearnSelectedStatus();
                tv_live_navi.setSelected(true);
                hideAllFragment(transaction);
                if (null == mLiveFragment) {
                    mLiveFragment = new LiveFragment();
                    transaction.add(R.id.fl_main_content,mLiveFragment,LiveFragmentTag);
                }else{
                    transaction.show(mLiveFragment);
                }
                break;
            case TYPE_FRIEND:
                clearnSelectedStatus();
                tv_friend_navi.setSelected(true);
                hideAllFragment(transaction);
                if (null == mFriendFragment) {
                    mFriendFragment = new FriendFragment();
                    transaction.add(R.id.fl_main_content,mFriendFragment,FriendFragmentTag);
                }else{
                    transaction.show(mFriendFragment);
                }
                break;
            case TYPE_MINE:
                clearnSelectedStatus();
                tv_mine_navi.setSelected(true);
                hideAllFragment(transaction);
                if (null == mMineFragment) {
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.fl_main_content,mMineFragment,MineFragmentTag);
                }else{
                    transaction.show(mMineFragment);
                }
                break;
        }
        transaction.commit();
        mCurPostion = curPostion;
    }

    @Override
    protected void initEventAndData() {

        getActivityComponent().inject(this);

//        mHomePageFragment = new HomePageFragment();
//        mLiveFragment = new LiveFragment();
//        mFriendFragment = new FriendFragment();
//        mMineFragment = new MineFragment();

//        loadMultipleRootFragment(R.id.fl_main_content, 0, mHomePageFragment, mLiveFragment, mFriendFragment, mMineFragment);
//        tv_home_navi.setSelected(true);
//        setHomePageFragment();

    }

    @OnClick({R.id.tv_home_navi, R.id.tv_live_navi, R.id.tv_friend_navi, R.id.tv_mine_navi})
    public void doClick(View view) {
        clearnSelectedStatus();
        switch (view.getId()) {
            case R.id.tv_home_navi:
//                tv_home_navi.setSelected(true);
//                showFragmentTag = TYPE_HOME_PAGE;
                resumeFragment(TYPE_HOME_PAGE);
                break;
            case R.id.tv_live_navi:
//                tv_live_navi.setSelected(true);
//                showFragmentTag = TYPE_LIVE;
                resumeFragment(TYPE_LIVE);
                break;
            case R.id.tv_friend_navi:
//                tv_friend_navi.setSelected(true);
//                showFragmentTag = TYPE_FRIEND;
                resumeFragment(TYPE_FRIEND);
                break;
            case R.id.tv_mine_navi:
//                tv_mine_navi.setSelected(true);
//                showFragmentTag = TYPE_MINE;
                resumeFragment(TYPE_MINE);
                break;
        }
        changeFullScreen();

//        showHideFragment(getTargetFragment(showFragmentTag), getTargetFragment(hideFragmentTag));
//        hideFragmentTag = showFragmentTag;
//
//        mDataManager.setCurMainFragmentTag(showFragmentTag);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void changeFullScreen() {
        if (Build.VERSION.SDK_INT >= 21) {
            int option = View.SYSTEM_UI_FLAG_VISIBLE;
            if (mCurPostion == TYPE_MINE) {
                option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(option);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else {
                if (Build.VERSION.SDK_INT < 23) {
                    View decorView = getWindow().getDecorView();
                    decorView.setSystemUiVisibility(option);
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    View decorView = getWindow().getDecorView();
//                    option |= View.SYSTEM_UI_FLAG_VISIBLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    option = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    decorView.setSystemUiVisibility(option);
                }
            }
        }
    }


    public void hideAllFragment(FragmentTransaction transaction){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        mHomePageFragment = (HomePageFragment)supportFragmentManager.findFragmentByTag(HomePageFragmentTag);
        mLiveFragment = (LiveFragment)supportFragmentManager.findFragmentByTag(LiveFragmentTag);
        mFriendFragment = (FriendFragment) supportFragmentManager.findFragmentByTag(FriendFragmentTag);
        mMineFragment = (MineFragment)supportFragmentManager.findFragmentByTag(MineFragmentTag);
        if (null != mHomePageFragment){
            transaction.hide(mHomePageFragment);
        }
        if (null != mLiveFragment){
            transaction.hide(mLiveFragment);
        }
        if (null != mFriendFragment){
            transaction.hide(mFriendFragment);
        }
        if (null != mMineFragment){
            transaction.hide(mMineFragment);
        }
    }

    private void clearnSelectedStatus() {
        tv_home_navi.setSelected(false);
        tv_live_navi.setSelected(false);
        tv_friend_navi.setSelected(false);
        tv_mine_navi.setSelected(false);
    }

//    private Fragment getTargetFragment(int type) {
//        switch (type) {
//            case TYPE_HOME_PAGE:
//                return mHomePageFragment;
//            case TYPE_LIVE:
//                return mLiveFragment;
//            case TYPE_FRIEND:
//                return mFriendFragment;
//            case TYPE_MINE:
//                return mMineFragment;
//        }
//        return mHomePageFragment;
//    }

    @Override
    public void onBackPressed() {
        long currentBackTime = System.currentTimeMillis();
        // 两次退出按钮时间间隔小于0.5秒则直接退出app
        if (currentBackTime - mLastOnBackPressedTime < 500) {
            MainActivity.this.finish();
        } else {
            ToastUtil.toastShort("连按两次退出应用");
            mLastOnBackPressedTime = currentBackTime;
        }
    }


}
