package com.rysis.number;

/**
 * Main_263
 * 丑数
 *
 * @author rysis
 * @version 1.00
 * @date 2021/4/11 00:24
 */
public class Main263 {

    public static void main(String[] args) {
//        int n = 6; // true
        int n = -6; // true
        System.out.println(isUgly(n));
    }

    public static boolean isUgly(int n) {
        // 负数一定不是丑数
        if (n <= 0) {
            return false;
        }

        while (n % 5 == 0) {
            n = n / 5;
        }
        while (n % 3 == 0) {
            n = n / 3;
        }
        while (n % 2 == 0) {
            n = n / 2;
        }
        return n == 1;
    }
}
