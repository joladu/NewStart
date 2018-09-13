package com.jola.onlineedu.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.app.App;
import com.jola.onlineedu.di.component.ActivityComponent;
import com.jola.onlineedu.di.component.DaggerActivityComponent;
import com.jola.onlineedu.di.module.ActivityModule;
import com.jola.onlineedu.util.ToastUtil;
import com.jola.onlineedu.widget.DialogLoadingView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by lenovo on 2018/8/10.
 * 使用butterknife ,方法抽象化，统一管理activity
 */

public abstract class SimpleActivity extends SupportActivity {

    private Unbinder mUnbind;
    protected Activity mContext;

    private CompositeDisposable mCompositeDisposable;
    DialogLoadingView dialogLoadingView;

    protected void showLoadingDialog(){
        if (null == dialogLoadingView){
            dialogLoadingView = new DialogLoadingView(this);
        }
        dialogLoadingView.show();
    }
    protected void hideLoadingDialog(){
        if (null != dialogLoadingView){
            dialogLoadingView.dismiss();
        }
    }

    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(App.getAppComponent())
                .build();
    }

    protected void setToolBar(Toolbar toolBar,String title){
        ((TextView)toolBar.findViewById(R.id.title_toolbar_tv)).setText(title);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void addSubscribe(Disposable disposable){
        if (null == mCompositeDisposable){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected void unSubscribe(){
        if (null != mCompositeDisposable){
            mCompositeDisposable.clear();
        }
    }
    protected void removeDisposable(Disposable disposable){
        if (null != mCompositeDisposable){
            mCompositeDisposable.remove(disposable);
        }
    }

    protected abstract int getLayout();

    protected  void onViewCreated(){};

    protected abstract void initEventAndData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnbind = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivy(this);
        mUnbind.unbind();
        unSubscribe();
    }

    protected void tipServerError(){
        ToastUtil.toastShort(getString(R.string.error_server_message));
    }


//    protected void stateLoading(){}
//    protected void stateEmpty(){}
//    protected void stateError(){}
//    protected void stateMain(){}

}
