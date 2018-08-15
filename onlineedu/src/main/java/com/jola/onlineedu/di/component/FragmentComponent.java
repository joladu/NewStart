package com.jola.onlineedu.di.component;

import com.jola.onlineedu.app.App;
import com.jola.onlineedu.di.module.FragmentModule;
import com.jola.onlineedu.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by lenovo on 2018/8/15.
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
}
