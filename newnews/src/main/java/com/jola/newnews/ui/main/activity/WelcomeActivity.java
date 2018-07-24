package com.jola.newnews.ui.main.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.jola.newnews.R;
import com.jola.newnews.base.BaseActivity;
import com.jola.newnews.component.ImageLoader;
import com.jola.newnews.contract.main.IWelcomeContract;
import com.jola.newnews.mode.bean.WelcomeBean;
import com.jola.newnews.presenter.main.WelcomePresenter;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/7/17.
 */

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeContract.IWelcomeView{

    public static final String TAG = "WelcomeActivity";

    @BindView(R.id.iv_welcome_bg)
    ImageView mIv_welcomeBg;
    @BindView(R.id.tv_welcome_author)
    TextView mTv_welcomeText;

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
//        super.initEventAndData();
//        引入依赖注入框架 ；替代 new 方式创建对象,因为Presenter是都要用的，所以在BaseActivity中注入，其它继承都可以得到相应的Presenter
//        WelcomePresenter welcomePresenter = new WelcomePresenter();
//        welcomePresenter.attachView(this);
//        welcomePresenter.getWelcomeData();

        mPresenter.getWelcomeData();

    }

    @Override
    public void showContent(WelcomeBean welcomeBean) {
//        Log.e("jola","begin showContent");
        mIv_welcomeBg.setImageDrawable(getDrawable(R.drawable.rabbit));
//        ImageLoader.load(this, welcomeBean.getImg(), mIv_welcomeBg);
        mIv_welcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        mTv_welcomeText.setText(welcomeBean.getText());
//        Log.e("jola","showContent:"+welcomeBean.getImg()+welcomeBean.getText());
//        Log.e("jola","end showContent");
    }



    @Override
    public void jumpToMain() {
//        Log.e("jola","jumpToMain:");
        startActivity(new Intent(this,MainActivity.class));
    }



}
