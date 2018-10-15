package com.jola.onlineedu.banner;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.bean.response.ResBannerHomepage;
import com.jola.onlineedu.ui.activity.LoadWebUrlActivity;

import java.util.List;

/**
 * Created by lenovo on 2018/9/30.
 */

public class BannerPagerAdapter extends PagerAdapter {
    
    public interface OnPageClickListener{
        void onPageClick(View view,int position);
    }

    private LayoutInflater layoutInflater;
    private List<ResBannerHomepage> mList;
    private Context context;

    private OnPageClickListener onPageClickListener;

    public void setOnPageClickListener(OnPageClickListener onPageClickListener){
        this.onPageClickListener = onPageClickListener;
    }


//    public BannerPagerAdapter(Context context,List<ResBannerHomepage> mList,OnPageClickListener onPageClickListener) {
//        this.mList = mList;
//        this.context = context;
//        this.onPageClickListener = onPageClickListener;
//        layoutInflater = LayoutInflater.from(context);
//    }

    public BannerPagerAdapter(Context context,List<ResBannerHomepage> mList) {
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
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        final View view = layoutInflater.inflate(R.layout.item_vp_banner_home_page,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_banner_hp);
        ImageLoader.load(context,mList.get(position).getDomain_img_url(),imageView);
        final  int curPos = position;
        if (null != onPageClickListener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPageClickListener.onPageClick(view,curPos);
                }
            });
        }else{
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LoadWebUrlActivity.class);
                    intent.putExtra("url",mList.get(curPos).getAdvertising_url());
                    context.startActivity(intent);
                }
            });
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
