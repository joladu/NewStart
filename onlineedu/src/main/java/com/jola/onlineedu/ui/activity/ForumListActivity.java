package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResForumListByTypeBean;
import com.jola.onlineedu.mode.bean.response.ResForumTypeBean;
import com.jola.onlineedu.ui.adapter.ForumListAdapter;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class ForumListActivity extends SimpleActivity {


    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;
    @BindView(R.id.et_hint_search_view)
    EditText et_hint_search_view;

    @BindView(R.id.refresh_layout_forum_list)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_state_info)
    RelativeLayout relativeLayoutStateInfo;
    @BindView(R.id.iv_tip_state)
    ImageView iv_stateImage;
    @BindView(R.id.tv_state_tip)
    TextView tv_stateText;



    @BindView(R.id.view_main)
    RecyclerView rv_list;

    @BindView(R.id.labels_list_forum)
    LabelsView labelsView;

    private List<ResForumListByTypeBean.DataBean.PostsBean> mList;
    private ForumListAdapter mAdapter;
    int mStartIndex = 1;
    private ArrayList<String> lableList;
    private HashMap<String, Integer> map_lableIds;
    private String curForumTypeId;
    private int page = 1;

    @Override
    protected int getLayout() {
        return R.layout.activity_forum_list;
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
        String searchKey = et_hint_search_view.getText().toString();
        if (TextUtils.isEmpty(searchKey)){
            searchKey = "";
        }
        getForumList(searchKey.length() == 0 ? null : searchKey,curForumTypeId);
    }

    @Override
    protected void initEventAndData() {
        getActivityComponent().inject(this);
        setToolBar(toolbar, getString(R.string.forum));
        et_hint_search_view.setHint(getString(R.string.forum_search_hint));

        labelsView.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(TextView label, Object data, boolean isSelect, int position) {
                if (isSelect) {
//                    ToastUtil.toastShort((String) data);
                    String searchKey = et_hint_search_view.getText().toString();
                    if (TextUtils.isEmpty(searchKey)){
                        searchKey = "";
                    }
                    curForumTypeId = map_lableIds.get((String) data) + "";
                    getForumList(searchKey.length() == 0 ? null : searchKey,curForumTypeId);
                }
            }
        });

        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                String searchKey = et_hint_search_view.getText().toString();
                if (TextUtils.isEmpty(searchKey)){
                    searchKey = "";
                }
                getForumListMore(searchKey.length() == 0 ? null : searchKey,curForumTypeId);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                String searchKey = et_hint_search_view.getText().toString();
                if (TextUtils.isEmpty(searchKey)){
                    searchKey = "";
                }
                refreshForumList(searchKey.length() == 0 ? null : searchKey,curForumTypeId);
            }
        });

        loadData();
    }

    private void loadData(){
        getForumTypeInfo();
    }

    @OnClick(R.id.iv_search)
    public void doClick(View view){
        String searchKey = et_hint_search_view.getText().toString();
        if (TextUtils.isEmpty(searchKey)){
            searchKey = null;
        }
        getForumList(searchKey,curForumTypeId);
    }


    private void getForumTypeInfo() {
        stateLoading();
        addSubscribe(dataManager.getForumTypeInfo()
                .compose(RxUtil.<ResForumTypeBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResForumTypeBean>() {
                    @Override
                    public void accept(ResForumTypeBean resForumTypeBean) throws Exception {
                        int error_code = resForumTypeBean.getError_code();
                        lableList = new ArrayList<>();
                        map_lableIds = new HashMap<>();
                        if (error_code == 0) {
                            ResForumTypeBean.DataBean data = resForumTypeBean.getData();
                            List<ResForumTypeBean.DataBean.TypesBean> types = data.getTypes();
                            for (ResForumTypeBean.DataBean.TypesBean typesBean : types) {
                                int id = typesBean.getId();
                                String name = typesBean.getName();
                                lableList.add(name);
                                map_lableIds.put(name, id);
                            }
                            if (lableList.size() == 0){
                                stateEmpty();
                            }else{
                                labelsView.setLabels(lableList);
                                labelsView.setSelects(0);
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
                }));
    }


    private void getForumList(String keyWord, String forumType) {
        page = 1;
        stateLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("type",forumType);
        map.put("page",page+"");
        map.put("pageSize","10");
        if (null != keyWord && keyWord.length() > 0){
            map.put("kw",keyWord);
        }
        addSubscribe(dataManager.getForumListByType(map)
                .compose(RxUtil.<ResForumListByTypeBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResForumListByTypeBean>() {
                    @Override
                    public void accept(ResForumListByTypeBean forumListByTypeBean) throws Exception {
                        int error_code = forumListByTypeBean.getError_code();
                        if (error_code == 0) {
//                            List<ResForumListByTypeBean.DataBean.PostsBean> posts = forumListByTypeBean.getData().getPosts();
                            mList = forumListByTypeBean.getData().getPosts();
                            mAdapter = new ForumListAdapter(ForumListActivity.this, mList);
                            rv_list.setAdapter(mAdapter);
                            if (mList.size() == 0){
                                stateEmpty();
                            }else{
                                stateMain();
                            }
                        }else{
                            stateError();
                        }
                        smartRefreshLayout.finishRefresh();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
//                        String message = throwable.getMessage();
                        stateError();
                        smartRefreshLayout.finishRefresh();
                    }
                }));
    }

    private void refreshForumList(String keyWord, String forumType) {
        page = 1;
//        stateLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("type",forumType);
        map.put("page",page+"");
        map.put("pageSize","10");
        if (null != keyWord && keyWord.length() > 0){
            map.put("kw",keyWord);
        }
        addSubscribe(dataManager.getForumListByType(map)
                .compose(RxUtil.<ResForumListByTypeBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResForumListByTypeBean>() {
                    @Override
                    public void accept(ResForumListByTypeBean forumListByTypeBean) throws Exception {
                        int error_code = forumListByTypeBean.getError_code();
                        if (error_code == 0) {
//                            List<ResForumListByTypeBean.DataBean.PostsBean> posts = forumListByTypeBean.getData().getPosts();
                            mList = forumListByTypeBean.getData().getPosts();
                            mAdapter = new ForumListAdapter(ForumListActivity.this, mList);
                            rv_list.setAdapter(mAdapter);
                            if (mList.size() == 0){
                                stateEmpty();
                            }else{
                                stateMain();
                            }
                        }else{
                            stateError();
                        }
                        smartRefreshLayout.finishRefresh();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
//                        String message = throwable.getMessage();
                        stateError();
                        smartRefreshLayout.finishRefresh();
                    }
                }));
    }


    private void getForumListMore(String keyWord, String forumType) {
        page++;
        HashMap<String, String> map = new HashMap<>();
        map.put("type",forumType);
        map.put("page",page+"");
        map.put("pageSize","10");
        if (null != keyWord && keyWord.length() > 0){
            map.put("kw",keyWord);
        }
        addSubscribe(dataManager.getForumListByType(map)
                .compose(RxUtil.<ResForumListByTypeBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResForumListByTypeBean>() {
                    @Override
                    public void accept(ResForumListByTypeBean forumListByTypeBean) throws Exception {
                        int error_code = forumListByTypeBean.getError_code();
                        if (error_code == 0) {
//                            List<ResForumListByTypeBean.DataBean.PostsBean> posts = forumListByTypeBean.getData().getPosts();
//                            mList = forumListByTypeBean.getData().getPosts();
//                            mAdapter = new ForumListAdapter(ForumListActivity.this, mList);
//                            rv_list.setAdapter(mAdapter);
                            mList.addAll(forumListByTypeBean.getData().getPosts());
                            mAdapter.notifyDataSetChanged();
                        }
                        smartRefreshLayout.finishLoadMore();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtil.toastShort("获取更多失败");
                        smartRefreshLayout.finishLoadMore();
                    }
                }));
    }


    @OnClick(R.id.add_icon_in_tool)
    public void addPublishForum(View view) {
//        Intent intent = new Intent(this, ForumPublishActivity.class);
//        if (null != map_lableIds && map_lableIds.size() > 0){
//            intent.putExtra("MapLabelIds",map_lableIds);
//        }
        startActivity(new Intent(this, ForumPublishActivity.class));
    }

}
