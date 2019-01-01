package com.jola.onlineedu.mode;

import com.jola.onlineedu.mode.bean.response.ResBannerHomepage;
import com.jola.onlineedu.mode.bean.response.ResClassListBean;
import com.jola.onlineedu.mode.bean.response.ResCommentListBean;
import com.jola.onlineedu.mode.bean.response.ResCourseCapterDetail;
import com.jola.onlineedu.mode.bean.response.ResCourseCapterList;
import com.jola.onlineedu.mode.bean.response.ResCourseDetail;
import com.jola.onlineedu.mode.bean.response.ResCourseList;
import com.jola.onlineedu.mode.bean.response.ResCourseRecommendBean;
import com.jola.onlineedu.mode.bean.response.ResCouserCommentList;
import com.jola.onlineedu.mode.bean.response.ResDownloadsBean;
import com.jola.onlineedu.mode.bean.response.ResExamsDetail;
import com.jola.onlineedu.mode.bean.response.ResExamsList;
import com.jola.onlineedu.mode.bean.response.ResForumComments;
import com.jola.onlineedu.mode.bean.response.ResForumDetailBean;
import com.jola.onlineedu.mode.bean.response.ResForumListByTypeBean;
import com.jola.onlineedu.mode.bean.response.ResForumTypeBean;
import com.jola.onlineedu.mode.bean.response.ResFriendDetailBean;
import com.jola.onlineedu.mode.bean.response.ResFriendList;
import com.jola.onlineedu.mode.bean.response.ResGetImageCode;
import com.jola.onlineedu.mode.bean.response.ResGradeListBean;
import com.jola.onlineedu.mode.bean.response.ResInteresListBean;
import com.jola.onlineedu.mode.bean.response.ResLiveCourseDetail;
import com.jola.onlineedu.mode.bean.response.ResLiveCourseList;
import com.jola.onlineedu.mode.bean.response.ResMajorListBean;
import com.jola.onlineedu.mode.bean.response.ResMessageDetailBean;
import com.jola.onlineedu.mode.bean.response.ResMessageListBean;
import com.jola.onlineedu.mode.bean.response.ResPlatformBean;
import com.jola.onlineedu.mode.bean.response.ResQuestionTypeBean;
import com.jola.onlineedu.mode.bean.response.ResSchoolListBean;
import com.jola.onlineedu.mode.bean.response.ResSelectionListBean;
import com.jola.onlineedu.mode.bean.response.ResStudyListBean;
import com.jola.onlineedu.mode.bean.response.ResTeacherAttestation;
import com.jola.onlineedu.mode.bean.response.ResTeacherBannerBean;
import com.jola.onlineedu.mode.bean.response.ResTeacherCourseDetail;
import com.jola.onlineedu.mode.bean.response.ResTeacherList;
import com.jola.onlineedu.mode.bean.response.ResUpdatepersonalInfoBean;
import com.jola.onlineedu.mode.bean.response.ResUploadFourmImageBean;
import com.jola.onlineedu.mode.bean.response.ResUploadUserImageBean;
import com.jola.onlineedu.mode.bean.response.ResUserInfoBean;
import com.jola.onlineedu.mode.bean.response.ResUserLogin;
import com.jola.onlineedu.mode.bean.response.ResUserRegister;
import com.jola.onlineedu.mode.bean.response.ResponseGetQiLiuBean;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.mode.db.DBHelper;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.mode.prefs.PreferencesHelper;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;

/**
 * Created by lenovo on 2018/8/14
 * http db preference
 */

public class DataManager implements MyApis ,DBHelper,PreferencesHelper{

    DBHelper mDBHelper;
    PreferencesHelper mPreferenceHelper;
    MyApis myApis;

    public DataManager(MyApis myApis, DBHelper mDBHelper, PreferencesHelper mPreferenceHelper) {
        this.myApis = myApis;
        this.mDBHelper = mDBHelper;
        this.mPreferenceHelper = mPreferenceHelper;
    }




