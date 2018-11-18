package com.jola.onlineedu.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResGetImageCode;
import com.jola.onlineedu.mode.bean.response.ResUserRegister;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import retrofit2.http.Body;

public class PersonInfoImproveActivity extends SimpleActivity {

    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;
    @BindView(R.id.et_user_name)
    EditText et_user_name;
    @BindView(R.id.et_input_phoneno)
    EditText et_input_phoneno;


    @Override
    protected int getLayout() {
        return R.layout.activity_personal_info_improve;
    }

    @Override
    protected void initEventAndData() {

        getActivityComponent().inject(this);

        setToolBar(toolbar, "完善信息");
    }




    @OnClick(R.id.tv_next_step)
    public void doNextStep(View view){

    }




}
