package com.rysis.bank;

/**
 * Main69
 * x的平方根
 *
 * @author rysis
 * @version 1.00
 * @date 2021/6/20 16:55
 */
public class Main69 {

    public static void main(String[] args) {
//        int i = 4;// 2
//        int i = 8;// 2
        int i = 25;// 5

        System.out.println(mySqrt(i));
    }

    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x < 4) {
            return 1;
        }
        int left = 1, right = x;
        while (left <= right) {
            // 位运算避免溢出和增加效率
            int mid = (left + right) >>> 1;
            // 使用除法避免int溢出
            if (x / mid == mid) {
                return mid;
            } else if (x / mid > mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 这里left > right且无法完全平方根，所以取较小的那个数作为结果
        return right;
    }
}
