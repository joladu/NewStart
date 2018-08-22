package com.jola.onlineedu.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jola.onlineedu.R;
import com.jola.onlineedu.app.App;
import com.jola.onlineedu.di.component.ActivityComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2018/8/10.
 * 使用butterknife ,方法抽象化，统一管理activity
 */

public abstract class SimpleActivity extends AppCompatActivity {

    private Unbinder mUnbind;
    protected Activity mContext;
    private CompositeDisposable mCompositeDisposable;

    protected abstract int getLayout();

    protected  void onViewCreated(){};

    protected abstract void initEventAndData();

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

    protected void setToolBar(Toolbar toolBar, String title){
//        setStatusBarColor();
        ((TextView)toolBar.findViewById(R.id.title_toolbar_tv)).setText(title);
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null){
            //        不显示默认的title
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
//                Toast.makeText(SimpleActivity.this,"toolBar.setNavigationOnClickListener",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 设置状态栏颜色
     */
    protected void setStatusBarColor(){
//        5.x系列可以设置状态栏颜色   >=21
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

//            Window window = activity.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(activity.getResources().getColor(colorResId));
            //底部导航栏
            //window.setNavigationBarColor(activity.getResources().getColor(colorResId));

            Window window = getWindow();
            //取消状态栏透明
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //添加Flag把状态栏设为可绘制模式
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            //设置系统状态栏处于可见状态
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            //让view不根据系统窗口来调整自己的布局
            ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, false);
                ViewCompat.requestApplyInsets(mChildView);
            }
        }
//        4.4-5.0方法：还没有API可以直接修改状态栏颜色，所以必须先将状态栏设置为透明，然后在布局中添加一个背景为期望色值的View来作为状态栏的填充。
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){

        }

    }








    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivy(this);
        mUnbind.unbind();
    }
}
