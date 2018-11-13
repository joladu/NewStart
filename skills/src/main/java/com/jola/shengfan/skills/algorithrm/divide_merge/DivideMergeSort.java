package com.jola.shengfan.skills.algorithrm.divide_merge;

/**
 * Created by lenovo on 2018/11/13.
 */

public class DivideMergeSort {

    public static void divideMergeSort(int[] arr,int start,int end){
        if (null == arr){
            return;
        }
       if (start < end){
            int mid = (start + end)/2;
            divideMergeSort(arr,start,mid);
            divideMergeSort(arr,mid+1,end);
            merge(arr,start,mid,end);
       }
    }

    public static void merge(int[] arr,int start,int mid,int end){
        int sizeLeft = mid - start + 1 + 1;// +1 end flag = max
        int sizeRight = end - (mid + 1) + 1 + 1 ;// +1 end flag = max

        int[] arrLeft = new int[sizeLeft];
        int[] arrRight = new int[sizeRight];

        System.arraycopy(arr,start,arrLeft,0,sizeLeft - 1);
        arrLeft[sizeLeft - 1] = Integer.MAX_VALUE;

        System.arraycopy(arr,mid+1,arrRight,0,sizeRight - 1);
        arrRight[sizeRight - 1] = Integer.MAX_VALUE;

        int indexLeft = 0,indexRight = 0;
        while(start <= end){
            if (arrLeft[indexLeft] < arrRight[indexRight]){
                arr[start++] = arrLeft[indexLeft++];
            }else{
                arr[start++] = arrRight[indexRight++];
            }
        }
    }

}
