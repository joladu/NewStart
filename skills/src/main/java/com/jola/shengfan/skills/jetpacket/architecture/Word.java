package com.jola.shengfan.skills.jetpacket.architecture;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by lenovo on 2018/11/8.
 */

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(String mWord) {
        this.mWord = mWord;
    }

    public String getmWord() {
        return mWord;
    }

    public void setmWord(String mWord) {
        this.mWord = mWord;
    }
}
