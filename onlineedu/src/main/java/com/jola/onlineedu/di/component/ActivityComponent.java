package com.jola.onlineedu.di.component;

import android.app.Activity;

import com.jola.onlineedu.di.module.ActivityModule;
import com.jola.onlineedu.di.scope.ActivityScope;
import com.jola.onlineedu.ui.activity.CommentsListActivity;
import com.jola.onlineedu.ui.activity.CourseDetailActivity;
import com.jola.onlineedu.ui.activity.ForgetPasswordActivity;
import com.jola.onlineedu.ui.activity.ForumDetailActivity;
import com.jola.onlineedu.ui.activity.ForumListActivity;
import com.jola.onlineedu.ui.activity.ForumPublishActivity;
import com.jola.onlineedu.ui.activity.FriendListFromApplyActivity;
import com.jola.onlineedu.ui.activity.FriendListToApplyActivity;
import com.jola.onlineedu.ui.activity.GroupChatActivity;
import com.jola.onlineedu.ui.activity.LiveDetailActivity;
import com.jola.onlineedu.ui.activity.LoginActivity;
import com.jola.onlineedu.ui.activity.MainActivity;
import com.jola.onlineedu.ui.activity.MessageDetailActivity;
import com.jola.onlineedu.ui.activity.MessageSendActivity;
import com.jola.onlineedu.ui.activity.ModifyPasswordActivity;
import com.jola.onlineedu.ui.activity.ModifyPhoneNoActivity;
import com.jola.onlineedu.ui.activity.ModifyProfileInfoActivity;
import com.jola.onlineedu.ui.activity.MyCollectionActivity;
import com.jola.onlineedu.ui.activity.MyDownloadsActivity;
import com.jola.onlineedu.ui.activity.MyInterestActivity;
import com.jola.onlineedu.ui.activity.MyMessageActivity;
import com.jola.onlineedu.ui.activity.MyStudyActivity;
import com.jola.onlineedu.ui.activity.PersonInfoImproveActivity;
import com.jola.onlineedu.ui.activity.RegisterActivity;
import com.jola.onlineedu.ui.activity.SearchFriendActivity;
import com.jola.onlineedu.ui.activity.SelectableCourseActivity;
import com.jola.onlineedu.ui.activity.TeacherAttestationActivity;
import com.jola.onlineedu.ui.activity.TeacherMasterActivity;
import com.jola.onlineedu.ui.activity.TeacherMasterDetailActivity;
import com.jola.onlineedu.ui.activity.TestPoolActivity;
import com.jola.onlineedu.ui.activity.UserDetailActivity;

import dagger.Component;

/**
 * Created by lenovo on 2018/8/15.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
//    void inject(WelcomeActivity welcomeActivity);
    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
    void inject(ForgetPasswordActivity forgetPasswordActivity);
    void inject(MainActivity mainActivity);
    void inject(ForumListActivity forumListActivity);
    void inject(ForumPublishActivity forumPublishActivity);
    void inject(ForumDetailActivity forumDetailActivity);
    void inject(TestPoolActivity testPoolActivity);
    void inject(SelectableCourseActivity selectableCourseActivity);
    void inject(CourseDetailActivity courseDetailActivity);
    void inject(TeacherMasterActivity teacherMasterActivity);
    void inject(LiveDetailActivity liveDetailActivity);
    void inject(ModifyPasswordActivity modifyPasswordActivity);
    void inject(TeacherAttestationActivity teacherAttestationActivity);
    void inject(ModifyProfileInfoActivity modifyProfileInfoActivity);
    void inject(ModifyPhoneNoActivity modifyPhoneNoActivity);
    void inject(PersonInfoImproveActivity personInfoImproveActivity);
    void inject(CommentsListActivity commentsListActivity);
    void inject(MyDownloadsActivity myDownloadsActivity);
    void inject(MyInterestActivity myInterestActivity);
    void inject(MyMessageActivity myMessageActivity);
    void inject(MyStudyActivity myStudyActivity);
    void inject(MyCollectionActivity myCollectionActivity);
    void inject(FriendListToApplyActivity friendListToApplyActivity);
    void inject(FriendListFromApplyActivity friendListFromApplyActivity);
    void inject(SearchFriendActivity searchFriendActivity);
    void inject(UserDetailActivity userDetailActivity);
    void inject(GroupChatActivity groupChatActivity);
    void inject(TeacherMasterDetailActivity teacherMasterDetailActivity);
    void inject(MessageDetailActivity messageDetailActivity);
    void inject(MessageSendActivity messageSendActivity);
}
