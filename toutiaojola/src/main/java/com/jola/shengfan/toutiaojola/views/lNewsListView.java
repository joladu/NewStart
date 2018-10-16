package com.jola.shengfan.toutiaojola.views;


import com.jola.shengfan.toutiaojola.mode.entity.News;

import java.util.List;


public interface lNewsListView {

    void onGetNewsListSuccess(List<News> newList, String tipInfo);

    void  onError();
}
