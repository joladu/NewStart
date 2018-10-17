package com.jola.onlineedu.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResCourseList;
import com.jola.onlineedu.ui.adapter.SelectableCourseListAdapter;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.jola.onlineedu.widget.DividerItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;


public class SelectableCourseActivity extends SimpleActivity {


    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;


    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_state_info)
    RelativeLayout relativeLayoutStateInfo;
    @BindView(R.id.iv_tip_state)
    ImageView iv_stateImage;
    @BindView(R.id.tv_state_tip)
    TextView tv_stateText;




    @BindView(R.id.et_hint_search_view)
    EditText et_hint_search_view;

//    @BindView(R.id.tv_tab_class1)
//    TextView tv_tab_class1;
//    @BindView(R.id.tv_tab_class2)
//    TextView tv_tab_class2;
//    @BindView(R.id.tv_tab_class3)
//    TextView tv_tab_class3;
//    @BindView(R.id.tv_tab_class4)
//    TextView tv_tab_class4;

    private int mCurrentIndex = 1;
    private PopupWindow popupWindow;

    List<ResCourseList.ResultsBean> mList;
    private int mStartIndex = 1;
    private SelectableCourseListAdapter mAdapter;
//    private RVLiveCourseAdapter mAdapter;
    private ListView lv_tabList;

    private int page = 1;
    private int pageSize = 10;
    private String nextUrl;

    @Override
    protected int getLayout() {
        return R.layout.activity_selectable_course;
    }

    @Override
    protected void initEventAndData() {
        getActivityComponent().inject(this);
        setToolBar(toolbar,getString(R.string.excellent_course));
        et_hint_search_view.setHint(getString(R.string.search_excellent_course));

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.addItemDecoration(new DividerItemDecoration(SelectableCourseActivity.this,10,10,getResources().getColor(R.color.divide_line_gray)));

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
               loadMoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData();
            }
        });

