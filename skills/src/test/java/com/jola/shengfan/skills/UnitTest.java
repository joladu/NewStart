package com.jola.shengfan.skills;

import com.jola.shengfan.skills.algorithrm.*;
import com.jola.shengfan.skills.algorithrm.SortTest;
import com.jola.shengfan.skills.algorithrm.divide_merge.DivideMergeSort;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * Created by lenovo on 2018/11/12.
 */

public class UnitTest {

    @Test
    public void testInsetSort(){

//        com.jola.shengfan.skills.algorithrm.SortTest.testInsertSort(20);
//        com.jola.shengfan.skills.algorithrm.SortTest.testInsertSortDes(20);

//        com.jola.shengfan.skills.algorithrm.SortTest.testSelectSortAsc(30);
//        com.jola.shengfan.skills.algorithrm.SortTest.testSelectSortDes(20);

//        com.jola.shengfan.skills.algorithrm.SortTest.testBubbleSortAsc(20);
//        com.jola.shengfan.skills.algorithrm.SortTest.testBubbleSortDes(10);



        com.jola.shengfan.skills.algorithrm.SortTest.testDivideMergeSortAsc(8);





//        arrayCopyTest();
//        testMerge();

//        int i = Runtime.getRuntime().availableProcessors();
//        ArrayDeque<String> strings = new ArrayDeque<>();
//        strings.
    }


    public static void arrayCopyTest(){
        int[] arr = DataUtil.randomIntArr(10);
        SortTest.printArr("arr:",arr);
        int[] copyResult = new int[5];
        System.arraycopy(arr,4,copyResult,0,5);
        SortTest.printArr("copyResult:",copyResult);
    }

    public static void testMerge(){
        int[] arrTest = {1, 7, 8, 10, 2, 5,8, 9, 20};
        SortTest.printArr("ori:",arrTest);
        DivideMergeSort.merge(arrTest,0,3,8);
        SortTest.printArr("ori:",arrTest);
    }


    @Test
    public void bitTranslateTest(){
        int bitOne = 1;
        System.out.println(bitOne);
        bitOne |= bitOne << 1;
        bitOne |= bitOne << 2;
        bitOne |= bitOne << 4;
        bitOne |= bitOne << 8;
        System.out.println(bitOne);
    }


}
