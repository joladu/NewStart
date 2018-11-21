package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.bean.response.ResCourseDetail;
import com.jola.onlineedu.mode.bean.response.ResLiveCourseDetail;
import com.jola.onlineedu.ui.activity.CourseDetailActivity;
import com.jola.onlineedu.ui.activity.LiveDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jola on 2018/9/8.
 */

public class RelativeLiveAdapter extends RecyclerView.Adapter <RelativeLiveAdapter.ViewHolder>{


    Context context;
    List<ResLiveCourseDetail.ReleatedCoursesBean> mList;
    LayoutInflater layoutInflater;

    public RelativeLiveAdapter(Context context, List<ResLiveCourseDetail.ReleatedCoursesBean> mList) {
        this.context = context;
        this.mList = mList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_relative_course,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ResLiveCourseDetail.ReleatedCoursesBean releatedCoursesBean = mList.get(position);
        ImageLoader.load(context,releatedCoursesBean.getCover_url(),holder.iv_course_cover);
        holder.tv_title_course.setText(releatedCoursesBean.getName());
        holder.tv_brief_content.setText(releatedCoursesBean.getIntro());

        String price = releatedCoursesBean.getPrice();
        if (null == price || price.length() == 0){
            holder.tv_price_relative_course.setText("免费");
        }else{
            holder.tv_price_relative_course.setText("￥"+releatedCoursesBean.getPrice());
        }

        holder.fl_live_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LiveDetailActivity.class);
                intent.putExtra("id",releatedCoursesBean.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fl_live_image)
        FrameLayout fl_live_image;
        @BindView(R.id.iv_course_cover)
        ImageView iv_course_cover;
        @BindView(R.id.tv_price_relative_course)
        TextView tv_price_relative_course;
        @BindView(R.id.tv_title_course)
        TextView tv_title_course;
        @BindView(R.id.tv_brief_content)
        TextView tv_brief_content;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
