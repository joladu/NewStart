package com.jola.onlineedu.mode.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.jola.onlineedu.app.App;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/8/14.
 */

public class PreferencesHelperImpl implements PreferencesHelper {


    private static final String SHAREDPREFERENCES_NAME = "online_edu_sp";

    public static final String SP_NIGHT_MODE = "night_mode";

    private static final boolean DEFAULT_NIGHT_MODE = false;

    private final SharedPreferences mSPrefs;

    @Inject
    public PreferencesHelperImpl() {
        mSPrefs = App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }



    @Override
    public boolean getNightModeState() {
        return mSPrefs.getBoolean(SP_NIGHT_MODE, DEFAULT_NIGHT_MODE);
    }

    @Override
    public void setNightModeState(boolean state) {
        mSPrefs.edit().putBoolean(SP_NIGHT_MODE, state).apply();
    }
}
