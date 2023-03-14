package com.rysis.bank;

/**
 * Main_1232
 * 缀点成线
 *
 * @author rysis
 * @version 1.00
 * @date 2021/1/17 15:53
 */
public class Main1232 {

    public static void main(String[] args) {
        int[][] coordinates = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}}; // true
//        int[][] coordinates = {{1, 1}, {2, 2}, {3, 4}, {4, 5}, {5, 6}, {7, 7}}; // false
        System.out.println(checkStraightLine(coordinates));
    }

    // 数学方法，求直线的斜率
    public static boolean checkStraightLine(int[][] coordinates) {
        for (int i = 1; i < coordinates.length - 1; i++) {
            int x = coordinates[i][0] - coordinates[i - 1][0];
            int y = coordinates[i][1] - coordinates[i - 1][1];
            int x1 = coordinates[i + 1][0] - coordinates[i][0];
            int y1 = coordinates[i + 1][1] - coordinates[i][1];
            // 注意：因为除数不能为0的原因，使用3个点做斜率相等公式。然后转乘相乘的等式
            // y0 - y-1 / x0 - x-1 = y+1 - y0 / x+1 - x0
            if (y * x1 != y1 * x) {
                return false;
            }
        }
        return true;
    }
}
