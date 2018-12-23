package com.jola.onlineedu.ui.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.jola.onlineedu.R;
import com.jola.onlineedu.app.App;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResCourseCapterList;
import com.jola.onlineedu.mode.bean.response.ResCourseDetail;
import com.jola.onlineedu.mode.bean.response.ResCouserCommentList;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.ui.adapter.CourseChapterListAdapter;
import com.jola.onlineedu.ui.adapter.CourseDetailCommentsAdapter;
import com.jola.onlineedu.ui.adapter.ForumListDetailAdapter;
import com.jola.onlineedu.ui.adapter.RelativeCourseAdapter;
import com.jola.onlineedu.util.DataUtils;
import com.jola.onlineedu.util.PUtil;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.jola.onlineedu.video.play.DataInter;
import com.jola.onlineedu.video.play.ReceiverGroupManager;
import com.jola.onlineedu.widget.StarBar;
import com.kk.taurus.playerbase.assist.InterEvent;
import com.kk.taurus.playerbase.assist.OnVideoViewEventHandler;
import com.kk.taurus.playerbase.entity.DataSource;
import com.kk.taurus.playerbase.event.OnPlayerEventListener;
import com.kk.taurus.playerbase.player.IPlayer;
import com.kk.taurus.playerbase.receiver.ReceiverGroup;
import com.kk.taurus.playerbase.render.AspectRatio;
import com.kk.taurus.playerbase.widget.BaseVideoView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CourseDetailActivity extends SimpleActivity implements OnPlayerEventListener {

    @Inject
    DataManager dataManager;

    private int id;
    private int page = 1;
    private int page_size = 10;


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

    @BindView(R.id.iv_heart_course)
    ImageView iv_heart_course;

    @BindView(R.id.iv_praise_course)
    ImageView iv_praise_course;

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
    @BindView(R.id.tv_commit_score)
    TextView tv_commit_score;
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

//    @BindView(R.id.group_brief_container)
//    Group group_brief_container;

    @BindView(R.id.cl_course_detail)
    ConstraintLayout cl_course_detail;

    @BindView(R.id.rv_course_chapters)
    RecyclerView rv_course_chapters;
    @BindView(R.id.fl_course_video)
    FrameLayout fl_course_video;
    @BindView(R.id.base_video_view)
    BaseVideoView mVideoView;


    private ReceiverGroup mReceiverGroup;
    private boolean userPause;
    private boolean isLandscape;
    private boolean hasStart;


    private List<ResCouserCommentList.ResultsBean> commentList;
    private CourseDetailCommentsAdapter adapter;
    private RelativeCourseAdapter mAdapterRelativeCourse;
    private int page_chapter = 1;
    private int PageSizeChapter = 10;
    private int mLastPlayingIndex  = 0;
    private List<ResCourseCapterList.ResultsBean> mChapterList;
    private CourseChapterListAdapter mCourseChapterListAdapter;


    @Override
    protected int getLayout() {
        return R.layout.activity_course_detail_new;
    }

    @Override
    protected void initEventAndData() {
        getActivityComponent().inject(this);

        //        changeFullScreen();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mReceiverGroup = ReceiverGroupManager.getInstance().getReceiverGroup(this);
        mReceiverGroup.getGroupValue().putBoolean(DataInter.Key.KEY_CONTROLLER_TOP_ENABLE, true);
        mVideoView.setReceiverGroup(mReceiverGroup);
        mVideoView.setEventHandler(onVideoViewEventHandler);
        mVideoView.setOnPlayerEventListener(this);


        id = getIntent().getIntExtra("id", 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        loadData();
        loadComments();
        smartRefreshLayout.setEnableRefresh(false);
//        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                loadMoreComments();
//            }
//        });

        loadCourseChaptersData();

        boolean hasFavoriteCourse = dataManager.hasFavoriteCourse(id);
        if (hasFavoriteCourse){
            iv_heart_course.setImageResource(R.drawable.icon_heart_selected_48);
        }

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

    @Override
    protected void onResume() {
        super.onResume();
        int state = mVideoView.getState();
        if (state == IPlayer.STATE_PLAYBACK_COMPLETE)
            return;
        if (mVideoView.isInPlaybackState()) {
            if (!userPause)
                mVideoView.resume();
        }
//        else{
//            mVideoView.rePlay(0);
//        }
        initPlay();
    }

    private void initPlay() {
        if (!hasStart) {
            mVideoView.setAspectRatio(AspectRatio.AspectRatio_MATCH_PARENT);
        }
    }

    private void startPlay(int position) {
        hideVideoView(true);
        if (mChapterList == null || mChapterList.size() == 0){
            ToastUtil.toastShort("该课程暂无相关章节信息，无法播放");
            return;
        }
        if (position >= mChapterList.size()){
            ToastUtil.toastShort("该课程无该章节信息，无法播放");
            return;
        }
//        test play address
        mVideoView.setDataSource(new DataSource(DataUtils.VIDEO_URL_02));
//        mVideoView.setDataSource(new DataSource(mChapterList.get(position).getVideo_url()));
        mVideoView.start();
        hasStart = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        int state = mVideoView.getState();
        if(state == IPlayer.STATE_PLAYBACK_COMPLETE)
            return;
        if(mVideoView.isInPlaybackState()){
            mVideoView.pause();
        }else{
            mVideoView.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mVideoView) {
            mVideoView.stopPlayback();
            mVideoView.stop();
        }
    }

    @Override
    public void onBackPressed() {
        if (isLandscape) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isLandscape = true;
            updateVideo(true);
        } else {
            isLandscape = false;
            updateVideo(false);
        }
        mReceiverGroup.getGroupValue().putBoolean(DataInter.Key.KEY_IS_LANDSCAPE, isLandscape);
    }

    private void updateVideo(boolean landscape) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mVideoView.getLayoutParams();
        if (landscape) {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.setMargins(0, 0, 0, 0);
        } else {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = PUtil.dip2px(this, 180);
            layoutParams.setMargins(0, 0, 0, 0);
        }
        mVideoView.setLayoutParams(layoutParams);
    }


    private OnVideoViewEventHandler onVideoViewEventHandler = new OnVideoViewEventHandler() {
        @Override
        public void onAssistHandle(BaseVideoView assist, int eventCode, Bundle bundle) {
            super.onAssistHandle(assist, eventCode, bundle);
            switch (eventCode) {
                case InterEvent.CODE_REQUEST_PAUSE:
                    userPause = true;
                    break;
                case DataInter.Event.EVENT_CODE_REQUEST_BACK:
                    if (isLandscape) {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    } else {
                        finish();
                    }
                    break;
                case DataInter.Event.EVENT_CODE_REQUEST_TOGGLE_SCREEN:
                    setRequestedOrientation(isLandscape ?
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT :
                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    break;
                case DataInter.Event.EVENT_CODE_ERROR_SHOW:
                    mVideoView.stop();
                    break;
            }
        }
    };

    @Override
    public void onPlayerEvent(int eventCode, Bundle bundle) {
        switch (eventCode) {
            case OnPlayerEventListener.PLAYER_EVENT_ON_VIDEO_RENDER_START:

                break;
        }
    }


    private void loadCourseChaptersData() {
        addSubscribe(dataManager.getCourseCapterList(id + "", page_chapter + "", PageSizeChapter + "")
                .compose(RxUtil.<ResCourseCapterList>rxSchedulerHelper())
                .subscribe(new Consumer<ResCourseCapterList>() {
                    @Override
                    public void accept(ResCourseCapterList resCourseCapterList) throws Exception {
                        mChapterList = resCourseCapterList.getResults();
//                        for testing data
//                        ResCourseCapterList.ResultsBean resultsBean = new ResCourseCapterList.ResultsBean();
//                        resultsBean.setPlay_status(-1);
//                        mChapterList.add(resultsBean);

                        mCourseChapterListAdapter = new CourseChapterListAdapter(mContext, mChapterList, new CourseChapterListAdapter.IPlayingListener() {
                            @Override
                            public void playPosition(int position) {
//                                ToastUtil.toastLong(mChapterList.get(position).getName() + " should be played");

                                startPlay(position);

                                mChapterList.get(mLastPlayingIndex).setPlay_status(-1);
                                mChapterList.get(position).setPlay_status(1);

                                mCourseChapterListAdapter.notifyDataSetChanged();

                                mLastPlayingIndex = position;
                            }
                        });
                        rv_course_chapters.setLayoutManager(new LinearLayoutManager(CourseDetailActivity.this,LinearLayoutManager.VERTICAL,false));
                        rv_course_chapters.addItemDecoration(new DividerItemDecoration(CourseDetailActivity.this,DividerItemDecoration.VERTICAL));
                        rv_course_chapters.setAdapter(mCourseChapterListAdapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtil.toastLong("获取章节信息失败！");
                    }
                })
        );
    }

    private void loadData() {
        showLoadingDialog();
        addSubscribe(dataManager.getCourseDetail(id + "")
                        .compose(RxUtil.<ResCourseDetail>rxSchedulerHelper())
                        .subscribe(new Consumer<ResCourseDetail>() {
                            @Override
                            public void accept(ResCourseDetail resCourseDetail) throws Exception {
                                hideLoadingDialog();
                                String cover = resCourseDetail.getCover_url();
                                ImageLoader.load(CourseDetailActivity.this, cover, iv_play_course);
                                tv_title_live_item.setText(resCourseDetail.getName());
//                        android:text="主讲：王仁杰  播放量：100"
                                tv_course_author.setText("主讲：" + resCourseDetail.getAuthor() + "  播放量：" + resCourseDetail.getSee_count());
                                tv_price_live_course.setText("￥" + resCourseDetail.getPrice());
                                tv_content_brief.setText(resCourseDetail.getSummary());
                                tv_share_num.setText(resCourseDetail.getShare_count() + "");
                                tv_praise_num.setText(resCourseDetail.getPraise_count() + "");
                                tv_heart_num.setText(resCourseDetail.getCollect_count() + "");

                                if (resCourseDetail.getHas_collected() == 1){
                                    iv_heart_course.setImageResource(R.drawable.icon_heart_selected_64);
                                }

                                if (resCourseDetail.getHas_praised() == 1){
                                    iv_praise_course.setImageResource(R.drawable.icon_praise_yes_64);
                                }


                                tv_score_num.setText(resCourseDetail.getScore() + "");
                                star_bar_score.setStarMark((float) resCourseDetail.getScore());

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

    private void loadComments() {
        page = 1;
        addSubscribe(dataManager.getCourseCommentList(id + "", page + "", page_size + "")
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

    private void loadMoreComments() {
        addSubscribe(dataManager.getCourseCommentList(id + "", (++page) + "", page_size + "")
                .compose(RxUtil.<ResCouserCommentList>rxSchedulerHelper())
                .subscribe(new Consumer<ResCouserCommentList>() {
                    @Override
                    public void accept(ResCouserCommentList resCouserCommentList) throws Exception {
                        List<ResCouserCommentList.ResultsBean> results = resCouserCommentList.getResults();
                        if (null == results || results.size() == 0) {
                            ToastUtil.toastShort("暂无更多评论");
                        } else {
                            commentList.addAll(results);
                        }
                        if (null != adapter) {
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

    public void changeFullScreen() {
        if (Build.VERSION.SDK_INT >= 21) {
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @OnClick({
            R.id.iv_back,
            R.id.tv_send_comment,
            R.id.tv_brief_title,
            R.id.tv_chapter_title,
            R.id.iv_play_video,

            R.id.iv_heart_course,
            R.id.iv_share_course,
            R.id.iv_praise_course,
    })
    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.iv_heart_course:
                collectCourse();
                break;
            case R.id.iv_share_course:
               ToastUtil.toastShort("暂无分享链接");
               break;
            case R.id.iv_praise_course:
//                ToastUtil.toastShort("暂无点赞接口");
                break;
            case R.id.iv_play_video:
                startPlay(0);
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_send_comment:
                confirmComment();
                break;
            case R.id.tv_brief_title:
                view_indication_chapter.setVisibility(View.INVISIBLE);
                view_indication_brief.setVisibility(View.VISIBLE);
                rv_course_chapters.setVisibility(View.INVISIBLE);

//                group_brief_container.setVisibility(View.VISIBLE);
                cl_course_detail.setVisibility(View.VISIBLE);

                break;
            case R.id.tv_chapter_title:
                view_indication_brief.setVisibility(View.INVISIBLE);
                view_indication_chapter.setVisibility(View.VISIBLE);

//                group_brief_container.setVisibility(View.INVISIBLE);
                cl_course_detail.setVisibility(View.INVISIBLE);

                rv_course_chapters.setVisibility(View.VISIBLE);
                if (null == mChapterList || mChapterList.size() == 0) {
                    ToastUtil.toastShort("暂无章节相关内容！");
                }
                break;
        }
    }

    private void collectCourse() {
        showLoadingDialog();
        dataManager.favoriteCourse(dataManager.getUserToken(),id)
                .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseSimpleResult>() {
                    @Override
                    public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
                        hideLoadingDialog();
                        if (responseSimpleResult.getError_code() == 0){
                            iv_heart_course.setImageResource(R.drawable.icon_heart_selected_48);
                            ToastUtil.toastShort("收藏成功！");
                            dataManager.favoriteCourse(id);
                        }else{
                            ToastUtil.toastShort(responseSimpleResult.getError_msg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        tipServerError();
                    }
                });
    }

    private void hideVideoView(boolean hideOrNo){
        if (hideOrNo){
            fl_course_video.setVisibility(View.INVISIBLE);
            mVideoView.setVisibility(View.VISIBLE);
        }else{
            fl_course_video.setVisibility(View.VISIBLE);
            mVideoView.setVisibility(View.INVISIBLE);
        }
    }

    private void confirmComment() {
        String commentContent = et_input_comment.getText().toString();
        if (commentContent.length() == 0) {
            ToastUtil.toastShort("请输入评价内容！");
        }
        String userId = dataManager.getUserId();
        showLoadingDialog();


//        addSubscribe(dataManager.publishCourseComment(dataManager.getUserToken(),id, userId, commentContent)
//                .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
//                .subscribe(new Consumer<ResponseSimpleResult>() {
//                    @Override
//                    public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
//                        hideLoadingDialog();
//                        if (responseSimpleResult.getError_code() == 0) {
//                            ToastUtil.toastShort("评论成功！");
//                        } else {
//                            ToastUtil.toastLong(responseSimpleResult.getError_msg());
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        hideLoadingDialog();
//                        tipServerError();
//                    }
//                })
//        );

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("course",RequestBody.create(MediaType.parse("text/plain"),id+""));
        map.put("user",RequestBody.create(MediaType.parse("text/plain"),userId+""));
        map.put("content",RequestBody.create(MediaType.parse("text/plain"),commentContent+""));

        addSubscribe(dataManager.publishCourseComment(dataManager.getUserToken(),map)
                .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseSimpleResult>() {
                    @Override
                    public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
                        hideLoadingDialog();
                        if (responseSimpleResult.getError_code() == 0) {
                            et_input_comment.setText("");
                            ToastUtil.toastShort("评论成功！");
                        } else {
                            ToastUtil.toastLong(responseSimpleResult.getError_msg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        tipServerError();
                    }
                })
        );


//        RequestParams requestParams = new RequestParams();
//        requestParams.put("course",id);
//        requestParams.put("user",userId);
//        requestParams.put("course",commentContent);
//        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION,dataManager.getUserToken());
//        App.getmAsyncHttpClient().post("http://yunketang.dev.attackt.com/api/v1/coursecomment/create/", requestParams, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                ResponseSimpleResult responseSimpleResult = new Gson().fromJson(new String(responseBody), ResponseSimpleResult.class);
//                hideLoadingDialog();
//                if (responseSimpleResult.getError_code() == 0) {
//                    ToastUtil.toastShort("评论成功！");
//                } else {
//                    ToastUtil.toastLong(responseSimpleResult.getError_msg());
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                hideLoadingDialog();
//                tipServerError();
//            }
//        });


    }

}














