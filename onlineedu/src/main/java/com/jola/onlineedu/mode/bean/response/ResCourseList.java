package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResCourseList {

    /**
     * count : 2
     * next : http://localhost:8000/api/v1/course/?page=2&page_size=1
     * previous : null
     * results : [{"id":1,"user":4,"name":"课程1","author":"MCE","cover":"","pay_type":0,"price":100,"see_count":1,"recommend_count":1,"share_count":1,"summary":"","is_recommend":0,"categories":[{"id":1,"name":"小学"}]}]
     */

    private int count;
    private String next;
    private Object previous;
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
         * user : 4
         * name : 课程1
         * author : MCE
         * cover :
         * pay_type : 0
         * price : 100
         * see_count : 1
         * recommend_count : 1
         * share_count : 1
         * summary :
         * is_recommend : 0
         * categories : [{"id":1,"name":"小学"}]
         */

        private int id;
        private int user;
        private String name;
        private String author;
        private String cover;
        private int pay_type;
        private int price;
        private int see_count;
        private int recommend_count;
        private int share_count;
        private String summary;
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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
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
             * id : 1
             * name : 小学
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
