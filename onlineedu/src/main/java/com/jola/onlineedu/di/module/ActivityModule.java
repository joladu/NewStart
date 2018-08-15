package com.jola.onlineedu.di.module;

import android.app.Activity;

import com.jola.onlineedu.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2018/8/15.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
