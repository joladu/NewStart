package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jola.onlineedu.R;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.bean.response.ResBannerHomepage;

import java.util.List;

/**
 * Created by jola on 2018/9/8.
 */

public class VPHomePagerBannerAdapter extends PagerAdapter {

    private  LayoutInflater layoutInflater;
    List<ResBannerHomepage> mList;
    Context context;


    public VPHomePagerBannerAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public VPHomePagerBannerAdapter(Context context,List<ResBannerHomepage> mList) {
        this.mList = mList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.item_vp_banner_home_page,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_banner_hp);
        ImageLoader.load(context,mList.get(position).getDomain_img_url(),imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
