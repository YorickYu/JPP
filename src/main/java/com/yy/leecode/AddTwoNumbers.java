package com.yy.leecode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

//private class  LinkNode
public class AddTwoNumbers {
//    public ListNode AddTwoNumbers(ListNode l1, ListNode l2) {
//
//    }

    public static int[] twoSum(int[] nums, int target) {
        int index_start = 0;
        int index_end = nums.length - 1;
        int a = nums[index_start];
        int b = nums[index_end];
        while (index_start < index_end && a + b != target) {
            if (index_end == index_start+1) {
                index_end = nums.length-1;
                index_start+=1;
            }else {
                index_end-=1;
            }
            b = nums[index_end];
            a = nums[index_start];
        }
        return index_start == index_end? new int[]{} : new int[]{index_start, index_end};
    }

    public static int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length ; i++) {
            int res = target - nums[i];
            if (map.containsKey(res)) {
                return new int[] {map.get(res), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static int[] twoSum3(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]+nums[j]==target) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
//        int nums[] = {2, 7, 11, 15};
        int nums[] = {3,2,4};
        int[] ints = twoSum3(nums, 8);
        System.out.println(ints.toString());
    }

}
