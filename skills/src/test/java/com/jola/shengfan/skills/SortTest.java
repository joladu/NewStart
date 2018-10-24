package com.jola.shengfan.skills;

import org.junit.Test;

import java.util.Random;

/**
 * Created by lenovo on 2018/10/23.
 */

public class SortTest {

    @Test
    public void testSortCorrect(){
//        int[] testArr = {5, 1, 3, 0, 9,10,8,11,5};
        int[] testArr = generateRandomArr(20);


        System.out.println();
        System.out.print("初始：");
        for (int temp : testArr){
            System.out.print(" "+temp);
        }
        int[] resultArr = selectSort(testArr);
        System.out.println();
        System.out.print("结果：");
        for (int temp : resultArr){
            System.out.print(" "+temp);
        }
    }


    /**
     * 单位：ms
     * 10^1 :
     * 10^2 :
     * 10^3 :
     * 10^4 :
     * 10^5 :
     * 10^6 :
     * 10^7 :
     */
    @Test
    public void testSortTime(){
//        int[] testArr = {5, 1, 3, 0, 9};
        int[] testArr = generateRandomArr(100_0000);


        System.out.println("开始：");
        long startTime = System.currentTimeMillis();
        int[] resultArr = insertSort(testArr);
//        int[] resultArr = selectSort(testArr);
        long endTime = System.currentTimeMillis();

        System.out.println("耗时："+(endTime - startTime)+" ms");
        System.out.println("结束。");
    }
    /**
     * 单位：ms
     * 10^1 : 0
     * 10^2 : 0
     * 10^3 : 2
     * 10^4 : 18
     * 10^5 : 991
     * 10^6 : 107387
     * 10^7 : 爆炸增长
     */
    private int[] insertSort(int[] arr){
        if (null == arr || arr.length < 2) {
            return arr;
        }
        int curIndex ;
        int curVal;
        for (int i = 1;i < arr.length;i++ ){
            curIndex = i;
            curVal = arr[curIndex];
            while(curIndex > 0 && curVal < arr[curIndex - 1]){
                arr[curIndex] = arr[curIndex - 1];
                curIndex--;
            }
            arr[curIndex] = curVal;
        }
        return arr;
    }

    /**
     * 单位：ms
     * 10^1 : 0
     * 10^2 : 0
     * 10^3 : 2
     * 10^4 : 18
     * 10^5 : 1051
     * 10^6 : 106597
     * 10^7 : 爆炸增长
     */
    private int[] selectSort(int[] arr){
        if (null == arr || arr.length < 2){
            return arr;
        }
        int curIndex;
        int curVal;
        for (int i = 1;i < arr.length;i++){
            curIndex = i;
            curVal = arr[curIndex];
            while(curIndex > 0 && curVal < arr[curIndex - 1]){
                arr[curIndex] = arr[curIndex - 1];
                curIndex --;
            }
            arr[curIndex] = curVal;
        }

        return arr;
    }

    @Test
    public void testMergeSortRight(){
//        int[] testArr = {5,8,9,12,16,19,45  ,3,6,7,10,40,41,50};
//        int[] testArr = {16,1};
        int[] testArr = generateRandomArr(15);
        System.out.println("初始");
        for (int temp : testArr){
            System.out.print(" "+temp);
        }


//        merge(testArr,0,(testArr.length)/2,testArr.length - 1);
        mergeSort(testArr,0,testArr.length - 1);

        System.out.println("");
        System.out.println("归并");
        for (int temp : testArr){
            System.out.print(" "+temp);
        }
    }


    /**
     * 单位：ms
     * 10^1 : 0
     * 10^2 : 0
     * 10^3 : 1
     * 10^4 : 2
     * 10^5 : 21
     * 10^6 : 188
     * 10^7 : 1993
     * 10^8: 20999
     */
    @Test
    public void testMergeSortTime(){

        int[] arr = generateRandomArr(10);


        System.out.println("开始：");
        long startTime = System.currentTimeMillis();

        mergeSort(arr,0,arr.length - 1);

        long endTime = System.currentTimeMillis();

        System.out.println("耗时："+(endTime - startTime)+" ms");
        System.out.println("结束。");


        return ;
    }

    public static void mergeSort(int[] arr,int left,int right){
        if (left >= right){
            return;
        }
        int midle = (left + right) /2;
        mergeSort(arr,left,midle);
        mergeSort(arr,midle + 1,right);
        merge(arr,left,midle,right);
    }

    public static void merge(int[] arr,int l,int m,int r){
        int[] resultArr = new int[r - l + 1];
       int indexLeft = l,indexRight = m+1,indexCur = 0;
        while (indexLeft <= m && indexRight <= r){
            if (arr[indexLeft] > arr[indexRight]){
                resultArr[indexCur++] = arr[indexRight++];
            }else{
                resultArr[indexCur++] = arr[indexLeft++];
            }
        }

        while(indexLeft <= m){
            resultArr[indexCur++] = arr[indexLeft++];
        }

        while (indexRight <= r){
            resultArr[indexCur++] = arr[indexRight++];
        }

      for (int temp : resultArr){
          arr[l++] = temp;
      }

    }











    public int[] generateRandomArr(int arrSize){
        int[] arr = new int[arrSize];
        Random random = new Random(arrSize);
        while (arrSize > 0){
            arr[arrSize - 1] = random.nextInt();
            arrSize --;
        }
        return arr;
    }


}
