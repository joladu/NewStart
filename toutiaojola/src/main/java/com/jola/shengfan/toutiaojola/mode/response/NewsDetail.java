package com.jola.shengfan.toutiaojola.mode.response;

/**
 * Created by lenovo on 2018/10/10.
 */

public class NewsDetail {

    public String detail_source;
    public MediaUserBean media_user;
    public int publish_time;
    public String title;
    public String url;
    public boolean is_original;
    public boolean is_pgc_article;
    public String content;
    public String source;
    public int video_play_count;

    public static class MediaUserBean {
        /**
         * no_display_pgc_icon : false
         * avatar_url : http://p1.pstatp.com/thumb/411000674c8942528d2
         * id : 6347463786
         * screen_name : 发现世界
         */

        public boolean no_display_pgc_icon;
        public String avatar_url;
        public String id;
        public String screen_name;
    }
}
