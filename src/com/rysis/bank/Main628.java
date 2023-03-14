package com.rysis.bank;

import java.util.Arrays;

/**
 * Main_628
 * 三个数的最大乘积
 *
 * @author rysis
 * @version 1.00
 * @date 2021/1/20 18:24
 */
public class Main628 {

    public static void main(String[] args) {
//        String s = "[1,2,3]"; // 6
//        String s = "[1,2,3,2,1,2,4]"; // 24
//        String s = "[0,0,0]"; // 0
        String s = "[-1,-2,-3]"; // -6
        System.out.println(maximumProduct(handleParam(s)));
    }

    private static int[] handleParam(String str) {
        return Arrays.stream(str.substring(1, str.length() - 1).split(",")).mapToInt(Integer::parseInt).toArray();
    }

    // 排序法
    public static int maximumProduct(int[] nums) {
        // todo
        return 1;
    }

    // mine 找出三个数的乘积最大的
    // 全正数/全负数 -》 三个最大的数
    // 有正有负 -》 都看绝对值的话，也是取其中最大的三个数。但是其实只有两种情况
    //  --+ 最小的两个负数*最大的正数（-++ 最大的负数*最小的两个正数，有且仅有一个负数时，这个也不成立）
    //  +++ 最大的三个正数（三个负数不可能，只要有正数，那么结果一定是正数）
    public static int maximumProduct1(int[] nums) {
        int top1 = Integer.MIN_VALUE;
        int top2 = Integer.MIN_VALUE;
        int top3 = Integer.MIN_VALUE;
        int low1 = Integer.MAX_VALUE;
        int low2 = Integer.MAX_VALUE;
        for (int num : nums) {
            // 每次进来都会检查最大值
            if (num > top1) {
                top3 = top2;
                top2 = top1;
                top1 = num;
            } else if (num > top2) {
                top3 = top2;
                top2 = num;
            } else if (num > top3) {
                top3 = num;
            }
            // 每次进来都会检查最小值
            if (num < low1) {
                low2 = low1;
                low1 = num;
            } else if (num < low2) {
                low2 = num;
            }
        }
        // 要防止初始值来干扰这步比较
        return Math.max(top1 * top2 * top3, top1 * low1 * low2);
    }
}
