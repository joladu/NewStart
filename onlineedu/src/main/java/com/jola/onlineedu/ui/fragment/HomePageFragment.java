package com.jola.onlineedu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.jola.onlineedu.R;
import com.jola.onlineedu.app.MyLog;
import com.jola.onlineedu.base.SimpleFragment;
import com.jola.onlineedu.ui.activity.ForumListActivity;
import com.jola.onlineedu.ui.activity.SelectableCourseActivity;
import com.jola.onlineedu.ui.activity.TestPoolActivity;
import com.jola.onlineedu.ui.adapter.RVRecommendCourseAdapter;
import com.jola.onlineedu.ui.adapter.VPHomePagerBannerAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jola on 2018/9/6.
 */

public class HomePageFragment extends SimpleFragment {


    @BindView(R.id.et_hint_search_view)
    EditText et_hint_search_view;

    @BindView(R.id.vp_banner_home_page)
    ViewPager vp_banner_home_page;

    @BindView(R.id.srl_home_page)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.view_main)
    RecyclerView rv_view_main;


    private VPHomePagerBannerAdapter vpHomePagerBannerAdapter;
    private List<String> mList;
    int mStartIndex = 1;
    private RVRecommendCourseAdapter rvRecommendCourseAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLog.logMy("HomePageFragment : onCreate");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initEventAndData() {
        et_hint_search_view.setHint(getString(R.string.tip_hint_input_search));

//        banner
        vpHomePagerBannerAdapter = new VPHomePagerBannerAdapter(getContext());
        vp_banner_home_page.setAdapter(vpHomePagerBannerAdapter);

//
        mList = new ArrayList<>();
        mList.add("text");
        mList.add("text");
        mList.add("text");
        mList.add("text");
        mList.add("text");
        mList.add("text");
        rvRecommendCourseAdapter = new RVRecommendCourseAdapter(getContext(), mList);
        rv_view_main.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_view_main.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        rv_view_main.setAdapter(rvRecommendCourseAdapter);

//
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                for (int i= mStartIndex;i < mStartIndex + 10;i++){
                    mList.add("每天观看直播节目，你们一般会评论什么，内容呢？：第"+i+"条");
                }
                mStartIndex += 10;
                smartRefreshLayout.finishLoadMore(2000);
                rvRecommendCourseAdapter.notifyDataSetChanged();
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
                rvRecommendCourseAdapter.notifyDataSetChanged();

            }
        });
    }

    @OnClick({R.id.iv_forum,R.id.iv_excellent_course,R.id.iv_test_pool,R.id.iv_teacher_master})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_excellent_course:
                startActivity(new Intent(getContext(), SelectableCourseActivity.class));
                break;
            case R.id.iv_forum:
                startActivity(new Intent(getContext(), ForumListActivity.class));
                break;
            case R.id.iv_teacher_master:
                break;
            case R.id.iv_test_pool:
                startActivity(new Intent(getContext(), TestPoolActivity.class));
                break;
        }
    }

}
