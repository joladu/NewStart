package com.jola.onlineedu.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    @BindView(R.id.tv_pet_name)
    TextView tv_pet_name;
    @BindView(R.id.tv_phone_no)
    TextView tv_phone_no;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_school)
    TextView tv_school;
    @BindView(R.id.tv_teach_course)
    TextView tv_teach_course;




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
            R.id.rl_head,
            R.id.rl_pet_name,
            R.id.rl_phone_no,
            R.id.rl_address,
            R.id.rl_school,
            R.id.rl_teach_course

    })
    public void doClick(View view){
        switch (view.getId()){
            case R.id.rl_head:
                break;
            case R.id.rl_pet_name:
                break;
            case R.id.rl_phone_no:
                break;
            case R.id.rl_address:
                break;
            case R.id.rl_school:
                break;
            case R.id.rl_teach_course:
                break;
        }
    }


}
