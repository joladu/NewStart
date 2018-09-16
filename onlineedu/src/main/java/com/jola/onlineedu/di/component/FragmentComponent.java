package com.jola.onlineedu.di.component;

import android.app.Activity;

import com.jola.onlineedu.app.App;
import com.jola.onlineedu.di.module.FragmentModule;
import com.jola.onlineedu.di.scope.FragmentScope;
import com.jola.onlineedu.ui.fragment.LiveFragment;
import com.jola.onlineedu.ui.fragment.MineFragment;

import dagger.Component;

/**
 * Created by lenovo on 2018/8/15.
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(MineFragment mineFragment);
    void inject(LiveFragment liveFragment);
}
