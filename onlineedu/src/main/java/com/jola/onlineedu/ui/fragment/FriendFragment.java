package com.jola.onlineedu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.jola.onlineedu.R;
import com.jola.onlineedu.app.MyLog;
import com.jola.onlineedu.base.SimpleFragment;

import butterknife.BindView;

/**
 * Created by jola on 2018/9/6.
 */

public class FriendFragment extends SimpleFragment {




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLog.logMy("FriendFragment : onCreate");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friend;
    }

    @Override
    protected void initEventAndData() {

    }
}
