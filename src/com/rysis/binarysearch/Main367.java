package com.rysis.binarysearch;

import java.util.List;

/**
 * 367-有效的完全平方数
 *
 * @author rysis
 * @version 1.00
 */
public class Main367 {

    public static void main(String[] args) {
        final List<Integer> integers = List.of(100, 16, 14, 9, 8, 7, 6, 2, 1);

        for (Integer num : integers) {
            System.out.printf("num %d -> %b%n", num, isPerfectSquare(num));
        }

    }

    // 二分法
    public static boolean isPerfectSquare(int num) {
        int left = 0;
        int right = num;

        while (left <= right) {
            // 9 / 2 = 4
            int mid = (right - left) / 2 + left;
            // 4 * 4 = 16
            long rs = (long) mid * mid;

            if (rs > num) {
                right = mid - 1;
            } else if (rs < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

}
