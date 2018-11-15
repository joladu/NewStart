package com.jola.shengfan.skills.algorithrm.divide_merge;

/**
 * Created by lenovo on 2018/11/13.
 */

public class DivideMergeSort {

    public static void divideMergeSortAsc(int[] arr,int start,int end){
       if (start < end){
            int mid = (start + end)/2;
           divideMergeSortAsc(arr,start,mid);
           divideMergeSortAsc(arr,mid+1,end);
            mergeAsc(arr,start,mid,end);
       }
    }

    public static void mergeAsc(int[] arr,int start,int mid,int end){
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




    public static void divideMergeSortAscNoRepeat(int[] arr,int start,int end){
        if (start < end){
            int mid = (start + end)/2;
            divideMergeSortAscNoRepeat(arr,start,mid);
            divideMergeSortAscNoRepeat(arr,mid+1,end);
            mergeAscNoRepeat(arr,start,mid,end);
        }
    }

    public static int  mergeAscNoRepeat(int[] arr,int start,int mid,int end){ //0 3 8  : 4 5
        int sizeLeft = mid - start + 1;
        int sizeRight = end - (mid + 1) + 1;

        int userfullLen = sizeLeft + sizeRight;

        int[] arrLeft = new int[sizeLeft];
        int[] arrRight = new int[sizeRight];

        System.arraycopy(arr,start,arrLeft,0,sizeLeft);

        System.arraycopy(arr,mid+1,arrRight,0,sizeRight);

        int indexLeft = 0,indexRight = 0,indexArr = start;
        arr[indexArr++] = arrLeft[indexLeft] < arrRight[indexRight] ? arrLeft[indexLeft++] : arrRight[indexRight++];
        while(indexLeft < sizeLeft && indexRight < sizeRight){
            if (arrLeft[indexLeft] < arrRight[indexRight]){
                if (arrLeft[indexLeft] != arr[indexArr - 1]){
                    arr[indexArr++] = arrLeft[indexLeft];
                }else{
                    userfullLen--;
                }
                indexLeft++;
            }else{
                if (arrRight[indexRight] != arr[indexArr - 1]){
                    arr[indexArr++] = arrRight[indexRight];
                }else{
                    userfullLen--;
                }
                indexRight++;
            }
        }

        while (indexLeft < sizeLeft){
            arr[indexArr++] = arrLeft[indexLeft++];
        }

        while (indexRight < sizeRight){
            arr[indexArr++] = arrRight[indexRight++];
        }
        return userfullLen;
    }




    public static void divideMergeSortDes(int[] arr,int start,int end){
        if (null == arr){
            return;
        }
        if (start < end){
            int mid = (start + end)/2;
            divideMergeSortDes(arr,start,mid);
            divideMergeSortDes(arr,mid+1,end);
            mergeDes(arr,start,mid,end);
        }
    }

    public static void mergeDes(int[] arr,int start,int mid,int end){
        int sizeLeft = mid - start + 1 + 1;// +1 start flag = max
        int sizeRight = end - (mid + 1) + 1 + 1 ;// +1 start flag = max

        int[] arrLeft = new int[sizeLeft];
        int[] arrRight = new int[sizeRight];

        arrLeft[0] = Integer.MAX_VALUE;
        System.arraycopy(arr,start,arrLeft,1,sizeLeft - 1);


        arrRight[0] = Integer.MAX_VALUE;
        System.arraycopy(arr,mid+1,arrRight,1,sizeRight - 1);


        int indexLeft = arrLeft.length - 1,indexRight = arrRight.length - 1;
        while (end >= start){
            if (arrLeft[indexLeft] < arrRight[indexRight]){
                arr[end--] = arrLeft[indexLeft--];
            }else{
                arr[end--] = arrRight[indexRight--];
            }
        }



    }














}
