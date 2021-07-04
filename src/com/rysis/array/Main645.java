package com.rysis.array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Main645
 * 错误的结合
 */
public class Main645 {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 2, 4}; // 2,3
//        int[] nums = {1, 1}; // 1,2
//        int[] nums = {2, 2, 3}; // 2,1
        int[] nums = {3, 2, 2}; // 2,1

        System.out.println(Arrays.toString(findErrorNums(nums)));
    }

    // nums 可能不是有序的，使用累加计算出当前的序列的和，然后在计算该数组应有的值，差值就是哪个重复元素所缺少的值。
    public static int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        int sum1 = 0;
        int sum2 = ((1 + nums.length) * nums.length) / 2;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            sum1 += num;
            if (set.contains(num)) {
                res[0] = num;
            }
            set.add(num);
        }

        res[1] = sum2 - sum1 + res[0];
        return res;
    }

    // nums 可能不是有序的，两次循环判断
    public static int[] findErrorNums1(int[] nums) {
        int[] res = new int[2];
        // 设计一个桶，索引是该元素，值是次数
        int[] b = new int[nums.length];

        for (int i : nums) {
            // 如果过该元素出现古
            if (b[i - 1] == 1) {
                res[0] = i;
            }
            // nums 元素放入桶中并计数
            b[i - 1]++;
        }

        System.out.println(Arrays.toString(b));

        for (int i = 0; i < b.length; i++) {
            if (b[i] == 0) {
                res[1] = i + 1;
                return res;
            }
        }


        return res;
    }
}
