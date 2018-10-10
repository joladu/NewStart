package com.jola.shengfan.toutiaojola.mode.response;

import com.google.gson.Gson;
import com.jola.shengfan.toutiaojola.mode.entity.NewsData;
import com.jola.shengfan.toutiaojola.mode.entity.TipEntity;

import java.util.List;

/**
 * Created by lenovo on 2018/10/10.
 */

public class NewsResponse {
    public int login_status;
    public int total_number;
    public boolean has_more;
    public String post_content_hint;
    public int show_et_status;
    public int feed_flag;
    public int action_to_last_stick;
    public String message;
    public boolean has_more_to_refresh;
    public TipEntity tips;
    public List<NewsData> data;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
