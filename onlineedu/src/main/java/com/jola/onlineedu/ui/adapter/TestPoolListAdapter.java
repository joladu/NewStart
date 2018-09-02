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
import com.jola.onlineedu.ui.activity.ForumDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jola on 2018/8/28.
 */

public class TestPoolListAdapter extends RecyclerView.Adapter<TestPoolListAdapter.ViewHolder> {


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


    public TestPoolListAdapter(Context context, List<String> mList) {
        this.mContext = context;
        this.mList = mList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_test_pool_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_titleTest.setText(position+":"+mList.get(position));
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


        @BindView(R.id.tv_title_test_pool_item)
        TextView tv_titleTest;
        @BindView(R.id.tv_describe_test_pool_item)
        TextView tv_describeTest;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
