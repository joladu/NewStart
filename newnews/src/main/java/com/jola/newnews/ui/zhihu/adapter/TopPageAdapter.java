package com.jola.newnews.ui.zhihu.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jola.newnews.R;
import com.jola.newnews.component.ImageLoader;
import com.jola.newnews.mode.bean.DailyListBean;

import java.util.List;

/**
 * Created by lenovo on 2018/7/24.
 */

public class TopPageAdapter extends PagerAdapter {

    private final LayoutInflater mInflater;
    private Context mContext;
    private List<DailyListBean.TopStoriesBean> mList;

    public TopPageAdapter(Context mContext, List<DailyListBean.TopStoriesBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.item_top_pager_daily, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_top_image);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_top_title);
        final DailyListBean.TopStoriesBean curBean = mList.get(position);
        ImageLoader.load(mContext,curBean.getImage(),imageView);
        tv_title.setText(curBean.getTitle());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,curBean.getId(),Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }
}
