package com.jola.onlineedu.mode.bean.response;

/**
 * Created by jola on 2019/1/10.
 */

public class ResThirdpartLoginBean {


    /**
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IlUzMDI5NzE1NDcxMjQ5OTEiLCJ1c2VyX2lkIjozOCwiZW1haWwiOiIiLCJleHAiOjMxMjM5MjQ5OTF9.ayxS4tepxw8BatkHU2dy0IO0f-4GV4A8m3Vu_T-B3bQ","user":{"avatar":"","email":"","id":38,"mobile":"","name":""}}
     * error_code : 0
     * error_msg : 登录成功
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
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IlUzMDI5NzE1NDcxMjQ5OTEiLCJ1c2VyX2lkIjozOCwiZW1haWwiOiIiLCJleHAiOjMxMjM5MjQ5OTF9.ayxS4tepxw8BatkHU2dy0IO0f-4GV4A8m3Vu_T-B3bQ
         * user : {"avatar":"","email":"","id":38,"mobile":"","name":""}
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
             * avatar :
             * email :
             * id : 38
             * mobile :
             * name :
             */

            private String avatar;
            private String email;
            private int id;
            private String mobile;
            private String name;

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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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
        }
    }
}
