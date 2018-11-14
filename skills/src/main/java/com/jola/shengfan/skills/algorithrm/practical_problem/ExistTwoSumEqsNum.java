package com.jola.shengfan.skills.algorithrm.practical_problem;

import com.jola.shengfan.skills.algorithrm.DataUtil;
import com.jola.shengfan.skills.algorithrm.Distinct;
import com.jola.shengfan.skills.algorithrm.divide_merge.DivideMergeSort;

/**
 *  Si  Sj  belong to S collection,X is given,
 *
 *     is exist Si+Sj = ValueX ?
 * Created by lenovo on 2018/11/14.
 */

public class ExistTwoSumEqsNum {

    public static int isExistAddTwoEqs(int[] arr,int givenValue){

//        divideMerge
        DivideMergeSort.divideMergeSortAsc(arr,0,arr.length - 1);

//        distinct
        int[] distinctSortArr = Distinct.distinctElements(arr);

        int[] buildDiffArr = new int[distinctSortArr.length];

        int j = buildDiffArr.length - 1;
        for (int i = 0 ; i < buildDiffArr.length ; i++){
            buildDiffArr[i] = givenValue - distinctSortArr[j--];
        }

        int[] mergeSortedArr = DataUtil.mergeSortedArr(distinctSortArr, buildDiffArr);

        return DataUtil.existSameElementIndex(mergeSortedArr);
    }

}
