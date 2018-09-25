package com.jola.shengfan.skills.picture_in_picture;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RawRes;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.jola.shengfan.skills.R;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;


public class MovieView extends RelativeLayout {

    private SurfaceView mSurfaceView;
    private View mShade;
    private ImageButton mToggle;
    private ImageButton mFastForward;
    private ImageButton mFastRewind;
    private ImageButton mMinimize;


    private static final int FAST_FORWARD_REWIND_INTERVAL = 5000; // ms

    private static final int TIMEOUT_CONTROLS = 3000; // ms



    @RawRes private int mVideoResourceId;

    private MediaPlayer mMediaPlayer;

    private int mSavedCurrentPosition;

    private boolean mAdjustViewBounds;

    private String mTitle;


    public abstract static class MovieListener {
        public void onMovieStarted(){};
        public void onMovieStopped(){};
        public void onMovieMinimized(){};

    }

    public void setmMovieListener(MovieListener mMovieListener){
        this.mMovieListener = mMovieListener;
    }


    MovieListener mMovieListener;

    /** Handles timeout for media controls. */
    TimeoutHandler mTimeoutHandler;

    private static class TimeoutHandler extends Handler {

        static final int MESSAGE_HIDE_CONTROLS = 1;

        private final WeakReference<MovieView> mMovieViewRef;

        TimeoutHandler(MovieView view) {
            mMovieViewRef = new WeakReference<>(view);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_HIDE_CONTROLS:
                    MovieView movieView = mMovieViewRef.get();
                    if (movieView != null) {
                        movieView.hideControls();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }



    public MovieView(Context context) {
        this(context,null);
    }

    public MovieView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MovieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.BLACK);

        inflate(context, R.layout.view_movie,this);

        mSurfaceView = findViewById(R.id.surface);
        mShade = findViewById(R.id.shade);
        mToggle = findViewById(R.id.toggle);
        mFastForward = findViewById(R.id.fast_forward);
        mFastRewind = findViewById(R.id.fast_rewind);
        mMinimize = findViewById(R.id.minimize);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MovieView, defStyleAttr, R.style.Widget_PictureInPicture_MovieView);
        setVideoResourceId(attributes.getResourceId(R.styleable.MovieView_android_src,0));
        setAdjustBounds(attributes.getBoolean(R.styleable.MovieView_android_adjustViewBounds,false));
        setTitle(attributes.getString(R.styleable.MovieView_android_title));
        attributes.recycle();


