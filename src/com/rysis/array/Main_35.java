package com.rysis.array;

/**
 * Main_1
 *
 * @author liang
 * @version 1.00
 * @date 2021/1/6 14:41
 */
public class Main_35 {
    public static void main(String[] args) {
//        int[] nums = {1, 3, 5, 6}; // 2
//        int target = 5;
        int[] nums = {1, 3, 5, 6}; // 1
        int target = 2;
        System.out.println(searchInsert(nums, target));
    }

    // 还是首先来个暴力破解
    public int searchInsert1(int[] nums, int target) {
        int lastIndex = -1; // 上次的索引
        for (int i = 0, len = nums.length; i < len; i++) {
            int value = nums[i];
            if (value == target) {
                return i;
            }
            if (value > target) {
                return i;
            }
            lastIndex = i;
        }
        return lastIndex + 1;
    }

    // 二分查找
    public static int searchInsert(int[] nums, int target) {
        if (target <= nums[0]) {
            return 0;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }

        int min = 0;
        int max = nums.length - 1;
        while (min <= max) {
            // 中位数的索引
            int midI = (min + max) / 2;
            // 中位数索引的元素
            int midV = nums[midI];
            if (midV == target) {
                return midI;
            }
            // 缩小索引范围
            if (midV > target) {
                max = midI - 1;
            } else {
                min = midI + 1;
            }
        }
        return min;
    }
}
