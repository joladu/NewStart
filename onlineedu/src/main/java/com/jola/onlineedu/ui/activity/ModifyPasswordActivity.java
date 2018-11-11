package com.jola.onlineedu.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyPasswordActivity extends SimpleActivity {


    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_modify_password;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, getString(R.string.modify_person_password));
    }




    @OnClick({
            R.id.tv_modify_password
    })
    public void doClick(View view){
        switch (view.getId()){
            case R.id.tv_modify_password:
                break;
        }
    }


}
