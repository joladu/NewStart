package com.jola.newnews.mode.db;

/**
 * Created by jola on 2018/7/28.
 */

public interface IDBHelper {
    void insertNewsId(int id);
    /**
     * 查询 阅读记录
     * @param id
     * @return
     */
    boolean queryNewsId(int id);
}
