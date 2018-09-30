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
import com.jola.onlineedu.banner.BannerPagerAdapter;
import com.jola.onlineedu.banner.BannerViewPager;
import com.jola.onlineedu.base.SimpleFragment;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResBannerHomepage;
import com.jola.onlineedu.mode.bean.response.ResCourseList;
import com.jola.onlineedu.ui.activity.ForumListActivity;
import com.jola.onlineedu.ui.activity.SelectableCourseActivity;
import com.jola.onlineedu.ui.activity.TeacherMasterActivity;
import com.jola.onlineedu.ui.activity.TestPoolActivity;
import com.jola.onlineedu.ui.adapter.RVRecommendCourseAdapter;
import com.jola.onlineedu.ui.adapter.VPHomePagerBannerAdapter;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;
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


//        loadBannerData();
        testBanner();


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

    private void testBanner() {
        ArrayList<ResBannerHomepage> listBanner = new ArrayList<>();
        ResBannerHomepage resBannerHomepage = new ResBannerHomepage();
        resBannerHomepage.setImg("data:image/jpeg;base64,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");
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
            .compose(RxUtil.<ResBannerHomepage>rxSchedulerHelper())
                .subscribe(new Consumer<ResBannerHomepage>() {
                    @Override
                    public void accept(ResBannerHomepage resBannerHomepage) throws Exception {
                        if (resBannerHomepage != null){
                            ArrayList<ResBannerHomepage> listBanner = new ArrayList<>();
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
                        }else{
                            vp_banner_home_page.setVisibility(View.INVISIBLE);
                            iv_holder_banner.setVisibility(View.VISIBLE);
                            iv_holder_banner.setImageDrawable(mContext.getResources().getDrawable(R.drawable.image_placeholder_fail));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        vp_banner_home_page.setVisibility(View.INVISIBLE);
                        iv_holder_banner.setVisibility(View.VISIBLE);
                        iv_holder_banner.setImageDrawable(mContext.getResources().getDrawable(R.drawable.image_placeholder_fail));
                    }
                })
        );
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
