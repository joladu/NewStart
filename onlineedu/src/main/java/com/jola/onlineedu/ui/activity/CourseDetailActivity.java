package com.jola.onlineedu.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.jola.onlineedu.util.RxUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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

    @BindView(R.id.iv_play_course)
    ImageView iv_play_course;
    @BindView(R.id.tv_title_live_item)
    TextView tv_title_live_item;
    @BindView(R.id.tv_persons_watched)
    TextView tv_persons_watched;
    @BindView(R.id.tv_price_live_course)
    TextView tv_price_live_course;
    @BindView(R.id.tv_content_brief)
    TextView tv_content_brief;
    @BindView(R.id.tv_content_brief_teacher)
    TextView tv_content_brief_teacher;

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    private List<ResCouserCommentList.ResultsBean> commentList;
    private CourseDetailCommentsAdapter adapter;


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
    }

    private void loadData() {
        showLoadingDialog();
        addSubscribe(dataManager.getCourseDetail(id+"")
            .compose(RxUtil.<ResCourseDetail>rxSchedulerHelper())
                .subscribe(new Consumer<ResCourseDetail>() {
                    @Override
                    public void accept(ResCourseDetail resCourseDetail) throws Exception {
                        hideLoadingDialog();
                        String cover = resCourseDetail.getCover();
                        if (null != cover && cover.length() > 0){
                            Glide.with(CourseDetailActivity.this)
                                    .load(cover)
                                    .apply(new RequestOptions().placeholder(R.drawable.image_placeholder).error(R.drawable.image_placeholder_fail))
                                    .into(iv_play_course);
                        }
                        tv_title_live_item.setText(resCourseDetail.getName());
                        tv_persons_watched.setText(resCourseDetail.getSee_count() + "");
                        tv_price_live_course.setText(resCourseDetail.getPrice());
                        tv_content_brief.setText(resCourseDetail.getSummary());
                        tv_content_brief_teacher.setText(resCourseDetail.getAuthor());
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

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

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

    @OnClick({R.id.iv_back,R.id.tv_send_comment})
    public void doClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.tv_send_comment:
                confirmComment();
                break;
        }
    }

    private void confirmComment() {
//        showLoadingDialog();
//        addSubscribe(dataManager.com);
    }

}
