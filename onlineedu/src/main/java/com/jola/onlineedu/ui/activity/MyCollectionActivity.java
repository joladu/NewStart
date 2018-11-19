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
import com.jola.onlineedu.mode.bean.response.ResInteresListBean;
import com.jola.onlineedu.mode.bean.response.ResMyCollectionListBean;
import com.jola.onlineedu.mode.http.MyApis;
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

public class MyCollectionActivity extends SimpleActivity {

    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.smr)
    SmartRefreshLayout smr;
    @BindView(R.id.rv)
    RecyclerView rv;

    List<ResMyCollectionListBean.DataBean.CoursesBean> mList = new ArrayList<>();
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

        setToolBar(toolbar, "收藏");

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
        ResMyCollectionListBean.DataBean.CoursesBean coursesBean = new ResMyCollectionListBean.DataBean.CoursesBean();

//         * category : null
//                * name : 数据结构
//                * cover_url : http://127.0.0.1:8002/media/cover_1537430138.jpeg
//             * author : 杨老师
        coursesBean.setCover_url("https://www.baidu.com/img/bd_logo1.png?where=super");
        coursesBean.setAuthor("测试老师");
        coursesBean.setName("测试名称");
        coursesBean.setCategory("测试科目");
        mList.add(coursesBean);
        mList.add(coursesBean);
        mList.add(coursesBean);
        mList.add(coursesBean);
        mList.add(coursesBean);
        mList.add(coursesBean);
        mList.add(coursesBean);
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
        requestParams.put("page", 1);
        requestParams.put("pageSize", 10);
        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION, dataManager.getUserToken());
        App.getmAsyncHttpClient().get("http://yunketang.dev.attackt.com/api/v1/uc/record/favoritecourse/", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                hideLoadingDialog();
                smr.finishRefresh();
                ResMyCollectionListBean resultBean = new Gson().fromJson(new String(responseBody), ResMyCollectionListBean.class);
                if (resultBean.getError_code() == 0) {
                    mList = resultBean.getData().getCourses();
                    mAdapter = new RecycleListAdapter(MyCollectionActivity.this);
                    rv.setLayoutManager(new LinearLayoutManager(MyCollectionActivity.this, LinearLayoutManager.VERTICAL, false));
                    rv.addItemDecoration(new DividerItemDecoration(MyCollectionActivity.this, DividerItemDecoration.VERTICAL));
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
        App.getmAsyncHttpClient().get("http://yunketang.dev.attackt.com/api/v1/uc/record/favoritecourse/", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                hideLoadingDialog();
                smr.finishLoadMore();
                ResMyCollectionListBean resultBean = new Gson().fromJson(new String(responseBody), ResMyCollectionListBean.class);
                if (resultBean.getError_code() == 0) {
                    mList .addAll( resultBean.getData().getCourses());
                    mAdapter.notifyDataSetChanged();
                    if (mList.size() == 0) {
                        ToastUtil.toastLong("暂无数据！");
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
            return new ViewHolder(layoutInflater.inflate(R.layout.item_collection,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ResMyCollectionListBean.DataBean.CoursesBean coursesBean = mList.get(position);
//              * category : null
//                * name : 数据结构
//                * cover_url : http://127.0.0.1:8002/media/cover_1537430138.jpeg
//             * author : 杨老师

            ImageLoader.load(MyCollectionActivity.this,coursesBean.getCover_url(),holder.iv_course_cover);
            holder.tv_course_name.setText(coursesBean.getName());
//            tools:text="讲师 李老师  课程类别 语文"
            holder.tv_teacher_category.setText("讲师 "+coursesBean.getAuthor() + "  课程类别"+coursesBean.getCategory());

        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            @BindView(R.id.iv_course_cover)
            ImageView iv_course_cover;

            @BindView(R.id.tv_course_name)
            TextView tv_course_name;

            @BindView(R.id.tv_teacher_category)
            TextView tv_teacher_category;

            @BindView(R.id.tv_cancel_collection)
            TextView tv_cancel_collection;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }

    }

//    end adapter





}