    @Override
    public Flowable<ResponseSimpleResult> getMsgCheckCode(String mobile) {
        return myApis.getMsgCheckCode(mobile);
    }

    @Override
    public Flowable<ResGetImageCode> getImageCode() {
        return myApis.getImageCode();
    }

    @Override
    public Flowable<ResponseGetQiLiuBean> getQiLiuInfo() {
        return myApis.getQiLiuInfo();
    }

    @Override
    public Flowable<ResUserLogin> getUserLoginInfo(String userName, String userPassword) {
        return myApis.getUserLoginInfo(userName,userPassword);
    }

    @Override
    public Flowable<ResUserRegister> getUserRegisterInfo(String userName, String mobileNum, String checkCode, String captcha_key, String captcha, String password, String passwordConfirm,String role) {
        return myApis.getUserRegisterInfo(userName,mobileNum,checkCode,captcha_key,captcha,password,passwordConfirm,role);
    }



    // @Field("mobile") String mobilePhone,
//    @Field("v_code") String msgCode,
//    @Field("captcha_key") String imageCodeKey,
//    @Field("captcha") String imageCode,
//    @Field("password") String password
    @Override
    public Flowable<ResponseSimpleResult> getUserForgetPasswrod(String mobilePhone, String msgCode, String imageCodeKey, String imageCode, String password) {
        return myApis.getUserForgetPasswrod(mobilePhone,msgCode,imageCodeKey,imageCode,password);
    }

    @Override
    public Flowable<ResponseSimpleResult> modifyPhoneNo(String token,String mobilePhone, String msgCode, String imageCodeKey, String imageCode) {
        return myApis.modifyPhoneNo(token,mobilePhone,msgCode,imageCodeKey,imageCode);
    }

    @Override
    public Flowable<ResUserInfoBean> getUserInfo(String token) {
        return myApis.getUserInfo(token);
    }

    @Override
    public Flowable<ResUploadUserImageBean> uploadUserImage(String token,String imageBase64Str) {
        return myApis.uploadUserImage(token,imageBase64Str);
    }

    @Override
    public Flowable<ResUpdatepersonalInfoBean> updateUserProfile(Map<String, String> map) {
        return myApis.updateUserProfile(map);
    }

    @Override
    public Flowable<ResForumTypeBean> getForumTypeInfo() {
        return myApis.getForumTypeInfo();
    }

    @Override
    public Flowable<ResForumListByTypeBean> getForumListByType(Map<String, String> map) {
        return myApis.getForumListByType(map);
    }


//    @Override
//    public Flowable<ResForumListByTypeBean> getForumListByType(String forumType) {
//        return myApis.getForumListByType(forumType);
//    }


    @Override
    public Flowable<ResForumDetailBean> getForumDetail(String id) {
        return myApis.getForumDetail(id);
    }

    @Override
    public Flowable<ResForumComments> getForumComments(String id, int page,int pageSize) {
        return myApis.getForumComments(id,page,pageSize);
    }

    @Override
    public Flowable<ResponseSimpleResult> commentForum(String token,String id, String content) {
        return myApis.commentForum(token,id,content);
    }

    @Override
    public Flowable<ResponseSimpleResult> praiseComment(String token,String id) {
        return myApis.praiseComment(token,id);
    }

    @Override
    public Flowable<ResUploadFourmImageBean> uploadForumImage(String img) {
        return myApis.uploadForumImage(img);
    }

//    @Override
//    public Flowable<ResUploadFourmImageBean> uploadForumImage(MultipartBody.Part[] file) {
//        return myApis.uploadForumImage(file);
//    }

    @Override
    public Flowable<ResponseSimpleResult> publishForumContent(String token,String type, String title, String content, List<String> imageUrlList) {
        return myApis.publishForumContent(token,type,title,content,imageUrlList);
    }

