package com.jola.onlineedu.ui.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.banner.BannerPagerAdapter;
import com.jola.onlineedu.banner.BannerViewPager;
import com.jola.onlineedu.base.SimpleFragment;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResBannerHomepage;
import com.jola.onlineedu.mode.bean.response.ResCourseList;
import com.jola.onlineedu.mode.bean.response.ResCourseRecommendBean;
import com.jola.onlineedu.ui.activity.ForumListActivity;
import com.jola.onlineedu.ui.activity.SelectableCourseActivity;
import com.jola.onlineedu.ui.activity.TeacherMasterActivity;
import com.jola.onlineedu.ui.activity.TestPoolActivity;
import com.jola.onlineedu.ui.adapter.RVRecommendCourseAdapter;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
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
    BannerViewPager vp_banner_home_page;
    @BindView(R.id.iv_holder_banner)
    ImageView iv_holder_banner;


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


    private BannerPagerAdapter vpHomePagerBannerAdapter;

    private List<ResCourseRecommendBean> mList = new ArrayList<>();
    private RVRecommendCourseAdapter rvRecommendCourseAdapter;

    private int page = 1;
    private int page_size = 10;
//    private String mNextUrlCourse;

    private void stateLoading(){
        showLoadingDialog();
//        smartRefreshLayout.setVisibility(View.INVISIBLE);
        relativeLayoutStateInfo.setVisibility(View.VISIBLE);
        iv_stateImage.setImageDrawable(getResources().getDrawable(R.drawable.state_loading));
        tv_stateText.setText(getString(R.string.state_loading_tip));
    }

    private void stateEmpty(){
        hideLoadingDialog();
//        smartRefreshLayout.setVisibility(View.INVISIBLE);
        relativeLayoutStateInfo.setVisibility(View.VISIBLE);
        iv_stateImage.setImageDrawable(getResources().getDrawable(R.drawable.state_empty));
        tv_stateText.setText(getString(R.string.state_empty_tip));
    }

    private void stateError(){
        hideLoadingDialog();
//        smartRefreshLayout.setVisibility(View.INVISIBLE);
        relativeLayoutStateInfo.setVisibility(View.VISIBLE);
        iv_stateImage.setImageDrawable(getResources().getDrawable(R.drawable.state_error_server));
        tv_stateText.setText(getString(R.string.state_error_server_tip));
    }

    private void stateMain(){
        hideLoadingDialog();
//        smartRefreshLayout.setVisibility(View.VISIBLE);
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


        loadBannerData();
//        testBanner();


        rvRecommendCourseAdapter = new RVRecommendCourseAdapter(getContext(), mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        loadData();

        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadDataMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mList.clear();
                loadData();
            }
        });


    }

    private void testBanner() {
        ArrayList<ResBannerHomepage> listBanner = new ArrayList<>();
        ResBannerHomepage resBannerHomepage = new ResBannerHomepage();
       resBannerHomepage.setAdvertising_url("https://www.baidu.com/img/bd_logo1.png?where=super");
        listBanner.add(resBannerHomepage);
        listBanner.add(resBannerHomepage);
        listBanner.add(resBannerHomepage);
        listBanner.add(resBannerHomepage);
        listBanner.add(resBannerHomepage);
        vpHomePagerBannerAdapter = new BannerPagerAdapter(getContext(),listBanner);
        vp_banner_home_page.setAdapter(vpHomePagerBannerAdapter,listBanner.size());

        vpHomePagerBannerAdapter.setOnPageClickListener(new BannerPagerAdapter.OnPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                ToastUtil.toastLong("position:"+position);
            }
        });

        vp_banner_home_page.setVisibility(View.VISIBLE);
        iv_holder_banner.setVisibility(View.INVISIBLE);
    }

    private void loadBannerData() {
        addSubscribe(dataManager.getBannerHomepage()
            .compose(RxUtil.<List<ResBannerHomepage>>rxSchedulerHelper())
                .subscribe(new Consumer<List<ResBannerHomepage>>() {
                    @Override
                    public void accept(List<ResBannerHomepage> resBannerHomepage) throws Exception {
                        if (resBannerHomepage != null){
//                            Log.e("jola11","bannner  accept(ResBannerHomepage resBannerHomepage");
//                            ArrayList<ResBannerHomepage> listBanner = new ArrayList<>();
//                            listBanner.add(resBannerHomepage);
                            vpHomePagerBannerAdapter = new BannerPagerAdapter(getContext(),resBannerHomepage);
                            vp_banner_home_page.setAdapter(vpHomePagerBannerAdapter,resBannerHomepage.size());

//                            vpHomePagerBannerAdapter.setOnPageClickListener(new BannerPagerAdapter.OnPageClickListener() {
//                                @Override
//                                public void onPageClick(View view, int position) {
//                                    ToastUtil.toastLong("position:"+position);
//                                }
//                            });

                            vp_banner_home_page.setVisibility(View.VISIBLE);
                            iv_holder_banner.setVisibility(View.INVISIBLE);
                        }else{
                            vp_banner_home_page.setVisibility(View.INVISIBLE);
                            iv_holder_banner.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
//                        Log.e("jola11","bannner  throws Exception ");
                        vp_banner_home_page.setVisibility(View.INVISIBLE);
                        iv_holder_banner.setVisibility(View.VISIBLE);
                    }
                })
        );
    }

    private void loadData() {
        stateLoading();
        page = 1;
        mList.clear();
        addSubscribe(dataManager.getCourseRecommendList(page+"",page_size+"")
        .compose(RxUtil.<List<ResCourseRecommendBean>>rxSchedulerHelper())
                .subscribe(new Consumer<List<ResCourseRecommendBean>>() {
                    @Override
                    public void accept(List<ResCourseRecommendBean> resCourseList) throws Exception {
                        smartRefreshLayout.finishRefresh();
                        if (resCourseList.size() > 0){
//                            List<ResCourseList.ResultsBean> results = resCourseList.getResults();
                            mList.addAll(resCourseList);
                            if (mList.size() > 0){
                                stateMain();
                                recyclerView.setAdapter(rvRecommendCourseAdapter);
                                rvRecommendCourseAdapter.notifyDataSetChanged();
//                                mNextUrlCourse = resCourseList.getNext();
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
                        smartRefreshLayout.finishRefresh();
                        stateError();
                    }
                })
        );
    }


    private void loadDataMore() {
//        if (null == mNextUrlCourse || mNextUrlCourse.length() == 0){
//            smartRefreshLayout.finishLoadMore();
//            ToastUtil.toastShort("暂无更多内容");
//            return;
//        }
        addSubscribe(dataManager.getCourseRecommendList((page++)+"",page_size+"")
                .compose(RxUtil.<List<ResCourseRecommendBean>> rxSchedulerHelper())
                .subscribe(new Consumer<List<ResCourseRecommendBean>>() {
                    @Override
                    public void accept(List<ResCourseRecommendBean> resCourseList) throws Exception {
                        if (resCourseList.size() > 0){
                            mList.addAll(resCourseList);
                            rvRecommendCourseAdapter.notifyDataSetChanged();
                        }
                        smartRefreshLayout.finishLoadMore();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        smartRefreshLayout.finishLoadMore();
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
