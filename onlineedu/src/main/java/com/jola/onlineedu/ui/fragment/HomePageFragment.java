package com.jola.onlineedu.ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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


    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    @BindView(R.id.srl_home_page)
    SmartRefreshLayout smartRefreshLayout;

    public static final int Type_SetAdapter = 1020;

    private boolean isBannerDataInited = false;
    private boolean isBodyDataInited = false;

    Handler mHandler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Type_SetAdapter:
                    setAdapter();
                    break;
            }
        }
    };


    private List<ResBannerHomepage> mBannerList;
    private List<ResCourseRecommendBean.ResultsBean> mList = new ArrayList<>();
    private RVRecommendCourseAdapter rvRecommendCourseAdapter;



    private int page = 1;
    private int page_size = 10;
    private String inputSearchContent;
    //    private String mNextUrlCourse;

//    private void stateLoading(){
//        showLoadingDialog();
////        smartRefreshLayout.setVisibility(View.INVISIBLE);
//        relativeLayoutStateInfo.setVisibility(View.VISIBLE);
//        iv_stateImage.setImageDrawable(getResources().getDrawable(R.drawable.state_loading));
//        tv_stateText.setText(getString(R.string.state_loading_tip));
//    }

//    private void stateEmpty(){
//        hideLoadingDialog();
////        smartRefreshLayout.setVisibility(View.INVISIBLE);
//        relativeLayoutStateInfo.setVisibility(View.VISIBLE);
//        iv_stateImage.setImageDrawable(getResources().getDrawable(R.drawable.state_empty));
//        tv_stateText.setText(getString(R.string.state_empty_tip));
//    }

//    private void stateError(){
//        hideLoadingDialog();
////        smartRefreshLayout.setVisibility(View.INVISIBLE);
//        relativeLayoutStateInfo.setVisibility(View.VISIBLE);
//        iv_stateImage.setImageDrawable(getResources().getDrawable(R.drawable.state_error_server));
//        tv_stateText.setText(getString(R.string.state_error_server_tip));
//    }

//    private void stateMain(){
//        hideLoadingDialog();
////        smartRefreshLayout.setVisibility(View.VISIBLE);
//        relativeLayoutStateInfo.setVisibility(View.INVISIBLE);
//    }


//    @OnClick(R.id.tv_state_tip)
//    public void retry(View view){
//        loadData();
//    }


    @Override
    protected int getLayoutId() {
//        return R.layout.fragment_home_page;
//        return R.layout.fragment_home_page_new;
        return R.layout.fragment_home_page_recycle;
    }

    @Override
    protected void initEventAndData() {
        getFragmentComponent().inject(this);


        loadBannerData();
        loadData();

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




    private void loadBannerData() {
        addSubscribe(dataManager.getBannerHomepage()
            .compose(RxUtil.<List<ResBannerHomepage>>rxSchedulerHelper())
                .subscribe(new Consumer<List<ResBannerHomepage>>() {
                    @Override
                    public void accept(List<ResBannerHomepage> resBannerHomepage) throws Exception {
                        isBannerDataInited = true;
                        mBannerList = resBannerHomepage;
                        mHandler.sendEmptyMessage(Type_SetAdapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtil.toastShort("网络连接失败");
                    }
                })
        );
    }

    private void loadData() {
        showLoadingDialog();
        page = 1;
        mList.clear();
        addSubscribe(dataManager.getCourseRecommendList(page+"",page_size+"")
        .compose(RxUtil.<ResCourseRecommendBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResCourseRecommendBean>() {
                    @Override
                    public void accept(ResCourseRecommendBean resCourseList) throws Exception {
                        hideLoadingDialog();
                        smartRefreshLayout.finishRefresh();
                        isBodyDataInited = true;
                        List<ResCourseRecommendBean.ResultsBean> results = resCourseList.getResults();
                        mList.addAll(results);
                        mHandler.sendEmptyMessage(Type_SetAdapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        smartRefreshLayout.finishRefresh();
                       ToastUtil.toastShort("网络连接失败！");
                    }
                })
        );
    }

    private void setAdapter() {

        if (!isBannerDataInited || !isBodyDataInited){
            return;
        }

        rvRecommendCourseAdapter = new RVRecommendCourseAdapter(getContext(), mList, mBannerList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.iv_excellent_course:
                    case R.id.et_hint_search_view:
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
//                    case R.id.iv_search:
//                        inputSearchContent = rvRecommendCourseAdapter.getInputSearchContent();
//                        loadData();
//                        break;

                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setLayoutManager(new com.jola.onlineedu.widget.LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.shape_item_divide_single_line));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(rvRecommendCourseAdapter);

        hideLoadingDialog();

    }


    private void loadDataMore() {
        page++;
        addSubscribe(dataManager.getCourseRecommendList(page+"",page_size+"")
                .compose(RxUtil.<ResCourseRecommendBean> rxSchedulerHelper())
                .subscribe(new Consumer<ResCourseRecommendBean>() {
                    @Override
                    public void accept(ResCourseRecommendBean resCourseList) throws Exception {
                        List<ResCourseRecommendBean.ResultsBean> results = resCourseList.getResults();
                        if (null != results && results.size() > 0){
                            mList.addAll(results);
                            rvRecommendCourseAdapter.notifyDataSetChanged();
                        }else{
                            ToastUtil.toastShort("暂无更多内容！");
                        }
                        smartRefreshLayout.finishLoadMore();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        smartRefreshLayout.finishLoadMore();
                        ToastUtil.toastShort("暂无更多内容！");
                    }
                })
        );
    }




}
