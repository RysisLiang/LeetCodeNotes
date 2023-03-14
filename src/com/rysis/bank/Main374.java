package com.rysis.bank;

/**
 * Main_374
 * 猜数字大小
 *
 * @author rysis
 * @version 1.00
 * @date 2021/6/14 14:12
 */
public class Main374 {

    public static void main(String[] args) {
        int n = 10, pick = 6;

        System.out.println(guessNumber(n));
    }

    static int guess(int num) {
        return 0;
    }

    // 二分法
    public static int guessNumber(int n) {
        int left = 1;
        int right = n;

        while(left < right) {
            int mid = left + (right - left) / 2;

            switch (guess(mid)) {
                case 1: // [mid, right]
                    left = mid + 1;
                    break;
                case -1: // [left, mid]
                    right = mid - 1;
                    break;
                default:
                    return mid;
            }
        }
        return left;
    }
}
