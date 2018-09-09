package com.jola.onlineedu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jola.onlineedu.R;
import com.jola.onlineedu.app.MyLog;
import com.jola.onlineedu.base.SimpleFragment;

/**
 * Created by jola on 2018/9/6.
 */

public class MineFragment extends SimpleFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLog.logMy("MineFragment : onCreate");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initEventAndData() {

    }
}
