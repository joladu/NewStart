package com.jola.onlineedu.mode;

import com.jola.onlineedu.mode.bean.WelcomeBean;
import com.jola.onlineedu.mode.db.DBHelper;
import com.jola.onlineedu.mode.http.HttpHelper;
import com.jola.onlineedu.mode.prefs.PreferencesHelper;

import io.reactivex.Flowable;

/**
 * Created by lenovo on 2018/8/14
 * http db preference
 */

public class DataManager implements HttpHelper ,DBHelper,PreferencesHelper{

    HttpHelper mHttpHelper;
    DBHelper mDBHelper;
    PreferencesHelper mPreferenceHelper;


    public DataManager(HttpHelper mHttpHelper, DBHelper mDBHelper, PreferencesHelper mPreferenceHelper) {
        this.mHttpHelper = mHttpHelper;
        this.mDBHelper = mDBHelper;
        this.mPreferenceHelper = mPreferenceHelper;
    }


    @Override
    public Flowable<WelcomeBean> fetchWelcomInfo(String res) {
        return mHttpHelper.fetchWelcomInfo(res);
    }

    @Override
    public void insertNewsId(int id) {
        mDBHelper.insertNewsId(id);
    }
}
