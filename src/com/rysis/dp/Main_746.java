package com.rysis.dp;

import com.rysis.util.ArrayUtil;

/**
 * Main_746
 * 使用最小花费爬楼梯
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/25 15:29
 */
public class Main_746 {

    public static void main(String[] args) {
        String cost = "[10, 15, 20]"; // 15
//        String cost = "[1, 100, 1, 1, 1, 100, 1, 1, 100, 1]"; // 6
        System.out.println(minCostClimbingStairs1(ArrayUtil.handleToIntArray(cost)));
        System.out.println(minCostClimbingStairs(ArrayUtil.handleToIntArray(cost)));
    }

    // 如果不使用数组记录体力消耗，每步的消耗只会与前两步相加，所以记录前两步的消耗记录
    public static int minCostClimbingStairs(int[] cost) {
        int last2 = cost[0]; // 上两步的累计体力消耗
        int last1 = cost[1]; // 上一步的累计体力消耗
        for (int i = 2; i < cost.length; i++) {
            int min = Math.min(last2, last1);
            // 更新前两步的累计体力消耗数据
            last2 = last1;
            last1 = min + cost[i]; // 到当前台阶时所花费的最小体力计算
        }
        return Math.min(last2, last1);
    }

    // 动态规划类型。
    // - 每次我们只能走一步或者两步，所落脚的位置就是我们需要花费的体力值
    // - 第一次可以从0或者1开始。
    // - 那么就可以看成从-1（地面）开始；到终点时以length+1为停止，这样就可以一直遵循每次都是1步或者两步的规律，且起点和终点是一致的。
    public static int minCostClimbingStairs1(int[] cost) {

        // 我们需要保证我们每步都是目前位置最小的体力花费，那么最终的体力花费肯定是最小的体力消耗
        int[] consume = new int[cost.length]; // 花费的体力
        for (int i = 0; i < 2; i++) {
            consume[i] = cost[i];
        }
        for (int i = 2; i < cost.length; i++) {
            // 到当前台阶时所花费的最小体力计算
            consume[i] = Math.min(consume[i - 2], consume[i - 1]) + cost[i];
        }

        return Math.min(consume[cost.length - 2], consume[cost.length - 1]);
    }
}
