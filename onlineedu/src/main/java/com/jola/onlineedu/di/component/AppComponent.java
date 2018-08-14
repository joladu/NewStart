package com.jola.onlineedu.di.component;

import com.jola.onlineedu.app.App;
import com.jola.onlineedu.di.module.AppModule;
import com.jola.onlineedu.di.module.HttpModule;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.db.RealmHelper;
import com.jola.onlineedu.mode.http.HttpHelper;
import com.jola.onlineedu.mode.http.RetrofitHelper;
import com.jola.onlineedu.mode.prefs.PreferencesHelperImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jola on 2018/8/14.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

    PreferencesHelperImpl preferencesHelper(); //提供sp帮助类

}
