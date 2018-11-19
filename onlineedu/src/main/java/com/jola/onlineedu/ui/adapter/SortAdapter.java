package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.bean.User;
import com.jola.onlineedu.mode.bean.response.ResFriendListBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SortAdapter extends BaseAdapter{

    private List<User> list = null;
    private Context mContext;

    public SortAdapter(Context mContext, List<User> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder;
        final User user = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_friend_sort, null);
            viewHolder.tv_friend_name = (TextView) view.findViewById(R.id.tv_friend_name);
            viewHolder.catalog = (TextView) view.findViewById(R.id.catalog);
            viewHolder.tv_friend_describe = (TextView) view.findViewById(R.id.tv_friend_describe);
            viewHolder.civ_head_user = (CircleImageView) view.findViewById(R.id.civ_head_user);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ImageLoader.load(mContext,user.getFriendsBean().getAvatar_url(),viewHolder.civ_head_user);

        //根据position获取首字母作为目录catalog
        String catalog = list.get(position).getFirstLetter();

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if(position == getPositionForSection(catalog)){
            viewHolder.catalog.setVisibility(View.VISIBLE);
            viewHolder.catalog.setText(user.getFirstLetter().toUpperCase());
        }else{
            viewHolder.catalog.setVisibility(View.GONE);
        }

        viewHolder.tv_friend_name.setText(this.list.get(position).getName());

        StringBuilder sb = new StringBuilder();
//        文学  民族学  北京交通大学海滨学院
        List<String> courses = user.getFriendsBean().getCourses();
        for (String tempCourse : courses){
            sb.append(tempCourse+" ");
        }
        sb.append(user.getFriendsBean().getSchool_name());
        viewHolder.tv_friend_describe.setText(sb.toString());

        return view;

    }

    final static class ViewHolder {
        CircleImageView civ_head_user;
        TextView catalog;
        TextView tv_friend_name;
        TextView tv_friend_describe;

    }

    /**
     * 获取catalog首次出现位置
     */
    public int getPositionForSection(String catalog) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getFirstLetter();
            if (catalog.equalsIgnoreCase(sortStr)) {
                return i;
            }
        }
        return -1;
    }

}