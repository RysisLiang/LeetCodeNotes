package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Main_137
 * 只出现一次的数字2
 *
 * @author rysis
 * @version 1.00
 * @date 2021/4/30 21:26
 */
public class Main137 {

    public static void main(String[] args) {
//        String nums = "[2,2,3,2]"; // 3
//        String nums = "[0,1,0,1,0,1,99]"; // 99
        String nums = "[30000,500,100,30000,100,30000,100]"; // 500

        System.out.println(singleNumber(ArrayUtil.handleToIntArray(nums)));
    }

    // 位运算。这个思路真的是巧啊~~~
    // a ^ 0 = a;  a ^ a = 0; => 0 ^ a ^ a = 0; 0 ^ a = a;
    // x = 0; y = 0;
    // x = x ^ a ^ y -> a
    // y = y ^ a ^ x -> 0
    // x = x ^ a ^ y -> 0
    // y = y ^ a ^ x -> a
    // x = x ^ a ^ y -> 0
    // y = y ^ a ^ x -> 0
    public static int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        long x = 0, y = 0;
        for (int num : nums) {
            x = x ^ (long) num ^ y;
            y = y ^ (long) num ^ x;
            System.out.printf("x=%d, y=%d %n", x, y);
        }
        return (int) x;
    }

    // 哈希表
    public static int singleNumber1(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        HashSet<Integer> hashSet = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();
        for (int num : nums) {
            // 临时哈希表中不存在 & 结果哈希表中已经存在
            if (!hashSet.contains(num) && !result.add(num)) {
                // 临时哈希表添加
                hashSet.add(num);
                // 结果哈希表删除
                result.remove(num);
            }
        }
        return result.iterator().next();
    }
}
