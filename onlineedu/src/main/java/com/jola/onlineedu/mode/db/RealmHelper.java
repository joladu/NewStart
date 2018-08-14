package com.jola.onlineedu.mode.db;

import com.jola.onlineedu.mode.bean.ReadStateBean;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by lenovo on 2018/8/14.
 */

public class RealmHelper implements DBHelper {

    private static final String DB_NAME = "OnlineRealm.realm";

    private Realm mRealm;

    public RealmHelper() {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }

    @Override
    public void insertNewsId(int id) {
        ReadStateBean bean = new ReadStateBean();
        bean.setId(id);
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(bean);
        mRealm.commitTransaction();
    }
}
