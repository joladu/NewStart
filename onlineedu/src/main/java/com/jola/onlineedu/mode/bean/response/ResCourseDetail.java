package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResCourseDetail {


    /**
     * author : 李老师
     * categories : [{"id":2,"name":"高中"}]
     * collect_count : 0
     * cover_url : http://yunketang.dev.attackt.com/media/cover_1537345153.png
     * has_collected : 0
     * has_praised : 0
     * has_scored : 0
     * id : 1
     * is_recommend : 1
     * name : 高二物理
     * pay_type : 1
     * praise_count : 4
     * price : 23
     * recommend_count : 1
     * releated_courses : [{"author":"木老师","categories":[{"id":1,"name":"中小学"}],"cover_url":"http://yunketang.dev.attackt.com/media/","id":3,"is_recommend":1,"name":"历史","pay_type":1,"price":45,"recommend_count":0,"score":0,"see_count":0,"share_count":0,"summary":"大师写的历史。","user":28},{"author":"韩老师","categories":[{"id":3,"name":"中专"}],"cover_url":"http://yunketang.dev.attackt.com/media/cover_1538986737.png","id":2,"is_recommend":1,"name":"数据结构","pay_type":0,"price":0,"recommend_count":1,"score":3.7,"see_count":38,"share_count":1,"summary":"专业讲授","user":6}]
     * score : 4.0
     * see_count : 85
     * share_count : 4
     * share_url : http://m.baidu.com
     * summary : 物理课。
     * user : 6
     */

    private String author;
    private int collect_count;
    private String cover_url;
    private int has_collected;
    private int has_praised;
    private int has_scored;
    private int id;
    private int is_recommend;
    private String name;
    private int pay_type;
    private int praise_count;
    private int price;
    private int recommend_count;
    private double score;
    private int see_count;
    private int share_count;
    private String share_url;
    private String summary;
    private int user;
    private List<CategoriesBean> categories;
    private List<ReleatedCoursesBean> releated_courses;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public int getHas_collected() {
        return has_collected;
    }

    public void setHas_collected(int has_collected) {
        this.has_collected = has_collected;
    }

    public int getHas_praised() {
        return has_praised;
    }

    public void setHas_praised(int has_praised) {
        this.has_praised = has_praised;
    }

    public int getHas_scored() {
        return has_scored;
    }

    public void setHas_scored(int has_scored) {
        this.has_scored = has_scored;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_recommend() {
        return is_recommend;
    }

    public void setIs_recommend(int is_recommend) {
        this.is_recommend = is_recommend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public int getPraise_count() {
        return praise_count;
    }

    public void setPraise_count(int praise_count) {
        this.praise_count = praise_count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRecommend_count() {
        return recommend_count;
    }

    public void setRecommend_count(int recommend_count) {
        this.recommend_count = recommend_count;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getSee_count() {
        return see_count;
    }

    public void setSee_count(int see_count) {
        this.see_count = see_count;
    }

    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int share_count) {
        this.share_count = share_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
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

    public static class ReleatedCoursesBean {
        /**
         * author : 木老师
         * categories : [{"id":1,"name":"中小学"}]
         * cover_url : http://yunketang.dev.attackt.com/media/
         * id : 3
         * is_recommend : 1
         * name : 历史
         * pay_type : 1
         * price : 45
         * recommend_count : 0
         * score : 0.0
         * see_count : 0
         * share_count : 0
         * summary : 大师写的历史。
         * user : 28
         */

        private String author;
        private String cover_url;
        private int id;
        private int is_recommend;
        private String name;
        private int pay_type;
        private int price;
        private int recommend_count;
        private double score;
        private int see_count;
        private int share_count;
        private String summary;
        private int user;
        private List<CategoriesBeanX> categories;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(int is_recommend) {
            this.is_recommend = is_recommend;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public int getRecommend_count() {
            return recommend_count;
        }

        public void setRecommend_count(int recommend_count) {
            this.recommend_count = recommend_count;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getSee_count() {
            return see_count;
        }

        public void setSee_count(int see_count) {
            this.see_count = see_count;
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

        public int getUser() {
            return user;
        }

        public void setUser(int user) {
            this.user = user;
        }

        public List<CategoriesBeanX> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBeanX> categories) {
            this.categories = categories;
        }

        public static class CategoriesBeanX {
            /**
             * id : 1
             * name : 中小学
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
