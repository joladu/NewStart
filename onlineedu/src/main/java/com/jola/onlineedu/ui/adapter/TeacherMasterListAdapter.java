package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jola.onlineedu.R;
import com.jola.onlineedu.mode.bean.response.ResTeacherList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jola on 2018/8/28.
 */

public class TeacherMasterListAdapter extends RecyclerView.Adapter<TeacherMasterListAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;
    private List<ResTeacherList.ResultsBean> mList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }


    public TeacherMasterListAdapter(Context context, List<ResTeacherList.ResultsBean> mList) {
        this.mContext = context;
        this.mList = mList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_teacher_master,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ResTeacherList.ResultsBean resultsBean = mList.get(position);
        Glide.with(mContext)
                .load(resultsBean.getAvatar())
                .apply(new RequestOptions().placeholder(R.drawable.image_placeholder).error(R.drawable.image_placeholder_fail))
                .into(holder.civ_head_user);

        holder.tv_teacher_name.setText(resultsBean.getName());
        holder.tv_teacher_describe.setText(resultsBean.getTeaching_courses());
        holder.tv_latest_course.setText(resultsBean.getTeaching_courses());

//        holder.tv_titleTest.setText(position+":"+mList.get(position));
//        holder.tv_describeTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mContext.startActivity(new Intent(mContext, ForumDetailActivity.class));
//            }
//        });
//        holder.relativeLayout.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return null == mList ? 0 :mList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.civ_head_user)
        CircleImageView civ_head_user;
        @BindView(R.id.tv_teacher_name)
        TextView tv_teacher_name;
        @BindView(R.id.tv_teacher_describe)
        TextView tv_teacher_describe;
        @BindView(R.id.tv_latest_course)
        TextView tv_latest_course;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
