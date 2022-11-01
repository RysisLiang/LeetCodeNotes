package com.rysis.array;

import java.util.Arrays;

/**
 * Main_26
 * 删除排序数组中的重复项
 *
 * @author rysis
 * @version 1.00
 * @date 2021/1/7 11:51
 */
public class Main26 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        int[] nums = {0, 0};
        System.out.println(removeDuplicates(nums));
        System.out.println("nums = " + Arrays.toString(nums));
    }

    // 自我实现法
    public static int removeDuplicates1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int lastvalue = nums[0];
        int lastIndex = 0;
        for (int i = 0, len = nums.length; i < len; i++) {
            int value = nums[i];
            if (value == lastvalue) {
                continue;
            }
            if (value > lastvalue) {
                lastvalue = value; // 重置新的元素
                lastIndex += 1; // 记录当前插入的位置
                nums[lastIndex] = value; // 替换元素
            }
        }
        return lastIndex + 1;
    }

    // 双指针法
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 复刻下双指针方法
        int slowIndex = 0;
        for (int i = 1, len = nums.length; i < len; i++) {
            if (nums[i] > nums[slowIndex]) {
                slowIndex++;
                nums[slowIndex] = nums[i];
            }
        }
        return slowIndex + 1;
    }
}
