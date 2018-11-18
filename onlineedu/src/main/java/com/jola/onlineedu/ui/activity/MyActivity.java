package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MyActivity extends SimpleActivity {


    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_my;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "我的");
    }

    @OnClick({
            R.id.rl_download,
            R.id.rl_interest,
            R.id.rl_message,
            R.id.rl_study,
    })
    public void doClick(View view){
        switch (view.getId()){
            case R.id.rl_download:
                startActivity(new Intent(this,MyDownloadsActivity.class));
                break;
            case R.id.rl_interest:
                startActivity(new Intent(this,MyInterestActivity.class));
                break;
            case R.id.rl_message:
                startActivity(new Intent(this,MyMessageActivity.class));
                break;
            case R.id.rl_study:
                startActivity(new Intent(this,MyStudyActivity.class));
                break;
        }
    }


}
