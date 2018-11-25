package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.jola.onlineedu.ui.activity.TeacherMasterActivity;
import com.jola.onlineedu.ui.activity.TeacherMasterDetailActivity;

import java.util.List;

/**
 * Created by jola on 2018/9/8.
 */

public class VPHomePagerBannerAdapter extends PagerAdapter {

    private  LayoutInflater layoutInflater;
    List<ResTeacherBannerBean.ResultsBean> mList;
    Context context;


    public VPHomePagerBannerAdapter(Context context, List<ResTeacherBannerBean.ResultsBean> mList) {
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
        View view = layoutInflater.inflate(R.layout.item_vp_banner_teacher,container,false);
        ImageView iv_teacher_head = (ImageView) view.findViewById(R.id.iv_teacher_head);

        TextView tv_teacher_name = (TextView) view.findViewById(R.id.tv_teacher_name);
//        TextView tv_teacher_profession = (TextView) view.findViewById(R.id.tv_teacher_profession);
        TextView tv_school = (TextView) view.findViewById(R.id.tv_school);
        TextView tv_describe_content = (TextView) view.findViewById(R.id.tv_describe_content);
        final ResTeacherBannerBean.ResultsBean resultsBean = mList.get(position);

        ImageLoader.load(context,resultsBean.getCover_url(),iv_teacher_head);
        tv_teacher_name.setText(resultsBean.getName());
        tv_school.setText(resultsBean.getTeaching_courses() +"  "+resultsBean.getSchool());
        tv_describe_content.setText(resultsBean.getSummary());

//        view.findViewById(R.id.iv_back_finish).setOnClickListener(clickListener);
        view.findViewById(R.id.tv_search_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.tv_search_detail:
                        int id = resultsBean.getId();
                        Intent intent = new Intent(context, TeacherMasterDetailActivity.class);
                        intent.putExtra("id",id);
                        context.startActivity(intent);
                        break;
                }
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
