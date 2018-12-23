package com.jola.onlineedu.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.banner.BannerPagerAdapter;
import com.jola.onlineedu.banner.BannerViewPager;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.bean.response.ResBannerHomepage;
import com.jola.onlineedu.mode.bean.response.ResCourseRecommendBean;
import com.jola.onlineedu.ui.activity.CourseDetailActivity;
import com.jola.onlineedu.widget.StarBar;
import com.luck.picture.lib.adapter.PictureImageGridAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jola on 2018/9/8.
 */

public class RVRecommendCourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    private LayoutInflater layoutInflater;


    List<ResCourseRecommendBean.ResultsBean> mList;


    private BannerPagerAdapter vpHomePagerBannerAdapter;
    private List<ResBannerHomepage> mBannerList;

    private View.OnClickListener clickListener;


    private static final int Type_Head = 1;
    private static final int Type_Body = 2;



    public RVRecommendCourseAdapter(Context context, List<ResCourseRecommendBean.ResultsBean> mList, List<ResBannerHomepage> resBannerHomepage , View.OnClickListener clickListener) {
        this.context = context;
        this.mList = mList;
        this.mBannerList = resBannerHomepage;
        this.clickListener = clickListener;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? Type_Head : Type_Body;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Type_Head) {
            return new ViewHolderHead(layoutInflater.inflate(R.layout.head_home_page_adapter, parent, false));
        } else {
            return new ViewHolderBody(layoutInflater.inflate(R.layout.item_rv_recommend_course, parent, false));
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderHead hoderHead = null;
        ViewHolderBody hoderBody = null;

        if (holder instanceof  ViewHolderHead) {
            hoderHead = (ViewHolderHead) holder;

            vpHomePagerBannerAdapter = new BannerPagerAdapter(context,mBannerList);
            hoderHead.vp_banner_home_page.setAdapter(vpHomePagerBannerAdapter,mBannerList.size());

            if (mBannerList != null && mBannerList.size() > 0){
                hoderHead.iv_holder_banner.setVisibility(View.INVISIBLE);
                hoderHead.vp_banner_home_page.setVisibility(View.VISIBLE);
            }else{
                hoderHead.iv_holder_banner.setVisibility(View.VISIBLE);
                hoderHead.vp_banner_home_page.setVisibility(View.INVISIBLE);
            }

            hoderHead.iv_excellent_course.setOnClickListener(clickListener);
            hoderHead.iv_forum.setOnClickListener(clickListener);
            hoderHead.iv_teacher_master.setOnClickListener(clickListener);
            hoderHead.iv_test_pool.setOnClickListener(clickListener);
            hoderHead.et_hint_search_view.setOnClickListener(clickListener);

        } else if (holder instanceof  ViewHolderBody){

            final ResCourseRecommendBean.ResultsBean resultsBean = mList.get(position - 1);

            hoderBody = (ViewHolderBody) holder;

            ImageLoader.load(context, resultsBean.getCover_url(), hoderBody.iv_course_cover);
            if (resultsBean.getPay_type() == 1) {
                hoderBody.tv_price.setText("￥" + resultsBean.getPrice());
//            hoderBody.tv_price.setVisibility(View.VISIBLE);
                hoderBody.tv_type_course.setText("付费");
            } else {
                hoderBody.tv_price.setText("免费");
//            hoderBody.tv_price.setVisibility(View.VISIBLE);
                hoderBody.tv_type_course.setText("免费");
            }
            hoderBody.tv_score_num.setText((float)resultsBean.getScore() + "");
            hoderBody.star_bar_score.setStarMark((float)resultsBean.getScore());
            hoderBody.tv_course_name.setText(resultsBean.getName());
            hoderBody.tv_author_course.setText("主讲：" + resultsBean.getAuthor());
            hoderBody.tv_persons_watched.setText(resultsBean.getSee_count() + "人看过");


            hoderBody.rl_parent_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CourseDetailActivity.class);
                    intent.putExtra("id", resultsBean.getId());
                    context.startActivity(intent);
                }
            });

        }


    }


    @Override
    public int getItemCount() {
        return mList == null ? 1: mList.size() + 1;
    }


    public class ViewHolderHead extends RecyclerView.ViewHolder {

        @BindView(R.id.et_hint_search_view)
        TextView et_hint_search_view;

        @BindView(R.id.vp_banner_home_page)
        BannerViewPager vp_banner_home_page;
        @BindView(R.id.iv_holder_banner)
        ImageView iv_holder_banner;

        @BindView(R.id.iv_excellent_course)
        ImageView iv_excellent_course;
        @BindView(R.id.iv_forum)
        ImageView iv_forum;
        @BindView(R.id.iv_teacher_master)
        ImageView iv_teacher_master;
        @BindView(R.id.iv_test_pool)
        ImageView iv_test_pool;




        public ViewHolderHead(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            et_hint_search_view.setHint("课程搜索");
        }

    }


    public class ViewHolderBody extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_parent_container)
        RelativeLayout rl_parent_container;
        @BindView(R.id.iv_course_cover)
        ImageView iv_course_cover;
        @BindView(R.id.tv_price)
        TextView tv_price;
        @BindView(R.id.tv_course_name)
        TextView tv_course_name;
        @BindView(R.id.tv_author_course)
        TextView tv_author_course;
        @BindView(R.id.tv_type_course)
        TextView tv_type_course;
        @BindView(R.id.star_bar_score)
        StarBar star_bar_score;
        @BindView(R.id.tv_score_num)
        TextView tv_score_num;
        @BindView(R.id.tv_persons_watched)
        TextView tv_persons_watched;


        public ViewHolderBody(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
