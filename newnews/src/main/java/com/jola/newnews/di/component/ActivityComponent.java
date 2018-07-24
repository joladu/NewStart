package com.jola.newnews.di.component;

import android.app.Activity;

import com.jola.newnews.di.module.ActivityModule;
import com.jola.newnews.di.scope.ActivityScope;
import com.jola.newnews.presenter.main.MainPresenter;
import com.jola.newnews.ui.main.activity.MainActivity;
import com.jola.newnews.ui.main.activity.WelcomeActivity;

import dagger.Component;

/**
 * Created by lenovo on 2018/7/18.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(WelcomeActivity welcomeActivity);

    void inject(MainActivity mainActivity);

}
