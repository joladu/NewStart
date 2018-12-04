package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by jola on 2018/11/19.
 */

public class ResMyCollectionListBean {


    /**
     * data : {"count":1,"courses":[{"author":"李老师","categories":["物理"],"id":1,"cover_url":"http://yunketang.dev.attackt.com/media/cover_1537345153.png","name":"高二物理"}],"page":1,"pageSize":10,"pageCount":1}
     * error_code : 0
     * error_msg : success
     */

    private DataBean data;
    private int error_code;
    private String error_msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public static class DataBean {
        /**
         * count : 1
         * courses : [{"author":"李老师","categories":["物理"],"id":1,"cover_url":"http://yunketang.dev.attackt.com/media/cover_1537345153.png","name":"高二物理"}]
         * page : 1
         * pageSize : 10
         * pageCount : 1
         */

        private int count;
        private int page;
        private int pageSize;
        private int pageCount;
        private List<CoursesBean> courses;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<CoursesBean> getCourses() {
            return courses;
        }

        public void setCourses(List<CoursesBean> courses) {
            this.courses = courses;
        }

        public static class CoursesBean {
            /**
             * author : 李老师
             * categories : ["物理"]
             * id : 1
             * cover_url : http://yunketang.dev.attackt.com/media/cover_1537345153.png
             * name : 高二物理
             */

            private String author;
            private int id;
            private String cover_url;
            private String name;
            private List<String> categories;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCover_url() {
                return cover_url;
            }

            public void setCover_url(String cover_url) {
                this.cover_url = cover_url;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getCategories() {
                return categories;
            }

            public void setCategories(List<String> categories) {
                this.categories = categories;
            }
        }
    }
}
