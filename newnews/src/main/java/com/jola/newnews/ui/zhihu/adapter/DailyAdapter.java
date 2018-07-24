package com.jola.newnews.ui.zhihu.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jola.newnews.R;
import com.jola.newnews.component.ImageLoader;
import com.jola.newnews.mode.bean.DailyListBean;
import com.jola.newnews.widget.SquareImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/7/24.
 */

public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater mInflater;
    private Context mContext;
    private List<DailyListBean.StoriesBean> mList;
    private List<DailyListBean.TopStoriesBean> mTopList;

    private String currentTitle = "今日热闻";

    private boolean mIsBefore = false;
    private TopPageAdapter mTopPagerAdapter;
    private ViewPager mTopViewPager;

    public enum ITEM_TYPE{
        ITEM_TOP,
        ITEM_DATA,
        ITEM_CONTENT,
    }


    public DailyAdapter(Context mContext, List<DailyListBean.StoriesBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (!mIsBefore){
            if (position == 0){
                return ITEM_TYPE.ITEM_TOP.ordinal();
            }else if (position == 1){
                return ITEM_TYPE.ITEM_DATA.ordinal();
            }else{
                return ITEM_TYPE.ITEM_CONTENT.ordinal();
            }
        }else{
            if (position == 0){
                return ITEM_TYPE.ITEM_DATA.ordinal();
            }else{
                return ITEM_TYPE.ITEM_CONTENT.ordinal();
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TOP.ordinal()){
            mTopPagerAdapter = new TopPageAdapter(mContext, mTopList);
            return new TopViewHolder(mInflater.inflate(R.layout.item_top_daily,parent,false));
        }else if (viewType == ITEM_TYPE.ITEM_DATA.ordinal()){
            return new DataViewHolder(mInflater.inflate(R.layout.item_data_daily,parent,false));
        }else{
            return new ContentViewHolder(mInflater.inflate(R.layout.item_content_daily,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentViewHolder){
            int contentPos;
            if (mIsBefore){
                contentPos = position - 1;
            }else{
                contentPos = position - 2;
            }
            DailyListBean.StoriesBean storiesBean = mList.get(contentPos);
            ContentViewHolder contentViewHolder = (ContentViewHolder)holder;
            contentViewHolder.textView.setText(storiesBean.getTitle());
            if (storiesBean.getReadState()){
                contentViewHolder.textView.setTextColor(ContextCompat.getColor(mContext,R.color.news_read));
            }else{
                contentViewHolder.textView.setTextColor(ContextCompat.getColor(mContext,R.color.news_unread));
            }
            ImageLoader.load(mContext,storiesBean.getImages().get(0),contentViewHolder.squareImageView);
        }else if (holder instanceof  DataViewHolder){
            ((DataViewHolder) holder).tv_data.setText(currentTitle);
        }else{
            ((TopViewHolder)holder).viewPager.setAdapter(mTopPagerAdapter);
            mTopViewPager = ((TopViewHolder)holder).viewPager;
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TopViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.vp_top)
        ViewPager viewPager;
        @BindView(R.id.ll_point_container)
        LinearLayout linearLayout;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_daily_date)
        TextView tv_data;

        public DataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_daily_item_image)
        SquareImageView squareImageView;
        @BindView(R.id.tv_daily_item_title)
        TextView textView;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }




}
