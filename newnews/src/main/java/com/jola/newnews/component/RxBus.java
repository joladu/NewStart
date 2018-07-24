package com.jola.newnews.component;


import com.jola.newnews.util.CommonUtil;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by lenovo on 2018/7/23.
 */

public class RxBus {

    private FlowableProcessor<Object> mBus;

    private RxBus() {
       mBus = PublishProcessor.create().toSerialized();
    }

    public static RxBus getInstanceDefault(){
        return RxBusInit.mRxBusInstance;
    }

    private static class RxBusInit{
        private static RxBus mRxBusInstance = new RxBus();
    }

    public void post(Object object){
        mBus.onNext(object);
        Flowable<RxBus> rxBusFlowable = mBus.ofType(RxBus.class);
    }


    /**
     * 根据主题返回被观察者
     */
    public <T> Flowable<T> toFlowable(Class<T> eventTypeClass){
        return mBus.ofType(eventTypeClass);
    }

    /**
     * 疯转默认订阅
     */
    public <T> Disposable toDefaultDisposable(Class<T> eventTypeClass, Consumer<T> consumer){
        return mBus
                .ofType(eventTypeClass)
                .compose(CommonUtil.<T>rxSchedulerHelper())
                .subscribe(consumer);
    }

}
