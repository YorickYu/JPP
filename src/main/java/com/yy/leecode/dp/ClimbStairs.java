package com.yy.leecode.dp;

public class ClimbStairs {

    //假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    //
    // 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    //
    // 注意：给定 n 是一个正整数。
    //
    // 示例 1：
    //
    // 输入： 2
    //输出： 2
    //解释： 有两种方法可以爬到楼顶。
    //1.  1 阶 + 1 阶
    //2.  2 阶
    //
    // 示例 2：
    //
    // 输入： 3
    //输出： 3
    //解释： 有三种方法可以爬到楼顶。
    //1.  1 阶 + 1 阶 + 1 阶
    //2.  1 阶 + 2 阶
    //3.  2 阶 + 1 阶
    //
    // Related Topics 动态规划


    /**
     * 递归
     * 时间复杂度 O(2^n)
     * @param n
     * @return
     */
    public int climbStairs_re(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        return climbStairs_re(n-1) + climbStairs_re(n-2);
    }

    public int climbStairs_dp(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 2; // 2 or 1+1
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }

    public int climbStairs_rdp(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        int n_1 = 2;
        int n_2 = 1;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = n_1 + n_2;
            n_2 = n_1;
            n_1 = result;
        }
        return result;
    }


    public static void main(String[] args) throws InterruptedException {
        ClimbStairs climbStairs = new ClimbStairs();
        int i = climbStairs.climbStairs_rdp(5);
        System.out.println("i = " + i);

        int a = 1;
        if (--a == 0) {
            System.out.println(a);
        }
    }


}
