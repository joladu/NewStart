package com.jola.onlineedu.di.component;

import android.app.Activity;

import com.jola.onlineedu.di.module.ActivityModule;
import com.jola.onlineedu.di.scope.ActivityScope;
import com.jola.onlineedu.ui.activity.CourseDetailActivity;
import com.jola.onlineedu.ui.activity.ForgetPasswordActivity;
import com.jola.onlineedu.ui.activity.ForumDetailActivity;
import com.jola.onlineedu.ui.activity.ForumListActivity;
import com.jola.onlineedu.ui.activity.ForumPublishActivity;
import com.jola.onlineedu.ui.activity.LoginActivity;
import com.jola.onlineedu.ui.activity.MainActivity;
import com.jola.onlineedu.ui.activity.RegisterActivity;
import com.jola.onlineedu.ui.activity.SelectableCourseActivity;
import com.jola.onlineedu.ui.activity.TestPoolActivity;

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
}
