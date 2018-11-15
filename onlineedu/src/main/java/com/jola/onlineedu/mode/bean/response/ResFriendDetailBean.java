package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/11/15.
 */

public class ResFriendDetailBean {

    /**
     * data : {"user":{"name":"张三","province_text":"河南","school_name":"郑州一中","courses":["数据结构","高二物理"],"avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15393402485717.jpg","city_text":"郑州","role":2}}
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
         * user : {"name":"张三","province_text":"河南","school_name":"郑州一中","courses":["数据结构","高二物理"],"avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15393402485717.jpg","city_text":"郑州","role":2}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * name : 张三
             * province_text : 河南
             * school_name : 郑州一中
             * courses : ["数据结构","高二物理"]
             * avatar_url : http://yunketang.dev.attackt.com/media/avatar/15393402485717.jpg
             * city_text : 郑州
             * role : 2
             */

            private String name;
            private String province_text;
            private String school_name;
            private String avatar_url;
            private String city_text;
            private int role;
            private List<String> courses;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProvince_text() {
                return province_text;
            }

            public void setProvince_text(String province_text) {
                this.province_text = province_text;
            }

            public String getSchool_name() {
                return school_name;
            }

            public void setSchool_name(String school_name) {
                this.school_name = school_name;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getCity_text() {
                return city_text;
            }

            public void setCity_text(String city_text) {
                this.city_text = city_text;
            }

            public int getRole() {
                return role;
            }

            public void setRole(int role) {
                this.role = role;
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
