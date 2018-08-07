package com.jola.newnews.mode.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.jola.newnews.app.App;
import com.jola.newnews.app.Constants;

import javax.inject.Inject;

/**
 * Created by jola on 2018/7/28.
 */

public class PreferenceHelperImpl implements IPreferenceHelper {


    private static final boolean DEFAULT_NIGHT_MODE = false;

    private static final int DEFAULT_CURRENT_ITEM = Constants.TYPE_ZHIHU;

    private final SharedPreferences mSPrefs;
    private final String SHAREPREFERENCE_NAME = "sp_newnews";

    @Inject
    public PreferenceHelperImpl() {
        mSPrefs = App.getInstance().getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE);
    }
    @Override
    public boolean getNightModeState() {
        return mSPrefs.getBoolean(Constants.SP_NIGHT_MODE, DEFAULT_NIGHT_MODE);
    }

    @Override
    public void setNightModeState(boolean state) {
        mSPrefs.edit().putBoolean(Constants.SP_NIGHT_MODE, state).apply();
    }

    @Override
    public int getCurrentItem() {
        return mSPrefs.getInt(Constants.SP_CURRENT_ITEM, DEFAULT_CURRENT_ITEM);
    }

    @Override
    public void setCurrentItem(int item) {
        mSPrefs.edit().putInt(Constants.SP_CURRENT_ITEM, item).apply();
    }
}
