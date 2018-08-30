package com.jola.onlineedu.mode.bean.response;

/**
 * Created by lenovo on 2018/8/30.
 */

public class ResUserRegister {

    /**
     * error_code : 0
     * error_msg : success
     * data : {"user":{"user_id":1,"realname":"姓名","mobile":"18513516234"},"token":"fhbjhryruh"}
     */

    private int error_code;
    private String error_msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user : {"user_id":1,"realname":"姓名","mobile":"18513516234"}
         * token : fhbjhryruh
         */

        private UserBean user;
        private String token;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserBean {
            /**
             * user_id : 1
             * realname : 姓名
             * mobile : 18513516234
             */

            private int user_id;
            private String realname;
            private String mobile;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }
        }
    }
}
