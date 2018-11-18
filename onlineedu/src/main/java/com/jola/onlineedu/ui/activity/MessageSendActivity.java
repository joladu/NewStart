package com.jola.onlineedu.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageSendActivity extends SimpleActivity {


    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_message_send;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "私信");
    }




    @OnClick({

    })
    public void doClick(View view){
        switch (view.getId()){

        }
    }


}
