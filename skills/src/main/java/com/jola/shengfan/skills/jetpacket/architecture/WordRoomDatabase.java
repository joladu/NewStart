//package com.jola.shengfan.skills.jetpacket.architecture;
//
//import android.arch.persistence.room.Database;
//import android.arch.persistence.room.Room;
//import android.arch.persistence.room.RoomDatabase;
//import android.content.Context;
//
///**
// * Created by lenovo on 2018/11/8.
// */
//
//@Database(entities = {Word.class,MeterInfo.class},version = 1)
//public abstract class WordRoomDatabase  extends RoomDatabase {
//
//    public static final String DB_NAME = "word_db";
//
//    public abstract WordDao wordDao();
//
//    private static volatile WordRoomDatabase instance;
//
//    public static WordRoomDatabase getInstance(Context context){
//        if (null == instance){
//            synchronized (WordRoomDatabase.class){
//                if (null == instance){
//                    instance = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, DB_NAME)
//                            .build();
//                }
//            }
//        }
//
////        instance.wordDao().
//        return instance;
//    }
//
//}
