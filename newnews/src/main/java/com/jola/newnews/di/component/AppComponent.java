package com.jola.newnews.di.component;

import com.jola.newnews.app.App;
import com.jola.newnews.di.module.AppModule;
import com.jola.newnews.di.module.HttpModule;
import com.jola.newnews.mode.DataManage;
import com.jola.newnews.mode.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lenovo on 2018/7/18.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();

    DataManage getDataManage();

    RetrofitHelper getRetrofitHelper();

}