    @Override
    public Flowable<ResponseSimpleResult> publishForumNew(String token, Map<String, RequestBody> map) {
        return myApis.publishForumNew(token,map);
    }

    @Override
    public Flowable<ResQuestionTypeBean> getQuestionType() {
        return myApis.getQuestionType();
    }

    @Override
    public Flowable<ResExamsList> getExamsList(String kw,int page,int pageSize) {
        return myApis.getExamsList(kw,page,pageSize);
    }

    @Override
    public Flowable<ResExamsDetail> getExamsDetail(String id) {
        return myApis.getExamsDetail(id);
    }

    @Override
    public Flowable<ResCourseList> getCourseList(String page, String page_size,String kw) {
        return myApis.getCourseList(page,page_size,kw);
    }

    @Override
    public Flowable<ResCourseRecommendBean> getCourseRecommendList(String page, String page_size) {
        return myApis.getCourseRecommendList(page,page_size);
    }

    @Override
    public Flowable<ResCourseDetail> getCourseDetail(String token,String id) {
        return myApis.getCourseDetail(token,id);
    }

    @Override
    public Flowable<ResCourseCapterList> getCourseCapterList(String course_id, String page, String page_size) {
        return myApis.getCourseCapterList(course_id,page,page_size);
    }

    @Override
    public Flowable<ResCourseCapterDetail> getCourseCapterDetail(String id) {
        return myApis.getCourseCapterDetail(id);
    }

    @Override
    public Flowable<ResCouserCommentList> getCourseCommentList(String course_id, String page, String page_size) {
        return myApis.getCourseCommentList(course_id,page,page_size);
    }

    @Override
    public Flowable<ResponseSimpleResult> publishCourseComment(String token,int id,String userId, String content) {
        return myApis.publishCourseComment(token,id,userId,content);
    }

    @Override
    public Flowable<ResponseSimpleResult> publishCourseComment(String token, Map<String, RequestBody> map) {
        return myApis.publishCourseComment(token,map);
    }

    @Override
    public Flowable<ResponseSimpleResult> praiseCommentCourse(String token,int id) {
        return myApis.praiseCommentCourse(token,id);
    }

    @Override
    public Flowable<ResponseSimpleResult> favoriteCourse(String token, int course_id) {
        return myApis.favoriteCourse(token,course_id);
    }

    @Override
    public Flowable<ResponseSimpleResult> cancelFavoriteCourse(String token, int course_id) {
        return myApis.cancelFavoriteCourse(token,course_id);
    }

    @Override
    public Flowable<ResponseSimpleResult> scoreCourse(String token, int course_id, int score) {
        return myApis.scoreCourse(token,course_id,score);
    }

    @Override
    public Flowable<ResLiveCourseList> getLiveCourseList(String page, String page_size) {
        return myApis.getLiveCourseList(page,page_size);
    }

    @Override
    public Flowable<ResLiveCourseDetail> getLiveCourseDetail(String id) {
        return myApis.getLiveCourseDetail(id);
    }

    @Override
    public Flowable<ResTeacherBannerBean> getTeacherBanner() {
        return myApis.getTeacherBanner();
    }

    @Override
    public Flowable<ResTeacherList> getTeacherList(String kw,String page, String page_size) {
        return myApis.getTeacherList(kw,page,page_size);
    }

    @Override
    public Flowable<ResTeacherCourseDetail> getTeacherCourseDetail(int id) {
        return myApis.getTeacherCourseDetail(id);
    }

    @Override
    public Flowable<List<ResBannerHomepage>> getBannerHomepage() {
        return myApis.getBannerHomepage();
    }

    @Override
    public Flowable<ResponseSimpleResult> changePassword(String token,String oldpwd, String newpwd, String newpwd2) {
        return myApis.changePassword(token,oldpwd,newpwd,newpwd2);
    }


    @Override
    public Flowable<ResponseSimpleResult> changePassword1(String token, RequestBody oldpwd, RequestBody newpwd, RequestBody newpwd2) {
        return myApis.changePassword1(token,oldpwd,newpwd,newpwd2);
    }

