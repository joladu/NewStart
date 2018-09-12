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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jola on 2018/8/28.
 */

public class ForumListDetailAdapter extends RecyclerView.Adapter<ForumListDetailAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;
    private List<String> mList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }


    public ForumListDetailAdapter(Context context, List<String> mList) {
        this.mContext = context;
        this.mList = mList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_forum_list_detail,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
//        holder.tv_forumContent.setText(position+":"+mList.get(position));
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mContext.startActivity(new Intent(mContext, ForumDetailActivity.class));
//            }
//        });
//        holder.relativeLayout.setOnClickListener();
        holder.iv_handPraise.setImageDrawable(mContext.getResources().getDrawable(R.drawable.hand_praise_no_2x));
        holder.iv_handPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.iv_handPraise.setImageDrawable(mContext.getResources().getDrawable(R.drawable.hand_praise_yes_2x));
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == mList ? 0 :mList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ci_head_img)
        CircleImageView circleImageView_head;
        @BindView(R.id.tv_commenter_name)
        TextView tv_name;
        @BindView(R.id.iv_praise_hand)
        ImageView iv_handPraise;
        @BindView(R.id.tv_num_praise)
        TextView tv_num_praise;
        @BindView(R.id.tv_forum_content)
        TextView tv_forum_content;
        @BindView(R.id.tv_comment_time_ago)
        TextView tv_comment_time_ago;
        @BindView(R.id.tv_recomment)
        TextView tv_recomment;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
