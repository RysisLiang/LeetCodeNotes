package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.List;

/**
 * 只出现一次的数字
 */
public class Main136 {

    public static void main(String[] args) {

        final List<String> strings = List.of(
                "[2,2,1]", // 1
                "[4,1,2,1,2]", // 4
                "[1]" // 1
        );
        for (String string : strings) {
            System.out.println(singleNumber(ArrayUtil.handleToIntArray(string)));
        }
    }

    // 线性时间，那么就意味着只能遍历一次出结果
    // 常量空间， 则表示不能使用其它的数据结构，只能是指针式的变量
    public static int singleNumber(int[] nums) {
        // a ^ 0 = a
        // a ^ a = 0
        // a ^ a ^ a = a
        // 只要是偶数个数，结果一定是0，那么剩下的那个就是奇数个
        // 说白了，就是把数组中，两两成对的都异或，然后都是0，最后那个没成对的与0异或还是自己
        int rs = 0;
        for (int num : nums) {
            rs = num ^ rs;
        }
        return rs;
    }

}
