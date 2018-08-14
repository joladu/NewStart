package com.jola.onlineedu.di.module;

import com.jola.onlineedu.app.App;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.db.DBHelper;
import com.jola.onlineedu.mode.db.RealmHelper;
import com.jola.onlineedu.mode.http.HttpHelper;
import com.jola.onlineedu.mode.http.RetrofitHelper;
import com.jola.onlineedu.mode.prefs.PreferencesHelper;
import com.jola.onlineedu.mode.prefs.PreferencesHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jola on 2018/8/14.
 */
@Module
public class AppModule {

    private  App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(PreferencesHelperImpl preferencesHelperImpl) {
        return preferencesHelperImpl;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper DBHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, DBHelper, preferencesHelper);
    }
}
