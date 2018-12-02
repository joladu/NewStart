package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by jola on 2018/12/2.
 */

public class ResFriendToApplyBean {

    /**
     * data : {"count":4,"friends":[{"username":"看看看","courses":[],"avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15431238674495.jpg","user_id":8,"name":"","created":"2018-11-25T13:23:25.508","status":0,"id":14,"school_name":""},{"username":"rubio","courses":[],"avatar_url":"","user_id":5,"name":"","created":"2018-11-25T10:12:19.724","status":0,"id":13,"school_name":""},{"username":"chushiyang","courses":[],"avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15393407939742.jpg","user_id":4,"name":"","created":"2018-11-25T10:12:13.779","status":0,"id":12,"school_name":""},{"username":"wakana ","courses":[],"avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15434844214488.jpg","user_id":2,"name":"哈哈哈","created":"2018-11-25T09:42:22.143","status":2,"id":11,"school_name":""}],"page":1,"pageSize":10,"pageCount":1}
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
         * count : 4
         * friends : [{"username":"看看看","courses":[],"avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15431238674495.jpg","user_id":8,"name":"","created":"2018-11-25T13:23:25.508","status":0,"id":14,"school_name":""},{"username":"rubio","courses":[],"avatar_url":"","user_id":5,"name":"","created":"2018-11-25T10:12:19.724","status":0,"id":13,"school_name":""},{"username":"chushiyang","courses":[],"avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15393407939742.jpg","user_id":4,"name":"","created":"2018-11-25T10:12:13.779","status":0,"id":12,"school_name":""},{"username":"wakana ","courses":[],"avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15434844214488.jpg","user_id":2,"name":"哈哈哈","created":"2018-11-25T09:42:22.143","status":2,"id":11,"school_name":""}]
         * page : 1
         * pageSize : 10
         * pageCount : 1
         */

        private int count;
        private int page;
        private int pageSize;
        private int pageCount;
        private List<FriendsBean> friends;

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

        public List<FriendsBean> getFriends() {
            return friends;
        }

        public void setFriends(List<FriendsBean> friends) {
            this.friends = friends;
        }

        public static class FriendsBean {
            /**
             * username : 看看看
             * courses : []
             * avatar_url : http://yunketang.dev.attackt.com/media/avatar/15431238674495.jpg
             * user_id : 8
             * name :
             * created : 2018-11-25T13:23:25.508
             * status : 0
             * id : 14
             * school_name :
             */

            private String username;
            private String avatar_url;
            private int user_id;
            private String name;
            private String created;
            private int status;
            private int id;
            private String school_name;
            private List<?> courses;

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

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

            public List<?> getCourses() {
                return courses;
            }

            public void setCourses(List<?> courses) {
                this.courses = courses;
            }
        }
    }
}
