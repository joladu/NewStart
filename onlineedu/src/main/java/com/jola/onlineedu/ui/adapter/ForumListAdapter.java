package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.mode.bean.response.ResForumListByTypeBean;
import com.jola.onlineedu.ui.activity.ForumDetailActivity;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jola on 2018/8/28.
 */

public class ForumListAdapter extends RecyclerView.Adapter<ForumListAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;
    private List<ResForumListByTypeBean.DataBean.PostsBean> mList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position,View view);
    }


    public ForumListAdapter(Context context,List<ResForumListByTypeBean.DataBean.PostsBean> mList) {
        this.mContext = context;
        this.mList = mList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_forum_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ResForumListByTypeBean.DataBean.PostsBean curBean = mList.get(position);
        final int is_essence = curBean.getIs_essence();
        if (is_essence == 1){
            holder.tv_forumTag.setText(mContext.getString(R.string.forum_essence));
            holder.tv_forumTag.setVisibility(View.VISIBLE);
        }else{
            holder.tv_forumTag.setVisibility(View.INVISIBLE);
        }

        holder.tv_forumContent.setText(curBean.getTitle());

//        版块：提问  楼主：小华 30条评论  40分钟前
        final String describeContent = "板块: "+curBean.getPost_type().getName()+"  楼主: "+curBean.getUser()+"  "+curBean.getComment_count()+"条评论  "+curBean.getCreated();
        holder.tv_describe_content.setText(describeContent);


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ForumDetailActivity.class);
                intent.putExtra("id",curBean.getId());
                intent.putExtra("is_essence",is_essence);
                intent.putExtra("author",curBean.getUser());
                intent.putExtra("describeContent",describeContent);
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return null == mList ? 0 :mList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.rl_forum_list_activity)
        RelativeLayout relativeLayout;

        @BindView(R.id.tv_forum_tag)
        TextView tv_forumTag;
        @BindView(R.id.tv_forum_content)
        TextView tv_forumContent;
        @BindView(R.id.ci_head_img)
        CircleImageView ci_head_img;
        @BindView(R.id.tv_describe_content)
        TextView tv_describe_content;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
