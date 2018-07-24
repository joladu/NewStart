package com.jola.newnews.di.module;

import android.app.Activity;

import com.jola.newnews.di.scope.ActivityScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2018/7/18.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @ActivityScope
    @Provides
    Activity provideActivity(){
        return mActivity;
    }


}
