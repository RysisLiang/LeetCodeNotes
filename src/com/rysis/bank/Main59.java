package com.rysis.bank;

import java.util.Arrays;

/**
 * Main_59
 * 螺旋矩阵2
 *
 * @author rysis
 * @version 1.00
 * @date 2021/3/16 16:48
 */
public class Main59 {

    public static void main(String[] args) {
        int n = 3; // [[1,2,3],[8,9,4],[7,6,5]]
//        int n = 1; // [[1]]
//        int n = 2; // [[1, 2], [4, 3]]

        System.out.println(Arrays.deepToString(generateMatrix(n)));
    }

    // 模拟
    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        String sign = "right"; // 移动的方向
        int r = n - 1, b = n - 1, l = 0, t = 1; // 四个方向的边界
        int x = 0, y = -1;

        for (int i = 1; i <= n * n; i++) {
            if (sign.equals("right")) {
                if (++y >= r) {
                    r--;
                    sign = "bottom";
                }
            } else if (sign.equals("bottom")) {
                if (++x >= b) {
                    b--;
                    sign = "left";
                }
            } else if (sign.equals("left")) {
                if (--y <= l) {
                    l++;
                    sign = "top";
                }
            } else {
                if (--x <= t) {
                    t++;
                    sign = "right";
                }
            }
            result[x][y] = i;
        }
        return result;
    }

    // 模拟 写错了以为蛇形尾随呢。。
    public static int[][] generateMatrix1(int n) {
        int[][] result = new int[n][n];
        int incr; // 递增递减，步长变量
        int num = -n + 1; // 累加的元素。初始值用于抵消开始的差值
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) { // 偶数行->递增
                incr = 1;
            } else { // 奇数行->递减
                incr = -1;
            }
            // 下一行的起始数目 = 上一行的结束 + 每行的个数 - 步长
            // （由于下面计算每次都会+步长，所以这里先把初始数据中的步长减去）
            num += n - incr;
            // 存入数据
            for (int j = 0; j < n; j++) {
                result[i][j] = num += incr;
            }
        }
        return result;
    }
}
