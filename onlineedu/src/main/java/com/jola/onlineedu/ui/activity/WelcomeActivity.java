package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.nfc.Tag;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jola.onlineedu.R;
import com.jola.onlineedu.base.BaseActivity;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.contract.WelcomeContract;
import com.jola.onlineedu.mode.bean.WelcomeBean;
import com.jola.onlineedu.presenter.WelcomePresenter;
import com.kk.taurus.playerbase.log.PLog;

import butterknife.BindView;



public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {


    public static final String Tag = "WelcomeActivity";

    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;
    @BindView(R.id.tv_welcome_author)
    TextView tvWelcomeAuthor;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getWelcomeData();
    }

    @Override
    public void showContent(WelcomeBean welcomeBean) {
        PLog.eJola(Tag,"welcomeBean.getImg():"+welcomeBean.getImg());
        PLog.eJola(Tag,"welcomeBean.getText():"+welcomeBean.getText());
        ImageLoader.load(this, welcomeBean.getImg(), ivWelcomeBg);
//        ImageLoader.load(this,"https://cn.bing.com/images/search?view=detailV2&ccid=5a6ewJSx&id=DC24E2A30FD6F8A111C553B42FF4543546CF0143&thid=OIP.xdvEXcLFx6dbyWHWAXIjYwHaE7&mediaurl=http%3a%2f%2fimg.hkwb.net%2fatt%2fsite2%2f20160725%2fc5dbc45dc2c5c7a75bc961d601722363.jpg&exph=333&expw=500&q=%E5%8C%97%E6%96%B9%E5%A4%9A%E5%9C%B0%E8%BF%9B%E5%85%A5%E7%82%99%E7%83%A4%E6%A8%A1%E5%BC%8F&simid=608029296927181865&selectedIndex=17", ivWelcomeBg);
        ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        tvWelcomeAuthor.setText(welcomeBean.getText());
//        tvWelcomeAuthor.setText("jola 欢迎你！");
    }

    @Override
    public void jumpToMain() {
        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        Glide.clear(ivWelcomeBg);
        super.onDestroy();
    }
}
