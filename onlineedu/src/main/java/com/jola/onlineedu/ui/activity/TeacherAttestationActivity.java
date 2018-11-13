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

public class TeacherAttestationActivity extends SimpleActivity {


    @Inject
    DataManager mDataManager;


    @BindView(R.id.toolbar_view)
    Toolbar toolbar;




    @Override
    protected int getLayout() {
        return R.layout.activity_teacher_attestation;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, getString(R.string.teacher_attestation_title));
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
