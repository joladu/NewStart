package com.jola.newnews.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.jola.newnews.di.scope.FragmentScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2018/7/24.
 */

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity(){
        return mFragment.getActivity();
    }

}