    @Override
    public Flowable<ResponseSimpleResult> changePassword(String token, Map<String, RequestBody> map) {
        return myApis.changePassword(token,map);
    }

    @Override
    public Flowable<ResTeacherAttestation> teacherVerify(String token, Map<String, RequestBody> map) {
        return myApis.teacherVerify(token,map);
    }

    @Override
    public Observable<ResTeacherAttestation> teacherVerifyObserable(String token, Map<String, RequestBody> param) {
        return myApis.teacherVerifyObserable(token,param);
    }


    @Override
    public Flowable<ResFriendDetailBean> getFriendDetailInfo(String token, String id) {
        return myApis.getFriendDetailInfo(token,id);
    }

    @Override
    public Flowable<ResponseSimpleResult> changeMobilePhoneNo(String token, String mobile, String vcode, String captcha_key, String captcha) {
        return myApis.changeMobilePhoneNo(token,mobile,vcode,captcha_key,captcha);
    }

    @Override
    public Flowable<ResFriendList> getFriendList(String token) {
        return myApis.getFriendList(token);
    }

    @Override
    public Flowable<ResFriendList> queryFriendByKey(String token, String keyWord) {
        return myApis.queryFriendByKey(token,keyWord);
    }

    @Override
    public Flowable<ResponseSimpleResult> addFriend(String token, int to_user_id) {
        return myApis.addFriend(token,to_user_id);
    }

    @Override
    public Flowable<ResponseSimpleResult> handFriendApply(String id, String token,int status) {
        return myApis.handFriendApply(id,token,status);
    }

    @Override
    public Flowable<ResMessageListBean> getMessageList(String token, int page, int pagesize) {
        return myApis.getMessageList(token,page,pagesize);
    }

    @Override
    public Flowable<ResMessageDetailBean> getMessageDetailInfo(String token, String id) {
        return myApis.getMessageDetailInfo(token,id);
    }

    @Override
    public Flowable<ResponseSimpleResult> sendMessage(String token, String to_user_id, String content) {
        return myApis.sendMessage(token,to_user_id,content);
    }

    @Override
    public Flowable<ResponseSimpleResult> responseMessage(String token, String id, String content) {
        return myApis.responseMessage(token,id,content);
    }

    @Override
    public Flowable<ResCommentListBean> getCommentsList(String token, int page, int pagesize) {
        return myApis.getCommentsList(token,page,pagesize);
    }

    @Override
    public Flowable<ResSelectionListBean> getSelectionList(String token,int page, int pagesize) {
        return myApis.getSelectionList(token,page,pagesize);
    }

    @Override
    public Flowable<ResDownloadsBean> getDownloadList(String token, int page, int pagesize) {
        return myApis.getDownloadList(token,page,pagesize);
    }

    @Override
    public Flowable<ResInteresListBean> getInterestList(String token, int page, int pagesize) {
        return myApis.getInterestList(token,page,pagesize);
    }

    @Override
    public Flowable<ResStudyListBean> getMyStudyList(String token, int page, int pagesize) {
        return myApis.getMyStudyList(token,page,pagesize);
    }

    @Override
    public Flowable<ResSchoolListBean> getSchoolList(String token, int page, int pagesize) {
        return myApis.getSchoolList(token,page,pagesize);
    }

    @Override
    public Flowable<ResGradeListBean> getGradeList(String token, int page, int pagesize) {
        return myApis.getGradeList(token,page,pagesize);
    }

    @Override
    public Flowable<ResClassListBean> getClassList(String token, int page, int pagesize) {
        return myApis.getClassList(token,page,pagesize);
    }

    @Override
    public Flowable<ResMajorListBean> getMajorList(String token, int page, int pagesize) {
        return myApis.getMajorList(token,page,pagesize);
    }

