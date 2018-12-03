package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jola.onlineedu.R;
import com.jola.onlineedu.mode.bean.response.ResTeacherBannerBean;
import com.jola.onlineedu.mode.bean.response.ResTeacherList;
import com.jola.onlineedu.ui.activity.TeacherMasterDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jola on 2018/8/28.
 */

public class TeacherMasterListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;

    private List<ResTeacherList.ResultsBean> mList;

    private  List<ResTeacherBannerBean.ResultsBean> mBannerList;



    private View.OnClickListener clickListener;

    private static final int Type_Head = 1;
    private static final int Type_Body = 2;


    private EditText etInputContent;

    public String getInputSearchContent() {
        return etInputContent.getText().toString();
    }




    @Override
    public int getItemViewType(int position) {
        return position == 0 ? Type_Head : Type_Body;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 1: mList.size() + 1;
    }



    public TeacherMasterListAdapter(Context context, List<ResTeacherList.ResultsBean> mList, List<ResTeacherBannerBean.ResultsBean> bannerList, View.OnClickListener clickListener) {
        this.mContext = context;
        this.mList = mList;
        this.mBannerList = bannerList;
        this.clickListener = clickListener;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Type_Head) {
            return new ViewHolderHead(inflater.inflate(R.layout.head_teacher_master_recycle, parent, false));
        } else {
            return new ViewHolderBody(inflater.inflate(R.layout.item_teacher_master, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        ViewHolderHead viewHolderHead =null;
        ViewHolderBody viewHolderBody =null;

        if (holder instanceof ViewHolderHead) {

            viewHolderHead = (ViewHolderHead)holder;

            viewHolderHead.vp_banner_teacher.setAdapter(new VPHomePagerBannerAdapter(mContext, mBannerList));

            viewHolderHead.iv_back_finish.setOnClickListener(clickListener);
            viewHolderHead.iv_search.setOnClickListener(clickListener);
            etInputContent = viewHolderHead.et_input_search;

        } else {

            viewHolderBody = (ViewHolderBody)holder;


            final ResTeacherList.ResultsBean resultsBean = mList.get(position - 1);
            Glide.with(mContext)
                    .load(resultsBean.getAvatar())
                    .apply(new RequestOptions().placeholder(R.drawable.image_placeholder).error(R.drawable.image_placeholder_fail))
                    .into(viewHolderBody.civ_head_user);

            viewHolderBody.tv_teacher_name.setText(resultsBean.getUsername());
            viewHolderBody.tv_teacher_describe.setText(resultsBean.getTeaching_courses());
            viewHolderBody.tv_latest_course.setText(resultsBean.getTeaching_courses());

            viewHolderBody.rl_item_teacher.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, TeacherMasterDetailActivity.class);
                    intent.putExtra("id", resultsBean.getId());
                    mContext.startActivity(intent);
                }
            });
        }


    }



    public static class ViewHolderHead extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_back_finish)
        ImageView iv_back_finish;
        @BindView(R.id.vp_banner_teacher)
        ViewPager vp_banner_teacher;
        @BindView(R.id.et_input_search)
        EditText et_input_search;
        @BindView(R.id.iv_search)
        ImageView iv_search;

        public ViewHolderHead(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public static class ViewHolderBody extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_item_teacher)
        RelativeLayout rl_item_teacher;
        @BindView(R.id.civ_head_user)
        CircleImageView civ_head_user;
        @BindView(R.id.tv_teacher_name)
        TextView tv_teacher_name;
        @BindView(R.id.tv_teacher_describe)
        TextView tv_teacher_describe;
        @BindView(R.id.tv_latest_course)
        TextView tv_latest_course;


        public ViewHolderBody(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
