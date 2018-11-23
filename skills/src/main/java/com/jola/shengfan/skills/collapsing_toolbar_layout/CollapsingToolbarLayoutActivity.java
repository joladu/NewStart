package com.jola.shengfan.skills.collapsing_toolbar_layout;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.jola.shengfan.skills.R;

public class CollapsingToolbarLayoutActivity extends AppCompatActivity {

    private CoordinatorLayout mCoordinatorLayout;
    private ImageView mImageViewCollapsingView;
    private Toolbar mToolBar;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaping_toolbar_layout);
        initView();
    }

    private void initView() {
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);

        mImageViewCollapsingView = (ImageView) findViewById(R.id.iv_collapsing_view);

        mToolBar = (Toolbar) findViewById(R.id.tool_bar);

        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.mipmap.ic_drawer_home);
        collapsingToolbarLayout.setTitle("智能圈存机");
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mImageViewCollapsingView.setImageResource(R.mipmap.ic_bg);


    }


}
