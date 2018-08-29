package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jola.onlineedu.R;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jola on 2018/8/28.
 */

public class ForumListAdapter extends RecyclerView.Adapter<ForumListAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;
    private List<String> mList;


    public ForumListAdapter(Context context,List<String> mList) {
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
        holder.tv_forumContent.setText(position+":"+mList.get(position));
    }

    @Override
    public int getItemCount() {
        return null == mList ? 0 :mList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_forum_tag)
        TextView tv_forumTag;

        @BindView(R.id.tv_forum_content)
        TextView tv_forumContent;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