//        testData();
        loadData();
    }



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

    private void loadData() {
        stateLoading();
        page = 1;
        addSubscribe(dataManager.getCourseList(page+"",pageSize+"")
            .compose(RxUtil.<ResCourseList>rxSchedulerHelper())
                .subscribe(new Consumer<ResCourseList>() {
                    @Override
                    public void accept(ResCourseList resCourseList) throws Exception {
                        smartRefreshLayout.finishRefresh();
                        if (resCourseList.getCount() > 0){
                            mList = resCourseList.getResults();
                            mAdapter = new SelectableCourseListAdapter(SelectableCourseActivity.this, mList);
                            recyclerView.setAdapter(mAdapter);
                            nextUrl = resCourseList.getNext();
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


    private void refreshData() {
        page = 1;
        addSubscribe(dataManager.getCourseList(page+"",pageSize+"")
                .compose(RxUtil.<ResCourseList>rxSchedulerHelper())
                .subscribe(new Consumer<ResCourseList>() {
                    @Override
                    public void accept(ResCourseList resCourseList) throws Exception {
                        smartRefreshLayout.finishRefresh();
                        if (resCourseList.getCount() > 0){
                            mList = resCourseList.getResults();
                            mAdapter = new SelectableCourseListAdapter(SelectableCourseActivity.this, mList);
                            recyclerView.setAdapter(mAdapter);
                            nextUrl = resCourseList.getNext();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        smartRefreshLayout.finishRefresh();
                    }
                })
        );
    }


    private void loadMoreData() {
        if (nextUrl == null || nextUrl.length() ==0){
            smartRefreshLayout.finishLoadMore();
            ToastUtil.toastShort("暂无更多内容！");
            return;
        }
        addSubscribe(dataManager.getCourseList((page++)+"",pageSize+"")
                .compose(RxUtil.<ResCourseList>rxSchedulerHelper())
                .subscribe(new Consumer<ResCourseList>() {
                    @Override
                    public void accept(ResCourseList resCourseList) throws Exception {
                        smartRefreshLayout.finishLoadMore();
                        if (null != resCourseList && resCourseList.getResults().size() > 0){
                            mList.addAll(resCourseList.getResults());
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        smartRefreshLayout.finishLoadMore();
//                        stateError();
                    }
                })
        );
    }

    private void testData() {
        mList = new ArrayList<>();
//        for (int i= mStartIndex;i < mStartIndex + 10;i++){
//            mList.add("测试"+i);
//        }
        mAdapter = new SelectableCourseListAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);


        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                for (int i= mStartIndex;i < mStartIndex + 10;i++){
//                    mList.add("测试内容：第"+i+"条");
//                }
                mStartIndex += 10;
                smartRefreshLayout.finishLoadMore(2000);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mList.clear();
                mStartIndex = 1;
//                for (int i= mStartIndex;i < mStartIndex + 10;i++){
//                    mList.add("测试内容：第"+i+"条");
//                }
                mStartIndex += 10;

                smartRefreshLayout.finishRefresh(2000);
                mAdapter.notifyDataSetChanged();

            }
        });

    }

//
//    @OnClick(R.id.rl_tab_select_first)
//    public void tabSelectFirst(View view){
//        mCurrentIndex = 1;
//        List<String> list_tabs = new ArrayList<>();
//        list_tabs.add("1class1 1class1 1class1");
//        list_tabs.add("1class2");
//        list_tabs.add("1class3");
//        list_tabs.add("1class4");
//        list_tabs.add("1class5");
////        showSelectTabView(tv_tab_class1,list_tabs,tv_tab_class1.getWidth());
//        showSelectTabView(view,list_tabs);
//    }
//
//    @OnClick(R.id.rl_tab_select_second)
//    public void tabSelectSecond(View view){
//        mCurrentIndex = 2;
//        List<String> list_tabs = new ArrayList<>();
//        list_tabs.add("2class1");
//        list_tabs.add("2class2");
//        list_tabs.add("2class3");
//        list_tabs.add("2class4");
//        list_tabs.add("2class5");
//        showSelectTabView(view,list_tabs);
//    }
//
//    @OnClick(R.id.rl_tab_select_third)
//    public void tabSelectThird(View view){
//        mCurrentIndex = 3;
//        List<String> list_tabs = new ArrayList<>();
//        list_tabs.add("3class1");
//        list_tabs.add("3class2");
//        list_tabs.add("3class3");
//        list_tabs.add("3class4");
//        list_tabs.add("3class5");
//        showSelectTabView(view,list_tabs);
//    }
//
//    @OnClick(R.id.rl_tab_select_fourth)
//    public void tabSelectFourth(View view){
//        mCurrentIndex = 4;
//        List<String> list_tabs = new ArrayList<>();
//        list_tabs.add("4class1");
//        list_tabs.add("4class2");
//        list_tabs.add("4class3");
//        list_tabs.add("4class4");
//        list_tabs.add("4class5");
//        showSelectTabView(view,list_tabs);
//    }
//
//
//
//
//    private void showSelectTabView(View view, final List<String> mSelectableList){
//        lv_tabList = new ListView(this);
//        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, R.layout.item_tab_select, mSelectableList);
//        lv_tabList.setAdapter(stringArrayAdapter);
//        lv_tabList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String clickContent = mSelectableList.get(position);
//                switch (mCurrentIndex){
//                    case 1:
//                        tv_tab_class1.setText(clickContent);
//                        break;
//                    case 2:
//                        tv_tab_class2.setText(clickContent);
//                        break;
//                    case 3:
//                        tv_tab_class3.setText(clickContent);
//                        break;
//                    case 4:
//                        tv_tab_class4.setText(clickContent);
//                        break;
//                }
//                popupWindow.dismiss();
//            }
//        });
//
//        popupWindow = new PopupWindow(lv_tabList, view.getWidth(), ActionBar.LayoutParams.WRAP_CONTENT);
//        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.shape_radiu_green_border);
//        popupWindow.setBackgroundDrawable(drawable);
//        popupWindow.setFocusable(true);
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                popupWindow.dismiss();
//            }
//        });
//        popupWindow.showAsDropDown(view,0,10, Gravity.CENTER);
//
//    }

}
