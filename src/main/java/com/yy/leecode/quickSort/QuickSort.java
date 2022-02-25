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

    //////
    public void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int i = partitionSwap(nums, lo, hi);
        quickSort(nums, lo, i - 1);
        quickSort(nums, i + 1, hi);
    }

    public int partitionSwap(int[] nums, int lo, int hi) {
        int left = lo + 1;
        int right = hi;
        int pivot = nums[lo];

        while (left <= right) {
            // 左指针要么越界要么指向下一个 >= pivot 的位置
            while (left <= hi && nums[left] < pivot) {
                left++;
            }
            // 如果右侧所有值都 >= pivot right=lo
            while (right > lo && nums[right] >= pivot) {
                right--;
            }
            // 防止左指针越界
            if (left < right) {
                swap(nums, left, right);
            }
        }

        swap(nums, lo, right);

        return right;
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {6,1,2,7,9,3,4,5,10,8};
        QuickSort quickSort = new QuickSort();
        quickSort.quick_sort(arr, 0, arr.length-1);
        System.out.println("");
    }

}
