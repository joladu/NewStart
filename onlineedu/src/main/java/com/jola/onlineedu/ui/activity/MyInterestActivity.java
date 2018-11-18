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

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResInteresListBean;
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

    List<ResInteresListBean.DataBean.DownloadsBean> mList = new ArrayList<>();
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

        testData();
//        refreshData();

    }

    private void testData() {
        ResInteresListBean.DataBean.DownloadsBean downloadsBean = new ResInteresListBean.DataBean.DownloadsBean();
        downloadsBean.setCover_url("https://www.baidu.com/img/bd_logo1.png?where=super");
        downloadsBean.setCreated("2018-11-16T11:50:02");
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
        addSubscribe(dataManager.getInterestList(dataManager.getUserToken(),1,pagesize)
            .compose(RxUtil.<ResInteresListBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResInteresListBean>() {
                    @Override
                    public void accept(ResInteresListBean resCommentListBean) throws Exception {
                        smr.finishRefresh();
                        if (resCommentListBean.getError_code() == 0){
                            mList = resCommentListBean.getData().getDownloads();
                            mAdapter = new RecycleListAdapter(MyInterestActivity.this);
                            rv.setLayoutManager(new LinearLayoutManager(MyInterestActivity.this,LinearLayoutManager.VERTICAL,false));
                            rv.addItemDecoration(new DividerItemDecoration(MyInterestActivity.this,DividerItemDecoration.VERTICAL));
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
        addSubscribe(dataManager.getInterestList(dataManager.getUserToken(),++page,pagesize)
                .compose(RxUtil.<ResInteresListBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResInteresListBean>() {
                    @Override
                    public void accept(ResInteresListBean resCommentListBean) throws Exception {
                        smr.finishLoadMore();
                        if (resCommentListBean.getError_code() == 0){
                            mList.addAll(resCommentListBean.getData().getDownloads());
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
            return new ViewHolder(layoutInflater.inflate(R.layout.item_interest,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
