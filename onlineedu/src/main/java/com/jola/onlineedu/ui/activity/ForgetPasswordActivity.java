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
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResGetImageCode;
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

public class ForgetPasswordActivity extends SimpleActivity {


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
    private Disposable disposableCountDown;

    private String captcha_img;
    private String captcha_key;


    @Override
    protected int getLayout() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "手机找回密码");
//        iv_ImageCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
        getActivityComponent().inject(this);

        getKatpchCode();

    }

    private void getKatpchCode() {
        iv_ImageCode.setImageResource(R.drawable.image_placeholder);
        //        获得图形验证码
        addSubscribe(dataManager.getImageCode()
                        .compose(RxUtil.<ResGetImageCode>rxSchedulerHelper())
                        .subscribe(new Consumer<ResGetImageCode>() {
                            @Override
                            public void accept(ResGetImageCode resGetImageCode) throws Exception {
                                int error_code = resGetImageCode.getError_code();
                                if (error_code == 0) {
                                    captcha_img = resGetImageCode.getData().getCaptcha_img();
//                                              "captcha_img":"/captcha/image/20d8699afac91bb9bc4fc26f40f564eacbc91b6e/"
//                            http://yunketang.dev.attackt.com/captcha/image/99d0501dea9230fd9984f41581b7e703a2652dbe/
                                    captcha_key = resGetImageCode.getData().getCaptcha_key();
//                                    Glide.with(ForgetPasswordActivity.this).load(MyApis.DOMAIN + captcha_img).into(iv_ImageCode);
                                    ImageLoader.loadWhitPrefix(ForgetPasswordActivity.this,captcha_img,iv_ImageCode);
                                } else {
                                    ToastUtil.toastLong(resGetImageCode.getError_msg());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                hideLoadingDialog();
                                ToastUtil.toastLong(getString(R.string.error_server_message));
                            }
                        })
        );

    }

    @OnClick(R.id.iv_image_code)
    public void refreshImageCode(View view) {
        getKatpchCode();
    }

    @OnClick(R.id.tv_getCheckCode)
    public void getCheckCode(View view) {
        String phoneNum = et_phone_num.getText().toString();
        if (TextUtils.isEmpty(phoneNum) || phoneNum.length() != 11) {
            ToastUtil.toastShort("请输入11位数字手机号码！");
            return;
        }
        tv_getCheckCode.setBackgroundColor(getResources().getColor(R.color.gray_tip_logout));
        tv_getCheckCode.setEnabled(false);
        disposableCountDown = Flowable.interval(0, 1, TimeUnit.SECONDS)
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
//                        Log.e("jola", "time:" + aLong + "s");
                        tv_getCheckCode.setText(aLong + "s");
                        if (aLong <= 1) {
                            tv_getCheckCode.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                            tv_getCheckCode.setEnabled(true);
                            tv_getCheckCode.setText(getResources().getString(R.string.get_check_code));
                        }
                    }
                });
        addSubscribe(disposableCountDown);
        addSubscribe(dataManager.getMsgCheckCode(phoneNum)
                .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseSimpleResult>() {
                    @Override
                    public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
                        int error_code = responseSimpleResult.getError_code();
                        if (error_code == 0) {
                            ToastUtil.toastShort("获取验证码成功！");
                            tv_getCheckCode.setBackgroundColor(getResources().getColor(R.color.gray_tip_logout));
                            tv_getCheckCode.setEnabled(false);
                        } else {
                            ToastUtil.toastShort("获取验证码失败：" + responseSimpleResult.getError_msg());
                            tv_getCheckCode.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                            tv_getCheckCode.setEnabled(true);
                            tv_getCheckCode.setText(getResources().getString(R.string.get_check_code));
                            removeDisposable(disposableCountDown);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtil.toastLong(getString(R.string.error_server_message));
                        tv_getCheckCode.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        tv_getCheckCode.setEnabled(true);
                        tv_getCheckCode.setText(getResources().getString(R.string.get_check_code));
                        removeDisposable(disposableCountDown);
                    }
                })
        );
    }

    @OnClick(R.id.tv_find_password)
    public void findPasswordBack(View view) {
        String phoneNum = et_phone_num.getText().toString();
        String imageCode = et_image_code.getText().toString();
        String checkCode = et_input_check_code.getText().toString();
        String password = et_input_password.getText().toString();

        if (TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(imageCode) || TextUtils.isEmpty(checkCode) || TextUtils.isEmpty(password)) {
            ToastUtil.toastShort(getString(R.string.tip_not_fill_all_content));
            return;
        }
//        String realCode = CodeUtils.getInstance().getCode();
//        if (!realCode.equals(imageCode)){
//            ToastUtil.toastShort(getString(R.string.tip_error_image_code));
//        }
        if (TextUtils.isEmpty(phoneNum) || phoneNum.length() != 11) {
            ToastUtil.toastShort("请输入11位数字手机号码！");
            return;
        }
        showLoadingDialog();


//    @Field("mobile") String mobilePhone,
//    @Field("v_code") String msgCode,
//    @Field("captcha_key") String imageCodeKey,
//    @Field("captcha") String imageCode,
//    @Field("password") String password

        addSubscribe(dataManager.getUserForgetPasswrod(phoneNum,checkCode, captcha_key, imageCode,  password)
                .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper()).subscribe(new Consumer<ResponseSimpleResult>() {
                    @Override
                    public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
                        hideLoadingDialog();
                        int error_code = responseSimpleResult.getError_code();
                        if (error_code == 0) {
                            ToastUtil.toastShort("重置密码成功！");
                            ForgetPasswordActivity.this.finish();
                        } else {
                            ToastUtil.toastShort(responseSimpleResult.getError_msg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        ToastUtil.toastLong(getString(R.string.error_server_message));
                    }
                }));

    }


}
