package com.jola.shengfan.skills.algorithrm;

import java.util.Random;

/**
 * Created by lenovo on 2018/11/12.
 */

public class DataUtil {

    public static int[] randomIntArr(int size){
        Random random = new Random(size);
        int[] arr = new int[size];
        int i = 0;
        while (i < size){
            arr[i++] = random.nextInt(size*10);
        }
        return arr;
    }

    public static int[] mergeSortedArr(int[] sortedArrA,int[] sortedArrB){

        int[] resultArr = new int[sortedArrA.length + sortedArrB.length];

        int indexA = 0,indexB = 0,indexResult = 0;


        while (indexA < sortedArrA.length && indexB < sortedArrB.length ){
            if (sortedArrA[indexA] < sortedArrB[indexB]){
                resultArr[indexResult++] = sortedArrA[indexA++];
            }else{
                resultArr[indexResult++] = sortedArrB[indexB++];
            }
        }

        while (indexA < sortedArrA.length){
            resultArr[indexResult++] = sortedArrA[indexA++];
        }

        while (indexB < sortedArrB.length){
            resultArr[indexResult++] = sortedArrB[indexB++];
        }

        return resultArr;
    }

    public static int existSameElementIndex(int[] sortedArr){
        int index = -1;
        for (int i = 0;i < sortedArr.length - 1;i++){
            if (sortedArr[i] == sortedArr [i+1]){
                return i;
            }
        }
        return index;
    }



}
