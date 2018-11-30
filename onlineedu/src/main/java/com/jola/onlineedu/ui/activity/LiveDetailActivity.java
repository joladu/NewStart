package com.jola.onlineedu.ui.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResCourseDetail;
import com.jola.onlineedu.mode.bean.response.ResLiveCourseDetail;
import com.jola.onlineedu.ui.adapter.RVLiveCourseAdapter;
import com.jola.onlineedu.ui.adapter.RelativeCourseAdapter;
import com.jola.onlineedu.ui.adapter.RelativeLiveAdapter;
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

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;

public class LiveDetailActivity extends SimpleActivity implements OnPlayerEventListener{


    @Inject
    DataManager dataManager;

    private int id;

    @BindView(R.id.tv_title_live_item)
    TextView tv_title_live_item;
    @BindView(R.id.star_bar_score)
    StarBar star_bar_score;
    @BindView(R.id.tv_score_num)
    TextView tv_score_num;
    @BindView(R.id.tv_persons_watched)
    TextView tv_persons_watched;
    @BindView(R.id.tv_price_live_course)
    TextView tv_price_live_course;
    @BindView(R.id.tv_content_brief)
    TextView tv_content_brief;
    @BindView(R.id.civ_head_user)
    CircleImageView civ_head_user;
    @BindView(R.id.tv_content_brief_teacher)
    TextView tv_content_brief_teacher;
    @BindView(R.id.rv_relative_live)
    RecyclerView rv_relative_live;

    @BindView(R.id.base_video_view)
    BaseVideoView mVideoView;

    @BindView(R.id.iv_cover_live)
    ImageView iv_cover_live;
    @BindView(R.id.fl_live_image)
    FrameLayout fl_live_image;

    private ReceiverGroup mReceiverGroup;


    private boolean userPause;
    private boolean isLandscape;

    private boolean hasStart;


    @Override
    protected int getLayout() {
        return R.layout.activity_live_detail;
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
        loadData();
    }


    @Override
    protected void onResume() {
        super.onResume();
        int state = mVideoView.getState();
        if(state == IPlayer.STATE_PLAYBACK_COMPLETE)
            return;
        if(mVideoView.isInPlaybackState()){
            if(!userPause)
                mVideoView.resume();
        }
//        else{
//            mVideoView.rePlay(0);
//        }
        initPlay();
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
        if (null != mVideoView){
            mVideoView.stopPlayback();
            mVideoView.stop();
        }
    }


    private void initPlay(){
        if(!hasStart){
            mVideoView.setAspectRatio(AspectRatio.AspectRatio_MATCH_PARENT);
        }
    }

    private void startPlay(){
        mVideoView.setDataSource(new DataSource(DataUtils.VIDEO_URL_MY_01));
        mVideoView.start();
        hasStart = true;
    }

    private OnVideoViewEventHandler onVideoViewEventHandler = new OnVideoViewEventHandler(){
        @Override
        public void onAssistHandle(BaseVideoView assist, int eventCode, Bundle bundle) {
            super.onAssistHandle(assist, eventCode, bundle);
            switch (eventCode){
                case InterEvent.CODE_REQUEST_PAUSE:
                    userPause = true;
                    break;
                case DataInter.Event.EVENT_CODE_REQUEST_BACK:
                    if(isLandscape){
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }else{
                        finish();
                    }
                    break;
                case DataInter.Event.EVENT_CODE_REQUEST_TOGGLE_SCREEN:
                    setRequestedOrientation(isLandscape ?
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:
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
        switch (eventCode){
            case OnPlayerEventListener.PLAYER_EVENT_ON_VIDEO_RENDER_START:

                break;

            case OnPlayerEventListener.PLAYER_EVENT_ON_PLAY_COMPLETE:

                break;
        }
    }



    private void loadData() {
        showLoadingDialog();
        addSubscribe(dataManager.getLiveCourseDetail(id+"")
        .compose(RxUtil.<ResLiveCourseDetail>rxSchedulerHelper())
                .subscribe(new Consumer<ResLiveCourseDetail>() {
                    @Override
                    public void accept(ResLiveCourseDetail resLiveCourseDetail) throws Exception {
//                        Log.e("okhttp1",resLiveCourseDetail.toString());
                        hideLoadingDialog();
                        String cover_url = resLiveCourseDetail.getCover_url();
                        ImageLoader.load(LiveDetailActivity.this,cover_url,iv_cover_live);
                        String avatarUserHead = resLiveCourseDetail.getTeacher_profile().getAvatar();
                        ImageLoader.load(LiveDetailActivity.this,avatarUserHead,civ_head_user);
                        tv_title_live_item.setText(resLiveCourseDetail.getName());
                        star_bar_score.setStarMark(resLiveCourseDetail.getEvaluate());
                        tv_score_num.setText(resLiveCourseDetail.getEvaluate()+"");
                        tv_price_live_course.setText("￥"+resLiveCourseDetail.getPrice());
                        tv_content_brief.setText(resLiveCourseDetail.getIntro());
                        tv_content_brief_teacher.setText(resLiveCourseDetail.getTeacher_profile().getSummary());

                        List<ResLiveCourseDetail.ReleatedCoursesBean> releated_courses = resLiveCourseDetail.getReleated_courses();
                        RelativeLiveAdapter relativeLiveAdapter = new RelativeLiveAdapter(LiveDetailActivity.this, releated_courses);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LiveDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        rv_relative_live.setLayoutManager(linearLayoutManager);
                        rv_relative_live.setAdapter(relativeLiveAdapter);

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

    public void changeFullScreen(){
        if (Build.VERSION.SDK_INT >= 21) {
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(option);
                getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
    }
    @OnClick({R.id.iv_back,R.id.iv_play_icon})
    public void doClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
//                mVideoView.stop();
//                this.finish();
//                onBackPressedSupport();
                super.onBackPressed();
//                ToastUtil.toastShort("返回点击了");
                break;
            case R.id.iv_play_icon:
                fl_live_image.setVisibility(View.INVISIBLE);
                mVideoView.setVisibility(View.VISIBLE);
                startPlay();
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if(isLandscape){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            isLandscape = true;
            updateVideo(true);
        }else{
            isLandscape = false;
            updateVideo(false);
        }
        mReceiverGroup.getGroupValue().putBoolean(DataInter.Key.KEY_IS_LANDSCAPE, isLandscape);
    }

    private void updateVideo(boolean landscape){
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mVideoView.getLayoutParams();
        if(landscape){
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.setMargins(0, 0, 0, 0);
        }else{
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = PUtil.dip2px(this,180);
            layoutParams.setMargins(0, 0, 0, 0);
        }
        mVideoView.setLayoutParams(layoutParams);
    }




    private void replay(){
        mVideoView.setDataSource(new DataSource(DataUtils.VIDEO_URL_MY_01));
        mVideoView.start();
    }


}
