package com.yy.sort.shellSort;

/**
 * @className: ShellSort
 * @description:
 * @author: yloopdaed
 * @date: 2022/2/26
 **/
public class ShellSort {
    static int[] insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        //希尔排序  升序
        for (int d = arr.length / 2; d > 0; d /= 2) { //d：增量  7   3   1
            for (int i = d; i < arr.length; i++) {
                //i:代表即将插入的元素角标，作为每一组比较数据的最后一个元素角标
                //j:代表与i同一组的数组元素角标
                for (int j = i - d; j >= 0; j -= d) { //在此处-d 为了避免下面数组角标越界
                    if (arr[j] > arr[j + d]) {// j+d 代表即将插入的元素所在的角标
                        //符合条件，插入元素（交换位置）
                        int temp = arr[j];
                        arr[j] = arr[j + d];
                        arr[j + d] = temp;
                    }
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        int[] ints = ShellSort.insertionSort(arr);
        System.out.println();
    }
}
