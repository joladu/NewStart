package com.jola.onlineedu.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.TimeFormatUtil;
import com.jola.onlineedu.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MessageDetailActivity extends SimpleActivity {


    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.tv_content_message)
    TextView tv_content_message;
    @BindView(R.id.tv_content_from)
    TextView tv_content_from;
    @BindView(R.id.et_input_replay)
    EditText et_input_replay;

    private int id;
    private String time;
    private String name;
    private String content;

    @Override
    protected int getLayout() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void initEventAndData() {

        getActivityComponent().inject(this);

        setToolBar(toolbar, "私信");

        id = getIntent().getIntExtra("id",-1);
        time = getIntent().getStringExtra("time");
        name = getIntent().getStringExtra("name");
        content = getIntent().getStringExtra("content");

        tv_content_message.setText(content);
//        发送时间：2018.08.09     来自小刚
//        StringBuilder sb = new StringBuilder();
//        sb.append("发送时间：");
//        sb.append(TimeFormatUtil.formatTime(time));
//        sb.append("     来自");
//        sb.append(name);
        String content_from = "发送时间："+TimeFormatUtil.formatTime(time)+"     来自"+name;
        tv_content_from.setText(content_from);
    }




    @OnClick({
            R.id.tv_replay_message
    })
    public void doClick(View view){
        switch (view.getId()){
            case R.id.tv_replay_message:
                replayMessage();
                break;
        }
    }

    private void replayMessage() {
        String replayContent = et_input_replay.getText().toString();
        if (TextUtils.isEmpty(replayContent)){
            ToastUtil.toastShort("请输入恢复内容后，再试！");
            return;
        }
        showLoadingDialog();
        dataManager.responseMessage(dataManager.getUserToken(),id+"",replayContent)
                .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseSimpleResult>() {
                    @Override
                    public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
                        hideLoadingDialog();
                        if (responseSimpleResult.getError_code() == 0){
                            ToastUtil.toastShort("私信回复成功");
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
