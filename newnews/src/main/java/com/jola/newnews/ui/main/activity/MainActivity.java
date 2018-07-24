package com.jola.newnews.ui.main.activity;

import android.support.v7.widget.Toolbar;

import com.jola.newnews.R;
import com.jola.newnews.base.BaseActivity;
import com.jola.newnews.contract.main.IMainContract;
import com.jola.newnews.presenter.main.MainPresenter;
import com.jola.newnews.util.LogUtil;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/7/20.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements IMainContract.IMainView{


    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {getActivityComponent().inject(this);}

    @Override
    protected void initEventAndData() {
//        LogUtil.logInteresting("MainActivity presenter inited :"+mPresenter.toString());
        setToolbar(mToolbar,"nfc手持机");
    }

    @Override
    public void showUpdateDialog(String updateInfo) {

    }


}
