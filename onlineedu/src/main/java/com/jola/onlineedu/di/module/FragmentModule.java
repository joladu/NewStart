package com.jola.onlineedu.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.jola.onlineedu.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2018/8/15.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }

}
