package com.jola.shengfan.skills.pull_refresh_custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.jola.shengfan.skills.R;

import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/11/9.
 */

public class HomeAdsView extends LinearLayout{

    public HomeAdsView(Context context) {
        this(context,null);
    }

    public HomeAdsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HomeAdsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.view_home_ads, this);
        ButterKnife.bind(view);
    }




}
