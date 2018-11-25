package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jola.onlineedu.R;
import com.jola.onlineedu.app.App;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResTeacherAttestation;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ModifyPasswordActivity extends SimpleActivity {


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


    boolean isOldVisible = true;
    boolean isNewVisible = false;
    boolean isNewAgainVisible = false;


    @Override
    protected int getLayout() {
        return R.layout.activity_modify_password;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, getString(R.string.modify_person_password));
        getActivityComponent().inject(this);
    }




    @OnClick({
            R.id.iv_visible_oldpassword,
            R.id.iv_visible_new_password,
            R.id.iv_visible_password_again,
            R.id.tv_modify_password
    })
    public void doClick(View view){
        switch (view.getId()){
            case R.id.iv_visible_oldpassword:
                String originalContent = et_input_password_original.getText().toString();
                et_input_password_original.setText("");
                if (isOldVisible){
                    et_input_password_original.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iv_visible_new_password.setImageResource(R.drawable.eye_open_2x);
                    isOldVisible = false;
                }else{
                    et_input_password_original.setInputType(InputType.TYPE_CLASS_TEXT);
                    iv_visible_new_password.setImageResource(R.drawable.eye_chose_2x);
                    isOldVisible = true;
                }
                et_input_password_original.setText(originalContent);
                break;
            case R.id.iv_visible_new_password:
                String originalContentNew = et_input_password_new.getText().toString();
                et_input_password_new.setText("");
                if (isNewVisible){
                    et_input_password_new.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iv_visible_new_password.setImageResource(R.drawable.eye_open_2x);
                    isNewVisible = false;
                }else{
                    et_input_password_new.setInputType(InputType.TYPE_CLASS_TEXT);
                    iv_visible_new_password.setImageResource(R.drawable.eye_chose_2x);
                    isNewVisible = true;
                }
                et_input_password_new.setText(originalContentNew);
                break;
            case R.id.iv_visible_password_again:
                String originalContentAgain = et_input_password_again.getText().toString();
                et_input_password_new.setText("");
                if (isNewAgainVisible){
                    et_input_password_again.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iv_visible_password_again.setImageResource(R.drawable.eye_open_2x);
                    isNewAgainVisible = false;
                }else{
                    et_input_password_again.setInputType(InputType.TYPE_CLASS_TEXT);
                    iv_visible_password_again.setImageResource(R.drawable.eye_chose_2x);
                    isNewAgainVisible = true;
                }
                et_input_password_new.setText(originalContentAgain);
                break;
            case R.id.tv_modify_password:
                confirmModify();
                break;
        }
    }

    private void confirmModify() {

        String originalPassword = et_input_password_original.getText().toString();
        String newPassword = et_input_password_new.getText().toString();
        String passwordAgain = et_input_password_again.getText().toString();
        if (TextUtils.isEmpty(originalPassword) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(passwordAgain)){
            ToastUtil.toastShort(getString(R.string.tip_not_fill_all_content));
            return;
        }
        if (!newPassword.equals(passwordAgain)){
            ToastUtil.toastShort(getString(R.string.tip_two_time_not_same));
            return;
        }
        showLoadingDialog();


        RequestParams requestParams = new RequestParams();
        requestParams.put("oldpwd", originalPassword);
        requestParams.put("newpwd", newPassword);
        requestParams.put("newpwd2", passwordAgain);

        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION, mDataManager.getUserToken());
        App.getmAsyncHttpClient().put
                ("http://yunketang.dev.attackt.com/api/v1/uc/chpwd/", requestParams, new
                        AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                ResponseSimpleResult resultBean = new Gson().fromJson(new String
                                        (responseBody), ResponseSimpleResult.class);
                                hideLoadingDialog();
                                int error_code = resultBean.getError_code();
                                if (error_code == 0) {
                                    ToastUtil.toastLong("密码修改成功,请用新密码重新登录！");
                                    startActivity(new Intent(ModifyPasswordActivity.this,LoginActivity.class));
                                    ModifyPasswordActivity.this.finish();
                                } else {
                                    ToastUtil.toastLong(resultBean.getError_msg());
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                hideLoadingDialog();
                                tipServerError();
                            }
                        });


//        addSubscribe(
//                mDataManager.changePassword(mDataManager.getUserToken(),originalPassword,newPassword,passwordAgain)
//                        .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
//                .subscribe(new Consumer<ResponseSimpleResult>() {
//                    @Override
//                    public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
//                        hideLoadingDialog();
//                        int error_code = responseSimpleResult.getError_code();
//                        if (error_code == 0){
//                            ToastUtil.toastShort("修改密码成功！");
//                            ModifyPasswordActivity.this.finish();
//                        }else{
//                            ToastUtil.toastLong(responseSimpleResult.getError_msg());
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        hideLoadingDialog();
//                        tipServerError();
//                        hideLoadingDialog();
//                    }
//                })
//        );


//        addSubscribe(
//                mDataManager.changePassword1(mDataManager.getUserToken(),
//                        RequestBody.create(MediaType.parse("multipart/form-data"),originalPassword),
//                        RequestBody.create(MediaType.parse("multipart/form-data"),newPassword),
//                        RequestBody.create(MediaType.parse("multipart/form-data"),passwordAgain))
//                        .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
//                        .subscribe(new Consumer<ResponseSimpleResult>() {
//                            @Override
//                            public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
//                                hideLoadingDialog();
//                                int error_code = responseSimpleResult.getError_code();
//                                if (error_code == 0){
//                                    ToastUtil.toastShort("修改密码成功！");
//                                    ModifyPasswordActivity.this.finish();
//                                }else{
//                                    ToastUtil.toastLong(responseSimpleResult.getError_msg());
//                                }
//                            }
//                        }, new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//                                hideLoadingDialog();
//                                tipServerError();
//                                hideLoadingDialog();
//                            }
//                        })
//        );

//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), originalPassword);
//        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), newPassword);
//        RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), passwordAgain);
//        HashMap<String, RequestBody> map = new HashMap<>();
//        map.put("oldpwd",requestBody);
//        map.put("newpwd",requestBody1);
//        map.put("newpwd2",requestBody2);
//        addSubscribe(
//                mDataManager.changePassword(mDataManager.getUserToken(),map)
//                        .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
//                        .subscribe(new Consumer<ResponseSimpleResult>() {
//                            @Override
//                            public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
//                                hideLoadingDialog();
//                                int error_code = responseSimpleResult.getError_code();
//                                if (error_code == 0){
//                                    ToastUtil.toastShort("修改密码成功！");
//                                    ModifyPasswordActivity.this.finish();
//                                }else{
//                                    ToastUtil.toastLong(responseSimpleResult.getError_msg());
//                                }
//                            }
//                        }, new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//                                hideLoadingDialog();
//                                tipServerError();
//                                hideLoadingDialog();
//                            }
//                        })
//        );






    }


}
