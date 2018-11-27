package com.jola.onlineedu.mode.bean.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jola on 2018/11/27.
 */

public class PraiseCommentOfCourseStatus extends RealmObject {

    @PrimaryKey
    private int id;

    public PraiseCommentOfCourseStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
