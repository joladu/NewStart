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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.app.MyLog;
import com.jola.onlineedu.base.SimpleFragment;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResCourseList;
import com.jola.onlineedu.ui.activity.ForumListActivity;
import com.jola.onlineedu.ui.activity.SelectableCourseActivity;
import com.jola.onlineedu.ui.activity.TeacherMasterActivity;
import com.jola.onlineedu.ui.activity.TestPoolActivity;
import com.jola.onlineedu.ui.adapter.RVRecommendCourseAdapter;
import com.jola.onlineedu.ui.adapter.VPHomePagerBannerAdapter;
import com.jola.onlineedu.util.RxUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Created by jola on 2018/9/6.
 */

public class HomePageFragment extends SimpleFragment {


    @Inject
    DataManager dataManager;

    @BindView(R.id.et_hint_search_view)
    EditText et_hint_search_view;

    @BindView(R.id.vp_banner_home_page)
    ViewPager vp_banner_home_page;


    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    @BindView(R.id.srl_home_page)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_state_info)
    RelativeLayout relativeLayoutStateInfo;
    @BindView(R.id.iv_tip_state)
    ImageView iv_stateImage;
    @BindView(R.id.tv_state_tip)
    TextView tv_stateText;


    private VPHomePagerBannerAdapter vpHomePagerBannerAdapter;

    private List<ResCourseList.ResultsBean> mList = new ArrayList<>();
    private RVRecommendCourseAdapter rvRecommendCourseAdapter;

    private int page = 1;
    private int page_size = 10;

    private void stateLoading(){
        showLoadingDialog();
        smartRefreshLayout.setVisibility(View.INVISIBLE);
        relativeLayoutStateInfo.setVisibility(View.VISIBLE);
        iv_stateImage.setImageDrawable(getResources().getDrawable(R.drawable.state_loading));
        tv_stateText.setText(getString(R.string.state_loading_tip));
    }

    private void stateEmpty(){
        hideLoadingDialog();
        smartRefreshLayout.setVisibility(View.INVISIBLE);
        relativeLayoutStateInfo.setVisibility(View.VISIBLE);
        iv_stateImage.setImageDrawable(getResources().getDrawable(R.drawable.state_empty));
        tv_stateText.setText(getString(R.string.state_empty_tip));
    }

    private void stateError(){
        hideLoadingDialog();
        smartRefreshLayout.setVisibility(View.INVISIBLE);
        relativeLayoutStateInfo.setVisibility(View.VISIBLE);
        iv_stateImage.setImageDrawable(getResources().getDrawable(R.drawable.state_error_server));
        tv_stateText.setText(getString(R.string.state_error_server_tip));
    }

    private void stateMain(){
        hideLoadingDialog();
        smartRefreshLayout.setVisibility(View.VISIBLE);
        relativeLayoutStateInfo.setVisibility(View.INVISIBLE);
    }


    @OnClick(R.id.tv_state_tip)
    public void retry(View view){
        loadData();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initEventAndData() {
        getFragmentComponent().inject(this);
        et_hint_search_view.setHint(getString(R.string.tip_hint_input_search));

//        banner
        vpHomePagerBannerAdapter = new VPHomePagerBannerAdapter(getContext());
        vp_banner_home_page.setAdapter(vpHomePagerBannerAdapter);


        loadBannerData();


        rvRecommendCourseAdapter = new RVRecommendCourseAdapter(getContext(), mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        loadData();

//
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mList.clear();
            }
        });
    }

    private void loadBannerData() {
//        addSubscribe(dataManager.getCourseRecommendList());
    }

    private void loadData() {
        stateLoading();
        addSubscribe(dataManager.getCourseRecommendList(page+"",page_size+"")
        .compose(RxUtil.<ResCourseList>rxSchedulerHelper())
                .subscribe(new Consumer<ResCourseList>() {
                    @Override
                    public void accept(ResCourseList resCourseList) throws Exception {
                        if (resCourseList.getCount() > 0){
                            List<ResCourseList.ResultsBean> results = resCourseList.getResults();
                            for (ResCourseList.ResultsBean temp : results){
                                if (temp.getIs_recommend() == 1){
                                    mList.add(temp);
                                }
                            }
                            if (mList.size() > 0){
                                stateMain();
                                recyclerView.setAdapter(rvRecommendCourseAdapter);
                                rvRecommendCourseAdapter.notifyDataSetChanged();
                            }else{
                                stateEmpty();
                            }
                        }else{
                            stateEmpty();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        stateError();
                    }
                })
        );
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
                startActivity(new Intent(getContext(), TeacherMasterActivity.class));
                break;
            case R.id.iv_test_pool:
                startActivity(new Intent(getContext(), TestPoolActivity.class));
                break;
        }
    }

}
