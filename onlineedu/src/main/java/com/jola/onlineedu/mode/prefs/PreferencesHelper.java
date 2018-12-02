package com.jola.onlineedu.mode.prefs;

/**
 * Created by lenovo on 2018/8/14.
 */

public interface PreferencesHelper {

    void setUserPassword(String password);
    String getUserPassword();

    void setCurMainFragmentTag(int fragmentTag);
    int getCurMainFragmentTag();

    void setUserId(String userId);
    String getUserId();

    void setUserRole(int role);
    int getUserRole();

    void setUserName(String userName);
    String getUserName();

    void setUserPhone(String phone);
    String getUserPhone();

    void setUserToken(String userToken);
    String getUserToken();

    void setUserAvater(String userAvater);
    String getUserAvater();


    void setUserTeachCourse(String teachCourse);
    String getUserTeachCourse();

    void setUserAddress(String address);
    String getUserAddress();

    void setUserSchool(String school);
    String getUserSchool();

    void setUserInfoJson(String userInfoBeanString);
    String getUserInfoJson();

//    void setUserSchoolCode(String schoolCode);
//    String getUserSchoolCode();
//
//
//    void setUserProviceText(String proviceText);
//    String getUserProviceText();
//
//    void setUserProviceCode(String proviceCode);
//    String getUserProviceCode();
//
//    void setUserCityText(String cityText);
//    String getUserCityText();
//
//    void setUserCityId(String cityId);
//    String getUserCityId();
//
//    void setUserDistrictText(String districtText);
//    String getUserDistrictText();
//
//    void setUserDistrictCode(String districtCode);
//    String getUserDistrictCode();
//
//    void setUserStudentCode(String studentCode);
//    String getUserStudentCode();
//
//    void setUserGrade(String grade);
//    String getuserGrade();
//
//    void setUserGradeCode(String gradeCode);
//    String getuserGradeCode();
//
//
//    void setUserClass(String userClass);
//    String getUserClass();
//
//    void setUserClassCode(String userClass);
//    String getUserClassCode();
//
//    void setUserMajor(String userMajor);
//    String getUserMajor();
//
//    void setUserMajorCode(String userMajorCode);
//    String getUserMajorCode();
//
//    void setUserDepartment(String userDepartment);
//    String getUserDepartment();
//
//    void setUserDepartmentCode(String userDepartmentCode);
//    String getUserDepartmentCode();









    boolean getNightModeState();

    void setNightModeState(boolean state);
}
