package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.bean.response.ResLiveCourseList;
import com.jola.onlineedu.ui.activity.LiveDetailActivity;
import com.jola.onlineedu.widget.StarBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jola on 2018/9/8.
 */

public class RVLiveCourseAdapter extends RecyclerView.Adapter <RVLiveCourseAdapter.ViewHolder>{


    Context context;
    List<ResLiveCourseList.ResultsBean> mList;
    LayoutInflater layoutInflater;

    public RVLiveCourseAdapter(Context context, List<ResLiveCourseList.ResultsBean> mList) {
        this.context = context;
        this.mList = mList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_rv_live_course,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ResLiveCourseList.ResultsBean resultsBean = mList.get(position);
        ImageLoader.loadWhitPrefix(context,resultsBean.getCover_url(),holder.iv_course_cover);
        holder.tv_title_live.setText(resultsBean.getName());
        holder.tv_price_live_course.setText("￥"+resultsBean.getPrice());
        holder.tv_score_num.setText(resultsBean.getEvaluate()+"");
        holder.starBar.setStarMark(resultsBean.getEvaluate());
        holder.tv_persons_watched.setText(resultsBean.getHot()+"");
        holder.iv_course_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LiveDetailActivity.class);
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

        @BindView(R.id.iv_course_cover)
        ImageView iv_course_cover;
        @BindView(R.id.tv_title_live_item)
        TextView tv_title_live;
        @BindView(R.id.star_bar_score)
        StarBar starBar;
        @BindView(R.id.tv_score_num)
        TextView tv_score_num;
        @BindView(R.id.tv_persons_watched)
        TextView tv_persons_watched;
        @BindView(R.id.tv_price_live_course)
        TextView tv_price_live_course;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
