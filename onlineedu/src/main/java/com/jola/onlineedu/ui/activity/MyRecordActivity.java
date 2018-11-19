package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MyRecordActivity extends SimpleActivity {


    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_record;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "记录");
    }

    @OnClick({
            R.id.rl_comments,
            R.id.rl_collection,
    })
    public void doClick(View view){
        switch (view.getId()){
            case R.id.rl_comments:
                startActivity(new Intent(this,CommentsListActivity.class));
                break;
            case R.id.rl_collection:
                startActivity(new Intent(this,MyCollectionActivity.class));
                break;
        }
    }


}
