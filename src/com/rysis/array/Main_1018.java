package com.rysis.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Main_1018
 * 可被5整除的二进制前缀
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/1/14 11:27
 */
public class Main_1018 {

    public static void main(String[] args) {
//        int[] a = {0, 1, 1}; // t,f,f
        int[] a = {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1};
        System.out.println(prefixesDivBy5(a));
    }

    // 注意提示里面有长度的限制，长度是小宇等于3000的，在java里会出现溢出的问题。
    // 0 / 5   / 10   / 15   / 20    / 25    / 50     / 100 ...这是一系列的能被5整除的数，分别转为二进制
    // 0 / 101 / 1010 / 1111 / 10100 / 11001 / 110010 / 1100100
    // 只取个位数
    public static List<Boolean> prefixesDivBy51(int[] A) {
        ArrayList<Boolean> result = new ArrayList<>();
        // 高位补0的操作
        int b = 0;
        for (int i : A) {
            // 这里右移虽然会出现长度溢出的情况，但是我们只看最后的个位数是不是0 or 5即可，先除10余数就是个位数
            b = ((b << 1) + i) % 10;
            result.add(b % 5 == 0);
        }
        return result;
    }

    // 同余定理-官方题解方法
    public static List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>();
        int prefix = 0;
        int length = A.length;
        for (int i = 0; i < length; i++) {
            prefix = ((prefix << 1) + A[i]) % 5;
            list.add(prefix == 0);
        }
        return list;
    }
}
