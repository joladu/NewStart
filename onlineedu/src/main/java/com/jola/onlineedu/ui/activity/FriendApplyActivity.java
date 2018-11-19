package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class FriendApplyActivity extends SimpleActivity {


    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_friend_apply;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "好友申请");
    }

    @OnClick({
            R.id.rl_apply_add,
            R.id.rl_apply_from,
    })
    public void doClick(View view){
        switch (view.getId()){
            case R.id.rl_apply_add:
                startActivity(new Intent(this,FriendListToApplyActivity.class));
                break;
            case R.id.rl_apply_from:
                startActivity(new Intent(this,FriendListFromApplyActivity.class));
                break;
        }
    }


}
