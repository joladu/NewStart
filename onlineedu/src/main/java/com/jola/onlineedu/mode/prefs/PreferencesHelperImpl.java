package com.jola.onlineedu.mode.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.jola.onlineedu.app.App;
import com.jola.onlineedu.ui.activity.MainActivity;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/8/14.
 */

public class PreferencesHelperImpl implements PreferencesHelper {


    public static final String SHAREDPREFERENCES_NAME = "online_edu_sp";

    public static final String SP_NIGHT_MODE = "night_mode";
    public static final String  TAG_MAIN_SHOW_FRAGMENT = "main_fragment";
    public static final String  TAG_USER_ID = "user_id";
    public static final String  TAG_USER_ROLE = "user_role";
    public static final String  TAG_USER_NAME = "user_name";
    public static final String  TAG_USER_PHONE = "user_phone";
    public static final String  TAG_USER_TOKEN = "user_token";
    public static final String  TAG_USER_AVATER = "user_avater";

    public static final String  TAG_USER_ADDRESS = "user_address";
    public static final String  TAG_USER_TEACH_COURSE = "user_teach_course";
    public static final String  TAG_USER_SCHOOL = "user_school";
    public static final String  TAG_USER_Password = "user_password";

    public static final String  TAG_School_Id = "school_id";
    public static final String  TAG_Student_Id = "student_id";

    public static final String  Tag_User_Json = "user_json";

    private static final boolean DEFAULT_NIGHT_MODE = false;

    private final SharedPreferences mSPrefs;

    @Inject
    public PreferencesHelperImpl() {
        mSPrefs = App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }


    @Override
    public void setUserPassword(String password) {
        mSPrefs.edit().putString(TAG_USER_Password,password).apply();
    }

    @Override
    public String getUserPassword() {
        return mSPrefs.getString(TAG_USER_Password,"");
    }

    @Override
    public void setCurMainFragmentTag(int fragmentTag) {
        mSPrefs.edit().putInt(TAG_MAIN_SHOW_FRAGMENT,fragmentTag).apply();
    }

    @Override
    public int getCurMainFragmentTag() {
        return mSPrefs.getInt(TAG_MAIN_SHOW_FRAGMENT, 101);
    }

    @Override
    public void setUserId(String userId) {
        mSPrefs.edit().putString(TAG_USER_ID,userId).apply();
    }

    @Override
    public String getUserId() {
        return mSPrefs.getString(TAG_USER_ID,"");
    }

    @Override
    public void setUserRole(int role) {
     mSPrefs.edit().putInt(TAG_USER_ROLE,role);
    }

    @Override
    public int getUserRole() {
        return mSPrefs.getInt(TAG_USER_ROLE,1);
    }

    @Override
    public void setUserName(String userName) {
        mSPrefs.edit().putString(TAG_USER_NAME,userName).apply();
    }

    @Override
    public String getUserName() {
        return mSPrefs.getString(TAG_USER_NAME,"");
    }

    @Override
    public void setUserPhone(String phone) {
        mSPrefs.edit().putString(TAG_USER_PHONE,phone).apply();
    }

    @Override
    public String getUserPhone() {
        return mSPrefs.getString(TAG_USER_PHONE,"");
    }

    @Override
    public void setUserToken(String userToken) {
        mSPrefs.edit().putString(TAG_USER_TOKEN,userToken).apply();
    }

    @Override
    public String getUserToken() {
        return mSPrefs.getString(TAG_USER_TOKEN,"");
    }

    @Override
    public void setUserAvater(String userAvater) {
        mSPrefs.edit().putString(TAG_USER_AVATER,userAvater).apply();

    }

    @Override
    public String getUserAvater() {
        return mSPrefs.getString(TAG_USER_AVATER,"");
    }

    @Override
    public void setUserAddress(String address) {
        mSPrefs.edit().putString(TAG_USER_ADDRESS,address).apply();
    }

    @Override
    public String getUserAddress() {
        return mSPrefs.getString(TAG_USER_ADDRESS,"");

    }

