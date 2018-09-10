package com.jola.onlineedu.mode.bean.response;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResCourseCapterDetail {

    /**
     * id : 1
     * name : 机械原理
     * author : 韩彬
     * video : o_1cih68kuj1ol36ma1q6n5rp1ug49.flv
     * video_duration : 5′14″
     * play_count : 1
     * share_count : 1
     */

    private int id;
    private String name;
    private String author;
    private String video;
    private String video_duration;
    private int play_count;
    private int share_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(String video_duration) {
        this.video_duration = video_duration;
    }

    public int getPlay_count() {
        return play_count;
    }

    public void setPlay_count(int play_count) {
        this.play_count = play_count;
    }

    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int share_count) {
        this.share_count = share_count;
    }
}
