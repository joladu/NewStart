package com.jola.onlineedu.mode.bean.response;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResUserInfoBean {

    /**
     * error_code : 0
     * error_msg : success
     * data : {"user":{"mobile":"18980871024","name":"admin","username":"admin","avatar":"http://xxx.jpg","email":"test@gmail.com"}}
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
         * user : {"mobile":"18980871024","name":"admin","username":"admin","avatar":"http://xxx.jpg","email":"test@gmail.com"}
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
             * mobile : 18980871024
             * name : admin
             * username : admin
             * avatar : http://xxx.jpg
             * email : test@gmail.com
             */

            private String mobile;
            private String name;
            private String username;
            private String avatar;
            private String email;

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }
        }
    }
}
