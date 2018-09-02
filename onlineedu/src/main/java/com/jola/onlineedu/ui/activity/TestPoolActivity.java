package com.jola.onlineedu.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.ui.adapter.TestPoolListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 题库
 */
public class TestPoolActivity extends SimpleActivity {


    @BindView(R.id.toolbar_view)
    Toolbar toolbar;
    @BindView(R.id.srl_test_pool_list)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    @BindView(R.id.et_hint_search_view)
    EditText et_hint_search_view;

    List<String> mList;
    private int mStartIndex = 1;
    private TestPoolListAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_test_pool;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar,"题库");
        et_hint_search_view.setHint("题库资源搜索");


        mList = new ArrayList<>();
        for (int i= mStartIndex;i < mStartIndex + 10;i++){
            mList.add("高考题目"+i);
        }
        mAdapter = new TestPoolListAdapter(this, mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                for (int i= mStartIndex;i < mStartIndex + 10;i++){
                    mList.add("河南省数学高考题：第"+i+"条");
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
                    mList.add("河南省数学高考题：第"+i+"条");
                }
                mStartIndex += 10;

                smartRefreshLayout.finishRefresh(2000);
                mAdapter.notifyDataSetChanged();

            }
        });


    }

}
