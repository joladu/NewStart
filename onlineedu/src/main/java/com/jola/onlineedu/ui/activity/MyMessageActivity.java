package com.jola.onlineedu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jola.onlineedu.R;
import com.jola.onlineedu.app.App;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResMessageListBean;
import com.jola.onlineedu.mode.bean.response.ResTeacherAttestation;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.TimeFormatUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;

public class MyMessageActivity extends SimpleActivity {

    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.smr)
    SmartRefreshLayout smr;
    @BindView(R.id.rv)
    RecyclerView rv;

    List<ResMessageListBean.DataBean.MessagesBean> mList = new ArrayList<>();
    private RecycleListAdapter mAdapter;
    int page = 1;
    int pagesize = 10;


    @Override
    protected int getLayout() {
        return R.layout.activity_message_list;
    }

    @Override
    protected void initEventAndData() {

        getActivityComponent().inject(this);

        setToolBar(toolbar, "私信");

        smr.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
            }



            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData();
            }
        });

//        testData();
        refreshData();

    }

    @OnClick({
            R.id.send_icon_in_tool
    })
    public void clickEvent(View view){
        switch (view.getId()){
            case R.id.send_icon_in_tool:
                startActivity(new Intent(this,MessageSendActivity.class));
                break;
        }
    }

    private void testData() {
        ResMessageListBean.DataBean.MessagesBean dataBean = new ResMessageListBean.DataBean.MessagesBean();
        dataBean.setAvatar_url("https://www.baidu.com/img/bd_logo1.png?where=super");
        dataBean.setContent("测试：私信内容");
        dataBean.setCreated("2018-11-16T11:50:02");
        dataBean.setName("测试：姓名");
        dataBean.setUsername("测试:用户名");
        mList.add(dataBean);
        mList.add(dataBean);
        mList.add(dataBean);
        mList.add(dataBean);
        mList.add(dataBean);
        mList.add(dataBean);
        mList.add(dataBean);
        mAdapter = new RecycleListAdapter(this);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rv.setAdapter(mAdapter);
    }



    private void refreshData() {

        addSubscribe(
                dataManager.getMessageList(dataManager.getUserToken(),1,pagesize)
            .compose(RxUtil.<ResMessageListBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResMessageListBean>() {
                    @Override
                    public void accept(ResMessageListBean resCommentListBean) throws Exception {
                        smr.finishRefresh();
                        if (resCommentListBean.getError_code() == 0){
                            mList = resCommentListBean.getData().getMessages();
                            mAdapter = new RecycleListAdapter(MyMessageActivity.this);
                            rv.setLayoutManager(new LinearLayoutManager(MyMessageActivity.this,LinearLayoutManager.VERTICAL,false));
                            rv.addItemDecoration(new DividerItemDecoration(MyMessageActivity.this,DividerItemDecoration.VERTICAL));
                            rv.setAdapter(mAdapter);
                            if (mList.size() == 0){
                                ToastUtil.toastLong("暂无数据！");
                            }
                        }else{
                            ToastUtil.toastLong(resCommentListBean.getError_msg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        smr.finishRefresh();
                        tipServerError();
                    }
                })
        );




    }


//    private void asyncHttpDataRefresh(){
//        RequestParams requestParams = new RequestParams();
//        requestParams.put("teacher_certification_id", teacherCardNo);
//        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION, dataManager.getUserToken());
//        App.getmAsyncHttpClient().post("http://yunketang.dev.attackt.com/api/v1/uc/teacherverify/", requestParams, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                ResTeacherAttestation resultBean = new Gson().fromJson(new String(responseBody), ResTeacherAttestation.class);
//                hideLoadingDialog();
//                int error_code = resultBean.getError_code();
//                if (error_code == 0) {
//                    ToastUtil.toastShort("资料上传成功，请等待审核！");
//                } else {
//                    ToastUtil.toastLong(resultBean.getError_msg());
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                hideLoadingDialog();
//                tipServerError();
//            }
//        });
//    }


    private void loadMoreData() {
        addSubscribe(dataManager.getMessageList(dataManager.getUserToken(),++page,pagesize)
                .compose(RxUtil.<ResMessageListBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResMessageListBean>() {
                    @Override
                    public void accept(ResMessageListBean resCommentListBean) throws Exception {
                        smr.finishLoadMore();
                        if (resCommentListBean.getError_code() == 0){
                            mList.addAll(resCommentListBean.getData().getMessages());
                            mAdapter.notifyDataSetChanged();
                        }else{
                            ToastUtil.toastLong(resCommentListBean.getError_msg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        smr.finishLoadMore();
                        tipServerError();
                    }
                })
        );
    }


//    begain adapter

    class RecycleListAdapter extends RecyclerView.Adapter<RecycleListAdapter.ViewHolder>{

        LayoutInflater layoutInflater;

        public RecycleListAdapter(Context context) {
            layoutInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(layoutInflater.inflate(R.layout.item_message,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final ResMessageListBean.DataBean.MessagesBean messagesBean = mList.get(position);
            holder.tv_content_message.setText(messagesBean.getContent());
            holder.tv_content_from.setText(messagesBean.getUsername());
            holder.tv_time.setText(TimeFormatUtil.formatTime(messagesBean.getCreated()));
            holder.rl_message_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyMessageActivity.this, MessageDetailActivity.class);
                    intent.putExtra("id",messagesBean.getId());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            @BindView(R.id.rl_message_list)
            RelativeLayout rl_message_list;
            @BindView(R.id.tv_content_message)
            TextView tv_content_message;
            @BindView(R.id.tv_content_from)
            TextView tv_content_from;
            @BindView(R.id.tv_time)
            TextView tv_time;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }

    }

//    end adapter





}
