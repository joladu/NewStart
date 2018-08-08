package com.jola.newnews.di.module;

import com.jola.newnews.app.App;
import com.jola.newnews.mode.DataManage;
import com.jola.newnews.mode.db.IDBHelper;
import com.jola.newnews.mode.db.RealmHelper;
import com.jola.newnews.mode.http.IHttpHelper;
import com.jola.newnews.mode.http.RetrofitHelper;
import com.jola.newnews.mode.prefs.IPreferenceHelper;
import com.jola.newnews.mode.prefs.PreferenceHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2018/7/18.
 */

@Module
public class AppModule {

    private App mApp;

    public AppModule(App mApp) {
        this.mApp = mApp;
    }


//    *******************begin 对外提供

    @Provides
    @Singleton
    App provideAppContext(){
        return mApp;
    }

    @Provides
    @Singleton
    DataManage provideDataManage(IHttpHelper iHttpHelper, IDBHelper idbHelper,IPreferenceHelper iPreferenceHelper){
        return new DataManage(iHttpHelper,idbHelper,iPreferenceHelper);
    }

//    *******************end  对内提供



    @Provides
    @Singleton
    IHttpHelper provideIHttpHelper(RetrofitHelper retrofitHelper){
        return retrofitHelper;
    }

    @Provides
    @Singleton
    IDBHelper provideIDBHelper(RealmHelper realmHelper){
        return realmHelper;
    }

    @Provides
    @Singleton
    IPreferenceHelper provideIPreferenceHelper(PreferenceHelperImpl preferenceHelper){
        return preferenceHelper;
    }








}
