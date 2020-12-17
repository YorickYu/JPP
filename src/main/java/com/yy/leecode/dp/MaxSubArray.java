package com.yy.leecode.dp;

import java.util.concurrent.CopyOnWriteArrayList;

public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        int pointvalue = 0;
        int max = nums[0];
        for (int num : nums) {
            pointvalue = Math.max(pointvalue+num, num);
            max = Math.max(max, pointvalue);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        int arr[] = {-2,1,-3,4,-1,2,1,-5,4};
        int i = maxSubArray.maxSubArray(arr);
        System.out.println("i = " + i);
    }
}
