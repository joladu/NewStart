package com.jola.onlineedu.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResLiveCourseDetail;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.widget.StarBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class LiveDetailActivity extends SimpleActivity {


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
    @BindView(R.id.tv_content_brief_teacher)
    TextView tv_content_brief_teacher;
    @BindView(R.id.iv_cover_live)
    ImageView iv_cover_live;

    @Override
    protected int getLayout() {
        return R.layout.activity_live_detail;
    }

    @Override
    protected void initEventAndData() {
        getActivityComponent().inject(this);
        changeFullScreen();
        id = getIntent().getIntExtra("id", 0);
        loadData();
    }

    private void loadData() {
        showLoadingDialog();
        addSubscribe(dataManager.getLiveCourseDetail(id+"")
        .compose(RxUtil.<ResLiveCourseDetail>rxSchedulerHelper())
                .subscribe(new Consumer<ResLiveCourseDetail>() {
                    @Override
                    public void accept(ResLiveCourseDetail resLiveCourseDetail) throws Exception {
                        String cover_url = resLiveCourseDetail.getCover_url();
                        ImageLoader.load(LiveDetailActivity.this,cover_url,iv_cover_live);
                        tv_title_live_item.setText(resLiveCourseDetail.getName());
                        star_bar_score.setStarMark(resLiveCourseDetail.getEvaluate());
                        tv_score_num.setText(resLiveCourseDetail.getEvaluate()+"");
                        tv_price_live_course.setText("￥"+resLiveCourseDetail.getPrice());
                        tv_content_brief.setText(resLiveCourseDetail.getIntro());
                        hideLoadingDialog();
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
    @OnClick({R.id.iv_back})
    public void doClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                this.finish();
                break;
        }
    }

}
