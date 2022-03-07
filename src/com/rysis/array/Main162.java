package com.rysis.array;

/**
 * 寻找峰值
 */
public class Main162 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};

        System.out.println(findPeakElement(nums));
    }

    // 二分查找的-模板2
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left +  ((right - left) / 2);

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
