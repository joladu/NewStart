package com.jola.onlineedu.mode.bean.response;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResUserInfoBean {


    /**
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IlU5ODEyMDE1Mzc0MzgxNzEiLCJ1c2VyX2lkIjozLCJlbWFpbCI6IiIsImV4cCI6MzExOTQyODgxNH0.gSbTtUEuEXYI1Z035rFWF2Z_nzyzF7o5LyxAuufAhps","user":{"username":"duzi","teaching_courses":"","name":"","school":{"province":0,"city":0,"district_text":"黄河","district":0,"province_text":"河南","city_text":"郑州","name":"郑州一中"},"mobile":"13135657378","id":2,"avatar":"http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg","email":"123@qq.com"}}
     * error_code : 0
     * error_msg : 查询成功
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
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IlU5ODEyMDE1Mzc0MzgxNzEiLCJ1c2VyX2lkIjozLCJlbWFpbCI6IiIsImV4cCI6MzExOTQyODgxNH0.gSbTtUEuEXYI1Z035rFWF2Z_nzyzF7o5LyxAuufAhps
         * user : {"username":"duzi","teaching_courses":"","name":"","school":{"province":0,"city":0,"district_text":"黄河","district":0,"province_text":"河南","city_text":"郑州","name":"郑州一中"},"mobile":"13135657378","id":2,"avatar":"http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg","email":"123@qq.com"}
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
             * teaching_courses :
             * name :
             * school : {"province":0,"city":0,"district_text":"黄河","district":0,"province_text":"河南","city_text":"郑州","name":"郑州一中"}
             * mobile : 13135657378
             * id : 2
             * avatar : http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg
             * email : 123@qq.com
             */

            private String username;
            private String teaching_courses;
            private String name;
            private SchoolBean school;
            private String mobile;
            private int id;
            private String avatar;
            private String email;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getTeaching_courses() {
                return teaching_courses;
            }

            public void setTeaching_courses(String teaching_courses) {
                this.teaching_courses = teaching_courses;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public SchoolBean getSchool() {
                return school;
            }

            public void setSchool(SchoolBean school) {
                this.school = school;
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

            public static class SchoolBean {
                /**
                 * province : 0
                 * city : 0
                 * district_text : 黄河
                 * district : 0
                 * province_text : 河南
                 * city_text : 郑州
                 * name : 郑州一中
                 */

                private int province;
                private int city;
                private String district_text;
                private int district;
                private String province_text;
                private String city_text;
                private String name;

                public int getProvince() {
                    return province;
                }

                public void setProvince(int province) {
                    this.province = province;
                }

                public int getCity() {
                    return city;
                }

                public void setCity(int city) {
                    this.city = city;
                }

                public String getDistrict_text() {
                    return district_text;
                }

                public void setDistrict_text(String district_text) {
                    this.district_text = district_text;
                }

                public int getDistrict() {
                    return district;
                }

                public void setDistrict(int district) {
                    this.district = district;
                }

                public String getProvince_text() {
                    return province_text;
                }

                public void setProvince_text(String province_text) {
                    this.province_text = province_text;
                }

                public String getCity_text() {
                    return city_text;
                }

                public void setCity_text(String city_text) {
                    this.city_text = city_text;
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
}
