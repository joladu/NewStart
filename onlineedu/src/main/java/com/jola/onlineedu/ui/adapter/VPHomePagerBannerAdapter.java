package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jola.onlineedu.R;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.bean.response.ResBannerHomepage;
import com.jola.onlineedu.mode.bean.response.ResTeacherBannerBean;

import java.util.List;

/**
 * Created by jola on 2018/9/8.
 */

public class VPHomePagerBannerAdapter extends PagerAdapter {

    private  LayoutInflater layoutInflater;
    List<ResTeacherBannerBean.ResultsBean> mList;
    Context context;


    public VPHomePagerBannerAdapter(Context context,List<ResTeacherBannerBean.ResultsBean> mList) {
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
        View view = layoutInflater.inflate(R.layout.item_vp_banner_teacher,container,false);
        ImageView iv_teacher_head = (ImageView) view.findViewById(R.id.iv_teacher_head);

        TextView tv_teacher_name = (TextView) view.findViewById(R.id.tv_teacher_name);
//        TextView tv_teacher_profession = (TextView) view.findViewById(R.id.tv_teacher_profession);
        TextView tv_school = (TextView) view.findViewById(R.id.tv_school);
        TextView tv_describe_content = (TextView) view.findViewById(R.id.tv_describe_content);

        /**
         * id : 1
         * teacher_id : 1
         * summary : 新教师
         * teaching_courses : 语文 数学 英语
         * school :
         * name : 李了了
         * cover_url : http://yunketang.dev.attackt.com/media/cover_1539338871.jpg
         */
        ResTeacherBannerBean.ResultsBean resultsBean = mList.get(position);

        ImageLoader.load(context,resultsBean.getCover_url(),iv_teacher_head);
        tv_teacher_name.setText(resultsBean.getName());
        tv_school.setText(resultsBean.getTeaching_courses() +"  "+resultsBean.getSchool());
        tv_describe_content.setText(resultsBean.getSummary());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
