package com.jola.onlineedu.ui.activity;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.ui.adapter.TeacherMasterListAdapter;
import com.jola.onlineedu.ui.adapter.TestPoolListAdapter;
import com.jola.onlineedu.ui.adapter.VPHomePagerBannerAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class TeacherMasterActivity extends SimpleActivity {


    @BindView(R.id.vp_banner_teacher)
    ViewPager vp_banner_teacher;

    @BindView(R.id.srl_test_pool_list)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.view_main)
    RecyclerView recyclerView;
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

    List<String> mList;
    private int mStartIndex = 1;
    private TeacherMasterListAdapter mAdapter;
    private ListView lv_tabList;

    private VPHomePagerBannerAdapter vpHomePagerBannerAdapter;


    @Override
    protected int getLayout() {
        return R.layout.activity_teacher_master;
    }

    @Override
    protected void initEventAndData() {
//        setToolBar(toolbar,"题库");
        et_hint_search_view.setHint("精英名师搜索");


        vpHomePagerBannerAdapter = new VPHomePagerBannerAdapter(this);
        vp_banner_teacher.setAdapter(vpHomePagerBannerAdapter);

        mList = new ArrayList<>();
        for (int i= mStartIndex;i < mStartIndex + 10;i++){
            mList.add("高考题目"+i);
        }
        mAdapter = new TeacherMasterListAdapter(this, mList);
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
