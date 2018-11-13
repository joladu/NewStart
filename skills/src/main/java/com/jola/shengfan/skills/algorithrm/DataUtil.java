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



}
