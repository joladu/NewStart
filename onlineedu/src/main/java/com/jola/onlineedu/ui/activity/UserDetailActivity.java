package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jola.onlineedu.R;
import com.jola.onlineedu.app.App;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.util.ToastUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jola on 2018/8/21.
 */

public class UserDetailActivity extends SimpleActivity {


    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.civ_head_user)
    CircleImageView civ_head_user;
    @BindView(R.id.tv_friend_name)
    TextView tv_friend_name;
    @BindView(R.id.tv_friend_describe)
    TextView tv_friend_describe;
    @BindView(R.id.tv_area_address)
    TextView tv_area_address;
    private int userId;


    @Override
    protected int getLayout() {
        return R.layout.activity_user_detail;
    }

    @Override
    protected void initEventAndData() {
        getActivityComponent().inject(this);
        setToolBar(toolbar, "详细资料");

        initIntentData();

    }

    private void initIntentData() {

//        intent.putExtra("userId",usersBean.getId());
//        intent.putExtra("headImgUrl",usersBean.getAvatar_url());
//        intent.putExtra("userName",usersBean.getUsername());
//        intent.putExtra("describe",sb.toString());
//        intent.putExtra("area","unknow");
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId",-1);
        String headImgUrl = intent.getStringExtra("headImgUrl");
        String userName = intent.getStringExtra("userName");
        String describe = intent.getStringExtra("describe");
        String area = intent.getStringExtra("area");

        ImageLoader.load(this,headImgUrl,civ_head_user);
        tv_friend_name.setText(userName);
        tv_friend_describe.setText(describe);
        tv_area_address.setText(area);

    }

    @OnClick({
            R.id.tv_add_friend,
    })
    public void clickEvent(View view){
        switch (view.getId()){
            case R.id.tv_add_friend:
                addFriend();
                break;
        }
    }

    private void addFriend() {
        showLoadingDialog();
        RequestParams requestParams = new RequestParams();
        requestParams.put("to_user_id",userId);
        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION, dataManager.getUserToken());
        App.getmAsyncHttpClient().post("http://yunketang.dev.attackt.com/api/v1/friend/add/", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ResponseSimpleResult resultBean = new Gson().fromJson(new String(responseBody), ResponseSimpleResult.class);
                hideLoadingDialog();
                if (resultBean.getError_code() == 0){
                    ToastUtil.toastShort("申请添加成功，请等待同意！");
                }else{
                    ToastUtil.toastLong(resultBean.getError_msg());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                hideLoadingDialog();
                tipServerError();
            }
        });
    }


}
