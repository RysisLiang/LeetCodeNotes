package com.rysis.bank;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Main_264
 * 丑数2
 *
 * @author rysis
 * @version 1.00
 * @date 2021/4/11 00:24
 */
public class Main264 {

    public static void main(String[] args) {
//        int n = 10; // 12
//        int n = 7; // 8
        int n = 1400; // 8
        System.out.println(Integer.MAX_VALUE);
        System.out.println(nthUglyNumber1(n));
        System.out.println(nthUglyNumber(n));
    }

    // 使用小根堆记录最小值计算
    public static int nthUglyNumber(int n) {
        if (n <= 6) {
            return n;
        }
        // 结果集，可以去重
        TreeSet<Integer> treeSet = new TreeSet<>();
        // 小顶堆。因为这里可能出现特别大的数去超出int范围，从而干扰结果，所以使用long
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.offer(1L);
        while (treeSet.size() < n) {
            Long min = queue.poll();
            treeSet.add(min.intValue());
            long[] compute = computer(min);
            if (!queue.contains(compute[0])) queue.offer(compute[0]);
            if (!queue.contains(compute[1])) queue.offer(compute[1]);
            if (!queue.contains(compute[2])) queue.offer(compute[2]);
        }
        return treeSet.last();
    }

    private static long[] computer(long x) {
        long x1 = x * 2;
        long x2 = x * 3;
        long x3 = x * 5;
        return new long[]{x1, x2, x3};
    }

    // 丑数 dp(abc) = 2^a * 3^b * 5^c = dp(a-1*bc)*2 = dp(ab-1*c)*3 = dp(abc-1)*5
    // a/b/c的最小值是0
    public static int nthUglyNumber1(int n) {
        if (n <= 6) {
            return n;
        }
        // 记录每个最小值的
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int a = 0, b = 0, c = 0;
        // 判断大顶堆中的元素个数是否达到了n
        for (int i = 1; i <= n; i++) {
            int i1 = dp[a] * 2;
            int i2 = dp[b] * 3;
            int i3 = dp[c] * 5;
            int min = Math.min(Math.min(i1, i2), i3);
            dp[i] = min;
            if (i1 == min) {
                a++;
            }
            if (i2 == min) {
                b++;
            }
            if (i3 == min) {
                c++;
            }

        }
        return dp[n - 1];
    }

}
