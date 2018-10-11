package com.jola.shengfan.toutiaojola.api;


import com.jola.shengfan.toutiaojola.mode.entity.VideoModel;
import com.jola.shengfan.toutiaojola.mode.response.CommentResponse;
import com.jola.shengfan.toutiaojola.mode.response.NewsDetail;
import com.jola.shengfan.toutiaojola.mode.response.NewsResponse;
import com.jola.shengfan.toutiaojola.mode.response.ResultResponse;
import com.jola.shengfan.toutiaojola.mode.response.VideoPathResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by lenovo on 2018/10/10.
 */

public interface ApiService {

    public static final String BASE_SERVER_URL = "http://is.snssdk.com/";

    String GET_ARTICLE_LIST = "api/news/feed/v62/?refer=1&count=20&loc_mode=4&device_id=34960436458&iid=13136511752";
    String GET_COMMENT_LIST = "article/v2/tab_comments/";

    @GET(GET_ARTICLE_LIST)
    Flowable<NewsResponse> getNewsList(@Query("category") String category, @Query("min_behot_time") long lastTime, @Query("last_refresh_sub_entrance_interval") long currentTime);

    @GET
    Flowable<ResultResponse<NewsDetail>> getNewsDetail(@Url String url);

    @GET(GET_COMMENT_LIST)
    Flowable<CommentResponse> getComment(@Query("group_id") String groupId, @Query("item_id") String itemId, @Query("offset") String offset, @Query("count") String count);

    @GET
    Flowable<ResultResponse<VideoModel>> getVideoData(@Url String url);


    @Headers({
            "Content-Type:application/x-www-form-urlencoded; charset=UTF-8",
            "Cookie:PHPSESSIID=334267171504; _ga=GA1.2.646236375.1499951727; _gid=GA1.2.951962968.1507171739; Hm_lvt_e0a6a4397bcb500e807c5228d70253c8=1507174305;Hm_lpvt_e0a6a4397bcb500e807c5228d70253c8=1507174305; _gat=1",
            "Origin:http://toutiao.iiilab.com"

    })
    @POST("http://service.iiilab.com/video/toutiao")
    Flowable<VideoPathResponse> getVideoPath(@Query("link") String link, @Query("r") String r, @Query("s") String s);







}
