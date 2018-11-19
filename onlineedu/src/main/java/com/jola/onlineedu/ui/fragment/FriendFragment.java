package com.jola.onlineedu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.gson.Gson;
import com.jola.onlineedu.R;
import com.jola.onlineedu.app.App;
import com.jola.onlineedu.app.MyLog;
import com.jola.onlineedu.base.SimpleFragment;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.User;
import com.jola.onlineedu.mode.bean.response.ResFriendListBean;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.ui.activity.FriendApplyActivity;
import com.jola.onlineedu.ui.adapter.SortAdapter;
import com.jola.onlineedu.util.ToastUtil;
import com.jola.onlineedu.widget.SideBar;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by jola on 2018/9/6.
 */

public class FriendFragment extends SimpleFragment {


    @Inject
    DataManager dataManager;

    @BindView(R.id.lv_friends)
    ListView listView;
    @BindView(R.id.side_bar)
    SideBar sideBar;
    private ArrayList<User> list;

    @BindView(R.id.tv_wait_answer)
    TextView tv_wait_answer;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friend;
    }

    @Override
    protected void initEventAndData() {

        getFragmentComponent().inject(this);

        sideBar.setOnStrSelectCallBack(new SideBar.ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                if (null == list || list.size() == 0){
                    return;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (selectStr.equalsIgnoreCase(list.get(i).getFirstLetter())) {
                        listView.setSelection(i); // 选择到首字母出现的位置
                        return;
                    }
                }
            }
        });


//        initData();
        loadFriendData(null);


    }

    private void loadFriendData(String kw) {

        RequestParams requestParams = new RequestParams();
        if (null != kw && kw.length() > 0){
            requestParams.add("kw",kw);
        }
        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION, dataManager.getUserToken());
        App.getmAsyncHttpClient().get("http://yunketang.dev.attackt.com/api/v1/friend/",requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ResFriendListBean resultBean = new Gson().fromJson(new String(responseBody), ResFriendListBean.class);
                if (resultBean.getError_code() == 0){
                    int friends_apply = resultBean.getData().getFriends_apply();
                    tv_wait_answer.setText(friends_apply+"");
                    List<ResFriendListBean.DataBean.FriendsBean> friends = resultBean.getData().getFriends();
                    initFriendAdapter(friends);
                }else{
                    ToastUtil.toastShort("获取朋友列表失败！");
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }



    @OnClick({
            R.id.rl_friend_apply,
    })
    public void clickEvent(View view){
        switch (view.getId()){
            case R.id.rl_friend_apply:
                startActivity(new Intent(getActivity(), FriendApplyActivity.class));
                break;
        }
    }


    private void initFriendAdapter(List<ResFriendListBean.DataBean.FriendsBean> friends) {
        list = new ArrayList<>();
        for (ResFriendListBean.DataBean.FriendsBean temp : friends){
            list.add(new User(temp.getName(),temp));
        }
        Collections.sort(list); // 对list进行排序，需要让User实现Comparable接口重写compareTo方法
        SortAdapter adapter = new SortAdapter(getContext(), list);
        listView.setAdapter(adapter);

    }


    private void initData() {
        list = new ArrayList<>();
//        list.add(new User("亳州")); // 亳[bó]属于不常见的二级汉字
        list.add(new User("大娃"));
        list.add(new User("二娃"));
        list.add(new User("三娃"));
        list.add(new User("四娃"));
        list.add(new User("五娃"));
        list.add(new User("六娃"));
        list.add(new User("七娃"));
        list.add(new User("喜羊羊"));
        list.add(new User("美羊羊"));
        list.add(new User("懒羊羊"));
        list.add(new User("沸羊羊"));
        list.add(new User("暖羊羊"));
        list.add(new User("慢羊羊"));
        list.add(new User("灰太狼"));
        list.add(new User("红太狼"));
        list.add(new User("孙悟空"));
        list.add(new User("黑猫警长"));
        list.add(new User("舒克"));
        list.add(new User("贝塔"));
        list.add(new User("海尔"));
        list.add(new User("阿凡提"));
        list.add(new User("邋遢大王"));
        list.add(new User("哪吒"));
        list.add(new User("没头脑"));
        list.add(new User("不高兴"));
        list.add(new User("蓝皮鼠"));
        list.add(new User("大脸猫"));
        list.add(new User("大头儿子"));
        list.add(new User("小头爸爸"));
        list.add(new User("蓝猫"));
        list.add(new User("淘气"));
        list.add(new User("叶峰"));
        list.add(new User("楚天歌"));
        list.add(new User("江流儿"));
        list.add(new User("Tom"));
        list.add(new User("Jerry"));
        list.add(new User("12345"));
        list.add(new User("54321"));
        list.add(new User("_(:з」∠)_"));
        list.add(new User("……%￥#￥%#"));
        Collections.sort(list); // 对list进行排序，需要让User实现Comparable接口重写compareTo方法
        SortAdapter adapter = new SortAdapter(getContext(), list);
        listView.setAdapter(adapter);
    }



}
