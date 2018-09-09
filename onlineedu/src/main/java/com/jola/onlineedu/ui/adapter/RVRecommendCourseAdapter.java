package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.widget.StarBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jola on 2018/9/8.
 */

public class RVRecommendCourseAdapter extends RecyclerView.Adapter <RVRecommendCourseAdapter.ViewHolder>{


    Context context;
    List<String> mList;
    LayoutInflater layoutInflater;

    public RVRecommendCourseAdapter(Context context, List<String> mList) {
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

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_course_cover)
        ImageView iv_course_cover;
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
