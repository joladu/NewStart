package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.bean.response.ResForumListByTypeBean;
import com.jola.onlineedu.ui.activity.ForumDetailActivity;
import com.jola.onlineedu.util.TimeFormatUtil;

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
        final int is_hot = curBean.getIs_hot();
//        final int is_top = curBean.getIs_top();
//        is_top ：最新
        final int is_top = curBean.getIs_new();
        int count = is_essence + is_hot + is_top;

//        Log.e("jola_forum","bean id :"+curBean.getId() + " is_essence:"+is_essence+ " is_hot:"+is_hot+ " is_top:"+is_top + "  count: "+count);

        if (count == 0){
            holder.tv_forum_is_essence.setVisibility(View.INVISIBLE);
            holder.tv_forum_is_hot.setVisibility(View.INVISIBLE);
            holder.tv_forum_is_new.setVisibility(View.INVISIBLE);
        }else if (count == 1){
            if (is_essence == 1){
                holder.tv_forum_is_essence.setText("精华");
            }else if (is_top == 1){
                holder.tv_forum_is_essence.setText("最新");
            }else if (is_hot == 1){
                holder.tv_forum_is_essence.setText("热门");
            }
            holder.tv_forum_is_essence.setVisibility(View.VISIBLE);
            holder.tv_forum_is_hot.setVisibility(View.INVISIBLE);
            holder.tv_forum_is_new.setVisibility(View.INVISIBLE);
        }else if (count == 2){
            if (is_essence == 0){
                holder.tv_forum_is_essence.setText("最新");
                holder.tv_forum_is_hot.setText("热门");
            }else if (is_top == 0){
                holder.tv_forum_is_essence.setText("精华");
                holder.tv_forum_is_hot.setText("热门");
            }else if (is_hot == 0){
                holder.tv_forum_is_essence.setText("最新");
                holder.tv_forum_is_hot.setText("精华");
            }
            holder.tv_forum_is_essence.setVisibility(View.VISIBLE);
            holder.tv_forum_is_hot.setVisibility(View.VISIBLE);
            holder.tv_forum_is_new.setVisibility(View.INVISIBLE);
        }else {
            holder.tv_forum_is_essence.setText("精华");
            holder.tv_forum_is_hot.setText("热门");
            holder.tv_forum_is_new.setText("最新");

            holder.tv_forum_is_essence.setVisibility(View.VISIBLE);
            holder.tv_forum_is_hot.setVisibility(View.VISIBLE);
            holder.tv_forum_is_new.setVisibility(View.VISIBLE);
        }


        ImageLoader.load(mContext,curBean.getUser().getAvatar_url(),holder.ci_head_img);

        holder.tv_forumContent.setText(curBean.getTitle());

//        版块：提问  楼主：小华 30条评论  40分钟前
        final String describeContent = "板块:"+curBean.getPost_type().getName()+"  楼主: "+curBean.getUser().getUsername()+"  "+curBean.getComment_count()+"条评论  "+ TimeFormatUtil.formatTime(curBean.getCreated());
        holder.tv_type_author.setText("板块:"+curBean.getPost_type().getName()+"  楼主:"+curBean.getUser().getUsername());
        holder.tv_comments_num.setText(curBean.getComment_count()+"条评论");
        holder.tv_time.setText(TimeFormatUtil.formatTimeSSS(curBean.getCreated()));


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ForumDetailActivity.class);
                intent.putExtra("id",curBean.getId());
                intent.putExtra("is_essence",is_essence);
                intent.putExtra("is_hot",is_hot);
                intent.putExtra("is_new",is_top);
                intent.putExtra("author",curBean.getUser().getUsername());
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

        @BindView(R.id.tv_forum_is_essence)
        TextView tv_forum_is_essence;
        @BindView(R.id.tv_forum_is_hot)
        TextView tv_forum_is_hot;
        @BindView(R.id.tv_forum_is_new)
        TextView tv_forum_is_new;


        @BindView(R.id.tv_forum_content)
        TextView tv_forumContent;
        @BindView(R.id.ci_head_img)
        CircleImageView ci_head_img;

        @BindView(R.id.tv_type_author)
        TextView tv_type_author;
        @BindView(R.id.tv_comments_num)
        TextView tv_comments_num;
        @BindView(R.id.tv_time)
        TextView tv_time;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
