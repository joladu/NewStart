package com.jola.newnews.di.module;

import com.jola.newnews.app.App;
import com.jola.newnews.mode.DataManage;
import com.jola.newnews.mode.http.IHttpHelper;
import com.jola.newnews.mode.http.RetrofitHelper;

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
    DataManage provideDataManage(IHttpHelper iHttpHelper){
        return new DataManage(iHttpHelper);
    }

//    *******************end  对内提供



    @Provides
    @Singleton
    IHttpHelper provideIHttpHelper(RetrofitHelper retrofitHelper){
        return retrofitHelper;
    }








}
