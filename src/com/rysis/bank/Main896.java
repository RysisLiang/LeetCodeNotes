package com.rysis.bank;

import com.rysis.util.ArrayUtil;

/**
 * Main_896
 * 单调数列
 *
 * @author rysis
 * @version 1.00
 * @date 2021/2/28 15:32
 */
public class Main896 {

    public static void main(String[] args) {
//        String s = "[1,2,2,3]"; // true
//        String s = "[6,5,4,4]"; // true
        String s = "[1,3,2]"; // false
//        String s = "[1,1,1]"; // true
//        String s = "[11,11,9,4,3,3,3,1,-1,-1,3,3,3,5,5,5]"; // false
        System.out.println(isMonotonic(ArrayUtil.handleToIntArray(s)));
    }

    public static boolean isMonotonic(int[] A) {
        if (A.length < 3) {
            return true;
        }
        int sub = 0;
        for (int i = 0; i < A.length - 1; i++) {
            int i1 = A[i];
            int i2 = A[i + 1];
            if ((i2 - i1) * sub < 0) {
                return false;
            }
            if (sub == 0) {
                sub = i2 - i1;
            }
        }
        return true;
    }
}
