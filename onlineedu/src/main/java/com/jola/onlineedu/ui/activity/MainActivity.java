package com.jola.onlineedu.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.VersionUpdateBean;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.ui.fragment.FriendFragment;
import com.jola.onlineedu.ui.fragment.HomePageFragment;
import com.jola.onlineedu.ui.fragment.LiveFragment;
import com.jola.onlineedu.ui.fragment.MineFragment;
import com.jola.onlineedu.util.ToastUtil;
import com.jola.onlineedu.util.UpdateAppHttpUtil;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.vector.update_app.utils.AppUpdateUtils.getVersionName;

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

    private int MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 101;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != savedInstanceState) {
            resumeFragment(savedInstanceState.getInt(Tag_Position));
        } else {
            setHomePageFragment();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        保存位置
        outState.putInt(Tag_Position, mCurPostion);
    }

    private void setHomePageFragment() {
        mCurPostion = TYPE_HOME_PAGE;
        clearnSelectedStatus();
        tv_home_navi.setSelected(true);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (null == mHomePageFragment) {
            mHomePageFragment = new HomePageFragment();
            fragmentTransaction.add(R.id.fl_main_content, mHomePageFragment, HomePageFragmentTag);
        } else {
            fragmentTransaction.show(mHomePageFragment);
        }
        fragmentTransaction.commit();
    }


    private void resumeFragment(int curPostion) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        switch (curPostion) {
            case TYPE_HOME_PAGE:
                clearnSelectedStatus();
                tv_home_navi.setSelected(true);
                hideAllFragment(transaction);
                if (null == mHomePageFragment) {
                    mHomePageFragment = new HomePageFragment();
                    transaction.add(R.id.fl_main_content, mHomePageFragment, HomePageFragmentTag);
                } else {
                    transaction.show(mHomePageFragment);
                }
                break;
            case TYPE_LIVE:
                clearnSelectedStatus();
                tv_live_navi.setSelected(true);
                hideAllFragment(transaction);
                if (null == mLiveFragment) {
                    mLiveFragment = new LiveFragment();
                    transaction.add(R.id.fl_main_content, mLiveFragment, LiveFragmentTag);
                } else {
                    transaction.show(mLiveFragment);
                }
                break;
            case TYPE_FRIEND:
                clearnSelectedStatus();
                tv_friend_navi.setSelected(true);
                hideAllFragment(transaction);
                if (null == mFriendFragment) {
                    mFriendFragment = new FriendFragment();
                    transaction.add(R.id.fl_main_content, mFriendFragment, FriendFragmentTag);
                } else {
                    transaction.show(mFriendFragment);
                }
                break;
            case TYPE_MINE:
                clearnSelectedStatus();
                tv_mine_navi.setSelected(true);
                hideAllFragment(transaction);
                if (null == mMineFragment) {
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.fl_main_content, mMineFragment, MineFragmentTag);
                } else {
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

//        暂未启用
//        checkVersion();

    }

    private void checkVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_STORAGE);
            } else {
                checkUpdateVersion();
            }
        } else {
            checkUpdateVersion();
        }

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

    //    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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


    public void hideAllFragment(FragmentTransaction transaction) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        mHomePageFragment = (HomePageFragment) supportFragmentManager.findFragmentByTag(HomePageFragmentTag);
        mLiveFragment = (LiveFragment) supportFragmentManager.findFragmentByTag(LiveFragmentTag);
        mFriendFragment = (FriendFragment) supportFragmentManager.findFragmentByTag(FriendFragmentTag);
        mMineFragment = (MineFragment) supportFragmentManager.findFragmentByTag(MineFragmentTag);
        if (null != mHomePageFragment) {
            transaction.hide(mHomePageFragment);
        }
        if (null != mLiveFragment) {
            transaction.hide(mLiveFragment);
        }
        if (null != mFriendFragment) {
            transaction.hide(mFriendFragment);
        }
        if (null != mMineFragment) {
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


    //    *******************************************begin app 更新***********************************************


    /**
     * 版本号信息
     */
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    /**
     * 获得包信息
     *
     * @param context
     * @return
     */
    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }


    /**
     * 自定义接口协议
     */
    public void checkUpdateVersion() {

        new UpdateAppManager
                .Builder()
                //必须设置，当前Activity
                .setActivity(this)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                //必须设置，更新地址
                .setUpdateUrl(MyApis.Url_Check_Version)

                //以下设置，都是可选
                //设置请求方式，默认get
//                .setPost(true)
                //不显示通知栏进度条
//                .dismissNotificationProgress()
                //是否忽略版本
//                .showIgnoreVersion()
                //添加自定义参数，默认version=1.0.0（app的versionName）；apkKey=唯一表示（在AndroidManifest.xml配置）
//                .setParams(params)
                //设置点击升级后，消失对话框，默认点击升级后，对话框显示下载进度
//                .hideDialogOnDownloading(false)
                //设置头部，不设置显示默认的图片，设置图片后自动识别主色调，然后为按钮，进度条设置颜色
//                .setTopPic(R.mipmap.top_8)
                //为按钮，进度条设置颜色。
                .setThemeColor(0xffffac5d)
                //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
//                .setTargetPath(path)
                //设置appKey，默认从AndroidManifest.xml获取，如果，使用自定义参数，则此项无效
//                .setAppKey("ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f")
                .build()
                //检测是否有新版本
                .checkNewApp(new UpdateCallback() {
                    /**
                     * 解析json,自定义协议
                     *
                     * @param json 服务器返回的json
                     * @return UpdateAppBean
                     */
                    @Override
                    protected UpdateAppBean parseJson(String json) {


                        //获得当前版本
//                        final int versionCode = getVersionCode(MainActivity.this);

                        final String versionName = getVersionName(MainActivity.this);

                        UpdateAppBean updateAppBean = new UpdateAppBean();

                        VersionUpdateBean versionUpdateBean = new Gson().fromJson(json, VersionUpdateBean.class);

                        VersionUpdateBean.DataBean.VersionBean version = versionUpdateBean.getData().getVersion();

                        String version_no = version.getVersion_no();
                        String down_url = version.getDown_url();
                        String content = version.getContent();

                        if (!versionName.equals(version_no)) {

                            updateAppBean
                                    //（必须）是否更新Yes,No
                                    .setUpdate("Yes")
                                    .setNewVersion(version_no)
                                    .setApkFileUrl(down_url)
                                    .setUpdateLog(content)
                            ;

                            String update_version = version.getUpdate_version().trim();
                            //选择性升级
                            if ("否".equals(update_version)) {
//                                    normalUpdate(MainActivity.this,ad_dw_path,ad_dw_log);
                                updateAppBean.setConstraint(false);
                                //强制升级
                            } else if ("是".equals(update_version)) {
//                                    forceUpdate(MainActivity.this,ad_dw_path,ad_dw_log);
                                updateAppBean.setConstraint(true);
                            }
                        }
                        return updateAppBean;
                    }

                    @Override
                    protected void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                        updateAppManager.showDialogFragment();
                    }

                    /**
                     * 网络请求之前
                     */
                    @Override
                    public void onBefore() {
//                        CProgressDialogUtils.showProgressDialog(MainActivity.this);
                    }

                    /**
                     * 网路请求之后
                     */
                    @Override
                    public void onAfter() {
//                        CProgressDialogUtils.cancelProgressDialog(MainActivity.this);
                    }

                    /**
                     * 没有新版本
                     */
                    @Override
                    public void noNewApp() {
//                        Toast.makeText(MainActivity.this, "没有新版本", Toast.LENGTH_SHORT).show();
                    }
                });

    }

//     end version update

}