    @Override
    public Flowable<ResPlatformBean> getPlatforms(int page, int pageSize, String kewWord) {
        return myApis.getPlatforms(page,pageSize,kewWord);
    }

    @Override
    public Flowable<ResponseSimpleResult> sendGroupMsg(String token, String content) {
        return myApis.sendGroupMsg(token,content);
    }


    @Override
    public void insertNewsId(int id) {
        mDBHelper.insertNewsId(id);
    }

    @Override
    public boolean hasPraiseCommentOfCourse(int commentId) {
        return mDBHelper.hasPraiseCommentOfCourse(commentId);
    }

    @Override
    public void praiseCommentOfCourse(int commentId) {
        mDBHelper.praiseCommentOfCourse(commentId);
    }

    @Override
    public void cacelFavoriteCourse(int courseId) {
        mDBHelper.cacelFavoriteCourse(courseId);
    }

    @Override
    public void favoriteCourse(int courseId) {
        mDBHelper.favoriteCourse(courseId);
    }

    @Override
    public boolean hasFavoriteCourse(int courseId) {
        return mDBHelper.hasFavoriteCourse(courseId);
    }

    @Override
    public void setUserPassword(String password) {
        mPreferenceHelper.setUserPassword(password);
    }

    @Override
    public String getUserPassword() {
        return mPreferenceHelper.getUserPassword();
    }

    @Override
    public void setCurMainFragmentTag(int fragmentTag) {
        mPreferenceHelper.setCurMainFragmentTag(fragmentTag);
    }

    @Override
    public int getCurMainFragmentTag() {
        return mPreferenceHelper.getCurMainFragmentTag();
    }

    @Override
    public void setUserId(String userId) {
        mPreferenceHelper.setUserId(userId);
    }

    @Override
    public String getUserId() {
        return mPreferenceHelper.getUserId();
    }

    @Override
    public void setUserRole(int role) {
        mPreferenceHelper.setUserRole(role);
    }

    @Override
    public int getUserRole() {
        return mPreferenceHelper.getUserRole();
    }

    @Override
    public void setUserName(String userName) {
        mPreferenceHelper.setUserName(userName);
    }

    @Override
    public String getUserName() {
        return mPreferenceHelper.getUserName();
    }

    @Override
    public void setUserPhone(String phone) {
        mPreferenceHelper.setUserPhone(phone);
    }

    @Override
    public String getUserPhone() {
        return mPreferenceHelper.getUserPhone();
    }

    @Override
    public void setUserToken(String userToken) {
        mPreferenceHelper.setUserToken(userToken);
    }

    @Override
    public String getUserToken() {
        return mPreferenceHelper.getUserToken();
    }

    @Override
    public void setUserAvater(String userAvater) {
        mPreferenceHelper.setUserAvater(userAvater);
    }

    @Override
    public String getUserAvater() {
        return mPreferenceHelper.getUserAvater();
    }

    @Override
    public void setUserAddress(String address) {
        mPreferenceHelper.setUserAddress(address);
    }

    @Override
    public String getUserAddress() {
        return mPreferenceHelper.getUserAddress();
    }

    @Override
    public void setUserTeachCourse(String teachCourse) {
        mPreferenceHelper.setUserTeachCourse(teachCourse);
    }

    @Override
    public String getUserTeachCourse() {
        return mPreferenceHelper.getUserTeachCourse();
    }

    @Override
    public void setUserSchool(String school) {
        mPreferenceHelper.setUserSchool(school);
    }

    @Override
    public String getUserSchool() {
        return mPreferenceHelper.getUserSchool();
    }

    @Override
    public void setUserInfoJson(String userInfoBeanString) {
        mPreferenceHelper.setUserInfoJson(userInfoBeanString);
    }

    @Override
    public String getUserInfoJson() {
        return mPreferenceHelper.getUserInfoJson();
    }

    @Override
    public boolean getNightModeState() {
        return mPreferenceHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean state) {
        mPreferenceHelper.setNightModeState(state);
    }
}
