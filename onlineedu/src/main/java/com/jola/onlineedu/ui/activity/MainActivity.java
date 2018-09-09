package com.jola.onlineedu.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.app.Constants;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.ui.fragment.FriendFragment;
import com.jola.onlineedu.ui.fragment.HomePageFragment;
import com.jola.onlineedu.ui.fragment.LiveFragment;
import com.jola.onlineedu.ui.fragment.MineFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends SimpleActivity {



    @Inject
    DataManager mDataManager;

    private static final int TYPE_HOME_PAGE = 101;
    private static final int TYPE_LIVE = 201;
    private static final int TYPE_FRIEND = 301;
    private static final int TYPE_MINE = 401;

    private int hideFragmentTag = TYPE_HOME_PAGE;
    private int showFragmentTag = TYPE_HOME_PAGE;

    HomePageFragment mHomePageFragment;
    LiveFragment mLiveFragment;
    FriendFragment mFriendFragment;
    MineFragment mMineFragment;


    @BindView(R.id.tv_home_navi)
    TextView tv_home_navi;
    @BindView(R.id.tv_live_navi)
    TextView tv_live_navi;
    @BindView(R.id.tv_friend_navi)
    TextView tv_friend_navi;
    @BindView(R.id.tv_mine_navi)
    TextView tv_mine_navi;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != savedInstanceState){
            showFragmentTag = mDataManager.getCurMainFragmentTag();
            hideFragmentTag = TYPE_HOME_PAGE;
            showHideFragment(getTargetFragment(showFragmentTag),getTargetFragment(hideFragmentTag));
            hideFragmentTag = showFragmentTag;
        }
    }

    @Override
    protected void initEventAndData() {

        getActivityComponent().inject(this);

        mHomePageFragment = new HomePageFragment();
        mLiveFragment = new LiveFragment();
        mFriendFragment = new FriendFragment();
        mMineFragment = new MineFragment();
        loadMultipleRootFragment(R.id.fl_main_content, 0, mHomePageFragment, mLiveFragment, mFriendFragment, mMineFragment);
        tv_home_navi.setSelected(true);

    }

    @OnClick({R.id.tv_home_navi, R.id.tv_live_navi, R.id.tv_friend_navi, R.id.tv_mine_navi})
    public void doClick(View view) {
        clearnSelectedStatus();
        switch (view.getId()) {
            case R.id.tv_home_navi:
                tv_home_navi.setSelected(true);
             showFragmentTag = TYPE_HOME_PAGE;
                break;
            case R.id.tv_live_navi:
                tv_live_navi.setSelected(true);
                showFragmentTag = TYPE_LIVE;
                break;
            case R.id.tv_friend_navi:
                tv_friend_navi.setSelected(true);
                showFragmentTag = TYPE_FRIEND;
                break;
            case R.id.tv_mine_navi:
                tv_mine_navi.setSelected(true);
                showFragmentTag = TYPE_MINE;
                break;
        }
        showHideFragment(getTargetFragment(showFragmentTag),getTargetFragment(hideFragmentTag));
        hideFragmentTag = showFragmentTag;
        changeFullScreen();
        mDataManager.setCurMainFragmentTag(showFragmentTag);
    }

    public void changeFullScreen(){
        if (Build.VERSION.SDK_INT >= 21) {
            int option = View.SYSTEM_UI_FLAG_VISIBLE;
            if (showFragmentTag == TYPE_MINE){
                option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(option);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }else{
                if (Build.VERSION.SDK_INT < 23){
                    View decorView = getWindow().getDecorView();
                    decorView.setSystemUiVisibility(option);
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
                }else{
                    View decorView = getWindow().getDecorView();
//                    option |= View.SYSTEM_UI_FLAG_VISIBLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    option = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    decorView.setSystemUiVisibility(option);
                }
            }
        }
    }


    private void clearnSelectedStatus(){
        tv_home_navi.setSelected(false);
        tv_live_navi.setSelected(false);
        tv_friend_navi.setSelected(false);
        tv_mine_navi.setSelected(false);
    }

    private SupportFragment getTargetFragment(int type){
        switch (type){
            case TYPE_HOME_PAGE:
                return mHomePageFragment;
            case TYPE_LIVE:
                return mLiveFragment;
            case TYPE_FRIEND:
                return mFriendFragment;
            case TYPE_MINE:
                return mMineFragment;
        }
        return mHomePageFragment;
    }


}
