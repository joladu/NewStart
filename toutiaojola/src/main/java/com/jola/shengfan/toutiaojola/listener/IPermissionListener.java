package com.jola.shengfan.toutiaojola.listener;

import java.util.List;

/**
 * Created by lenovo on 2018/10/11.
 */

public interface IPermissionListener {
    void onGranted();
    void onDenied(List<String> deniedPermissions);
}
