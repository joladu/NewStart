package com.jola.newnews.presenter.main;

import android.net.wifi.aware.SubscribeConfig;

import com.jola.newnews.base.RxPresenter;
import com.jola.newnews.component.RxBus;
import com.jola.newnews.contract.main.IMainContract;
import com.jola.newnews.mode.DataManage;
import com.jola.newnews.mode.bean.VersionBean;
import com.jola.newnews.mode.event.NightModeEvent;
import com.jola.newnews.mode.http.response.MyHttpResponse;
import com.jola.newnews.util.CommonUtil;
import com.jola.newnews.widget.CommonSubscribe;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by lenovo on 2018/7/20.
 */

public class MainPresenter extends RxPresenter<IMainContract.IMainView> implements IMainContract.IMainPresenter {


    private DataManage mDataManage;

    @Inject
    public MainPresenter(DataManage dataManage) {
        mDataManage = dataManage;
    }

    @Override
    public void attachView(IMainContract.IMainView view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getInstanceDefault().toFlowable(NightModeEvent.class)
                .compose(CommonUtil.<NightModeEvent>rxSchedulerHelper())
                .map(new Function<NightModeEvent, Boolean>() {
                    @Override
                    public Boolean apply(NightModeEvent nightModeEvent) throws Exception {
                        return nightModeEvent.getIsNightMode();
                    }
                })
                .subscribeWith(new CommonSubscribe<Boolean>(mView, "切换模式失败！") {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mView.useNightMode(aBoolean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        registerEvent();
                    }
                }));
    }


    @Override
    public void checkVersion(final String currentVersion) {
        addSubscribe(mDataManage.fetchVersionInfo()
                .compose(CommonUtil.<MyHttpResponse<VersionBean>>rxSchedulerHelper())
                .compose(CommonUtil.<VersionBean>handleMyResult())
                .filter(new Predicate<VersionBean>() {
                    @Override
                    public boolean test(@NonNull VersionBean versionBean) throws Exception {
                        return Integer.valueOf(currentVersion.replace(".", "")) < Integer.valueOf(versionBean.getCode().replace(".", ""));
                    }
                })
             .map(new Function<VersionBean, String>() {
                 @Override
                 public String apply(VersionBean bean) throws Exception {
                     StringBuilder content = new StringBuilder("版本号: v");
                     content.append(bean.getCode());
                     content.append("\r\n");
                     content.append("版本大小: ");
                     content.append(bean.getSize());
                     content.append("\r\n");
                     content.append("更新内容:\r\n");
                     content.append(bean.getDes().replace("\\r\\n","\r\n"));
                     return content.toString();
                 }
             })
                .subscribeWith(new CommonSubscribe<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        mView.showUpdateDialog(s);
                    }
                })
        );
    }













}
