package com.jola.onlineedu.mode.bean.response;

/**
 * Created by jola on 2018/11/17.
 */

public class ResUpdatepersonalInfoBean {

    /**
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwidXNlcl9pZCI6MSwiZW1haWwiOiJraGFraUBodWl5dW50LmNvbSIsImV4cCI6MzExOTA1MjYyN30.BsInvelTy5wcddsHWcC8iRihrHTV57pg4uZsI0_e35A","user":{"teaching_courses":"计算机，生物，化学","name":"李强","mobile":"18980871024","school":{"province":"22","city":"221","district_text":"洪山区","district":"2213","province_text":"湖北省","city_text":"武汉市","name":"华师附中"},"avatar":"http://127.0.0.1:8002/media/avatar/15393402485717.jpg","id":2}}
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
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwidXNlcl9pZCI6MSwiZW1haWwiOiJraGFraUBodWl5dW50LmNvbSIsImV4cCI6MzExOTA1MjYyN30.BsInvelTy5wcddsHWcC8iRihrHTV57pg4uZsI0_e35A
         * user : {"teaching_courses":"计算机，生物，化学","name":"李强","mobile":"18980871024","school":{"province":"22","city":"221","district_text":"洪山区","district":"2213","province_text":"湖北省","city_text":"武汉市","name":"华师附中"},"avatar":"http://127.0.0.1:8002/media/avatar/15393402485717.jpg","id":2}
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
             * teaching_courses : 计算机，生物，化学
             * name : 李强
             * mobile : 18980871024
             * school : {"province":"22","city":"221","district_text":"洪山区","district":"2213","province_text":"湖北省","city_text":"武汉市","name":"华师附中"}
             * avatar : http://127.0.0.1:8002/media/avatar/15393402485717.jpg
             * id : 2
             */

            private String teaching_courses;
            private String name;
            private String mobile;
            private SchoolBean school;
            private String avatar;
            private int id;

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

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public SchoolBean getSchool() {
                return school;
            }

            public void setSchool(SchoolBean school) {
                this.school = school;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public static class SchoolBean {
                /**
                 * province : 22
                 * city : 221
                 * district_text : 洪山区
                 * district : 2213
                 * province_text : 湖北省
                 * city_text : 武汉市
                 * name : 华师附中
                 */

                private String province;
                private String city;
                private String district_text;
                private String district;
                private String province_text;
                private String city_text;
                private String name;

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getDistrict_text() {
                    return district_text;
                }

                public void setDistrict_text(String district_text) {
                    this.district_text = district_text;
                }

                public String getDistrict() {
                    return district;
                }

                public void setDistrict(String district) {
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
