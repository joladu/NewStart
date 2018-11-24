package com.jola.onlineedu.mode.bean.response;

/**
 * Created by lenovo on 2018/8/30.
 */

public class ResUserLogin {


    /**
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IlU5ODEyMDE1Mzc0MzgxNzEiLCJ1c2VyX2lkIjozLCJlbWFpbCI6IiIsImV4cCI6MzExOTgyNTgzOX0.-teZyYCWjhM6XVtL5l7ocmA0JY6sihV3RL3wX-GifmY","user":{"username":"duzi","mobile":"13135657378","id":3}}
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
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IlU5ODEyMDE1Mzc0MzgxNzEiLCJ1c2VyX2lkIjozLCJlbWFpbCI6IiIsImV4cCI6MzExOTgyNTgzOX0.-teZyYCWjhM6XVtL5l7ocmA0JY6sihV3RL3wX-GifmY
         * user : {"username":"duzi","mobile":"13135657378","id":3}
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
             * username : duzi
             * mobile : 13135657378
             * id : 3
             */

            private String username;
            private String mobile;
            private int id;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
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
        }
    }
}
