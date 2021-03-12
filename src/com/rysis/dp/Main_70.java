package com.rysis.dp;

/**
 * Main_70
 * 爬楼梯
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/3/12 16:14
 */
public class Main_70 {

    public static void main(String[] args) {
//        int n = 2; // 2
//        int n = 4; // 5
        int n = 25; // 121393
        System.out.println(climbStairs(n));
    }

    // dp
    // 依次计算，第一级有一种，第二级有两种，第三级=三种，第四级=5种。
    // 就是任意一个台阶的种类=前面-1台阶的种类+前面-2台阶的种类
    // 推导公式 》d(n) = d(n - 1) + d(n - 2)
    public static int climbStairs(int n) {
        if (n <= 3) {
            return n;
        }
        // 定义数组，用于保存状态（每个台阶的方法个数）
        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }
}
