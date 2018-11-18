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

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResStudyListBean;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.TimeFormatUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MyStudyActivity extends SimpleActivity {

    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.smr)
    SmartRefreshLayout smr;
    @BindView(R.id.rv)
    RecyclerView rv;

    List<ResStudyListBean.DataBean.CourcesBean> mList = new ArrayList<>();
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

        setToolBar(toolbar, "学习");

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
        ResStudyListBean.DataBean.CourcesBean courcesBean = new ResStudyListBean.DataBean.CourcesBean();
        courcesBean.setName("测试：课程名称");
        courcesBean.setCreated("2018-11-16T11:50:02");

        mList.add(courcesBean);
        mList.add(courcesBean);
        mList.add(courcesBean);
        mList.add(courcesBean);
        mList.add(courcesBean);
        mList.add(courcesBean);
        mList.add(courcesBean);
        mAdapter = new RecycleListAdapter(this);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rv.setAdapter(mAdapter);
    }



    private void refreshData() {
        addSubscribe(dataManager.getMyStudyList(dataManager.getUserToken(),1,pagesize)
            .compose(RxUtil.<ResStudyListBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResStudyListBean>() {
                    @Override
                    public void accept(ResStudyListBean resCommentListBean) throws Exception {
                        smr.finishRefresh();
                        if (resCommentListBean.getError_code() == 0){
                            mList = resCommentListBean.getData().getCources();
                            mAdapter = new RecycleListAdapter(MyStudyActivity.this);
                            rv.setLayoutManager(new LinearLayoutManager(MyStudyActivity.this,LinearLayoutManager.VERTICAL,false));
                            rv.addItemDecoration(new DividerItemDecoration(MyStudyActivity.this,DividerItemDecoration.VERTICAL));
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


    private void loadMoreData() {
        addSubscribe(dataManager.getMyStudyList(dataManager.getUserToken(),++page,pagesize)
                .compose(RxUtil.<ResStudyListBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResStudyListBean>() {
                    @Override
                    public void accept(ResStudyListBean resCommentListBean) throws Exception {
                        smr.finishLoadMore();
                        if (resCommentListBean.getError_code() == 0){
                            mList.addAll(resCommentListBean.getData().getCources());
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
            return new ViewHolder(layoutInflater.inflate(R.layout.item_study,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ResStudyListBean.DataBean.CourcesBean courcesBean = mList.get(position);
            holder.tv_study_title.setText(courcesBean.getName());
            holder.tv_time.setText(TimeFormatUtil.formatTime(courcesBean.getCreated()));
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            @BindView(R.id.tv_study_title)
            TextView tv_study_title;
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
