package com.jola.onlineedu.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.jola.onlineedu.mode.bean.response.ResTeacherBannerBean;
import com.jola.onlineedu.mode.bean.response.ResTeacherList;
import com.jola.onlineedu.ui.adapter.TeacherMasterListAdapter;
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


public class TeacherMasterActivity extends SimpleActivity {


    @Inject
    DataManager dataManager;


    @BindView(R.id.srl_test_pool_list)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.view_main)
    RecyclerView recyclerView;

    public static final int Type_SetAdapter = 1030;




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


//    @BindView(R.id.tv_tab_class1)
//    TextView tv_tab_class1;
//    @BindView(R.id.tv_tab_class2)
//    TextView tv_tab_class2;
//    @BindView(R.id.tv_tab_class3)
//    TextView tv_tab_class3;
//    @BindView(R.id.tv_tab_class4)
//    TextView tv_tab_class4;

//    private int mCurrentIndex = 1;
//    private PopupWindow popupWindow;

//    private ListView lv_tabList;
//    private int mStartIndex = 1;

    List<ResTeacherList.ResultsBean> mList = new ArrayList<>();
    private TeacherMasterListAdapter mAdapter;

//    private VPHomePagerBannerAdapter vpHomePagerBannerAdapter;
    List<ResTeacherBannerBean.ResultsBean> mBannerList;

    private int page = 1;
    private int page_size = 10;

    String searchContent = "";


//    private void stateLoading(){
//        showLoadingDialog();
////        smartRefreshLayout.setVisibility(View.INVISIBLE);
//        relativeLayoutStateInfo.setVisibility(View.VISIBLE);
//        iv_stateImage.setImageDrawable(getResources().getDrawable(R.drawable.state_loading));
//        tv_stateText.setText(getString(R.string.state_loading_tip));
//    }
//
//    private void stateEmpty(){
//        hideLoadingDialog();
////        smartRefreshLayout.setVisibility(View.INVISIBLE);
//        relativeLayoutStateInfo.setVisibility(View.VISIBLE);
//        iv_stateImage.setImageDrawable(getResources().getDrawable(R.drawable.state_empty));
//        tv_stateText.setText(getString(R.string.state_empty_tip));
//    }
//
//    private void stateError(){
//        hideLoadingDialog();
////        smartRefreshLayout.setVisibility(View.INVISIBLE);
//        relativeLayoutStateInfo.setVisibility(View.VISIBLE);
//        iv_stateImage.setImageDrawable(getResources().getDrawable(R.drawable.state_error_server));
//        tv_stateText.setText(getString(R.string.state_error_server_tip));
//    }
//
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
    protected int getLayout() {
//        return R.layout.activity_teacher_master;
        return R.layout.activity_teacher_master_new;
    }

    @Override
    protected void initEventAndData() {

        changeFullScreen();

        getActivityComponent().inject(this);

        loadBannerData();
        loadData();

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new com.jola.onlineedu.widget.LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

//        mAdapter = new TeacherMasterListAdapter(this, mList);
//        recyclerView.setAdapter(mAdapter);

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


    public void changeFullScreen(){
        if (Build.VERSION.SDK_INT >= 21) {
            int option = View.SYSTEM_UI_FLAG_VISIBLE;
            option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
    }

    private void loadBannerData() {
        addSubscribe(dataManager.getTeacherBanner()
        .compose(RxUtil.<ResTeacherBannerBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResTeacherBannerBean>() {
                    @Override
                    public void accept(ResTeacherBannerBean resTeacherBannerBean) throws Exception {

                        mBannerList = resTeacherBannerBean.getResults();
                        isBannerDataInited = true;
                        mHandler.sendEmptyMessage(Type_SetAdapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        tipServerError();
                    }
                })
        );
    }


    private void loadData(){
//        stateLoading();
        showLoadingDialog();
        page = 1;
        if (searchContent == null){
            searchContent = "";
        }
        mList.clear();
        addSubscribe(dataManager.getTeacherList(searchContent,page+"",page_size+"")
            .compose(RxUtil.<ResTeacherList>rxSchedulerHelper())
                .subscribe(new Consumer<ResTeacherList>() {
                    @Override
                    public void accept(ResTeacherList resTeacherList) throws Exception {
                        hideLoadingDialog();
                        smartRefreshLayout.finishRefresh();
                        mList.addAll(resTeacherList.getResults());
                        isBodyDataInited = true;
                        mHandler.sendEmptyMessage(Type_SetAdapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        smartRefreshLayout.finishRefresh();
                    }
                })
        );
    }

    private void loadDataMore(){
        page ++;
        if (null == searchContent){
            searchContent = "";
        }
        showLoadingDialog();
        addSubscribe(dataManager.getTeacherList(searchContent,page+"",page_size+"")
                .compose(RxUtil.<ResTeacherList>rxSchedulerHelper())
                .subscribe(new Consumer<ResTeacherList>() {
                    @Override
                    public void accept(ResTeacherList resTeacherList) throws Exception {
                        hideLoadingDialog();
                        smartRefreshLayout.finishLoadMore();
                        if (resTeacherList.getCount() > 0){
                            mList.addAll(resTeacherList.getResults());
                            mAdapter.notifyDataSetChanged();
                        }else{
                            ToastUtil.toastShort(getString(R.string.tip_no_more_data));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        smartRefreshLayout.finishLoadMore();
                    }
                })
        );
    }

    private void setAdapter() {
        if (!isBannerDataInited || !isBodyDataInited) {
            return;
        }
        recyclerView.setLayoutManager(new com.jola.onlineedu.widget.LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        mAdapter = new TeacherMasterListAdapter(TeacherMasterActivity.this, mList, mBannerList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_search:
                        searchContent = mAdapter.getInputSearchContent();
                        ToastUtil.toastShort(searchContent);
                        loadData();
                        break;
                    case R.id.iv_back_finish:
                        TeacherMasterActivity.this.finish();
                        break;
                }
            }
        });
        recyclerView.setAdapter(mAdapter);
    }



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
//   }

}
