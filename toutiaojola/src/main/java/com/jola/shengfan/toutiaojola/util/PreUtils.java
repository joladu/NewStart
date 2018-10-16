package com.jola.shengfan.toutiaojola.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.jola.shengfan.toutiaojola.app.MyApp;

public class PreUtils {

    public static final String CONFIG_FILE_NAME = "config";


    public static void putBoolean(String key, boolean value){
        
        SharedPreferences sp = MyApp.getAppContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).apply();
    }

    public static boolean getBoolean(String key, boolean defValue){
        SharedPreferences sp = MyApp.getAppContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }

    public static void putString(String key, String value){
        SharedPreferences sp = MyApp.getAppContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key,value).apply();
    }

    public static String getString(String key, String defValue){
        SharedPreferences sp = MyApp.getAppContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }

    public static void putInt(String key, int value){
        SharedPreferences sp = MyApp.getAppContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).apply();
    }

    public static int getInt(String key, int defValue){
        SharedPreferences sp = MyApp.getAppContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key,defValue);
    }

    public static void putLong(String key, long value){
        SharedPreferences sp = MyApp.getAppContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putLong(key,value).apply();
    }

    public static long getLong(String key, long defValue){
        SharedPreferences sp = MyApp.getAppContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key,defValue);
    }

}
