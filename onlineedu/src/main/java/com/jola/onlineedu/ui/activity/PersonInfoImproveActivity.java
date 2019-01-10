package com.jola.onlineedu.ui.activity;

import android.content.Intent;
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
import com.jola.onlineedu.wxapi.WXEntryActivity;

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

    @BindView(R.id.iv_choose_teacher)
    ImageView iv_choose_teacher;

    @BindView(R.id.iv_choose_student)
    ImageView iv_choose_student;

    int role = 1;

    @Override
    protected int getLayout() {
        return R.layout.activity_personal_info_improve;
    }

    @Override
    protected void initEventAndData() {

        getActivityComponent().inject(this);

        setToolBar(toolbar, "完善信息");
    }


    @OnClick({
            R.id.iv_choose_teacher,
            R.id.tv_type_teacher,

            R.id.iv_choose_student,
            R.id.tv_type_student,

            R.id.tv_next_step,
    })
    public void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_next_step:
                doNextStep();
                break;
            case R.id.iv_choose_teacher:
            case R.id.tv_type_teacher:
                role = 2;
                setSelect();
                break;
            case R.id.iv_choose_student:
            case R.id.tv_type_student:
                role = 1;
                setSelect();
                break;

        }
    }

    private void setSelect() {
        iv_choose_student.setImageResource(R.drawable.oval_gray_ring_64);
        iv_choose_teacher.setImageResource(R.drawable.oval_gray_ring_64);
        if (role == 1){
            iv_choose_student.setImageResource(R.drawable.oval_green_64);
        }else{
            iv_choose_teacher.setImageResource(R.drawable.oval_green_64);
        }
    }


    public void doNextStep() {
        String name = et_user_name.getText().toString();
        String phoneNum = et_input_phoneno.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phoneNum)){
            ToastUtil.toastShort("请输入用户名 或 手机号！");
            return;
        }
        showLoadingDialog();
        dataManager.thirdpartLoginImprove(dataManager.getUserToken(),name,phoneNum,role)
                .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseSimpleResult>() {
                    @Override
                    public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
                        hideLoadingDialog();
                        if (responseSimpleResult.getError_code() == 0){
                            startActivity(new Intent(PersonInfoImproveActivity.this, MainActivity.class));
                            PersonInfoImproveActivity.this.finish();
                        }else{
                            ToastUtil.toastShort(responseSimpleResult.getError_msg());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        tipServerError();
                    }
                });

    }


}
