package com.rysis.number;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;

/**
 * Main_1744
 * 你能在你最喜欢的那天吃到你最喜欢的糖果吗
 *
 * @author rysis
 * @version 1.00
 * @date 2021/6/1 23:44
 */
public class Main1744 {

    public static void main(String[] args) {
        // [false,true,true,false,false]
        String candiesCount = "[5,2,6,4,1]", queries = "[[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]";

        System.out.println(Arrays.toString(canEat(
                ArrayUtil.handleToIntArray(candiesCount),
                ArrayUtil.handleToNestedIntArray(queries)
        )));
    }

    // 前缀和
    public static boolean[] canEat(int[] cs, int[][] qs) {
        // 拥有的糖果类型数目
        int m = cs.length;
        // 需要判断的条件个数
        int n = qs.length;
        // 结果集
        boolean[] ans = new boolean[n];

        // 记录每个糖果之前的糖果总数，index=0是没有糖果的类型
        long[] sum = new long[m + 1];

        for (int i = 1; i <= m; i++) {
            // 上一个糖果类型前的糖果总数 + 上一个类型的糖果个数
            sum[i] = sum[i - 1] + cs[i - 1];
        }

        // 开始判断各个条件
        for (int i = 0; i < n; i++) {
            // 糖果类型
            int type = qs[i][0];
            // 吃到糖果的天数。由于要算上0天，所以需要天数= +1
            int days = qs[i][1] + 1;
            // 每天能吃的糖果个数上限
            int max = qs[i][2];
            // 吃到目标类型糖果前所需要吃掉的糖果的天数
            long minDays = sum[type] / max + 1;
            // 吃完目标类型糖果的所耗费的最大天数
            long maxDays = sum[type + 1];
            // 判断是否在范围内即可
            ans[i] = minDays <= days && days <= maxDays;
        }

        return ans;
    }
}
