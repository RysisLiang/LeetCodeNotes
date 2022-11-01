package com.rysis.binarysearch;

/**
 * Main33
 * x的平方根
 *
 * @author rysis
 * @version 1.00
 * @date 2021/6/20 16:55
 */
public class Main33 {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int i = 0; // 4

        System.out.println(search(nums, i));
    }

    // 数组元素不重复
    // 原本是升序的，后经过旋转则变为了两端升序的组合
    // 找出升序的交接点，然后看是属于那部分
    public static int search(int[] nums, int target) {
        // 左右边界
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[left] == target) {
                return left;
            }
            if (nums[right] == target) {
                return right;
            }
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] < nums[mid]) { // 左半段是连续的
                if (nums[left] < target && target < nums[mid]) { // 在左半段
                    right = mid - 1;
                } else { // 在右半段
                    left = mid + 1;
                }
            } else { // 右半段是连续的
                if (nums[mid] < target && target < nums[right]) { // 在右半段
                    left = mid + 1;
                } else { // 在左半段
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
