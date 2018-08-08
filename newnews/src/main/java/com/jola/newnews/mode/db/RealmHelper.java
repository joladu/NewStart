package com.jola.newnews.mode.db;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jola on 2018/7/28.
 */

public class RealmHelper implements IDBHelper {

    private final String DB_NAME = "myRealmDb";

    private Realm mRealm;

    @Inject
    public RealmHelper() {
        mRealm = Realm.getInstance(
                new RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                        .name(DB_NAME)
                        .build()
        );
    }

    @Override
    public void insertNewsId(int id) {

    }

    @Override
    public boolean queryNewsId(int id) {
        return false;
    }
}
