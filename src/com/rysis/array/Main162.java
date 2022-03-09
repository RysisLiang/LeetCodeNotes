package com.rysis.array;

import java.util.List;

/**
 * 寻找峰值
 */
public class Main162 {

    public static void main(String[] args) {
        List.of(
                new int[]{1, 2, 3, 1}, // 2
                new int[]{1, 2, 1, 3, 5, 6, 4} // 1 or 5
        ).forEach(nums -> System.out.println(findPeakElement(nums)));
    }

    // 二分查找的-模板3
    public static int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + ((right - left) / 2);

            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                return mid;
            }

            // 去下半区找峰值
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return nums[left] > nums[right] ? left : right;
    }

    // 二分查找的-模板2
    public static int findPeakElement1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) / 2);

            // 去下半区找峰值
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }

        }

        return left;
    }
}
