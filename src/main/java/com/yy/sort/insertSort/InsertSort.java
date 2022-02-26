package com.yy.sort.insertSort;

/**
 * @className: InsertSort
 * @description:
 * @author: yloopdaed
 * @date: 2022/2/26
 **/
public class InsertSort {
    static void insertSort(int a[]) {
        int i,temp,p;
        //从第２个元素开始
        for (i = 1; i<a.length; i++){
            temp = a[i];//将待插入元素拿出来
            p = i-1;
            while (p>=0 && temp<a[p]){
                // 比较大的元素向后挪一位，腾出空间
                a[p+1] = a[p];
                p--; // 向前扫描
            }
            //插入
            a[p+1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {6,1,2,7,9,3,4,5,10,8};
        InsertSort.insertSort(arr);
    }
}
