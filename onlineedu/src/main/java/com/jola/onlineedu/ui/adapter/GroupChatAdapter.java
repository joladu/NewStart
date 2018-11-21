package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.bean.response.ResGroupChatBean;
import com.jola.onlineedu.util.TimeFormatUtil;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jola on 2018/11/21.
 */

public class GroupChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ResGroupChatBean.DataBean.ChatsBean> mList;
    LayoutInflater layoutInflater;

    public GroupChatAdapter(Context context, List<ResGroupChatBean.DataBean.ChatsBean> mList) {
        this.context = context;
        this.mList = mList;
        layoutInflater = LayoutInflater.from(context);
    }




    @Override
    public int getItemViewType(int position) {
//        1 : to; !1 :from
        return mList.get(position).getIs_my_chat();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1){
            return (RecyclerView.ViewHolder) new ViewHolderTo(layoutInflater.inflate(R.layout.item_group_chat_to, parent, false));
        }else{
            return (RecyclerView.ViewHolder) new ViewHolderFrom(layoutInflater.inflate(R.layout.item_group_chat_from, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ResGroupChatBean.DataBean.ChatsBean chatsBean = mList.get(position);
        int itemViewType = getItemViewType(position);
        if (itemViewType == 1){
            ViewHolderTo viewHolderTo = (ViewHolderTo) holder;
            ImageLoader.load(context,chatsBean.getAvatar_url(),viewHolderTo.civ_head_user);
            viewHolderTo.tv_name_to.setText(chatsBean.getUsername());
            viewHolderTo.tv_time.setText(TimeFormatUtil.formatTime(chatsBean.getCreated()));
            viewHolderTo.tv_content.setText(chatsBean.getContent());
        }else{
            ViewHolderFrom viewHolderFrom = (ViewHolderFrom) holder;
            ImageLoader.load(context,chatsBean.getAvatar_url(),viewHolderFrom.civ_head_user);
            viewHolderFrom.tv_name_from.setText(chatsBean.getUsername());
            viewHolderFrom.tv_time.setText(TimeFormatUtil.formatTime(chatsBean.getCreated()));
            viewHolderFrom.tv_content.setText(chatsBean.getContent());
        }
    }



    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }




    class ViewHolderFrom extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_head_user)
        CircleImageView civ_head_user;
        @BindView(R.id.tv_name_from)
        TextView tv_name_from;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.tv_content)
        TextView tv_content;


        public ViewHolderFrom(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    class ViewHolderTo extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_head_user)
        CircleImageView civ_head_user;
        @BindView(R.id.tv_name_to)
        TextView tv_name_to;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.tv_content)
        TextView tv_content;


        public ViewHolderTo(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }





}
