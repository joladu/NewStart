package com.jola.onlineedu.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

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
                if (isOldVisible){
                    et_input_password_original.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iv_visible_new_password.setImageResource(R.drawable.eye_open_2x);
                    isOldVisible = false;
                }else{
                    et_input_password_original.setInputType(InputType.TYPE_CLASS_TEXT);
                    iv_visible_new_password.setImageResource(R.drawable.eye_chose_2x);
                    isOldVisible = true;
                }
                break;
            case R.id.iv_visible_new_password:
                if (isNewVisible){
                    et_input_password_new.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iv_visible_new_password.setImageResource(R.drawable.eye_open_2x);
                    isNewVisible = false;
                }else{
                    et_input_password_new.setInputType(InputType.TYPE_CLASS_TEXT);
                    iv_visible_new_password.setImageResource(R.drawable.eye_chose_2x);
                    isNewVisible = true;
                }
                break;
            case R.id.iv_visible_password_again:
                if (isNewAgainVisible){
                    et_input_password_again.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iv_visible_password_again.setImageResource(R.drawable.eye_open_2x);
                    isNewAgainVisible = false;
                }else{
                    et_input_password_again.setInputType(InputType.TYPE_CLASS_TEXT);
                    iv_visible_password_again.setImageResource(R.drawable.eye_chose_2x);
                    isNewAgainVisible = true;
                }
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
        addSubscribe(
                mDataManager.changePassword(mDataManager.getUserToken(),originalPassword,newPassword,passwordAgain)
                        .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseSimpleResult>() {
                    @Override
                    public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
                        hideLoadingDialog();
                        int error_code = responseSimpleResult.getError_code();
                        if (error_code == 0){
                            ToastUtil.toastShort("修改密码成功！");
                            ModifyPasswordActivity.this.finish();
                        }else{
                            ToastUtil.toastLong(responseSimpleResult.getError_msg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        tipServerError();
                        hideLoadingDialog();
                    }
                })
        );


    }


}