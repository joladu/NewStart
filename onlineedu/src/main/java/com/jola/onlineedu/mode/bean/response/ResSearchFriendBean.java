package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/11/20.
 */

public class ResSearchFriendBean {

    /**
     * data : {"users":[{"username":"test","courses":["数据结构","高二物理"],"avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15393402485717.jpg","name":"张三","created":"2018-10-12T13:52:38.259","mobile":"189****1024","id":1,"school_name":"郑州一中"}],"page":1,"pageSize":10,"pageCount":1}
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
         * users : [{"username":"test","courses":["数据结构","高二物理"],"avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15393402485717.jpg","name":"张三","created":"2018-10-12T13:52:38.259","mobile":"189****1024","id":1,"school_name":"郑州一中"}]
         * page : 1
         * pageSize : 10
         * pageCount : 1
         */

        private int page;
        private int pageSize;
        private int pageCount;
        private List<UsersBean> users;

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

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public static class UsersBean {
            /**
             * username : test
             * courses : ["数据结构","高二物理"]
             * avatar_url : http://yunketang.dev.attackt.com/media/avatar/15393402485717.jpg
             * name : 张三
             * created : 2018-10-12T13:52:38.259
             * mobile : 189****1024
             * id : 1
             * school_name : 郑州一中
             */

            private String username;
            private String avatar_url;
            private String name;
            private String created;
            private String mobile;
            private int id;
            private String school_name;
            private List<String> courses;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSchool_name() {
                return school_name;
            }

            public void setSchool_name(String school_name) {
                this.school_name = school_name;
            }

            public List<String> getCourses() {
                return courses;
            }

            public void setCourses(List<String> courses) {
                this.courses = courses;
            }
        }
    }
}
