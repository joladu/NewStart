package com.jola.onlineedu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.app.MyLog;
import com.jola.onlineedu.base.SimpleFragment;
import com.jola.onlineedu.ui.adapter.RVLiveCourseAdapter;
import com.jola.onlineedu.widget.DividerGridItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jola on 2018/9/6.
 */

public class LiveFragment extends SimpleFragment {


    @BindView(R.id.srl_live_course)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.view_main)
    RecyclerView rv_view_main;

    private List<String> mList;
    int mStartIndex = 1;
    private RVLiveCourseAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLog.logMy("LiveFragment : onCreate");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initEventAndData() {

        mList = new ArrayList<>();
        mList.add("text");
        mList.add("text");
        mList.add("text");
        mList.add("text");
        mList.add("text");
        mList.add("text");
        mAdapter = new RVLiveCourseAdapter(getContext(), mList);
        rv_view_main.setLayoutManager(new GridLayoutManager(getContext(),2));

        rv_view_main.addItemDecoration(new DividerGridItemDecoration(getContext()));
//        rv_view_main.addItemDecoration(new GridDivider(getContext(),10,getContext().getResources().getColor(R.color.divide_line_gray)));

//        rv_view_main.addItemDecoration(new RecycleViewDivider(
//                mContext, LinearLayoutManager.HORIZONTAL, 10, getResources().getColor(R.color.divide_line_gray)));


        rv_view_main.setAdapter(mAdapter);

//
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
}
