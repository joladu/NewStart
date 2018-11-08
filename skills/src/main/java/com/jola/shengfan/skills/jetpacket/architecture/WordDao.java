package com.jola.shengfan.skills.jetpacket.architecture;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by lenovo on 2018/11/8.
 */

@Dao
public interface WordDao {

    @Insert
    void insertWord(Word word);

    @Update
    void updateWord(Word word);

    @Delete
    void deleteWord(Word word);

    @Query("DELETE FROM word_table")
    void deleteAdd();

//    @Query("SELECT word FROM word_table ORDER BY word ASC ")
//    List<Word> getAllWords();

//    使用LiveData动态管理数据的更新改变
    @Query("select word from word_table order by word asc")
    LiveData<List<Word>> getAllWords();




}
