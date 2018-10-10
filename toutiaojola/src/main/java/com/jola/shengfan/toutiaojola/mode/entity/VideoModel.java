package com.jola.shengfan.toutiaojola.mode.entity;

/**
 * Created by lenovo on 2018/10/10.
 */

public class VideoModel {
    public int status;
    public String user_id;
    public String video_id;
    public double video_duration;

    public VideoListBean video_list;

    public static class VideoListBean {
        public Video video_1;
        public Video video_2;
        public Video video_3;

    }

}
