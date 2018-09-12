package com.jola.onlineedu.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jola.onlineedu.app.App;
import com.jola.onlineedu.di.component.DaggerFragmentComponent;
import com.jola.onlineedu.di.component.FragmentComponent;
import com.jola.onlineedu.di.module.FragmentModule;
import com.jola.onlineedu.widget.DialogLoadingView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by lenovo on 2018/8/27.
 */

public abstract class SimpleFragment extends SupportFragment {

    protected Activity mActivity;
    protected Context mContext;
//    private View mView;
    private Unbinder mUnbinder;

    private boolean isInited = false;

    private Unbinder mUnbind;

    private CompositeDisposable mCompositeDisposable;
    DialogLoadingView dialogLoadingView;

    protected void showLoadingDialog(){
        if (null == dialogLoadingView){
            dialogLoadingView = new DialogLoadingView(mContext);
        }
        dialogLoadingView.show();
    }
    protected void hideLoadingDialog(){
        if (null != dialogLoadingView){
            dialogLoadingView.dismiss();
        }
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



    protected abstract int getLayoutId();
    protected abstract void initEventAndData();


    protected FragmentComponent getFragmentComponent(){
       return DaggerFragmentComponent.builder()
               .appComponent(App.getAppComponent())
               .fragmentModule(new FragmentModule(this)).build();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mContext = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        mView = inflater.inflate(getLayoutId(), null);
        return inflater.inflate(getLayoutId(), null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        isInited = true;
        initEventAndData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
