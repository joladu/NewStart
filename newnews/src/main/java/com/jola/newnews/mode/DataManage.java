package com.jola.newnews.mode;

import com.jola.newnews.mode.bean.DailyListBean;
import com.jola.newnews.mode.bean.VersionBean;
import com.jola.newnews.mode.bean.WelcomeBean;
import com.jola.newnews.mode.db.IDBHelper;
import com.jola.newnews.mode.http.IHttpHelper;
import com.jola.newnews.mode.http.response.MyHttpResponse;
import com.jola.newnews.mode.prefs.IPreferenceHelper;
import com.jola.newnews.util.LogUtil;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by lenovo on 2018/7/18.
 *获取 网络请求、数据库、sharepreference 等数据的入口
 */

public class DataManage implements IHttpHelper ,IDBHelper,IPreferenceHelper{

    public static final String TAG = "DataManage.class";

    IHttpHelper mIHttpHelper;
    IDBHelper mIDBHelper;
    IPreferenceHelper mIPreferenceHelper;


//    public DataManage(IHttpHelper mIHttpHelper) {
//        LogUtil.logInteresting(TAG+"：创建DataManage");
//        this.mIHttpHelper = mIHttpHelper;
//    }

    public DataManage(IHttpHelper mIHttpHelper,IDBHelper idbHelper) {
        this.mIHttpHelper = mIHttpHelper;
        this.mIDBHelper = idbHelper;
    }

    public DataManage(IHttpHelper mIHttpHelper,IDBHelper idbHelper,IPreferenceHelper iPreferenceHelper) {
        this.mIHttpHelper = mIHttpHelper;
        this.mIDBHelper = idbHelper;
        this.mIPreferenceHelper = iPreferenceHelper;
    }

    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        LogUtil.logInteresting("调用"+TAG+" fetchWelcomeInfo 由IHttpHelper实现");
        return mIHttpHelper.fetchWelcomeInfo(res);
    }

    @Override
    public Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo() {
        return mIHttpHelper.fetchVersionInfo();
    }

    @Override
    public Flowable<DailyListBean> fetchDailyListBean() {
        return mIHttpHelper.fetchDailyListBean();
    }


    @Override
    public void insertNewsId(int id) {
        mIDBHelper.insertNewsId(id);
    }

    @Override
    public boolean queryNewsId(int id) {
        return  mIDBHelper.queryNewsId(id);
    }

    @Override
    public boolean getNightModeState() {
        return mIPreferenceHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean state) {
        mIPreferenceHelper.setNightModeState(state);
    }

    @Override
    public int getCurrentItem() {
        return mIPreferenceHelper.getCurrentItem();
    }

    @Override
    public void setCurrentItem(int item) {
        mIPreferenceHelper.setCurrentItem(item);
    }
}
