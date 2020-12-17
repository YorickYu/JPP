package com.yy.leecode.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class LengthOfLIS {

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) { // 记录最长子数组下标+1
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {

        int arr[] = {1,9,5,9,3};

        LengthOfLIS lengthOfLIS = new LengthOfLIS();
        int i = lengthOfLIS.lengthOfLIS(arr);
        System.out.println("i = " + i);


    }


}


