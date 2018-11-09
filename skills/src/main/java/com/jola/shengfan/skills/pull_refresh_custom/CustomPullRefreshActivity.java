package com.jola.shengfan.skills.pull_refresh_custom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jola.shengfan.skills.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomPullRefreshActivity extends AppCompatActivity {

    @BindView(R.id.srl_main)
    SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_pull_refresh);
        ButterKnife.bind(this);
        initSmartRefreshLayout();
    }

    private void initSmartRefreshLayout() {
        smartRefreshLayout.setRefreshHeader(new CustomRefreshHeader(this));
    }


}
