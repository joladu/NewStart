package com.jola.onlineedu.ui.activity;


import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.util.CodeUtils;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ForgetPasswordActivity extends SimpleActivity {

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.iv_image_code)
    ImageView iv_ImageCode;

    @BindView(R.id.tv_getCheckCode)
    TextView tv_getCheckCode;

    @Override
    protected int getLayout() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "手机找回密码");
        iv_ImageCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
    }

    @OnClick(R.id.iv_image_code)
    public void refreshImageCode(View view){
        iv_ImageCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
    }

    @OnClick(R.id.tv_getCheckCode)
    public void getCheckCode(View view) {
        ToastUtil.toastShort("获取验证码");
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
    }




}
