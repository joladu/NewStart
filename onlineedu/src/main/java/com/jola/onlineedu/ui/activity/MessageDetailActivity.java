package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageDetailActivity extends SimpleActivity {


    @BindView(R.id.toolbar_view)
    Toolbar toolbar;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "私信");
        id = getIntent().getIntExtra("id",-1);
    }




    @OnClick({

    })
    public void doClick(View view){
        switch (view.getId()){

        }
    }


}
