package com.jola.onlineedu.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleFragment;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResLiveCourseList;
import com.jola.onlineedu.ui.adapter.RVLiveCourseAdapter;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Created by jola on 2018/9/6.
 */

public class LiveFragment extends SimpleFragment {

    @Inject
    DataManager dataManager;

    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    @BindView(R.id.srl_live_course)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_state_info)
    RelativeLayout relativeLayoutStateInfo;
    @BindView(R.id.iv_tip_state)
    ImageView iv_stateImage;
    @BindView(R.id.tv_state_tip)
    TextView tv_stateText;
    @BindView(R.id.et_hint_search_view)
    EditText et_hint_search_view;


//    int mStartIndex = 1;
//    private LiveCourseListAdapter mAdapter;
    private RVLiveCourseAdapter mAdapter;

    private int page = 1;
    private int page_size = 10;
    private List<ResLiveCourseList.ResultsBean> mList;
    private Object nextUrl;


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
        return R.layout.fragment_live;
    }

    @Override
    protected void initEventAndData() {
        getFragmentComponent().inject(this);
        et_hint_search_view.setHint("搜索课程");


        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

//     testData();
     loadData();
//        rv_view_main.addItemDecoration(new DividerItemDecoration(getContext(),10,10,getContext().getResources().getColor(R.color.divide_line_gray)));
//        rv_view_main.addItemDecoration(new GridDivider(getContext(),10,getContext().getResources().getColor(R.color.divide_line_gray)));

//        rv_view_main.addItemDecoration(new RecycleViewDivider(
//                mContext, LinearLayoutManager.HORIZONTAL, 10, getResources().getColor(R.color.divide_line_gray)));


        recyclerView.setAdapter(mAdapter);

//
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadDataMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
              loadData();
            }
        });

    }

    private void loadData() {
        page = 1;
        stateLoading();
        addSubscribe(dataManager.getLiveCourseList(page+"",page_size+"")
            .compose(RxUtil.<ResLiveCourseList>rxSchedulerHelper())
                .subscribe(new Consumer<ResLiveCourseList>() {
                    @Override
                    public void accept(ResLiveCourseList resLiveCourseList) throws Exception {
                        smartRefreshLayout.finishRefresh();
                        if (resLiveCourseList.getCount() > 0){
                            mList = resLiveCourseList.getResults();
                            mAdapter = new RVLiveCourseAdapter(mContext, mList);
                            recyclerView.setAdapter(mAdapter);
                            nextUrl = resLiveCourseList.getNext();
                            stateMain();
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
        page ++;
        addSubscribe(dataManager.getLiveCourseList(page+"",page_size+"")
                .compose(RxUtil.<ResLiveCourseList>rxSchedulerHelper())
                .subscribe(new Consumer<ResLiveCourseList>() {
                    @Override
                    public void accept(ResLiveCourseList resLiveCourseList) throws Exception {
                        smartRefreshLayout.finishLoadMore();
                        if (resLiveCourseList.getCount() > 0){
                            mList.addAll(resLiveCourseList.getResults());
                            mAdapter.notifyDataSetChanged();
                            nextUrl = resLiveCourseList.getNext();
                        }else{
                            ToastUtil.toastShort("未获取到更多内容！");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        smartRefreshLayout.finishLoadMore();
                        ToastUtil.toastShort("未获取到更多内容！");
                    }
                })
        );
    }

//    private void testData(){
//        mList = new ArrayList<>();
////        mList.add("text");
////        mList.add("text");
////        mList.add("text");
////        mAdapter = new RVLiveCourseAdapter(getContext(), mList);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
//    }
}
