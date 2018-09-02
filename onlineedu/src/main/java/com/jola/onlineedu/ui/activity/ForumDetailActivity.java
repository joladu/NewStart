package com.jola.onlineedu.ui.activity;


import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.ui.adapter.ForumImagesGridViewAdapter;
import com.jola.onlineedu.ui.adapter.ForumListDetailAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;

public class ForumDetailActivity extends SimpleActivity {


    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.gv_forum_imgs)
    GridView gridView;

    @BindView(R.id.srl_forum_comment_list)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.view_main)
    RecyclerView recyclerView;

    private ArrayList<String> mListComments;
    int mStartIndex = 1;



    private ForumImagesGridViewAdapter forumImagesGridViewAdapter;
    private ForumListDetailAdapter forumListDetailAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_forum_detail;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar,"帖子详情");

//        模拟帖子有图片数据
        ArrayList<String> imageList = new ArrayList<>();
        imageList.add("url001");
        imageList.add("url001");
        imageList.add("url001");
        forumImagesGridViewAdapter = new ForumImagesGridViewAdapter(this, imageList);
        gridView.setAdapter(forumImagesGridViewAdapter);
        gridView.setVisibility(View.VISIBLE);

//        模拟评论数据
        mListComments = new ArrayList<>();
        for (int i= mStartIndex;i < mStartIndex + 10;i++){
            mListComments.add("第"+i+"条测试：薛定谔的猫，到底藏在哪里，课程讲的非常好支持支持支持，支持");
        }
        forumListDetailAdapter = new ForumListDetailAdapter(this, mListComments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(forumListDetailAdapter);

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                for (int i= mStartIndex;i < mStartIndex + 10;i++){
                    mListComments.add("第"+i+"条测试：薛定谔的猫，到底藏在哪里，课程讲的非常好支持支持支持，支持");
                }
                mStartIndex += 10;
                smartRefreshLayout.finishLoadMore(2000);
                forumListDetailAdapter.notifyDataSetChanged();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mListComments.clear();
                mStartIndex = 1;
                for (int i= mStartIndex;i < mStartIndex + 10;i++){
                    mListComments.add("每天观看直播节目，你们一般会评论什么，内容呢？：第"+i+"条");
                }
                mStartIndex += 10;

                smartRefreshLayout.finishRefresh(2000);
                forumListDetailAdapter.notifyDataSetChanged();

            }
        });


    }

}
