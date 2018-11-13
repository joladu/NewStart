package com.jola.shengfan.skills.algorithrm.select_sort;

/**
 * Created by lenovo on 2018/11/12.
 */

public class SelectSort {

    public static void normalSelectSortAsc(int[] arr){
        for (int i = 0;i < arr.length ;i++){
            int minVal = arr[i];
            int minIndex = i;
            for (int j = i ; j < arr.length - 1 ;j++){
                if (arr[j+1] < minVal){
                    minVal = arr[j+1];
                    minIndex = j+1;
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = minVal;
            }
        }
    }


    public static void normalSelectSortDes(int[] arr){
        for (int i = 0;i < arr.length ;i++){
            int minVal = arr[i];
            int minIndex = i;
            for (int j = i ; j < arr.length - 1 ;j++){
                if (arr[j+1] > minVal){
                    minVal = arr[j+1];
                    minIndex = j+1;
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = minVal;
            }
        }
    }





}
