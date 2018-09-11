package com.jola.onlineedu.mode.prefs;

/**
 * Created by lenovo on 2018/8/14.
 */

public interface PreferencesHelper {

    void setUserPassword(String password);
    String getUserPassword();

    void setCurMainFragmentTag(int fragmentTag);
    int getCurMainFragmentTag();

    void setUserId(String userId);
    String getUserId();

    void setUserName(String userName);
    String getUserName();

    void setUserPhone(String phone);
    String getUserPhone();

    void setUserToken(String userToken);
    String getUserToken();

    boolean getNightModeState();

    void setNightModeState(boolean state);
}
