package com.rysis.bit;

/**
 * Main_191
 * 位1的个数
 *
 * @author rysis
 * @version 1.00
 * @date 2021/3/22 18:23
 */
public class Main191 {

    public static void main(String[] args) {
//        int n = 3; //
        int n = -3; // 31
        System.out.println(hammingWeight(n));
    }

    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (Math.abs(n % 2) == 1) {
                count++;
            }
            n = n >>> 1;
        }
        return count;
    }
}
