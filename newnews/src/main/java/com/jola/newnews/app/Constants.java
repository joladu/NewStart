package com.jola.newnews.app;

import java.io.File;

/**
 * Created by lenovo on 2018/7/18.
 */

public class Constants {
    //================= PATH ====================

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

}
