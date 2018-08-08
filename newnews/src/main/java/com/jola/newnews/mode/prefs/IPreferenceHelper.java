package com.jola.newnews.mode.prefs;

/**
 * Created by jola on 2018/7/28.
 */

public interface IPreferenceHelper {

    boolean getNightModeState();

    void setNightModeState(boolean state);

    int getCurrentItem();

    void setCurrentItem(int item);

}
