package com.jola.shengfan.toutiaojola.ui.presenter;

import com.google.gson.Gson;
import com.jola.shengfan.toutiaojola.mode.entity.News;
import com.jola.shengfan.toutiaojola.mode.entity.NewsData;
import com.jola.shengfan.toutiaojola.mode.response.NewsResponse;
import com.jola.shengfan.toutiaojola.ui.base.BasePresenter;
import com.jola.shengfan.toutiaojola.util.PreUtils;
import com.jola.shengfan.toutiaojola.views.lNewsListView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/10/16.
 */

public class NewsListPresenter extends BasePresenter<lNewsListView> {

    private long lastTime;


    public NewsListPresenter(lNewsListView view) {
        super(view);
    }

    public void getNewsList(String channelCode){
        lastTime = PreUtils.getLong(channelCode,0);//读取对应频道下最后一次刷新的时间戳
        if (lastTime == 0){
            //如果为空，则是从来没有刷新过，使用当前时间戳
            lastTime = System.currentTimeMillis() / 1000;
        }
        addSubscribe(apiService.getNewsList(channelCode,lastTime,System.currentTimeMillis()/1000)
//            .compose(new FlowableTransformer<NewsResponse, NewsResponse>() {
//                @Override
//                public Flowable<NewsResponse> apply(Flowable<NewsResponse> observable) {
//                    return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//                }
//            })
                .compose((observable) -> observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()))
                .subscribe(new Consumer<NewsResponse>() {
                    @Override
                    public void accept(NewsResponse response) throws Exception {

                        lastTime = System.currentTimeMillis() / 1000;
                        PreUtils.putLong(channelCode,lastTime);

                        List<NewsData> data = response.data;
                        List<News> newsList = new ArrayList<>();
                        if (null != data &&  data.size() > 0){
                            for (NewsData newsData : data) {
                                News news = new Gson().fromJson(newsData.content, News.class);
                                newsList.add(news);
                            }
                        }
                        mView.onGetNewsListSuccess(newsList,response.tips.display_info);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError();
                    }
                })
        );

    }

}
