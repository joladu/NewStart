package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonInfoActivity extends SimpleActivity {


    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.rl_teacher_attestation)
    RelativeLayout rl_teacher_attestation;
    @BindView(R.id.view_end_line)
    View view_end_line;


    private int role;

    @Override
    protected int getLayout() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, getString(R.string.person_info));
        role = getIntent().getIntExtra("role",1);
        if (role != 1){
            rl_teacher_attestation.setVisibility(View.VISIBLE);
            view_end_line.setVisibility(View.VISIBLE);
        }
    }




    @OnClick({
            R.id.rl_modify_info,
            R.id.rl_modify_password,
            R.id.rl_teacher_attestation,
    })
    public void doClick(View view){
        switch (view.getId()){
            case R.id.rl_modify_info:
                startActivity(new Intent(this,ModifyProfileInfoActivity.class));
                break;
            case R.id.rl_modify_password:
                startActivity(new Intent(this,ModifyPasswordActivity.class));
                break;
            case R.id.rl_teacher_attestation:
                startActivity(new Intent(this,TeacherAttestationActivity.class));
                break;
        }
    }


}
