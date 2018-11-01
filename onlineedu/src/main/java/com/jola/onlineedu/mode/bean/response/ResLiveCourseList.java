package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResLiveCourseList {


    /**
     * count : 2
     * next : null
     * previous : null
     * results : [{"id":1,"user":1,"name":"物理课程直播","cover_url":"/media/cover_1539338864.jpg","see_count":1,"speaker":"李先生","hot":0,"evaluate":0,"price":"99.0","duration":"","menu":"1、绪论\r\n2、正文\r\n3、总结","intro":"直播天体运动","categories":[{"id":2,"name":"物理"}]},{"id":2,"user":1,"name":"直播操作系统课程","cover_url":"/media/cover_1539338871.jpg","see_count":1,"speaker":"张老师","hot":0,"evaluate":0,"price":"58.0","duration":"","menu":"1、绪论\r\n2、正文\r\n3、总结","intro":"计算机操作系统概论","categories":[{"id":3,"name":"计算机"}]}]
     */

    private int count;
    private Object next;
    private Object previous;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
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
         * user : 1
         * name : 物理课程直播
         * cover_url : /media/cover_1539338864.jpg
         * see_count : 1
         * speaker : 李先生
         * hot : 0
         * evaluate : 0
         * price : 99.0
         * duration :
         * menu : 1、绪论
         2、正文
         3、总结
         * intro : 直播天体运动
         * categories : [{"id":2,"name":"物理"}]
         */

        private int id;
        private int user;
        private String name;
        private String cover_url;
        private int see_count;
        private String speaker;
        private int hot;
        private int evaluate;
        private String price;
        private String duration;
        private String menu;
        private String intro;
        private List<CategoriesBean> categories;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser() {
            return user;
        }

        public void setUser(int user) {
            this.user = user;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public int getSee_count() {
            return see_count;
        }

        public void setSee_count(int see_count) {
            this.see_count = see_count;
        }

        public String getSpeaker() {
            return speaker;
        }

        public void setSpeaker(String speaker) {
            this.speaker = speaker;
        }

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public int getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(int evaluate) {
            this.evaluate = evaluate;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getMenu() {
            return menu;
        }

        public void setMenu(String menu) {
            this.menu = menu;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class CategoriesBean {
            /**
             * id : 2
             * name : 物理
             */

            private int id;
            private String name;

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
        }
    }
}
