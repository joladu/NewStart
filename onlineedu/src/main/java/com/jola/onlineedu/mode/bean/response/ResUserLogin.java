package com.jola.onlineedu.mode.bean.response;

/**
 * Created by lenovo on 2018/8/30.
 */

public class ResUserLogin {


    /**
     * data : {"token":"fhbjhryruh","user":{"genre":1,"mobile":"18513516234","realname":"姓名","user_id":1}}
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
         * token : fhbjhryruh
         * user : {"genre":1,"mobile":"18513516234","realname":"姓名","user_id":1}
         */

        private String token;
        private UserBean user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * genre : 1
             * mobile : 18513516234
             * realname : 姓名
             * user_id : 1
             */

            private int genre;
            private String mobile;
            private String realname;
            private int user_id;

            public int getGenre() {
                return genre;
            }

            public void setGenre(int genre) {
                this.genre = genre;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }
        }
    }
}
