package com.jola.newnews.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.jola.newnews.R;
import com.jola.newnews.app.Constants;
import com.jola.newnews.base.BaseActivity;
import com.jola.newnews.contract.main.IMainContract;
import com.jola.newnews.presenter.main.MainPresenter;
import com.jola.newnews.ui.zhihu.fragment.ZhiHuMainFragment;
import com.jola.newnews.util.LogUtil;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by lenovo on 2018/7/20.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements IMainContract.IMainView{


    @BindView(R.id.drawer_layout_main)
    DrawerLayout mDrawableLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation_view_main)
    NavigationView mNavigationView;
    @BindView(R.id.view_search)
    MaterialSearchView mMaterialSearchView;

    ZhiHuMainFragment mZhiHuMainFragment;
    ZhiHuMainFragment mGankFragment;
    ZhiHuMainFragment mWechatFragment;
    ZhiHuMainFragment mGoldFragment;
    ZhiHuMainFragment mVtexFragment;
    ZhiHuMainFragment mLikeFragment;
    ZhiHuMainFragment mSettingFragment;
    ZhiHuMainFragment mAboutFragment;

    MenuItem mLastMenuItem;
    MenuItem mSearchMenuItem;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private int hideFragment = Constants.TYPE_ZHIHU;
    private int showFragment = Constants.TYPE_ZHIHU;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == savedInstanceState){
            mPresenter.setNightModeState(false);
        }else{
            showFragment = mPresenter.getCurrentItem();
            hideFragment = Constants.TYPE_ZHIHU;
            showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
            mNavigationView.getMenu().findItem(R.id.drawer_zhihu).setChecked(false);
            mToolbar.setTitle(mNavigationView.getMenu().findItem(getCurrentItem(showFragment)).getTitle().toString());
            hideFragment = showFragment;
        }
    }

    @Override
    protected void initInject() {getActivityComponent().inject(this);}

    @Override
    protected void initEventAndData() {
//        LogUtil.logInteresting("MainActivity presenter inited :"+mPresenter.toString());
        setToolbar(mToolbar,"知乎日报");

        mZhiHuMainFragment = new ZhiHuMainFragment();
        mGankFragment = new ZhiHuMainFragment();
        mWechatFragment = new ZhiHuMainFragment();
        mGoldFragment = new ZhiHuMainFragment();
        mVtexFragment = new ZhiHuMainFragment();
        mLikeFragment = new ZhiHuMainFragment();
        mSettingFragment = new ZhiHuMainFragment();
        mAboutFragment = new ZhiHuMainFragment();

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawableLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mActionBarDrawerToggle.syncState();
        mDrawableLayout.addDrawerListener(mActionBarDrawerToggle);

        mLastMenuItem = mNavigationView.getMenu().findItem(R.id.drawer_zhihu);
//        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_container_main,mZhiHuMainFragment).commit();
        loadMultipleRootFragment(R.id.frame_layout_container_main,0,mZhiHuMainFragment,mGankFragment,mWechatFragment,mGoldFragment,mVtexFragment,mLikeFragment,mSettingFragment,mAboutFragment);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.drawer_zhihu:
                        showFragment = Constants.TYPE_ZHIHU;
                        mSearchMenuItem.setVisible(false);
                        break;
                    case R.id.drawer_gank:
                        showFragment = Constants.TYPE_GANK;
                        mSearchMenuItem.setVisible(true);
                        break;
                    case R.id.drawer_wechat:
                        showFragment = Constants.TYPE_WECHAT;
                        mSearchMenuItem.setVisible(true);
                        break;
                    case R.id.drawer_gold:
                        showFragment = Constants.TYPE_GOLD;
                        mSearchMenuItem.setVisible(false);
                        break;
                    case R.id.drawer_vtex:
                        showFragment = Constants.TYPE_VTEX;
                        mSearchMenuItem.setVisible(false);
                        break;
                    case R.id.drawer_setting:
                        showFragment = Constants.TYPE_SETTING;
                        mSearchMenuItem.setVisible(false);
                        break;
                    case R.id.drawer_like:
                        showFragment = Constants.TYPE_LIKE;
                        mSearchMenuItem.setVisible(false);
                        break;
                    case R.id.drawer_about:
                        showFragment = Constants.TYPE_ABOUT;
                        mSearchMenuItem.setVisible(false);
                        break;
                }
                if(mLastMenuItem != null) {
                    mLastMenuItem.setChecked(false);
                }
                mLastMenuItem = menuItem;
                mPresenter.setCurrentItem(showFragment);
                menuItem.setChecked(true);
                mToolbar.setTitle(menuItem.getTitle());
                mDrawableLayout.closeDrawers();
                showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
                hideFragment = showFragment;
                return true;
            }
        });

        mMaterialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(showFragment == Constants.TYPE_GANK) {
//                    mGankFragment.doSearch(query);
                } else if(showFragment == Constants.TYPE_WECHAT) {
//                    RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_WECHAT));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(true);
        mMaterialSearchView.setMenuItem(item);
        mSearchMenuItem = item;
        return true;
    }

    @Override
    public void showUpdateDialog(String updateInfo) {

    }

    private SupportFragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_ZHIHU:
                return mZhiHuMainFragment;
            case Constants.TYPE_GANK:
                return mGankFragment;
            case Constants.TYPE_WECHAT:
                return mWechatFragment;
            case Constants.TYPE_GOLD:
                return mGoldFragment;
            case Constants.TYPE_VTEX:
                return mVtexFragment;
            case Constants.TYPE_LIKE:
                return mLikeFragment;
            case Constants.TYPE_SETTING:
                return mSettingFragment;
            case Constants.TYPE_ABOUT:
                return mAboutFragment;
        }
        return mZhiHuMainFragment;
    }


    private int getCurrentItem(int item) {
        switch (item) {
            case Constants.TYPE_ZHIHU:
                return R.id.drawer_zhihu;
            case Constants.TYPE_GANK:
                return R.id.drawer_gank;
            case Constants.TYPE_WECHAT:
                return R.id.drawer_wechat;
            case Constants.TYPE_GOLD:
                return R.id.drawer_gold;
            case Constants.TYPE_VTEX:
                return R.id.drawer_vtex;
            case Constants.TYPE_LIKE:
                return R.id.drawer_like;
            case Constants.TYPE_SETTING:
                return R.id.drawer_setting;
            case Constants.TYPE_ABOUT:
                return R.id.drawer_about;
        }
        return R.id.drawer_zhihu;
    }


}
