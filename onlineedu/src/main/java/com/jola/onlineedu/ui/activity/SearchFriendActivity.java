package com.jola.onlineedu.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jola.onlineedu.R;
import com.jola.onlineedu.app.App;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.User;
import com.jola.onlineedu.mode.bean.response.ResSearchFriendBean;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.util.ToastUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

public class SearchFriendActivity extends SimpleActivity {

    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;


    @BindView(R.id.et_hint_search_view)
    EditText et_hint_search_view;
    @BindView(R.id.tv_tip_no_user)
    TextView tv_tip_no_user;
    @BindView(R.id.tv_cancel_search)
    TextView tv_cancel_search;

    @BindView(R.id.smr)
    SmartRefreshLayout smr;
    @BindView(R.id.rv)
    RecyclerView rv;

    RecycleListAdapter mAdapter;
    List<ResSearchFriendBean.DataBean.UsersBean> mList = new ArrayList<>();

    int page = 1;
    String kw;
    boolean isSearching = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_search_friend;
    }

    @Override
    protected void initEventAndData() {
        getActivityComponent().inject(this);
        setToolBar(toolbar, getString(R.string.search_friend_text));
        setAdapter();
        loadFriendPageIndex(null, 1);
    }

    private void setAdapter() {
        mAdapter = new RecycleListAdapter(SearchFriendActivity.this);
        rv.setLayoutManager(new LinearLayoutManager(SearchFriendActivity.this, LinearLayoutManager.VERTICAL, false));
        rv.addItemDecoration(new DividerItemDecoration(SearchFriendActivity.this, DividerItemDecoration.VERTICAL));
        rv.setAdapter(mAdapter);

        smr.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadmoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData();
            }
        });
    }

    private void refreshData() {
        kw = et_hint_search_view.getText().toString();
        page = 1;
        loadFriendPageIndex(kw, page);
    }

    private void loadmoreData() {
        loadFriendPageIndex(kw, ++page);
    }

    private void loadFriendPageIndex(String kw, final int page) {
        showLoadingDialog();
        if (page == 1){
            mList.clear();
            rv.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
        isSearching = true;
        RequestParams requestParams = new RequestParams();
        if (null == kw || kw.length() == 0) {
            requestParams.put("kw", "");
        }else{
            requestParams.put("kw", kw);
        }
        requestParams.put("page", page);
        requestParams.put("pageSize", 10);
        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION, dataManager.getUserToken());
        App.getmAsyncHttpClient().get("http://yunketang.dev.attackt.com/api/v1/common/usersearch/", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ResSearchFriendBean resultBean = new Gson().fromJson(new String(responseBody), ResSearchFriendBean.class);
                smr.finishRefresh();
                smr.finishLoadMore();
                hideLoadingDialog();
                if (resultBean.getError_code() == 0) {
                    tv_tip_no_user.setVisibility(View.GONE);
                    List<ResSearchFriendBean.DataBean.UsersBean> usersList = resultBean.getData().getUsers();
                    mList.addAll(usersList);
                    mAdapter.notifyDataSetChanged();
                    if (mList.size() == 0) {
                        tv_tip_no_user.setVisibility(View.VISIBLE);
                    }
                } else {
                    ToastUtil.toastShort(resultBean.getError_msg());
                }
                tv_cancel_search.setText("确定");
                isSearching = false;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                smr.finishRefresh();
                smr.finishLoadMore();
                hideLoadingDialog();
                tipServerError();
                tv_cancel_search.setText("确定");
                isSearching = false;
            }
        });
    }

    @OnClick({
            R.id.iv_search,
            R.id.tv_cancel_search,
    })
    public void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                String searchContent = et_hint_search_view.getText().toString();
                page = 1;
                loadFriendPageIndex(searchContent, page);
                break;
            case R.id.tv_cancel_search:
                if (isSearching) {
                    App.getmAsyncHttpClient().cancelAllRequests(true);
                    hideLoadingDialog();
                    ToastUtil.toastShort("已取消搜索");
                    tv_cancel_search.setText("确定");
                    isSearching = false;
                } else {
                    refreshData();
                    tv_cancel_search.setText("取消");
                    isSearching = true;
                }
                break;
        }
    }


//    begain adapter

    class RecycleListAdapter extends RecyclerView.Adapter<SearchFriendActivity.RecycleListAdapter.ViewHolder> {

        LayoutInflater layoutInflater;

        public RecycleListAdapter(Context context) {
            layoutInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public SearchFriendActivity.RecycleListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SearchFriendActivity.RecycleListAdapter.ViewHolder(layoutInflater.inflate(R.layout.item_friend_search, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SearchFriendActivity.RecycleListAdapter.ViewHolder holder, int position) {
            final ResSearchFriendBean.DataBean.UsersBean usersBean = mList.get(position);
            ImageLoader.load(SearchFriendActivity.this, usersBean.getAvatar_url(), holder.civ_head_user);
            holder.tv_friend_name.setText(usersBean.getUsername());
            final StringBuilder sb = new StringBuilder();
//          文学  民族学  北京交通大学海滨学院
            List<String> courses = usersBean.getCourses();
            for (String tempCourse : courses) {
                sb.append(tempCourse + " ");
            }
            sb.append(usersBean.getSchool_name());
            holder.tv_friend_describe.setText(sb.toString());

            holder.rl_friend_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SearchFriendActivity.this, UserDetailActivity.class);
                    intent.putExtra("userId",usersBean.getId());
                    intent.putExtra("headImgUrl",usersBean.getAvatar_url());
                    intent.putExtra("userName",usersBean.getUsername());
                    intent.putExtra("describe",sb.toString());
                    intent.putExtra("area","unknow");
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.rl_friend_info)
            RelativeLayout rl_friend_info;
            @BindView(R.id.civ_head_user)
            CircleImageView civ_head_user;
            @BindView(R.id.tv_friend_name)
            TextView tv_friend_name;
            @BindView(R.id.tv_friend_describe)
            TextView tv_friend_describe;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

    }

//    end adapter


}
