package com.jola.onlineedu.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.jola.onlineedu.R;
import com.jola.onlineedu.app.App;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResGroupChatBean;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.ui.adapter.GroupChatAdapter;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import io.reactivex.functions.Consumer;

/**
 * Created by jola on 2018/11/21.
 */

public class GroupChatActivity extends SimpleActivity {

    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;
    @BindView(R.id.smr)
    SmartRefreshLayout smr;
    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.et_input_message)
    EditText et_input_message;

    boolean isSend = false;


    private List<ResGroupChatBean.DataBean.ChatsBean> mList = new ArrayList<>();
    private GroupChatAdapter mAdapter;

    int page = 1;
    int pageSize = 10;

    @Override
    protected int getLayout() {
        return R.layout.activity_group_chat;
    }

    @Override
    protected void initEventAndData() {
        getActivityComponent().inject(this);
        setToolBar(toolbar,"群聊天");
        setAdapter();
        loadGroupChats();
        smr.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreGroupChats();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadGroupChats();
            }
        });
    }

    private void setAdapter() {
        mAdapter = new GroupChatAdapter(this, mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(mAdapter);
    }

    private void loadMoreGroupChats() {
        page++;
        final RequestParams requestParams = new RequestParams();
        requestParams.put("page",page);
        requestParams.put("pageSize",pageSize);
        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION,dataManager.getUserToken());
        App.getmAsyncHttpClient().get("http://yunketang.dev.attackt.com/api/v1/uc/chat/", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ResGroupChatBean resGroupChatBean = new Gson().fromJson(new String(responseBody), ResGroupChatBean.class);
                if (resGroupChatBean.getError_code() == 0){
                    List<ResGroupChatBean.DataBean.ChatsBean> chats = resGroupChatBean.getData().getChats();
                    if (chats.size() == 0){
                        ToastUtil.toastShort("暂无更多聊天信息");
                    }else{
                        mList.addAll(chats);
                        mAdapter.notifyDataSetChanged();
                    }
                }else{
                    ToastUtil.toastLong(resGroupChatBean.getError_msg());
                }
                smr.finishLoadMore();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                tipServerError();
                smr.finishLoadMore();
            }
        });
    }



    private void loadGroupChats() {
        mList.clear();
        showLoadingDialog();
        page = 1;
        final RequestParams requestParams = new RequestParams();
        requestParams.put("page",page);
        requestParams.put("pageSize",pageSize);
        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION,dataManager.getUserToken());
        App.getmAsyncHttpClient().get("http://yunketang.dev.attackt.com/api/v1/uc/chat/", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                smr.finishRefresh();
                hideLoadingDialog();
                ResGroupChatBean resGroupChatBean = new Gson().fromJson(new String(responseBody), ResGroupChatBean.class);
                if (resGroupChatBean.getError_code() == 0){
                    mList.addAll(resGroupChatBean.getData().getChats());
                    mAdapter.notifyDataSetChanged();
                    if (mList.size() == 0){
                        ToastUtil.toastShort("暂无聊天信息");
                    }else{
//                        if (isSend){
//                            rv.scrollToPosition(mList.size() - 1);
//                        }
                    }
                }else{
                    ToastUtil.toastLong(resGroupChatBean.getError_msg());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                hideLoadingDialog();
                tipServerError();
                smr.finishRefresh();
            }
        });
    }

    @OnClick(R.id.tv_send_message)
    public void sendMsg(){

        String msg = et_input_message.getText().toString();
        if (TextUtils.isEmpty(msg)){
            ToastUtil.toastShort("请输入聊天信息，再发送！");
            return;
        }
        showLoadingDialog();
        dataManager.sendGroupMsg(dataManager.getUserToken(),msg)
                .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseSimpleResult>() {
                    @Override
                    public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
                        hideLoadingDialog();
                        if (responseSimpleResult.getError_code() == 0){
                            et_input_message.setText("");
                            ToastUtil.toastShort("发送成功！");
                            loadGroupChats();

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
