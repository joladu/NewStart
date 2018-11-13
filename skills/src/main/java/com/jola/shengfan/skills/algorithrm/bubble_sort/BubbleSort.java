package com.jola.shengfan.skills.algorithrm.bubble_sort;

/**
 * Created by lenovo on 2018/11/13.
 */

public class BubbleSort {

    public static void normalBubbleSortAsc(int[] arr){
        if (null == arr || arr.length < 2){
            return;
        }
        int tmp;
        for (int i = arr.length - 1;i > 0;i--){
            for (int j = 0;j < i;j++){
                if (arr[j] > arr[j+1]){
                    tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }


    public static void normalBubbleSortDes(int[] arr){
        if (null == arr || arr.length < 2){
            return;
        }
        int tmp;
        for (int i = 0 ; i < arr.length - 1;i++){
            for (int j = arr.length - 1;j > i;j--){
                if (arr[j] > arr[j-1]){
                    tmp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = tmp;
                }
            }
        }

    }



}
