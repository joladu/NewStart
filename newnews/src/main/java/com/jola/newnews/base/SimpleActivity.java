package com.jola.newnews.base;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jola.newnews.app.App;
import com.jola.newnews.util.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by lenovo on 2018/7/17
 * 基础 简单 Activity 基类，不带mvp结构
 *
 * ButterKnife 绑定和解绑
 */

public abstract class SimpleActivity extends SupportActivity {

    public static final String TAG = "SimpleActivity";

    protected Activity mContext;
    private Unbinder mUnbinder;

    /**
     * 设置布局
     */
    protected abstract int getLayout();

    /**
     * 视图创建后的工作
     */
    protected void onViewCreated() {

    }

    /**
     * 初始化监听和数据
     */
    protected void initEventAndData(){};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        LogUtil.log("e","jola", TAG+"begin onCreate");
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
//       如果是使用mvp结构，在 onViewCreated()方法中 要完成Presenter 与 View 的关联，如果等到在子类的onCreate()方法中再去关联时，本方法中的initEventAndData() 如果使用Presenter 会导致报NullPointException
        onViewCreated();
        App.getInstance().addActivity(this);
        initEventAndData();
//        LogUtil.log("e","jola", TAG+"end onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        App.getInstance().removeActivity(this);
    }

    protected void setToolbar(Toolbar toolbar,String title){
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
    }


}
