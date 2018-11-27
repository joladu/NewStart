package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResCouserCommentList {


    /**
     * count : 31
     * next : http://yunketang.dev.attackt.com/api/v1/coursecomment/1/?page=2
     * previous : null
     * results : [{"id":15,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"x1","praise_count":1},{"id":16,"user":6,"name":"张三","avatar":"http://yunketang.dev.attackt.com/media/avatar/15393402485717.jpg","sex":"1","content":"1112222","praise_count":0},{"id":17,"user":6,"name":"张三","avatar":"http://yunketang.dev.attackt.com/media/avatar/15393402485717.jpg","sex":"1","content":"好课程","praise_count":0},{"id":18,"user":2,"name":"哈哈哈","avatar":"http://yunketang.dev.attackt.com/media/avatar/15428701295586.jpg","sex":"1","content":"晚上的评论。","praise_count":0},{"id":19,"user":6,"name":"张三","avatar":"http://yunketang.dev.attackt.com/media/avatar/15393402485717.jpg","sex":"1","content":"这节课非常好","praise_count":0},{"id":20,"user":2,"name":"哈哈哈","avatar":"http://yunketang.dev.attackt.com/media/avatar/15428701295586.jpg","sex":"1","content":"这节课非常好","praise_count":0},{"id":21,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"束带结发快圣诞节疯狂","praise_count":0},{"id":22,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"束带结发快圣诞节疯狂","praise_count":0},{"id":23,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"束带结发快圣诞节疯狂","praise_count":0},{"id":24,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"asdfasdf","praise_count":0},{"id":25,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"asdfasdf","praise_count":0},{"id":26,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"asdfasdf","praise_count":0},{"id":27,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"asdfasdf","praise_count":0},{"id":28,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"asdfasdf","praise_count":0},{"id":29,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"asdfasdf","praise_count":0},{"id":30,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"asdfasdf","praise_count":0},{"id":31,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"asdfasdf","praise_count":0},{"id":32,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"fsadfsadfsadfsad","praise_count":0},{"id":33,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"fsadfsadfsadfsad","praise_count":0},{"id":34,"user":3,"name":"duzi","avatar":"http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg","sex":"1","content":"userid=500000 能发成功？","praise_count":0}]
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
         * id : 15
         * user : 3
         * name : duzi
         * avatar : http://yunketang.dev.attackt.com/media/avatar/15431234317790.jpg
         * sex : 1
         * content : x1
         * praise_count : 1
         */

        private int id;
        private int user;
        private String name;
        private String avatar;
        private String sex;
        private String content;
        private int praise_count;

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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getPraise_count() {
            return praise_count;
        }

        public void setPraise_count(int praise_count) {
            this.praise_count = praise_count;
        }
    }
}
