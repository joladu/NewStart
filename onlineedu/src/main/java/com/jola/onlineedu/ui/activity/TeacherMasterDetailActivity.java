package com.jola.onlineedu.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResTeacherCourseDetail;
import com.jola.onlineedu.util.RxUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;

public class TeacherMasterDetailActivity extends SimpleActivity {



    @Inject
    DataManager dataManager;

    @BindView(R.id.civ_head_user)
    CircleImageView civ_head_user;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_school)
    TextView tv_school;
    @BindView(R.id.tv_hot_value)
    TextView tv_hot_value;
    @BindView(R.id.tv_score)
    TextView tv_score;
    @BindView(R.id.tv_brief_content)
    TextView tv_brief_content;




    private int mId;

    @Override
    protected int getLayout() {
        return R.layout.activity_teacher_master_detail;
    }

    @Override
    protected void initEventAndData() {
        getActivityComponent().inject(this);
        mId = getIntent().getIntExtra("id", -1);

        changeFullScreen();

        loadDetail();

    }

    public void changeFullScreen(){
        if (Build.VERSION.SDK_INT >= 21) {
            int option = View.SYSTEM_UI_FLAG_VISIBLE;
            option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
    }

    private void loadDetail() {
        showLoadingDialog();
        dataManager.getTeacherCourseDetail(mId)
                .compose(RxUtil.<ResTeacherCourseDetail>rxSchedulerHelper())
                .subscribe(new Consumer<ResTeacherCourseDetail>() {
                    @Override
                    public void accept(ResTeacherCourseDetail resTeacherCourseDetail) throws Exception {
                        hideLoadingDialog();
                        ImageLoader.load(mContext,resTeacherCourseDetail.getAvatar(),civ_head_user);
                        tv_name.setText(resTeacherCourseDetail.getUsername());
                        tv_school.setText(resTeacherCourseDetail.getTeaching_courses());
                        tv_hot_value.setText(resTeacherCourseDetail.getHot()+"");
                        tv_score.setText(resTeacherCourseDetail.getScore()+"");
                        tv_brief_content.setText(resTeacherCourseDetail.getSummary());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        tipServerError();
                    }
                });
    }

    @OnClick({
            R.id.iv_back_finish,
    })
    public void clickEvent(View view){
        switch (view.getId()){
            case R.id.iv_back_finish:
                this.finish();
                break;
        }
    }


}