        OnClickListener onClickListener = new OnClickListener() {

            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.surface:
                        toggleControls();
                        break;
                    case R.id.toggle:
                        toggle();
                        break;
                    case R.id.fast_forward:
                        fastForward();
                        break;
                    case R.id.fast_rewind:
                        fastRewind();
                        break;
                    case R.id.minimize:
                        if (mMovieListener != null) {
                            mMovieListener.onMovieMinimized();
                        }
                        break;
                }
                // Start or reset the timeout to hide controls
                if (mMediaPlayer != null) {
                    if (mTimeoutHandler == null) {
                        mTimeoutHandler = new TimeoutHandler(MovieView.this);
                    }
                    mTimeoutHandler.removeMessages(TimeoutHandler.MESSAGE_HIDE_CONTROLS);
                    if (mMediaPlayer.isPlaying()) {
                        mTimeoutHandler.sendEmptyMessageDelayed(
                                TimeoutHandler.MESSAGE_HIDE_CONTROLS, TIMEOUT_CONTROLS);
                    }
                }
            }
        };

        mSurfaceView.setOnClickListener(onClickListener);
        mToggle.setOnClickListener(onClickListener);
        mFastForward.setOnClickListener(onClickListener);
        mFastRewind.setOnClickListener(onClickListener);
        mMinimize.setOnClickListener(onClickListener);

        mSurfaceView.getHolder()
                .addCallback(new SurfaceHolder.Callback() {
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {
                        openVideo(holder.getSurface());
                    }

                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {
                        if (null != mMediaPlayer){
                            mSavedCurrentPosition = mMediaPlayer.getCurrentPosition();
                        }
                        closeVideo();
                    }
                });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (null != mMediaPlayer){
            int videoWidth = mMediaPlayer.getVideoWidth();
            int videoHeight = mMediaPlayer.getVideoHeight();
            if (videoHeight != 0 && videoWidth != 0){
                float heightWidthRatio = (float) videoHeight / videoWidth;
                int width = MeasureSpec.getSize(widthMeasureSpec);
                int widthMode = MeasureSpec.getMode(widthMeasureSpec);
                int height = MeasureSpec.getSize(heightMeasureSpec);
                int heightMode = MeasureSpec.getMode(heightMeasureSpec);
                if (mAdjustViewBounds){
                    if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY){
                        super.onMeasure(widthMeasureSpec,MeasureSpec.makeMeasureSpec((int)(width * heightMeasureSpec),MeasureSpec.EXACTLY));
                    }else if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY){
                        super.onMeasure(MeasureSpec.makeMeasureSpec((int)(height/heightWidthRatio),MeasureSpec.EXACTLY),heightMeasureSpec);
                    }else{
                        super.onMeasure(widthMeasureSpec,MeasureSpec.makeMeasureSpec((int)(width * heightWidthRatio),MeasureSpec.EXACTLY));
                    }
                }else{
                    float viewRation = (float)height / width;
                    if (heightWidthRatio > viewRation){
                        int padding = (int)((width - height / heightWidthRatio)/2);
                        setPadding(padding,0,padding,0);
                    }else{
                        int padding = (int) ((height - width / heightWidthRatio)/2);
                        setPadding(0,padding,0,padding);
                    }
                    super.onMeasure(widthMeasureSpec,heightMeasureSpec);
                }
                return;
            }
        }
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onDetachedFromWindow() {
        if (null != mTimeoutHandler){
            mTimeoutHandler.removeMessages(TimeoutHandler.MESSAGE_HIDE_CONTROLS);
            mTimeoutHandler = null;
        }
        super.onDetachedFromWindow();
    }

    public String getTitle() {
        return mTitle;
    }

    public int getVideoResourceId() {
        return mVideoResourceId;
    }

    void toggleControls() {
        if (mShade.getVisibility() == View.VISIBLE) {
            hideControls();
        } else {
            showControls();
        }
    }

    void toggle() {
        if (mMediaPlayer == null) {
            return;
        }
        if (mMediaPlayer.isPlaying()) {
            pause();
        } else {
            play();
        }
    }

    public void pause() {
        if (mMediaPlayer == null) {
            adjustToggleState();
            return;
        }
        mMediaPlayer.pause();
        adjustToggleState();
        setKeepScreenOn(false);
        if (mMovieListener != null) {
            mMovieListener.onMovieStopped();
        }
    }

    /** Fast-forward the video. */
    public void fastForward() {
        if (mMediaPlayer == null) {
            return;
        }
        mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition() + FAST_FORWARD_REWIND_INTERVAL);
    }

    /** Fast-rewind the video. */
    public void fastRewind() {
        if (mMediaPlayer == null) {
            return;
        }
        mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition() - FAST_FORWARD_REWIND_INTERVAL);
    }

    public void showControls() {
        TransitionManager.beginDelayedTransition(this);
        mShade.setVisibility(View.VISIBLE);
        mToggle.setVisibility(View.VISIBLE);
        mFastForward.setVisibility(View.VISIBLE);
        mFastRewind.setVisibility(View.VISIBLE);
        mMinimize.setVisibility(View.VISIBLE);
    }

    public void hideControls() {
        TransitionManager.beginDelayedTransition(this);
        mShade.setVisibility(View.INVISIBLE);
        mToggle.setVisibility(View.INVISIBLE);
        mFastForward.setVisibility(View.INVISIBLE);
        mFastRewind.setVisibility(View.INVISIBLE);
        mMinimize.setVisibility(View.INVISIBLE);
    }



    private void setTitle(String title) {
        mTitle = title;
    }

    private void setAdjustBounds(boolean adjustViewBounds) {
        if (mAdjustViewBounds == adjustViewBounds) {
            return;
        }
        mAdjustViewBounds = adjustViewBounds;
        if (adjustViewBounds) {
            setBackground(null);
        } else {
            setBackgroundColor(Color.BLACK);
        }
        requestLayout();
    }

    public void setVideoResourceId(@RawRes int id){
        if (id == mVideoResourceId){
            return;
        }
        mVideoResourceId = id;
        Surface surface = mSurfaceView.getHolder().getSurface();
        if (null != surface && surface.isValid()){
            closeVideo();
            openVideo(surface);
        }
    }

    private void openVideo(Surface surface) {
        if (mVideoResourceId == 0){
            return;
        }
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setSurface(surface);
        startVideo();
    }

    private void startVideo() {
        mMediaPlayer.reset();
        AssetFileDescriptor assetFileDescriptor = getResources().openRawResourceFd(mVideoResourceId);
        try {
            mMediaPlayer.setDataSource(assetFileDescriptor);
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    requestLayout();
                    if (mSavedCurrentPosition > 0){
                        mMediaPlayer.seekTo(mSavedCurrentPosition);
                        mSavedCurrentPosition = 0;
                    }else{
                        play();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (null == mMediaPlayer){
            return;
        }
        mMediaPlayer.start();
        adjustToggleState();
        setKeepScreenOn(true);
        if (null != mMovieListener){
            mMovieListener.onMovieStarted();
        }


    }

    public boolean isPlaying() {
        return mMediaPlayer != null && mMediaPlayer.isPlaying();
    }

    private void adjustToggleState() {
        if (null != mMediaPlayer && mMediaPlayer.isPlaying()){
            mToggle.setContentDescription(getResources().getString(R.string.pause));
            mToggle.setImageResource(R.drawable.ic_pause_64dp);
        }else{
            mToggle.setContentDescription(getResources().getString(R.string.play));
            mToggle.setImageResource(R.drawable.ic_play_arrow_64dp);
        }
    }

    private void closeVideo() {
        if (null != mMediaPlayer){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }


}
