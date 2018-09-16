//package com.jola.onlineedu.ui.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;
//import com.jola.onlineedu.R;
//import com.jola.onlineedu.mode.bean.response.ResCourseList;
//import com.jola.onlineedu.mode.bean.response.ResLiveCourseList;
//import com.jola.onlineedu.ui.activity.CourseDetailActivity;
//import com.jola.onlineedu.widget.StarBar;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * Created by jola on 2018/8/28.
// */
//
//public class LiveCourseListAdapter extends RecyclerView.Adapter<LiveCourseListAdapter.ViewHolder> {
//
//
//    private LayoutInflater inflater;
//    private Context context;
//    private List<ResLiveCourseList.ResultsBean> list;
//    private OnItemClickListener onItemClickListener;
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
//        this.onItemClickListener = onItemClickListener;
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(int position, View view);
//    }
//
//
//    public LiveCourseListAdapter(Context context, List<ResLiveCourseList.ResultsBean> mList) {
//        this.context = context;
//        list = mList;
//        inflater = LayoutInflater.from(context);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new ViewHolder(inflater.inflate(R.layout.item_choosable_course,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
////        ResCourseList.ResultsBean resultsBean = list.get(position);
//        ResLiveCourseList.ResultsBean resultsBean = list.get(position);
//        final int id = resultsBean.getId();
//        String coverUrl = resultsBean.getCover();
//        Glide.with(context).load(coverUrl).apply(new RequestOptions().error(R.drawable.image_placeholder_fail).placeholder(R.drawable.image_placeholder)).into(holder.iv_course_cover);
//        if (resultsBean.getPay_type() == 1){
//            holder.tv_price_course.setText("￥"+resultsBean.getPrice());
//            holder.tv_price_course.setVisibility(View.VISIBLE);
//            holder.tv_type_course.setText("付费");
//        }else{
//            holder.tv_price_course.setVisibility(View.INVISIBLE);
//            holder.tv_type_course.setText("免费");
//        }
//        holder.tv_title_choosable_course.setText(resultsBean.getSummary());
//        holder.tv_persons_watched.setText(resultsBean.getSee_count()+"");
//
//        holder.iv_course_cover.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, CourseDetailActivity.class);
//                intent.putExtra("id",id);
//                context.startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return null == list ? 0 : list.size();
//    }
//
//
//    public static class ViewHolder extends RecyclerView.ViewHolder{
//
//
//        @BindView(R.id.iv_course_cover)
//        ImageView iv_course_cover;
//        @BindView(R.id.tv_price_course)
//        TextView tv_price_course;
//        @BindView(R.id.tv_title_choosable_course)
//        TextView tv_title_choosable_course;
//        @BindView(R.id.tv_type_course)
//        TextView tv_type_course;
//        @BindView(R.id.star_bar_score)
//        StarBar star_bar_score;
//        @BindView(R.id.tv_score_num)
//        TextView tv_score_num;
//        @BindView(R.id.tv_persons_watched)
//        TextView tv_persons_watched;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this,itemView);
//        }
//    }
//}
