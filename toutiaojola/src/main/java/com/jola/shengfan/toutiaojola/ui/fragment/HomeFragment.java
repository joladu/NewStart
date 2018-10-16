package com.jola.shengfan.toutiaojola.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jola.shengfan.toutiaojola.R;
import com.jola.shengfan.toutiaojola.constants.Constant;
import com.jola.shengfan.toutiaojola.mode.entity.Channel;
import com.jola.shengfan.toutiaojola.ui.base.BaseFragment;
import com.jola.shengfan.toutiaojola.ui.base.BasePresenter;
import com.jola.shengfan.toutiaojola.util.PreUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.weyye.library.colortrackview.ColorTrackTabLayout;

/**
 * Created by lenovo on 2018/10/16.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.tab_channel)
    ColorTrackTabLayout mTabChannel;

    @BindView(R.id.iv_operation)
    ImageView ivAddChannel;

    @BindView(R.id.vp_content)
    ViewPager mVpContent;


    private List<Channel> mSelectedChannels = new ArrayList<>();
    private List<Channel> mUnSelectedChannels = new ArrayList<>();

    private Gson mGson = new Gson();

    private String[] mChannelCodes;




    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        initChannelData();
        initChannelFragments();
    }

    private void initChannelData() {
        String selectedChannelJson = PreUtils.getString(Constant.SELECTED_CHANNEL_JSON, "");
        String unselectChannel = PreUtils.getString(Constant.UNSELECTED_CHANNEL_JSON, "");

        if (TextUtils.isEmpty(selectedChannelJson) || TextUtils.isEmpty(unselectChannel)){
            String[] channels = getResources().getStringArray(R.array.channel);
            String[] channelCodes = getResources().getStringArray(R.array.channel_code);
            for (int i = 0; i < channelCodes.length; i++) {
                String title = channels[i];
                String code = channelCodes[i];
                mSelectedChannels.add(new Channel(title, code));
            }
            selectedChannelJson = mGson.toJson(mSelectedChannels);//将集合转换成json字符串
            PreUtils.putString(Constant.SELECTED_CHANNEL_JSON, selectedChannelJson);//保存到sp
        }else{
            List<Channel> selectedChannel = mGson.fromJson(selectedChannelJson, new TypeToken<List<Channel>>() {}.getType());
            List<Channel> unselectedChannel = mGson.fromJson(unselectChannel, new TypeToken<List<Channel>>() {}.getType());
            mSelectedChannels.addAll(selectedChannel);
            mUnSelectedChannels.addAll(unselectedChannel);
        }
    }

    private void initChannelFragments() {
        mChannelCodes = getResources().getStringArray(R.array.channel_code);
        for (Channel channel : mSelectedChannels) {
//            NewsListFragment newsFragment = new NewsListFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString(Constant.CHANNEL_CODE, channel.channelCode);
//            bundle.putBoolean(Constant.IS_VIDEO_LIST, channel.channelCode.equals(mChannelCodes[1]));//是否是视频列表页面,根据判断频道号是否是视频
//            newsFragment.setArguments(bundle);
//            mChannelFragments.add(newsFragment);//添加到集合中
        }
    }




}
