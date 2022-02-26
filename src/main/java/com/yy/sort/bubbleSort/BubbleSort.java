package com.yy.sort.bubbleSort;

/**
 * @className: BubbleSort
 * @description:
 * @author: yloopdaed
 * @date: 2022/2/26
 **/
public class BubbleSort {

    static void bublleSort(int []arr) {
        int length = arr.length;
        for(int i = 0; i < length - 1; i++) { //趟数
            for(int j = 0; j < length - i - 1; j++) { //比较次数
                if(arr[j] > arr[j+1]) { // 升序
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {6,1,2,7,9,3,4,5,10,8};
        BubbleSort.bublleSort(arr);
    }
}
