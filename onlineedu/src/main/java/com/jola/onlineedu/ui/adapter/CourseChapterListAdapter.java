package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.mode.bean.response.ResCourseCapterList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/10/18.
 */

public class CourseChapterListAdapter extends RecyclerView.Adapter<CourseChapterListAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<ResCourseCapterList.ResultsBean> mList;
    private int currentPlayingPosition = -1;
    private IPlayingListener iPlayingListener;

    public interface IPlayingListener{
        void playPosition(int position);
    }

    public CourseChapterListAdapter(Context mContext, List<ResCourseCapterList.ResultsBean> mList,IPlayingListener iPlayingListener) {
        this.mContext = mContext;
        this.mList = mList;
        this.iPlayingListener = iPlayingListener;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ViewHolder(layoutInflater.inflate(R.layout.item_course_capter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.rl_item_chapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPlayingListener.playPosition(holder.getAdapterPosition());
                currentPlayingPosition = holder.getAdapterPosition();
            }
        });
        ResCourseCapterList.ResultsBean resultsBean = mList.get(position);
        holder.tv_chapter_name.setText(resultsBean.getName());

//        if (resultsBean.getPlay_status() == 1){
//            holder.iv_play_icon.setVisibility(View.VISIBLE);
//        }else{
//            holder.iv_play_icon.setVisibility(View.INVISIBLE);
//        }

        if (currentPlayingPosition == position){
            holder.iv_play_icon.setVisibility(View.VISIBLE);
        }else{
            holder.iv_play_icon.setVisibility(View.INVISIBLE);
        }
        Log.e("okhttp123","position:"+position);
        Log.e("okhttp123","currentPlayingPosition:"+currentPlayingPosition);


    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.rl_item_chapter)
        RelativeLayout rl_item_chapter;
        @BindView(R.id.tv_chapter_name)
        TextView tv_chapter_name;
        @BindView(R.id.iv_play_icon)
        ImageView iv_play_icon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
