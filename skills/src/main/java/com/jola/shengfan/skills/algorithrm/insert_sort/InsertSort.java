package com.jola.shengfan.skills.algorithrm.insert_sort;

/**
 * Created by lenovo on 2018/11/12.
 */

public class InsertSort {

    public static void normalInsertSortAsc(int[] arr){
        if (null == arr || arr.length < 2){
            return;
        }
        for (int i = 1 ; i < arr.length;i++){
            int temp = arr[i];
            int tempIndex = i;
            while(tempIndex > 0 && arr[tempIndex - 1] > temp){
                arr[tempIndex] = arr[tempIndex - 1];
                tempIndex --;
            }
            arr[tempIndex] = temp;
        }
    }

    public static void normalInsertSortDes(int[] arr){
        if (null == arr || arr.length < 2){
            return;
        }
        for (int i = 1 ; i < arr.length;i++){
            int temp = arr[i];
            int tempIndex = i;
            while(tempIndex > 0 && arr[tempIndex - 1] < temp){
                arr[tempIndex] = arr[tempIndex - 1];
                tempIndex --;
            }
            arr[tempIndex] = temp;
        }
    }



}
