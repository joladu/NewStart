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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jola.onlineedu.R;
import com.jola.onlineedu.app.App;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResDownloadsBean;
import com.jola.onlineedu.mode.bean.response.ResInteresListBean;
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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;

public class MyInterestActivity extends SimpleActivity {

    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.smr)
    SmartRefreshLayout smr;
    @BindView(R.id.rv)
    RecyclerView rv;

    List<ResInteresListBean.DataBean.FollowsBean> mList = new ArrayList<>();
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

        setToolBar(toolbar, "关注");

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

    private void testData() {
        ResInteresListBean.DataBean.FollowsBean downloadsBean = new ResInteresListBean.DataBean.FollowsBean();
        downloadsBean.setAvatar_url("https://www.baidu.com/img/bd_logo1.png?where=super");
//        downloadsBean.set("2018-11-16T11:50:02");
        downloadsBean.setName("测试名称");
        mList.add(downloadsBean);
        mList.add(downloadsBean);
        mList.add(downloadsBean);
        mList.add(downloadsBean);
        mList.add(downloadsBean);
        mList.add(downloadsBean);
        mList.add(downloadsBean);
        mAdapter = new RecycleListAdapter(this);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rv.setAdapter(mAdapter);
    }



    private void refreshData() {
//        addSubscribe(dataManager.getInterestList(dataManager.getUserToken(),1,pagesize)
//            .compose(RxUtil.<ResInteresListBean>rxSchedulerHelper())
//                .subscribe(new Consumer<ResInteresListBean>() {
//                    @Override
//                    public void accept(ResInteresListBean resCommentListBean) throws Exception {
//                        smr.finishRefresh();
//                        if (resCommentListBean.getError_code() == 0){
//                            mList = resCommentListBean.getData().getDownloads();
//                            mAdapter = new RecycleListAdapter(MyInterestActivity.this);
//                            rv.setLayoutManager(new LinearLayoutManager(MyInterestActivity.this,LinearLayoutManager.VERTICAL,false));
//                            rv.addItemDecoration(new DividerItemDecoration(MyInterestActivity.this,DividerItemDecoration.VERTICAL));
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
        page = 1;
        requestParams.put("page", 1);
        requestParams.put("pageSize", 10);
        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION, dataManager.getUserToken());
        App.getmAsyncHttpClient().get("http://yunketang.dev.attackt.com/api/v1/uc/follow/", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                hideLoadingDialog();
                smr.finishRefresh();
                ResInteresListBean resultBean = new Gson().fromJson(new String(responseBody), ResInteresListBean.class);
                if (resultBean.getError_code() == 0) {
                    mList = resultBean.getData().getFollows();
                    mAdapter = new RecycleListAdapter(MyInterestActivity.this);
                    rv.setLayoutManager(new LinearLayoutManager(MyInterestActivity.this, LinearLayoutManager.VERTICAL, false));
                    rv.addItemDecoration(new DividerItemDecoration(MyInterestActivity.this, DividerItemDecoration.VERTICAL));
                    rv.setAdapter(mAdapter);
                    if (mList.size() == 0) {
                        ToastUtil.toastLong("暂无数据！");
                    }
                } else {
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
//        addSubscribe(dataManager.getInterestList(dataManager.getUserToken(),++page,pagesize)
//                .compose(RxUtil.<ResInteresListBean>rxSchedulerHelper())
//                .subscribe(new Consumer<ResInteresListBean>() {
//                    @Override
//                    public void accept(ResInteresListBean resCommentListBean) throws Exception {
//                        smr.finishLoadMore();
//                        if (resCommentListBean.getError_code() == 0){
//                            mList.addAll(resCommentListBean.getData().getDownloads());
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
        App.getmAsyncHttpClient().get("http://yunketang.dev.attackt.com/api/v1/uc/follow/", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                hideLoadingDialog();
                smr.finishLoadMore();
                ResInteresListBean resultBean = new Gson().fromJson(new String(responseBody), ResInteresListBean.class);
                if (resultBean.getError_code() == 0) {
                    List<ResInteresListBean.DataBean.FollowsBean> followsList = resultBean.getData().getFollows();
                    if (followsList.size() == 0) {
                        ToastUtil.toastLong("无更多数据！");
                    }else{
                        mList .addAll(followsList);
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
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
            return new ViewHolder(layoutInflater.inflate(R.layout.item_interest,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ResInteresListBean.DataBean.FollowsBean followsBean = mList.get(position);
//            followsBean.getAvatar_url()
            ImageLoader.load(MyInterestActivity.this,followsBean.getAvatar_url(),holder.civ_head_user);
            holder.tv_interest_name.setText(followsBean.getName());
//            文学  民族学  北京交通大学海滨学院
            List<String> courses = followsBean.getCourses();
            StringBuilder stringBuilder = new StringBuilder();
            for (String tempCourse : courses){
                stringBuilder.append(tempCourse + " ");
            }
            stringBuilder.append(followsBean.getSchool_name());
            holder.tv_interest_person_describe.setText(stringBuilder.toString());
//            关注：50    已关注
            holder.tv_interest_num.setText("关注: "+followsBean.getFollow_number()+"     已关注");
            holder.tv_cancel_interest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.toastShort("暂无相关功能！");
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            @BindView(R.id.civ_head_user)
            CircleImageView civ_head_user;
            @BindView(R.id.tv_interest_name)
            TextView tv_interest_name;
            @BindView(R.id.tv_cancel_interest)
            TextView tv_cancel_interest;
            @BindView(R.id.tv_interest_person_describe)
            TextView tv_interest_person_describe;
            @BindView(R.id.tv_interest_num)
            TextView tv_interest_num;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }

//    end adapter





}
