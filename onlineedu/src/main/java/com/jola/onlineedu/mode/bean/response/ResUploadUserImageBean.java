package com.jola.onlineedu.mode.bean.response;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResUploadUserImageBean {

    /**
     * error_code : 0
     * error_msg : success
     * data : {"user":{"avatar":"http://xxxx.jpg"}}
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
         * user : {"avatar":"http://xxxx.jpg"}
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
             * avatar : http://xxxx.jpg
             */

            private String avatar;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
