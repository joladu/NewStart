package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResCourseDetail {

    /**
     * id : 2
     * user : null
     * name : 数据结构
     * author : 韩老师
     * cover_url : http://yunketang.dev.attackt.com/media/cover_1538986737.png
     * pay_type : 0
     * price : 0
     * see_count : 1
     * recommend_count : 1
     * share_count : 1
     * collect_count : 1
     * praise_count : 1
     * score : 1
     * summary : 专业讲授
     * is_recommend : 1
     * categories : [{"id":3,"name":"计算机"}]
     * releated_courses : [{"id":1,"user":null,"name":"高二物理","author":"李老师","cover_url":"http://yunketang.dev.attackt.com/media/cover_1537345153.png","pay_type":1,"price":23,"see_count":1,"recommend_count":1,"share_count":1,"summary":"物理课。","score":1,"is_recommend":1,"categories":[{"id":2,"name":"物理"}]}]
     */

    private int id;
    private Object user;
    private String name;
    private String author;
    private String cover_url;
    private int pay_type;
    private int price;
    private int see_count;
    private int recommend_count;
    private int share_count;
    private int collect_count;
    private int praise_count;
    private int score;
    private String summary;
    private int is_recommend;
    private List<CategoriesBean> categories;
    private List<ReleatedCoursesBean> releated_courses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
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

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public int getPraise_count() {
        return praise_count;
    }

    public void setPraise_count(int praise_count) {
        this.praise_count = praise_count;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public List<ReleatedCoursesBean> getReleated_courses() {
        return releated_courses;
    }

    public void setReleated_courses(List<ReleatedCoursesBean> releated_courses) {
        this.releated_courses = releated_courses;
    }

    public static class CategoriesBean {
        /**
         * id : 3
         * name : 计算机
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

    public static class ReleatedCoursesBean {
        /**
         * id : 1
         * user : null
         * name : 高二物理
         * author : 李老师
         * cover_url : http://yunketang.dev.attackt.com/media/cover_1537345153.png
         * pay_type : 1
         * price : 23
         * see_count : 1
         * recommend_count : 1
         * share_count : 1
         * summary : 物理课。
         * score : 1
         * is_recommend : 1
         * categories : [{"id":2,"name":"物理"}]
         */

        private int id;
        private Object user;
        private String name;
        private String author;
        private String cover_url;
        private int pay_type;
        private int price;
        private int see_count;
        private int recommend_count;
        private int share_count;
        private String summary;
        private int score;
        private int is_recommend;
        private List<CategoriesBeanX> categories;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getUser() {
            return user;
        }

        public void setUser(Object user) {
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

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(int is_recommend) {
            this.is_recommend = is_recommend;
        }

        public List<CategoriesBeanX> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBeanX> categories) {
            this.categories = categories;
        }

        public static class CategoriesBeanX {
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
