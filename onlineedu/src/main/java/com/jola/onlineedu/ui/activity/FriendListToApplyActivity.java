package com.jola.onlineedu.ui.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jola.onlineedu.R;
import com.jola.onlineedu.app.App;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResCommentListBean;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.util.TimeFormatUtil;
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
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * 我申请的
 */
public class FriendListToApplyActivity extends SimpleActivity {

    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.smr)
    SmartRefreshLayout smr;
    @BindView(R.id.rv)
    RecyclerView rv;

    List<ResCommentListBean.DataBean.CommentsBean> mList = new ArrayList<>();
    private RecycleListAdapter mAdapter;
    int page = 1;
    int pagesize = 10;


    @Override
    protected int getLayout() {
        return R.layout.activity_comments_list;
    }

    @Override
    protected void initEventAndData() {

        getActivityComponent().inject(this);

        setToolBar(toolbar, "我申请添加的");

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

        testData();
//        refreshData();

    }

    private void testData() {
        ResCommentListBean.DataBean.CommentsBean commentsBean = new ResCommentListBean.DataBean.CommentsBean();
        commentsBean.setContent("测试评论内容");
        mList.add(commentsBean);
        commentsBean.setCreated("2018-11-16T11:50:02");
        mList.add(commentsBean);
        mList.add(commentsBean);
        mList.add(commentsBean);
        mList.add(commentsBean);
        mList.add(commentsBean);
        mList.add(commentsBean);
        mAdapter = new RecycleListAdapter(this);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rv.setAdapter(mAdapter);
    }



    private void refreshData() {
//        addSubscribe(dataManager.getCommentsList(dataManager.getUserToken(),1,pagesize)
//            .compose(RxUtil.<ResCommentListBean>rxSchedulerHelper())
//                .subscribe(new Consumer<ResCommentListBean>() {
//                    @Override
//                    public void accept(ResCommentListBean resCommentListBean) throws Exception {
//                        smr.finishRefresh();
//                        if (resCommentListBean.getError_code() == 0){
//                            mList = resCommentListBean.getData().getComments();
//                            mAdapter = new RecycleListAdapter(CommentsListActivity.this);
//                            rv.setLayoutManager(new LinearLayoutManager(CommentsListActivity.this,LinearLayoutManager.VERTICAL,false));
//                            rv.addItemDecoration(new DividerItemDecoration(CommentsListActivity.this,DividerItemDecoration.VERTICAL));
//                            rv.setAdapter(mAdapter);
//                            if (mList.size() == 0){
//                                ToastUtil.toastLong("暂无数据！");
//                            }
//                        }else{
//                            ToastUtil.toastLong(resCommentListBean.getError_msg());
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        smr.finishRefresh();
//                        tipServerError();
//                    }
//                })
//        );
        asyncRefresh();

    }


    private void asyncRefresh() {
        showLoadingDialog();
        RequestParams requestParams = new RequestParams();
        requestParams.put("page", 1);
        requestParams.put("pageSize", 10);
        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION, dataManager.getUserToken());
        App.getmAsyncHttpClient().get("http://yunketang.dev.attackt.com/api/v1/uc/record/coursecomment/", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                hideLoadingDialog();
                smr.finishRefresh();
                ResCommentListBean resultBean = new Gson().fromJson(new String(responseBody), ResCommentListBean.class);
                if (resultBean.getError_code() == 0){
                    mList = resultBean.getData().getComments();
                    mAdapter = new RecycleListAdapter(FriendListToApplyActivity.this);
                    rv.setLayoutManager(new LinearLayoutManager(FriendListToApplyActivity.this,LinearLayoutManager.VERTICAL,false));
                    rv.addItemDecoration(new DividerItemDecoration(FriendListToApplyActivity.this,DividerItemDecoration.VERTICAL));
                    rv.setAdapter(mAdapter);
                    if (mList.size() == 0){
                        ToastUtil.toastLong("暂无数据！");
                    }
                }else{
                    ToastUtil.toastLong(resultBean.getError_msg());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                smr.finishRefresh();
                hideLoadingDialog();
                tipServerError();
            }
        });
    }


    private void loadMoreData() {
//        addSubscribe(dataManager.getCommentsList(dataManager.getUserToken(),++page,pagesize)
//                .compose(RxUtil.<ResCommentListBean>rxSchedulerHelper())
//                .subscribe(new Consumer<ResCommentListBean>() {
//                    @Override
//                    public void accept(ResCommentListBean resCommentListBean) throws Exception {
//                        smr.finishLoadMore();
//                        if (resCommentListBean.getError_code() == 0){
//                            mList.addAll(resCommentListBean.getData().getComments());
//                            mAdapter.notifyDataSetChanged();
//                        }else{
//                            ToastUtil.toastLong(resCommentListBean.getError_msg());
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        smr.finishLoadMore();
//                        tipServerError();
//                    }
//                })
//        );
        asyncLoadmore();
    }

    private void asyncLoadmore() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("page", ++page);
        requestParams.put("pageSize", 10);
        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION, dataManager.getUserToken());
        App.getmAsyncHttpClient().get("http://yunketang.dev.attackt.com/api/v1/uc/record/coursecomment/", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                hideLoadingDialog();
                smr.finishLoadMore();
                ResCommentListBean resultBean = new Gson().fromJson(new String(responseBody), ResCommentListBean.class);
                if (resultBean.getError_code() == 0){
                    mList .addAll(resultBean.getData().getComments());
                    mAdapter.notifyDataSetChanged();
                    if (mList.size() == 0){
                        ToastUtil.toastLong("暂无数据！");
                    }
                }else{
                    ToastUtil.toastLong(resultBean.getError_msg());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                smr.finishLoadMore();
                hideLoadingDialog();
                tipServerError();
            }
        });
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
            return new ViewHolder(layoutInflater.inflate(R.layout.item_friend_to_apply,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//            ResCommentListBean.DataBean.CommentsBean commentsBean = mList.get(position);
//            holder.tv_comment_content.setText(commentsBean.getContent());
////            holder.tv_time.setText(commentsBean.getCreated());
//            holder.tv_time.setText(TimeFormatUtil.formatTime(commentsBean.getCreated()));
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

//            @BindView(R.id.tv_comment_content)
//            TextView tv_comment_content;
//            @BindView(R.id.tv_time)
//            TextView tv_time;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }

    }

//    end adapter





}
