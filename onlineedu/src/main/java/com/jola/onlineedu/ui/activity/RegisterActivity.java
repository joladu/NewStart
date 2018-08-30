package com.jola.onlineedu.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResUserRegister;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.util.CodeUtils;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.StatusBarUtil;
import com.jola.onlineedu.util.ToastUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RegisterActivity extends SimpleActivity {

    @Inject
    DataManager dataManager;
    @BindView(R.id.toolbar_view)
    Toolbar toolbar;
    @BindView(R.id.iv_image_code)
    ImageView iv_ImageCode;
    @BindView(R.id.tv_getCheckCode)
    TextView tv_getCheckCode;
    @BindView(R.id.et_phone_num)
    EditText et_phone_num;
    @BindView(R.id.et_image_code)
    EditText et_image_code;
    @BindView(R.id.et_input_check_code)
    EditText et_input_check_code;
    @BindView(R.id.et_input_password)
    EditText et_input_password;
    @BindView(R.id.et_input_password_again)
    EditText et_input_password_again;
    @BindView(R.id.et_input_username)
    EditText et_input_username;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initEventAndData() {

        getActivityComponent().inject(this);

        setToolBar(toolbar, "注册");
        iv_ImageCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
    }

    @OnClick(R.id.iv_image_code)
    public void refreshImageCode(View view){
        iv_ImageCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
    }

    @OnClick(R.id.tv_back)
    public void backToLogin(View view){
        this.finish();
    }

    @OnClick(R.id.tv_register)
    public void doRegister(View view){
        String phoneNum = et_phone_num.getText().toString();
        String imageCode = et_image_code.getText().toString();
        String msgCheckCode = et_input_check_code.getText().toString();
        String password = et_input_password.getText().toString();
        String passwordConfirm = et_input_password_again.getText().toString();
        String userName = et_input_username.getText().toString();
        if (TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(imageCode) || TextUtils.isEmpty(msgCheckCode)
                || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordConfirm) || TextUtils.isEmpty(userName)){
            ToastUtil.toastShort("请输入所有注册信息后，再注册！");
            return;
        }
        String realImageCode = CodeUtils.getInstance().getCode();
        if (!realImageCode.equals(imageCode)){
            ToastUtil.toastShort("图形验证码错误！");
            return;
        }
        if (!password.equals(passwordConfirm)){
            ToastUtil.toastShort("两次密码输入不一致！");
            return;
        }
        addSubscribe(dataManager.fetchUserRegisterInfo(userName,phoneNum,msgCheckCode,imageCode,imageCode,password,passwordConfirm)
            .compose(RxUtil.<ResUserRegister>rxSchedulerHelper())
                .subscribe(new Consumer<ResUserRegister>() {
                    @Override
                    public void accept(ResUserRegister resUserRegister) throws Exception {
                        int error_code = resUserRegister.getError_code();
                        if (error_code == 0){
                            ResUserRegister.DataBean data = resUserRegister.getData();
                            String token = data.getToken();
                            int user_id = data.getUser().getUser_id();
                            ToastUtil.toastShort("注册成功："+"token:"+token+" ; user_id"+user_id);
                        }else{
                            ToastUtil.toastShort("注册失败："+resUserRegister.getError_msg());
                        }
                    }
                })
        );
    }

    @OnClick(R.id.tv_getCheckCode)
    public void getCheckCode(View view) {
        String phoneNum = et_phone_num.getText().toString();
        if (TextUtils.isEmpty(phoneNum) || phoneNum.length() != 11){
            ToastUtil.toastShort("请输入11位数字手机号码！");
            return;
        }

        tv_getCheckCode.setBackgroundColor(getResources().getColor(R.color.divide_line_gray));
        tv_getCheckCode.setEnabled(false);
        addSubscribe(dataManager.fetchMsgCheckCode(phoneNum)
            .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseSimpleResult>() {
                    @Override
                    public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
                        int error_code = responseSimpleResult.getError_code();
                        if (error_code == 0){
                            ToastUtil.toastShort("获取验证码成功！");
                            tv_getCheckCode.setBackgroundColor(getResources().getColor(R.color.divide_line_gray));
                            tv_getCheckCode.setEnabled(false);
                            addSubscribe(Flowable.interval(0,1, TimeUnit.SECONDS)
                                    .take(60)
                                    .map(new Function<Long, Long>() {
                                        @Override
                                        public Long apply(Long aLong) throws Exception {
                                            return 60 - aLong;
                                        }
                                    })
                                    .compose(RxUtil.<Long>rxSchedulerHelper())
                                    .subscribe(new Consumer<Long>() {
                                        @Override
                                        public void accept(Long aLong) throws Exception {
                                            tv_getCheckCode.setText(aLong+"s");
                                            if (aLong <= 1){
                                                tv_getCheckCode.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                                tv_getCheckCode.setEnabled(true);
                                                tv_getCheckCode.setText(getResources().getString(R.string.get_check_code));
                                            }
                                        }
                                    }));
                        }else{
                            ToastUtil.toastShort("获取验证码失败："+responseSimpleResult.getError_msg());
                            tv_getCheckCode.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                            tv_getCheckCode.setEnabled(true);
                            tv_getCheckCode.setText(getResources().getString(R.string.get_check_code));
                        }
                    }
                })
        );
    }

//    @Override
//    protected void onDestroy() {
//        ImmersionBar.with(this).destroy();
//        super.onDestroy();
//    }


}
