package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/11/16.
 */

public class ResFriendList {

    /**
     * data : {"friends_apply":1,"friends":[{"created":"2018-11-15T14:53:07","id":2,"username":"khaki","avatar_url":"http://127.0.0.1:8002/media/avatar/15393402485717.jpg","name":"李强","school_name":"华师附中","courses":["操作系统"]}],"page":1,"pageSize":10,"pageCount":1}
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
         * friends_apply : 1
         * friends : [{"created":"2018-11-15T14:53:07","id":2,"username":"khaki","avatar_url":"http://127.0.0.1:8002/media/avatar/15393402485717.jpg","name":"李强","school_name":"华师附中","courses":["操作系统"]}]
         * page : 1
         * pageSize : 10
         * pageCount : 1
         */

        private int friends_apply;
        private int page;
        private int pageSize;
        private int pageCount;
        private List<FriendsBean> friends;

        public int getFriends_apply() {
            return friends_apply;
        }

        public void setFriends_apply(int friends_apply) {
            this.friends_apply = friends_apply;
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

        public List<FriendsBean> getFriends() {
            return friends;
        }

        public void setFriends(List<FriendsBean> friends) {
            this.friends = friends;
        }

        public static class FriendsBean {
            /**
             * created : 2018-11-15T14:53:07
             * id : 2
             * username : khaki
             * avatar_url : http://127.0.0.1:8002/media/avatar/15393402485717.jpg
             * name : 李强
             * school_name : 华师附中
             * courses : ["操作系统"]
             */

            private String created;
            private int id;
            private String username;
            private String avatar_url;
            private String name;
            private String school_name;
            private List<String> courses;

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
