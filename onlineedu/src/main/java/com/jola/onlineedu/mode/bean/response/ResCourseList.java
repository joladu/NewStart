package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResCourseList {


    /**
     * count : 3
     * next : null
     * previous : null
     * results : [{"id":1,"user":6,"name":"高二物理","author":"李老师","cover_url":"http://yunketang.dev.attackt.com/media/cover_1537345153.png","pay_type":1,"price":23,"see_count":82,"recommend_count":1,"share_count":4,"summary":"物理课。","score":4,"is_recommend":1,"categories":[{"id":2,"name":"高中"}]},{"id":2,"user":6,"name":"数据结构","author":"韩老师","cover_url":"http://yunketang.dev.attackt.com/media/cover_1538986737.png","pay_type":0,"price":0,"see_count":38,"recommend_count":1,"share_count":1,"summary":"专业讲授","score":3.7,"is_recommend":1,"categories":[{"id":3,"name":"中专"}]},{"id":3,"user":28,"name":"历史","author":"木老师","cover_url":"http://yunketang.dev.attackt.com/media/","pay_type":1,"price":45,"see_count":0,"recommend_count":0,"share_count":0,"summary":"大师写的历史。","score":0,"is_recommend":1,"categories":[{"id":1,"name":"中小学"}]}]
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
         * user : 6
         * name : 高二物理
         * author : 李老师
         * cover_url : http://yunketang.dev.attackt.com/media/cover_1537345153.png
         * pay_type : 1
         * price : 23
         * see_count : 82
         * recommend_count : 1
         * share_count : 4
         * summary : 物理课。
         * score : 4.0
         * is_recommend : 1
         * categories : [{"id":2,"name":"高中"}]
         */

        private int id;
        private int user;
        private String name;
        private String author;
        private String cover_url;
        private int pay_type;
        private int price;
        private int see_count;
        private int recommend_count;
        private int share_count;
        private String summary;
        private double score;
        private int is_recommend;
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

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSee_count() {
            return see_count;
        }

        public void setSee_count(int see_count) {
            this.see_count = see_count;
        }

        public int getRecommend_count() {
            return recommend_count;
        }

        public void setRecommend_count(int recommend_count) {
            this.recommend_count = recommend_count;
        }

        public int getShare_count() {
            return share_count;
        }

        public void setShare_count(int share_count) {
            this.share_count = share_count;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(int is_recommend) {
            this.is_recommend = is_recommend;
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
             * name : 高中
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
