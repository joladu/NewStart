package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 * private int play_status = 1 playing   ; ！1 pause
 */

public class ResCourseCapterList {

    /**
     * count : 1
     * next : null
     * previous : null
     * results : [{"id":1,"name":"热力动能","author":"王花花","video_url":"http://yunketang.dev.attackt.com/media/12738271321.mp4","video_duration":"12\u201811","play_count":1,"share_count":1}]
     */

    private int count;
    private String next;
    private String previous;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 1
         * name : 热力动能
         * author : 王花花
         * video_url : http://yunketang.dev.attackt.com/media/12738271321.mp4
         * video_duration : 12‘11
         * play_count : 1
         * share_count : 1
         */

        private int id;
        private String name;
        private String author;
        private String video_url;
        private String video_duration;
        private int play_count;
        private int share_count;
        private int play_status;

        public int getPlay_status() {
            return play_status;
        }

        public void setPlay_status(int play_status) {
            this.play_status = play_status;
        }

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

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
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
}
