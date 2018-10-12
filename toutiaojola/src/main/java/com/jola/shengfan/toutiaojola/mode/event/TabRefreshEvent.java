package com.jola.shengfan.toutiaojola.mode.event;

import com.chaychan.library.BottomBarItem;

/**
 * Created by lenovo on 2018/10/12.
 */

public class TabRefreshEvent {

    private String channelCode;
    private BottomBarItem bottomBarItem;
    private boolean isHomeTab;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public BottomBarItem getBottomBarItem() {
        return bottomBarItem;
    }

    public void setBottomBarItem(BottomBarItem bottomBarItem) {
        this.bottomBarItem = bottomBarItem;
    }

    public boolean isHomeTab() {
        return isHomeTab;
    }

    public void setHomeTab(boolean homeTab) {
        isHomeTab = homeTab;
    }
}
