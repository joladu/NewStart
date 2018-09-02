package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jola.onlineedu.R;
import com.jola.onlineedu.component.ImageLoader;

import java.util.List;

/**
 * Created by jola on 2018/9/1.
 */

public class ForumImagesGridViewAdapter extends BaseAdapter {


    Context context;
    List<String> mList;
    LayoutInflater layoutInflater;

    public ForumImagesGridViewAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_forum_image_gv, parent, false);
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.iv_forum_image_item);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        ImageLoader.
        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
    }

}
