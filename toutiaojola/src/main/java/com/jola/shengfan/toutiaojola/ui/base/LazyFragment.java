package com.jola.shengfan.toutiaojola.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by lenovo on 2018/10/12.
 */

public abstract class LazyFragment extends Fragment {

    public static final String TAG = "LazyFragment.class";

    private boolean isFirstEnter = true;
    private boolean isReuseView = true;
    private boolean isFragmentVisible = false;
    private View rootView;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (rootView == null){
            return;
        }
        if (isFirstEnter && isVisibleToUser){
            onFragmentFirstVisible();
            isFirstEnter = false;
        }
        if (isVisibleToUser){
            isFragmentVisible = true;
            onFragmentVisibleChange(true);
            return;
        }

        if (isFragmentVisible){
            isFragmentVisible = false;
            onFragmentVisibleChange(false);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (null == rootView){
            rootView = view;
            if (getUserVisibleHint()){
                if (isFirstEnter){
                    onFragmentFirstVisible();
                    isFirstEnter = false;
                }
                isFragmentVisible = true;
                onFragmentVisibleChange(true);
            }
        }
        super.onViewCreated(isReuseView ? rootView : view, savedInstanceState);

    }

    private void onFragmentVisibleChange(boolean isVisible) {

    }

    protected void onFragmentFirstVisible() {

    }


}
