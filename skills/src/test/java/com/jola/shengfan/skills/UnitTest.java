package com.jola.shengfan.skills;

import com.jola.shengfan.skills.algorithrm.*;
import com.jola.shengfan.skills.algorithrm.SortTest;
import com.jola.shengfan.skills.algorithrm.divide_merge.DivideMergeSort;
import com.jola.shengfan.skills.algorithrm.practical_problem.ExistTwoSumEqsNum;

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

//        com.jola.shengfan.skills.algorithrm.SortTest.testDivideMergeSortAsc(15);
//        com.jola.shengfan.skills.algorithrm.SortTest.testDivideMergeSortDes(20);




//        arrayCopyTest();
//        testMergeDes();
//        testMergeAscNoRepeat();

//        int[] arrTest = {1, 7, 7, 8, 10,15,15,20};
//        printArr("ori",arrTest);
//        int[] result = Distinct.distinctElements(arrTest);
//        printArr("res:",result);

//        testMerget();

//        int i = Runtime.getRuntime().availableProcessors();
//        ArrayDeque<String> strings = new ArrayDeque<>();
//        strings.

        testExistSum();

    }

    static void testExistSum(){
        int[] testArr = DataUtil.randomIntArr(100);
        int givenNum = 74;
        int existAddTwoEqs = ExistTwoSumEqsNum.isExistAddTwoEqs(testArr, givenNum);
        printArr(givenNum+"",testArr);
        System.out.println("index:"+existAddTwoEqs);

    }


    public static void arrayCopyTest(){
        int[] arr = DataUtil.randomIntArr(10);
        SortTest.printArr("arr:",arr);
        int[] copyResult = new int[5];
        System.arraycopy(arr,4,copyResult,0,5);
        SortTest.printArr("copyResult:",copyResult);
    }

    public static void testMergeAsc(){
        int[] arrTest = {1, 7, 8, 10, 2, 5,8, 9, 20};
        SortTest.printArr("ori:",arrTest);
        DivideMergeSort.mergeAsc(arrTest,0,3,8);
        SortTest.printArr("ori:",arrTest);
    }

    public static void testMergeDes(){
//        int[] arrTest = {1, 7, 8, 10,   2, 5,8, 9, 20};
        int[] arrTest = {10,8,7,1, 20,9,8,5,2};
        SortTest.printArr("ori:",arrTest);
//        DivideMergeSort.mergeAsc(arrTest,0,3,8);
        DivideMergeSort.mergeDes(arrTest,0,3,8);
        SortTest.printArr("ori:",arrTest);
    }

    public static void testMergeAscNoRepeat(){
        int[] arrTest = {1, 7, 8, 10,   2, 5,8, 9, 30};
        SortTest.printArr("org:",arrTest);
//        DivideMergeSort.mergeAsc(arrTest,0,3,8);
        DivideMergeSort.mergeAscNoRepeat(arrTest,0,3,8);
        SortTest.printArr("rst:",arrTest);
    }


    public void bitTranslateTest(){
        int bitOne = 1;
        System.out.println(bitOne);
        bitOne |= bitOne << 1;
        bitOne |= bitOne << 2;
        bitOne |= bitOne << 4;
        bitOne |= bitOne << 8;
        System.out.println(bitOne);
    }

    static void testMerget(){
        int[] arrA = {3,6,10,23,53,100,120};
        int[] arrB = {2,4,8,32,35,110,130,150};
        int[] resultArr = DataUtil.mergeSortedArr(arrA, arrB);
        printArr("result",resultArr);
    }

    public static void printArr(String tag,int[] arr){
        System.out.println(tag);
        for (int temp : arr){
            System.out.print(temp+" ");
        }
        System.out.println();
    }


}
