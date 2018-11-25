package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;

public class MessageSendActivity extends SimpleActivity {


    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.civ_head_user)
    CircleImageView civ_head_user;
    @BindView(R.id.tv_friend_name)
    TextView tv_friend_name;
    @BindView(R.id.et_input_message)
    EditText et_input_message;


    private int id;
    private String username;
    private String avatar_url;

    @Override
    protected int getLayout() {
        return R.layout.activity_message_send;
    }

    @Override
    protected void initEventAndData() {

        getActivityComponent().inject(this);

        setToolBar(toolbar, "私信");

//        intent.putExtra("id",id);
//        intent.putExtra("username",username);
//        intent.putExtra("avatar_url",avatar_url);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        username = intent.getStringExtra("username");
        avatar_url = intent.getStringExtra("avatar_url");

        ImageLoader.load(this,avatar_url,civ_head_user);
        tv_friend_name.setText(username);

    }




    @OnClick({
        R.id.tv_send_message
    })
    public void doClick(View view){
        switch (view.getId()){
            case R.id.tv_send_message:
                confirmSendMessage();
                break;
        }
    }

    private void confirmSendMessage() {

        String inputContent = et_input_message.getText().toString();
        if (TextUtils.isEmpty(inputContent)){
            ToastUtil.toastShort("请输入私信内容后，再试！");
            return;
        }
        showLoadingDialog();
        dataManager.sendMessage(dataManager.getUserToken(),id+"",inputContent)
                .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseSimpleResult>() {
                    @Override
                    public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
                        hideLoadingDialog();
                        if (responseSimpleResult.getError_code() == 0){
                            ToastUtil.toastShort("私信发送成功！");
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