    @Override
    public void setUserTeachCourse(String teachCourse) {
        mSPrefs.edit().putString(TAG_USER_TEACH_COURSE,teachCourse).apply();
    }

    @Override
    public String getUserTeachCourse() {
        return mSPrefs.getString(TAG_USER_TEACH_COURSE,"");
    }

    @Override
    public void setUserSchool(String school) {
        mSPrefs.edit().putString(TAG_USER_SCHOOL,school).apply();
    }

    @Override
    public String getUserSchool() {
        return mSPrefs.getString(TAG_USER_SCHOOL,"");
    }

    @Override
    public void setUserInfoJson(String userInfoBeanString) {
        mSPrefs.edit().putString(Tag_User_Json,userInfoBeanString).apply();
    }

    @Override
    public String getUserInfoJson() {
        return mSPrefs.getString(Tag_User_Json,"");
    }

//    @Override
//    public void setUserSchoolCode(String schoolCode) {
//
//    }
//
//    @Override
//    public String getUserSchoolCode() {
//        return null;
//    }
//
//    @Override
//    public void setUserProviceText(String proviceText) {
//
//    }
//
//    @Override
//    public String getUserProviceText() {
//        return null;
//    }
//
//    @Override
//    public void setUserProviceCode(String proviceCode) {
//
//    }
//
//    @Override
//    public String getUserProviceCode() {
//        return null;
//    }
//
//    @Override
//    public void setUserCityText(String cityText) {
//
//    }
//
//    @Override
//    public String getUserCityText() {
//        return null;
//    }
//
//    @Override
//    public void setUserCityId(String cityId) {
//
//    }
//
//    @Override
//    public String getUserCityId() {
//        return null;
//    }
//
//    @Override
//    public void setUserDistrictText(String districtText) {
//
//    }
//
//    @Override
//    public String getUserDistrictText() {
//        return null;
//    }
//
//    @Override
//    public void setUserDistrictCode(String districtCode) {
//
//    }
//
//    @Override
//    public String getUserDistrictCode() {
//        return null;
//    }
//
//    @Override
//    public void setUserStudentCode(String studentCode) {
//
//    }
//
//    @Override
//    public String getUserStudentCode() {
//        return null;
//    }
//
//    @Override
//    public void setUserGrade(String grade) {
//
//    }
//
//    @Override
//    public String getuserGrade() {
//        return null;
//    }
//
//    @Override
//    public void setUserGradeCode(String gradeCode) {
//
//    }
//
//    @Override
//    public String getuserGradeCode() {
//        return null;
//    }
//
//    @Override
//    public void setUserClass(String userClass) {
//
//    }
//
//    @Override
//    public String getUserClass() {
//        return null;
//    }
//
//    @Override
//    public void setUserClassCode(String userClass) {
//
//    }
//
//    @Override
//    public String getUserClassCode() {
//        return null;
//    }
//
//    @Override
//    public void setUserMajor(String userMajor) {
//
//    }
//
//    @Override
//    public String getUserMajor() {
//        return null;
//    }
//
//    @Override
//    public void setUserMajorCode(String userMajorCode) {
//
//    }
//
//    @Override
//    public String getUserMajorCode() {
//        return null;
//    }
//
//    @Override
//    public void setUserDepartment(String userDepartment) {
//
//    }
//
//    @Override
//    public String getUserDepartment() {
//        return null;
//    }
//
//    @Override
//    public void setUserDepartmentCode(String userDepartmentCode) {
//
//    }
//
//    @Override
//    public String getUserDepartmentCode() {
//        return null;
//    }

    @Override
    public boolean getNightModeState() {
        return mSPrefs.getBoolean(SP_NIGHT_MODE, DEFAULT_NIGHT_MODE);
    }

    @Override
    public void setNightModeState(boolean state) {
        mSPrefs.edit().putBoolean(SP_NIGHT_MODE, state).apply();
    }
}
