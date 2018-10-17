package com.jola.onlineedu.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResCourseDetail;
import com.jola.onlineedu.mode.bean.response.ResCouserCommentList;
import com.jola.onlineedu.ui.adapter.CourseDetailCommentsAdapter;
import com.jola.onlineedu.ui.adapter.ForumListDetailAdapter;
import com.jola.onlineedu.ui.adapter.RelativeCourseAdapter;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.jola.onlineedu.widget.StarBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class CourseDetailActivity extends SimpleActivity {

    @Inject
    DataManager dataManager;

    private int id;
    private int page = 1;
    private int page_size = 10;


//    tv_title_live_item,
//    tv_course_author,
//    star_bar_score,
//    tv_score_num,
//    tv_share_num,
//    tv_praise_num,
//    tv_heart_num,
//    tv_price_live_course,

//    view_divide_below_price,

//    tv_brief,


//    tv_content_brief,


//    view_divide_below_teacher,


//    tv_relative_course,

//    tv_comment_project,

//    ed_input_comment,

//    tv_send_comment,


//    smart_refresh_layout,
//    rv_relative_course,
//    et_input_comment


    @BindView(R.id.iv_play_course)
    ImageView iv_play_course;
    @BindView(R.id.tv_title_live_item)
    TextView tv_title_live_item;

    @BindView(R.id.tv_price_live_course)
    TextView tv_price_live_course;
    @BindView(R.id.tv_content_brief)
    TextView tv_content_brief;
    @BindView(R.id.tv_course_author)
    TextView tv_course_author;
    @BindView(R.id.tv_heart_num)
    TextView tv_heart_num;
    @BindView(R.id.tv_praise_num)
    TextView tv_praise_num;
    @BindView(R.id.tv_share_num)
    TextView tv_share_num;
    @BindView(R.id.tv_score_num)
    TextView tv_score_num;
    @BindView(R.id.star_bar_score)
    StarBar star_bar_score;
//    @BindView(R.id.tv_num_comments)
//    TextView tv_num_comments;

    @BindView(R.id.et_input_comment)
    EditText et_input_comment;
    @BindView(R.id.rv_relative_course)
    RecyclerView rv_relativeCourse;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.view_main)
    RecyclerView recyclerView;

    @BindView(R.id.view_indication_brief)
    View view_indication_brief;
    @BindView(R.id.view_indication_chapter)
    View view_indication_chapter;
    @BindView(R.id.group_brief_container)
    Group group_brief_container;
    @BindView(R.id.rv_course_chapters)
    RecyclerView rv_course_chapters;



    private List<ResCouserCommentList.ResultsBean> commentList;
    private CourseDetailCommentsAdapter adapter;
    private RelativeCourseAdapter mAdapterRelativeCourse;


    @Override
    protected int getLayout() {
        return R.layout.activity_course_detail;
    }

    @Override
    protected void initEventAndData() {
        getActivityComponent().inject(this);
        changeFullScreen();
        id = getIntent().getIntExtra("id", 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        loadData();
        loadComments();
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreComments();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadComments();
            }
        });
    }

    private void loadData() {
        showLoadingDialog();
        addSubscribe(dataManager.getCourseDetail(id+"")
            .compose(RxUtil.<ResCourseDetail>rxSchedulerHelper())
                .subscribe(new Consumer<ResCourseDetail>() {
                    @Override
                    public void accept(ResCourseDetail resCourseDetail) throws Exception {
                        hideLoadingDialog();
                        String cover = resCourseDetail.getCover_url();
                        if (null != cover && cover.length() > 0){
                            Glide.with(CourseDetailActivity.this)
                                    .load(cover)
                                    .apply(new RequestOptions().placeholder(R.drawable.image_placeholder).error(R.drawable.image_placeholder_fail))
                                    .into(iv_play_course);
                        }
                        tv_title_live_item.setText(resCourseDetail.getName());
//                        android:text="主讲：王仁杰  播放量：100"
                        tv_course_author.setText("主讲："+resCourseDetail.getAuthor()+"  播放量："+resCourseDetail.getSee_count());
                        tv_price_live_course.setText("￥"+resCourseDetail.getPrice());
                        tv_content_brief.setText(resCourseDetail.getSummary());
                        tv_share_num.setText(resCourseDetail.getShare_count()+"");
                        tv_praise_num.setText(resCourseDetail.getPraise_count()+"");
                        tv_heart_num.setText(resCourseDetail.getCollect_count()+"");
                        tv_score_num.setText(resCourseDetail.getScore()+"");
                        star_bar_score.setStarMark(resCourseDetail.getScore());

//                        加载相关课程
                        List<ResCourseDetail.ReleatedCoursesBean> releated_courses = resCourseDetail.getReleated_courses();
                        mAdapterRelativeCourse = new RelativeCourseAdapter(CourseDetailActivity.this, releated_courses);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CourseDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        rv_relativeCourse.setLayoutManager(linearLayoutManager);
                        rv_relativeCourse.setAdapter(mAdapterRelativeCourse);


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        tipServerError();
                    }
                })
        );
    }

    private void loadComments(){
        addSubscribe(dataManager.getCourseCommentList(id+"",page+"",page_size+"")
            .compose(RxUtil.<ResCouserCommentList>rxSchedulerHelper())
                .subscribe(new Consumer<ResCouserCommentList>() {
                    @Override
                    public void accept(ResCouserCommentList resCouserCommentList) throws Exception {
                        commentList = resCouserCommentList.getResults();
                        adapter = new CourseDetailCommentsAdapter(CourseDetailActivity.this, commentList, dataManager);
                        recyclerView.setAdapter(adapter);
                        smartRefreshLayout.finishRefresh();
//                        tv_num_comments.setText(commentList == null ? 0 : commentList.size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        smartRefreshLayout.finishRefresh();
                    }
                })
        );
    }

    private void loadMoreComments(){
        addSubscribe(dataManager.getCourseCommentList(id+"",(page++)+"",page_size+"")
                .compose(RxUtil.<ResCouserCommentList>rxSchedulerHelper())
                .subscribe(new Consumer<ResCouserCommentList>() {
                    @Override
                    public void accept(ResCouserCommentList resCouserCommentList) throws Exception {
                        commentList.addAll(resCouserCommentList.getResults());
                        if (null != adapter){
                            adapter.notifyDataSetChanged();
                        }
                        smartRefreshLayout.finishLoadMore();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        smartRefreshLayout.finishLoadMore();
                    }
                })
        );
    }

    public void changeFullScreen(){
        if (Build.VERSION.SDK_INT >= 21) {
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(option);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @OnClick({R.id.iv_back,R.id.tv_send_comment,R.id.tv_brief_title,R.id.tv_chapter_title})
    public void doClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.tv_send_comment:
                confirmComment();
                break;
            case R.id.tv_brief_title:
                view_indication_chapter.setVisibility(View.INVISIBLE);
                view_indication_brief.setVisibility(View.VISIBLE);
                rv_course_chapters.setVisibility(View.INVISIBLE);
                group_brief_container.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_chapter_title:
                view_indication_brief.setVisibility(View.INVISIBLE);
                view_indication_chapter.setVisibility(View.VISIBLE);
                group_brief_container.setVisibility(View.INVISIBLE);
                rv_course_chapters.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void confirmComment() {
        String commentContent = et_input_comment.getText().toString();
        if (commentContent.length() ==0){
            ToastUtil.toastShort("请输入评价内容！");
        }

        ToastUtil.toastShort("无评价接口！");

//        showLoadingDialog();
//        addSubscribe(dataManager.publishCourseComment());
    }

}
