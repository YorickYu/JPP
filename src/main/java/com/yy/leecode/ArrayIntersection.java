package com.yy.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;


// leetcode 350题 两个数组的交集2
public class ArrayIntersection {

    // 1 hashmap
    /*
    *   问题1：同数组重复元素处理不当
    *   问题2：统计HashMap处理不当
    *
    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> arrayMap = new HashMap<Integer, Integer>();
        int[] returnints = new int[(nums1.length>nums2.length)?nums2.length:nums1.length];

        for (int a: nums1) {
            Integer times = 0;
            if (arrayMap.get(a) != null)
                times = arrayMap.get(a);
            arrayMap.put(a, ++times);
        }

        for (int b: nums2) {
            Integer times = 0;
            if (arrayMap.get(b) != null)
                times = arrayMap.get(b);
            arrayMap.put(b, ++times);
        }

        int index = 0;
        Iterator<Integer> iterator = arrayMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            Integer values = arrayMap.get(key);
            if (values > 1) {
                returnints[index++] = key;
            }
        }
        return returnints;
    }
    */

    //
    public static int[] intersect(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> array1Map = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> array2Map = new HashMap<Integer, Integer>();
        for (int a: nums1) {
            Integer times = 0;
            if (array1Map.get(a) != null)
                times = array1Map.get(a);
            array1Map.put(a, ++times);
        }
        for (int b: nums2) {
            Integer times = 0;
            if (array2Map.get(b) != null)
                times = array2Map.get(b);
            array2Map.put(b, ++times);
        }

        // 最小遍历次数
        HashMap<Integer, Integer> target;
        HashMap<Integer, Integer> copy;
        if (nums1.length > nums2.length) {
            target = array2Map;
            copy = array1Map;
        }else {
            target = array1Map;
            copy = array2Map;
        }

        ArrayList<Integer> arrList = new ArrayList<Integer>(target.keySet().size());
        Iterator<Integer> iterator = target.keySet().iterator();
        while (iterator.hasNext()) {
            Integer targetKey = iterator.next();
            Integer targetValue = target.get(targetKey);
            Integer copyValue = copy.get(targetKey);

            if (copyValue == null)
                continue;

            int finalValue = Math.min(targetValue, copyValue);
            for (int i = 0; i < finalValue; i++) {
                arrList.add(targetKey);
            }
        }
        int[] returnints = new int[arrList.size()]; // = new int[(nums1.length>nums2.length)?nums2.length:nums1.length];
        int index = 0;
        for (final Integer value: arrList) {
            returnints[index++] = value;
        }

        return returnints;
    }

    // 2 双指针
    public static int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int f1 = 0, f2 = 0;
        ArrayList<Integer> arrl = new ArrayList<>();
        while(f1<nums1.length && f2<nums2.length){
            if(nums1[f1]<nums2[f2]){
                f1++;
            }else if(nums1[f1]>nums2[f2]){
                f2++;
            }else{
                arrl.add(nums1[f1]);
                f1++;
                f2++;
            }
        }
        int[] returnints = new int[arrl.size()];
        for(int i=0; i<arrl.size(); i++)
        {
            returnints[i] = arrl.get(i);
        }
        return returnints;
    }

    public static void main(String[] args) {
//        int[] num1 = new int[]{1,2,2,1};
//        int[] num2 = new int[]{2,2};

        int[] num1 = new int[]{4,9,5};
        int[] num2 = new int[]{9,4,9,8,4};

        int[] intersect = ArrayIntersection.intersect2(num1, num2);
        for (int i: intersect) {
            System.out.println(i);
        }
    }

}
