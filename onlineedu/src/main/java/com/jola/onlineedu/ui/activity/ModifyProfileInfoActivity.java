package com.jola.onlineedu.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyProfileInfoActivity extends SimpleActivity {


    @Inject
    DataManager mDataManager;


    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.iv_visible_oldpassword)
    ImageView iv_visible_oldpassword;
    @BindView(R.id.iv_visible_new_password)
    ImageView iv_visible_new_password;
    @BindView(R.id.iv_visible_password_again)
    ImageView iv_visible_password_again;

    @BindView(R.id.et_input_password_original)
    EditText et_input_password_original;
    @BindView(R.id.et_input_password_new)
    EditText et_input_password_new;
    @BindView(R.id.et_input_password_again)
    EditText et_input_password_again;


    @Override
    protected int getLayout() {
        return R.layout.activity_modify_user_profile;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, getString(R.string.modify_person_info));
        getActivityComponent().inject(this);

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
