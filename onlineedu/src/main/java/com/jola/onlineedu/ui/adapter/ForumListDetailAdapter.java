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
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResForumComments;
import com.jola.onlineedu.mode.bean.response.ResForumDetailBean;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;

/**
 * Created by jola on 2018/8/28.
 */

public class ForumListDetailAdapter extends RecyclerView.Adapter<ForumListDetailAdapter.ViewHolder> {


    DataManager dataManager;
    private LayoutInflater inflater;
    private Context mContext;
    private List<ResForumComments.DataBean.CommentsBean> mList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }


    public ForumListDetailAdapter(Context context, List<ResForumComments.DataBean.CommentsBean> mList, DataManager dataManager) {
        this.mContext = context;
        this.mList = mList;
        inflater = LayoutInflater.from(context);
        this.dataManager = dataManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_forum_list_detail,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        ResForumComments.DataBean.CommentsBean curBean = mList.get(position);
        holder.tv_name.setText(curBean.getUser().getUsername());
        holder.tv_forum_content.setText(curBean.getContent());
        holder.tv_num_praise.setText(curBean.getPraise_count());
        final int id = curBean.getId();

        holder.iv_handPraise.setImageDrawable(mContext.getResources().getDrawable(R.drawable.hand_praise_no_2x));
        holder.iv_handPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataManager.praiseComment(id+"")
                        .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
                        .subscribe(new Consumer<ResponseSimpleResult>() {
                            @Override
                            public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
                                if (0 == responseSimpleResult.getError_code()){
                                    holder.iv_handPraise.setImageDrawable(mContext.getResources().getDrawable(R.drawable.hand_praise_yes_2x));
                                    ToastUtil.toastShort("点赞成功!");
                                }else{
                                    ToastUtil.toastShort(responseSimpleResult.getError_msg());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                ToastUtil.toastShort(mContext.getString(R.string.error_server_message));
                            }
                        });

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
