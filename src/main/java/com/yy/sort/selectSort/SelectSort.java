package com.yy.sort.selectSort;

/**
 * @className: SelectSort
 * @description:
 * @author: yloopdaed
 * @date: 2022/2/26
 **/
public class SelectSort {
    static void selectSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) { //进行n-1轮选择,也就是i的取值为【0，n-2】
            int min_index = i;
            //记录第i小的数所在的索引
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min_index])
                    min_index = j;
            }
            if (i != min_index) { //根据记录的第i小的数的索引，找到了第i小的数。然后将该数放到其正确位置。也就是第i个位置。
                swap(a, i, min_index);
            }
        }
    }

    static void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        SelectSort.selectSort(arr);
    }
}
