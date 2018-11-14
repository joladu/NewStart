package com.jola.shengfan.skills.algorithrm;

import java.util.ArrayList;

/**
 * Created by lenovo on 2018/11/14.
 */

public class Distinct {

    public static int[] distinctElements(int[] arrSorted){

        ArrayList<Integer> list = new ArrayList<>();
        int j = 0;
        for (int i = 0 ; i < arrSorted.length - 1 ;i++){
            if (arrSorted[i] != arrSorted[i+1]){
                list.add(arrSorted[i]);
            }
        }
        list.add(arrSorted[arrSorted.length - 1]);
        int[] resultArr = new int[list.size()];
       for (int i = 0 ; i < list.size() ;i ++){
           resultArr[i] =  list.get(i);
       }
        return resultArr;

    }
}
