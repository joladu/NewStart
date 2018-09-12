package com.jola.onlineedu.component;

import com.jola.onlineedu.util.RxUtil;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by lenovo on 2018/8/29.
 */

public class RxBus {

    /**
     * 订阅：RxBus.getDefault.toFlowable(EventType.class) ；RxBus.getDefault.toDefaultFlowable(EventType.class,Consumer)
     * 发送：RxBus.getDefault.post(EventType.class)
     */
    private final FlowableProcessor<Object> bus;

    public RxBus() {
        bus = PublishProcessor.create().toSerialized();
    }

    public static class RxBusHolder{
        private static final RxBus rxBusInstance = new RxBus();
    }

    public static RxBus getDefault(){
        return RxBusHolder.rxBusInstance;
    }


    public void post(Object o){
        bus.onNext(o);
    }

    public <T>Flowable<T> toFlowable(Class<T> eventType){
        return bus.ofType(eventType);
    }

    public <T>Disposable toDefaultFlowable(Class<T> eventType, Consumer<T> consumer){
        return bus.ofType(eventType).compose(RxUtil.<T>rxSchedulerHelper()).subscribe(consumer);
    }



}
