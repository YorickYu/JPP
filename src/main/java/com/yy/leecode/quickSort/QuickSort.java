package com.yy.leecode.quickSort;

/**
 * @className: QuickSort
 * @description:
 * @author: yloopdaed
 * @date: 2022/2/25
 **/
public class QuickSort {
    int partition(int arr[], int low, int high)//返回划分的中间值
    {
        int key;
        key = arr[low];//相当于在索引low处挖坑，下一个就要找合适的数据来填坑

        while(low < high)
        {
            while(low < high && arr[high] >= key){
                high --;
            }
            if(low < high)
                arr[low ++] = arr[high];//找到合适的数据填到了low坑，但是形成了high坑，继续找合适的数据

            while( low < high && arr[low] <= key)
                low ++;
            if( low < high)
                arr[high --] =  arr[low];//low又成了坑
        }

        arr[low] = key;//将key填到这个坑
        return low;
    }
    void quick_sort(int num[], int low, int high)
    {
        int pos;
        if(low < high){
            pos = partition(num, low, high);
            quick_sort(num, low, pos-1);
            quick_sort(num, pos+1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = {6,1,2,7,9,3,4,5,10,8};
        QuickSort quickSort = new QuickSort();
        quickSort.quick_sort(arr, 0, arr.length-1);
        System.out.println("");
    }

}
