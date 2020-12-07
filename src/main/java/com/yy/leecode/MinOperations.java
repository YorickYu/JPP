package com.yy.leecode;

public class MinOperations {
    public static int minOperations(int[] nums, int x) {
        int count = 0;
        int p1 = 0;
        int p2 = nums.length-1;

        while (p2>p1) {
            System.out.println(p1+":"+nums[p1]+ "," +p2+":"+ nums[p2]);
            System.out.println("减之前"+x);
            if (nums[p1] + nums[p2] == x) {
                // +2 返回
                count +=2;
                x = 0;
                break;
            }else if (nums[p1] + nums[p2] < x) {
                // 两边都可以移动，选大的移
                count += 1;
                x -= nums[p1] >= nums[p2] ? nums[p1++] : nums[p2--];
            }else if (nums[p1] + nums[p2] > x) {
                if (nums[p1] == x || nums[p2] == x) {
                    // +1 返回
                    count += 1;
                    x = 0;
                    break;
                }else if (nums[p1] > x && nums[p2] < x) {
                    // 移动p2
                    x -= nums[p2--];
                    count += 1;
                }else if (nums[p2] > x && nums[p1] < x) {
                    // 移动p1
                    x -= nums[p1++];
                    count += 1;
                } else if (nums[p1] < x && nums[p2] < x){
                    x -= nums[p1] <= nums[p2] ? nums[p1++] : nums[p2--];
                    count += 1;
                } else {
                    // -1
                    count = -1;
                    break;
                }
            }
            System.out.println("减之后"+x);
        }
        return x == 0 ? count: -1;
    }


    public static void main(String[] args) {
//        int[] nums = new int[]{1,1,4,2,3}; //5
        int[] nums = new int[]{3,2,20,1,1,3}; // 10
//        int[] nums = new int[]{1,1}; // 3
//        int[] nums = new int[]{5207,5594,477,6938,8010,7606,2356,6349,3970,751,5997,6114,9903,3859,6900,7722,2378,1996,8902,228,4461,90,7321,7893,4879,9987,1146,8177,1073,7254,5088,402,4266,6443,3084,1403,5357,2565,3470,3639,9468,8932,3119,5839,8008,2712,2735,825,4236,3703,2711,530,9630,1521,2174,5027,4833,3483,445,8300,3194,8784,279,3097,1491,9864,4992,6164,2043,5364,9192,9649,9944,7230,7224,585,3722,5628,4833,8379,3967,5649,2554,5828,4331,3547,7847,5433,3394,4968,9983,3540,9224,6216,9665,8070,31,3555,4198,2626,9553,9724,4503,1951,9980,3975,6025,8928,2952,911,3674,6620,3745,6548,4985,5206,5777,1908,6029,2322,2626,2188,5639};
        int count = MinOperations.minOperations(nums, 10);
        System.out.println(count);
    }
}
