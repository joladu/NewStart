package com.jola.shengfan.skills.algorithrm;

import com.jola.shengfan.skills.algorithrm.bubble_sort.BubbleSort;
import com.jola.shengfan.skills.algorithrm.divide_merge.DivideMergeSort;
import com.jola.shengfan.skills.algorithrm.insert_sort.InsertSort;
import com.jola.shengfan.skills.algorithrm.select_sort.SelectSort;

/**
 * Created by lenovo on 2018/11/12.
 */

public class SortTest {

    public static void testInsertSort(int size){
        int[] arr = DataUtil.randomIntArr(size);
        printArr("org",arr);
        long startTime = System.currentTimeMillis();
        InsertSort.normalInsertSortAsc(arr);
        long endTime = System.currentTimeMillis();
        printArr("res",arr);
        System.out.println("time:"+(endTime - startTime));

    }

    public static void testInsertSortDes(int size){
        int[] arr = DataUtil.randomIntArr(size);
        printArr("org",arr);
        InsertSort.normalInsertSortDes(arr);
        printArr("res",arr);
    }


    public static void testSelectSortAsc(int size){
        int[] arr = DataUtil.randomIntArr(size);
        printArr("org",arr);
        long startTime = System.currentTimeMillis();
//        InsertSort.normalInsertSortAsc(arr);
        SelectSort.normalSelectSortAsc(arr);
        long endTime = System.currentTimeMillis();
        printArr("res",arr);
        System.out.println("time:"+(endTime - startTime));

    }


    public static void testSelectSortDes(int size){
        int[] arr = DataUtil.randomIntArr(size);
        printArr("org",arr);
        long startTime = System.currentTimeMillis();
        SelectSort.normalSelectSortDes(arr);
        long endTime = System.currentTimeMillis();
        printArr("res",arr);
        System.out.println("time:"+(endTime - startTime));

    }



    public static void testBubbleSortAsc(int size){
        int[] arr = DataUtil.randomIntArr(size);
        printArr("org",arr);
        long startTime = System.currentTimeMillis();
        BubbleSort.normalBubbleSortAsc(arr);
        long endTime = System.currentTimeMillis();
        printArr("res",arr);
        System.out.println("time:"+(endTime - startTime));

    }


    public static void testBubbleSortDes(int size){
        int[] arr = DataUtil.randomIntArr(size);
        printArr("org",arr);
        long startTime = System.currentTimeMillis();
        BubbleSort.normalBubbleSortDes(arr);
        long endTime = System.currentTimeMillis();
        printArr("res",arr);
        System.out.println("time:"+(endTime - startTime));

    }


    public static void testDivideMergeSortAsc(int size){
        int[] arr = DataUtil.randomIntArr(size);
        printArr("org",arr);
        long startTime = System.currentTimeMillis();
        DivideMergeSort.divideMergeSort(arr,0,arr.length - 1);
        long endTime = System.currentTimeMillis();
        printArr("res",arr);
        System.out.println("time:"+(endTime - startTime));

    }





    public static void printArr(String tag,int[] arr){
        System.out.println(tag);
        for (int temp : arr){
            System.out.print(temp+" ");
        }
        System.out.println();
    }

}
