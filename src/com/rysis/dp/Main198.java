package com.rysis.dp;

/**
 * 打家劫舍
 */
public class Main198 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1}; // 12

        System.out.println(rob(nums));
    }

    // rob1方案是使用数组存储中间态的，但是通过状态方程知道，dp[i]只与dp[i -1]和dp[i-2]有关，减少i空间的使用率
    public static int rob(int[] nums) {
        // dp[i-2]
        int dp2 = 0;
        // dp[i-1]
        int dp1 = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            int temp = dp1;
            dp1 = Math.max(dp2 + nums[i - 1], dp1);
            dp2 = temp;
        }
        return dp1;
    }

    // DP如题，每次偷取的房屋肯定不是相连的。中间至少间隔一个房屋。
    // dp[i] = Max(dp[i - 2] + nums[i], dp[i - 1])
    // i这个位置的金额，可能是i-2位置累加和+nums[i]的和，或者是i -1的累加和。的最大值
    // dp[0] = 0, dp[1] = nums[1]
    public static int rob1(int[] nums) {
        // 用于记录不同位置的和的最大值
        int[] dp = new int[nums.length + 1];

        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }
}
