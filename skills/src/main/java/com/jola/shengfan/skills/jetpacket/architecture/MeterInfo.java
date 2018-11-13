//package com.jola.shengfan.skills.jetpacket.architecture;
//
//import android.arch.persistence.room.ColumnInfo;
//import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.PrimaryKey;
//import android.support.annotation.NonNull;
//
///**
// * Created by lenovo on 2018/11/8.
// */
//
//@Entity(tableName = "meterInfo_table")
//public class MeterInfo {
//
//    @PrimaryKey
//    @NonNull
//    @ColumnInfo(name = "meterNo")
//    public String meterNo;
//
//    @ColumnInfo(name = "childNo")
//    private String childNo;
//
//
//    public MeterInfo(String meterNo, String childNo) {
//        this.meterNo = meterNo;
//        this.childNo = childNo;
//    }
//
//
//    public String getMeterNo() {
//        return meterNo;
//    }
//
//    public void setMeterNo(String meterNo) {
//        this.meterNo = meterNo;
//    }
//
//    public String getChildNo() {
//        return childNo;
//    }
//
//    public void setChildNo(String childNo) {
//        this.childNo = childNo;
//    }
//}
