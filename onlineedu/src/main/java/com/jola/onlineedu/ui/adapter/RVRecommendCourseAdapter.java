package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jola.onlineedu.R;
import com.jola.onlineedu.mode.bean.response.ResCourseList;
import com.jola.onlineedu.ui.activity.CourseDetailActivity;
import com.jola.onlineedu.ui.activity.LiveDetailActivity;
import com.jola.onlineedu.widget.StarBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jola on 2018/9/8.
 */

public class RVRecommendCourseAdapter extends RecyclerView.Adapter <RVRecommendCourseAdapter.ViewHolder>{


    Context context;
    List<ResCourseList.ResultsBean> mList;
    LayoutInflater layoutInflater;

    public RVRecommendCourseAdapter(Context context, List<ResCourseList.ResultsBean> mList) {
        this.context = context;
        this.mList = mList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_rv_recommend_course,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ResCourseList.ResultsBean resultsBean = mList.get(position);
        Glide.with(context).load(resultsBean.getCover())
                .apply(new RequestOptions().placeholder(R.drawable.image_placeholder).error(R.drawable.image_placeholder_fail))
                .into(holder.iv_course_cover);
        if (resultsBean.getPay_type() == 1){
            holder.tv_price.setText("￥"+resultsBean.getPrice());
            holder.tv_price.setVisibility(View.VISIBLE);
            holder.tv_type_course.setText("付费");
        }else{
            holder.tv_price.setVisibility(View.INVISIBLE);
            holder.tv_type_course.setText("免费");
        }
        holder.tv_course_name.setText(resultsBean.getName());
        holder.tv_author_course.setText("主讲："+resultsBean.getAuthor());
        holder.tv_persons_watched.setText(resultsBean.getSee_count()+"人看过");



        holder.rl_parent_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseDetailActivity.class);
                intent.putExtra("id",resultsBean.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_parent_container)
        RelativeLayout rl_parent_container;
        @BindView(R.id.iv_course_cover)
        ImageView iv_course_cover;
        @BindView(R.id.tv_price)
        TextView tv_price;
        @BindView(R.id.tv_course_name)
        TextView tv_course_name;
        @BindView(R.id.tv_author_course)
        TextView tv_author_course;
        @BindView(R.id.tv_type_course)
        TextView tv_type_course;
        @BindView(R.id.star_bar_score)
        StarBar star_bar_score;
        @BindView(R.id.tv_score_num)
        TextView tv_score_num;
        @BindView(R.id.tv_persons_watched)
        TextView tv_persons_watched;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
