package com.jola.onlineedu.mode.bean.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResUserInfoBean implements Serializable{


    /**
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IlU5ODEyMDE1Mzc0MzgxNzEiLCJ1c2VyX2lkIjozLCJlbWFpbCI6IiIsImV4cCI6MzEyMDU0MTYxM30.vfM7m0OdCCO_u1SChG230Qx4gg3hWGxq3LksNrEj6Qo","user":{"username":"duzi","province":22,"teaching_courses":"","name":"duzi","district":2213,"province_text":"湖北省","mobile":"13135657378","class":{"name":"","id":""},"city":221,"school":{"name":"","id":1},"email":"123@qq.com","grade":{"name":"","id":""},"role":2,"avatar":"http://yunketang.dev.attackt.com/media/avatar/15393402485717.jpg","student_code":"","department":"","city_text":"武汉市","major":{"name":"","id":""},"id":3,"district_text":""}}
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
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IlU5ODEyMDE1Mzc0MzgxNzEiLCJ1c2VyX2lkIjozLCJlbWFpbCI6IiIsImV4cCI6MzEyMDU0MTYxM30.vfM7m0OdCCO_u1SChG230Qx4gg3hWGxq3LksNrEj6Qo
         * user : {"username":"duzi","province":22,"teaching_courses":"","name":"duzi","district":2213,"province_text":"湖北省","mobile":"13135657378","class":{"name":"","id":""},"city":221,"school":{"name":"","id":1},"email":"123@qq.com","grade":{"name":"","id":""},"role":2,"avatar":"http://yunketang.dev.attackt.com/media/avatar/15393402485717.jpg","student_code":"","department":"","city_text":"武汉市","major":{"name":"","id":""},"id":3,"district_text":""}
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
             * province : 22
             * teaching_courses :
             * name : duzi
             * district : 2213
             * province_text : 湖北省
             * mobile : 13135657378
             * class : {"name":"","id":""}
             * city : 221
             * school : {"name":"","id":1}
             * email : 123@qq.com
             * grade : {"name":"","id":""}
             * role : 2
             * avatar : http://yunketang.dev.attackt.com/media/avatar/15393402485717.jpg
             * student_code :
             * department :
             * city_text : 武汉市
             * major : {"name":"","id":""}
             * id : 3
             * district_text :
             */

            private String username;
            private int province;
            private String teaching_courses;
            private String name;
            private int district;
            private String province_text;
            private String mobile;
            @SerializedName("class")
            private ClassBean classX;
            private int city;
            private SchoolBean school;
            private String email;
            private GradeBean grade;
            private int role;
            private String avatar;
            private String student_code;
            private String department;
            private String city_text;
            private MajorBean major;
            private int id;
            private String district_text;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public int getProvince() {
                return province;
            }

            public void setProvince(int province) {
                this.province = province;
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

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public ClassBean getClassX() {
                return classX;
            }

            public void setClassX(ClassBean classX) {
                this.classX = classX;
            }

            public int getCity() {
                return city;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public SchoolBean getSchool() {
                return school;
            }

            public void setSchool(SchoolBean school) {
                this.school = school;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public GradeBean getGrade() {
                return grade;
            }

            public void setGrade(GradeBean grade) {
                this.grade = grade;
            }

            public int getRole() {
                return role;
            }

            public void setRole(int role) {
                this.role = role;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getStudent_code() {
                return student_code;
            }

            public void setStudent_code(String student_code) {
                this.student_code = student_code;
            }

            public String getDepartment() {
                return department;
            }

            public void setDepartment(String department) {
                this.department = department;
            }

            public String getCity_text() {
                return city_text;
            }

            public void setCity_text(String city_text) {
                this.city_text = city_text;
            }

            public MajorBean getMajor() {
                return major;
            }

            public void setMajor(MajorBean major) {
                this.major = major;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDistrict_text() {
                return district_text;
            }

            public void setDistrict_text(String district_text) {
                this.district_text = district_text;
            }

            public static class ClassBean {
                /**
                 * name :
                 * id :
                 */

                private String name;
                private String id;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }

            public static class SchoolBean {
                /**
                 * name :
                 * id : 1
                 */

                private String name;
                private String id;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }

            public static class GradeBean {
                /**
                 * name :
                 * id :
                 */

                private String name;
                private String id;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }

            public static class MajorBean {
                /**
                 * name :
                 * id :
                 */

                private String name;
                private String id;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }
        }
    }
}