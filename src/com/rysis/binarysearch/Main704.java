package com.rysis.binarysearch;

/**
 * Main704
 * 二分查找
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/6/20 16:55
 */
public class Main704 {

    public static void main(String[] args) {
//        int[] nums = {-1, 0, 3, 5, 9, 12};
//        int target = 9; // 4
//        int target = 2; // -1
        int[] nums = {5};
        int target = 5; // 0

        System.out.println(search(nums, target));
    }

    // 目标数组是有序的、可以使用角标查询
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // 二分中间值
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
