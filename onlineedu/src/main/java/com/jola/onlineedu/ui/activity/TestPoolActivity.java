package com.jola.onlineedu.ui.activity;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResExamsList;
import com.jola.onlineedu.ui.adapter.TestPoolListAdapter;
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


public class TestPoolActivity extends SimpleActivity {


    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    @BindView(R.id.srl_test_pool_list)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_state_info)
    RelativeLayout relativeLayoutStateInfo;
    @BindView(R.id.iv_tip_state)
    ImageView iv_stateImage;
    @BindView(R.id.tv_state_tip)
    TextView tv_stateText;


    @BindView(R.id.et_hint_search_view)
    EditText et_hint_search_view;

    @BindView(R.id.tv_tab_class1)
    TextView tv_tab_class1;
    @BindView(R.id.tv_tab_class2)
    TextView tv_tab_class2;
    @BindView(R.id.tv_tab_class3)
    TextView tv_tab_class3;
    @BindView(R.id.tv_tab_class4)
    TextView tv_tab_class4;

    private int mCurrentIndex = 1;
    private PopupWindow popupWindow;

    List<ResExamsList.DataBean.ExamsBean> mList;
    private int mStartIndex = 1;
    private TestPoolListAdapter mAdapter;
    private ListView lv_tabList;

    @Override
    protected int getLayout() {
        return R.layout.activity_test_pool;
    }

    @Override
    protected void initEventAndData() {

        getActivityComponent().inject(this);

        setToolBar(toolbar,getString(R.string.test_pool));
        et_hint_search_view.setHint(getString(R.string.hint_search_test_pool));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

//        测试数据
//        testData();

        loadData();

    }

    @OnClick(R.id.tv_state_tip)
    public void retry(View view){
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

    private void loadData() {
        getExamsList();
    }

    private void getExamsList(){
        stateLoading();
        addSubscribe(dataManager.getExamsList()
        .compose(RxUtil.<ResExamsList>rxSchedulerHelper())
                .subscribe(new Consumer<ResExamsList>() {
                    @Override
                    public void accept(ResExamsList resExamsList) throws Exception {
                        if (resExamsList.getError_code() == 0){
                           mList = resExamsList.getData().getExams();
                            mAdapter = new TestPoolListAdapter(TestPoolActivity.this, mList);
                            recyclerView.setAdapter(mAdapter);
                           if (mList.size() == 0){
                               stateEmpty();
                           }else{
                               stateMain();
                           }
                        }else{
                            stateError();
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


    private void testData(){
        mList = new ArrayList<>();
//        for (int i= mStartIndex;i < mStartIndex + 10;i++){
//            mList.add("高考题目"+i);
//        }
        mAdapter = new TestPoolListAdapter(this, mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                for (int i= mStartIndex;i < mStartIndex + 10;i++){
//                    mList.add("河南省数学高考题：第"+i+"条");
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
//                    mList.add("河南省数学高考题：第"+i+"条");
//                }
                mStartIndex += 10;

                smartRefreshLayout.finishRefresh(2000);
                mAdapter.notifyDataSetChanged();

            }
        });
    }






    @OnClick(R.id.rl_tab_select_first)
    public void tabSelectFirst(View view){
        mCurrentIndex = 1;
        List<String> list_tabs = new ArrayList<>();
        list_tabs.add("1class1 1class1 1class1");
        list_tabs.add("1class2");
        list_tabs.add("1class3");
        list_tabs.add("1class4");
        list_tabs.add("1class5");
//        showSelectTabView(tv_tab_class1,list_tabs,tv_tab_class1.getWidth());
        showSelectTabView(view,list_tabs);
    }

    @OnClick(R.id.rl_tab_select_second)
    public void tabSelectSecond(View view){
        mCurrentIndex = 2;
        List<String> list_tabs = new ArrayList<>();
        list_tabs.add("2class1");
        list_tabs.add("2class2");
        list_tabs.add("2class3");
        list_tabs.add("2class4");
        list_tabs.add("2class5");
        showSelectTabView(view,list_tabs);
    }

    @OnClick(R.id.rl_tab_select_third)
    public void tabSelectThird(View view){
        mCurrentIndex = 3;
        List<String> list_tabs = new ArrayList<>();
        list_tabs.add("3class1");
        list_tabs.add("3class2");
        list_tabs.add("3class3");
        list_tabs.add("3class4");
        list_tabs.add("3class5");
        showSelectTabView(view,list_tabs);
    }

    @OnClick(R.id.rl_tab_select_fourth)
    public void tabSelectFourth(View view){
        mCurrentIndex = 4;
        List<String> list_tabs = new ArrayList<>();
        list_tabs.add("4class1");
        list_tabs.add("4class2");
        list_tabs.add("4class3");
        list_tabs.add("4class4");
        list_tabs.add("4class5");
        showSelectTabView(view,list_tabs);
    }




    private void showSelectTabView(View view, final List<String> mSelectableList){
        lv_tabList = new ListView(this);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, R.layout.item_tab_select, mSelectableList);
        lv_tabList.setAdapter(stringArrayAdapter);
        lv_tabList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickContent = mSelectableList.get(position);
                switch (mCurrentIndex){
                    case 1:
                        tv_tab_class1.setText(clickContent);
                        break;
                    case 2:
                        tv_tab_class2.setText(clickContent);
                        break;
                    case 3:
                        tv_tab_class3.setText(clickContent);
                        break;
                    case 4:
                        tv_tab_class4.setText(clickContent);
                        break;
                }
                popupWindow.dismiss();
            }
        });

        popupWindow = new PopupWindow(lv_tabList, view.getWidth(), ActionBar.LayoutParams.WRAP_CONTENT);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.shape_radiu_green_border);
        popupWindow.setBackgroundDrawable(drawable);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(view,0,10, Gravity.CENTER);


    }






















}
