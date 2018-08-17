package com.jola.onlineedu.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by lenovo on 2018/7/18.
 */

public class Constants {

    //================= PATH ====================

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "onlineedu" + File.separator + "imgs";





}
