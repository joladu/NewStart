package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResCouserCommentList {


    /**
     * count : 76
     * next : http://yunketang.dev.attackt.com/api/v1/coursecomment/1/?page=2&page_size=10
     * results : [{"avatar":"http://yunketang.dev.attackt.com/media/avatar/15444324358147.jpg","content":"哈哈哈哈哈哈哈哈哈","created":"2018-12-13T15:33:09","id":136,"name":"氢气球12","praise_count":0,"sex":"1","user":2},{"avatar":"http://yunketang.dev.attackt.com/media/avatar/15447579138108.jpg","content":"你","created":"2018-12-13T02:13:24","id":135,"name":"杨耀翔","praise_count":0,"sex":"1","user":6},{"avatar":"http://yunketang.dev.attackt.com/media/avatar/15447579138108.jpg","content":"我让我朋友","created":"2018-12-12T11:59:36","id":134,"name":"杨耀翔","praise_count":0,"sex":"1","user":6},{"avatar":"http://yunketang.dev.attackt.com/media/avatar/15447579138108.jpg","content":"我让我朋友","created":"2018-12-12T11:56:46","id":133,"name":"杨耀翔","praise_count":0,"sex":"1","user":6},{"avatar":"http://yunketang.dev.attackt.com/media/avatar/15447579138108.jpg","content":"我让我朋友","created":"2018-12-12T11:56:43","id":132,"name":"杨耀翔","praise_count":0,"sex":"1","user":6},{"avatar":"http://yunketang.dev.attackt.com/media/avatar/15447579138108.jpg","content":"我让我朋友","created":"2018-12-12T11:56:32","id":131,"name":"杨耀翔","praise_count":0,"sex":"1","user":6},{"avatar":"http://yunketang.dev.attackt.com/media/avatar/15447579138108.jpg","content":"我让我朋友","created":"2018-12-12T11:56:31","id":130,"name":"杨耀翔","praise_count":0,"sex":"1","user":6},{"avatar":"http://yunketang.dev.attackt.com/media/avatar/15447579138108.jpg","content":"","created":"2018-12-10T15:34:26","id":125,"name":"杨耀翔","praise_count":0,"sex":"1","user":6},{"avatar":"http://yunketang.dev.attackt.com/media/avatar/15444324358147.jpg","content":"我","created":"2018-12-09T14:11:45","id":88,"name":"氢气球12","praise_count":0,"sex":"1","user":2},{"avatar":"http://yunketang.dev.attackt.com/media/avatar/15444324358147.jpg","content":"我","created":"2018-12-09T14:11:40","id":87,"name":"氢气球12","praise_count":0,"sex":"1","user":2}]
     */

    private int count;
    private String next;
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

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * avatar : http://yunketang.dev.attackt.com/media/avatar/15444324358147.jpg
         * content : 哈哈哈哈哈哈哈哈哈
         * created : 2018-12-13T15:33:09
         * id : 136
         * name : 氢气球12
         * praise_count : 0
         * sex : 1
         * user : 2
         */

        private String avatar;
        private String content;
        private String created;
        private int id;
        private String name;
        private int praise_count;
        private String sex;
        private int user;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
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

        public int getPraise_count() {
            return praise_count;
        }

        public void setPraise_count(int praise_count) {
            this.praise_count = praise_count;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getUser() {
            return user;
        }

        public void setUser(int user) {
            this.user = user;
        }
    }
}
