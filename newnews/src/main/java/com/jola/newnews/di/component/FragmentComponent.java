package com.jola.newnews.di.component;

import android.app.Activity;

import com.jola.newnews.di.module.FragmentModule;
import com.jola.newnews.di.scope.FragmentScope;
import com.jola.newnews.ui.zhihu.fragment.DailyFragment;

import dagger.Component;

/**
 * Created by lenovo on 2018/7/24.
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
    void inject(DailyFragment dailyFragment);
}
