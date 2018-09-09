package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.jola.onlineedu.R;
import com.jola.onlineedu.base.RootSimpleActivity;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResForumTypeBean;
import com.jola.onlineedu.ui.adapter.ForumListAdapter;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class ForumListActivity extends RootSimpleActivity {


    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.refresh_layout_forum_list)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.view_main)
    RecyclerView rv_list;

    @BindView(R.id.labels_list_forum)
    LabelsView labelsView;

    private ArrayList<String> mList;
    private ForumListAdapter mAdapter;
    int mStartIndex = 1;

    @Override
    protected int getLayout() {
        return R.layout.activity_forum_list;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        getActivityComponent().inject(this);
        setToolBar(toolbar,"论坛");

//        获取帖子类型

        ArrayList<String> label = new ArrayList<>();
        label.add("全部");
        label.add("提问");
        label.add("灌水");
        label.add("反馈");
        label.add("提问");
        label.add("灌水");
        labelsView.setLabels(label); //直接设置一个字符串数组就可以了。
        //标签的选中监听
        labelsView.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(TextView label, Object data, boolean isSelect, int position) {
                //label是被选中的标签，data是标签所对应的数据，isSelect是是否选中，position是标签的位置。
                if (isSelect){
                    ToastUtil.toastShort((String) data);
                }
            }
        });

        mList = new ArrayList<>();
        for (int i= mStartIndex;i < mStartIndex + 10;i++){
            mList.add("每天观看直播节目，你们一般会评论什么，内容呢？：第"+i+"条");
        }
        mStartIndex += 10;
        mAdapter = new ForumListAdapter(this, mList);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rv_list.setAdapter(mAdapter);

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                for (int i= mStartIndex;i < mStartIndex + 10;i++){
                    mList.add("每天观看直播节目，你们一般会评论什么，内容呢？：第"+i+"条");
                }
                mStartIndex += 10;
                smartRefreshLayout.finishLoadMore(2000);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mList.clear();
                mStartIndex = 1;
                for (int i= mStartIndex;i < mStartIndex + 10;i++){
                    mList.add("每天观看直播节目，你们一般会评论什么，内容呢？：第"+i+"条");
                }
                mStartIndex += 10;

                smartRefreshLayout.finishRefresh(2000);
                mAdapter.notifyDataSetChanged();

            }
        });
    }


    private void getForumTypeInfo(){
        addSubscribe(dataManager.getForumTypeInfo()
        .compose(RxUtil.<ResForumTypeBean>rxSchedulerHelper())
        .subscribe(new Consumer<ResForumTypeBean>() {
            @Override
            public void accept(ResForumTypeBean resForumTypeBean) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }));
    }


    @OnClick(R.id.add_icon_in_tool)
    public void addPublishForum(View view){
        startActivity(new Intent(this,ForumPublishActivity.class));
    }

}
