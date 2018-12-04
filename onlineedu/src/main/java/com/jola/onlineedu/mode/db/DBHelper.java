package com.jola.onlineedu.mode.db;

/**
 * Created by lenovo on 2018/8/14.
 */

public interface DBHelper {
    void insertNewsId(int id);
    boolean hasPraiseCommentOfCourse(int commentId);
    void praiseCommentOfCourse(int commentId);
    void cacelFavoriteCourse(int courseId);
    void favoriteCourse(int courseId);
    boolean hasFavoriteCourse(int courseId);
}
