package com.jola.onlineedu.di.component;

import android.app.Activity;

import com.jola.onlineedu.di.module.ActivityModule;
import com.jola.onlineedu.di.scope.ActivityScope;
import com.jola.onlineedu.ui.activity.WelcomeActivity;

import dagger.Component;

/**
 * Created by lenovo on 2018/8/15.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
    void inject(WelcomeActivity welcomeActivity);
}
