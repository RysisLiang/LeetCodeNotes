package com.rysis.array;

import com.rysis.util.ArrayUtil;

/**
 * 搜索旋转排序数组2
 */
public class Main81 {

    public static void main(String[] args) {
//        String nums = "[2,5,6,0,0,1,2]";
//        int target = 0;
        String nums = "[1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1]";
        int target = 2;
        System.out.println(search(ArrayUtil.handleToIntArray(nums), target));
    }

    // 这回不先把旋转后的数组，拆成两个连续递增的数组了。直接二分
    public static boolean search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // 计算中位索引
            int mid = (left + right) / 2;
            // 检查边界
            if (nums[left] == target || nums[right] == target || nums[mid] == target) {
                return true;
            }
            // 前半段边界一致时，缩小前半段
            if (nums[left] == nums[mid]) {
                left++;
                continue;
            }
            // 拆出两段后，肯定有一段是升序数组，另一段是两小段升序。先找出升序中是否存在target
            if (nums[left] < nums[mid]) { // 前半段有序
                if (nums[left] < target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // 后半段有序
                if (nums[mid] < target && target < nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }

    // 就是一个升序的数组，经过旋转，变成了两段升序的数组，然后找出这个元素是否存在。
    // 除了最简单的遍历之外。使用二分法，将数组的分成两段升序的数组，然后判断元素是否在两侧，然后进行递归操作；
    // 由于元素是可以重复的，所以两段数组的min和max是可能重复的。
    public static boolean search1(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target;
        }

        int left = 0, right = nums.length - 1, mid = right / 2;
        // 先找出分段数组中间点
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                mid = i - 1;
                break;
            }
        }

        // 检查是否是边界重复值
        if (target == nums[left] || target == nums[right]) {
            return true;
        }
        if (target > nums[left]) {
            right = mid;
        } else if (target < nums[right]) {
            left = mid + 1;
        } else {
            return false;
        }

        while (left <= right) {
            mid = (left + right) / 2;
            if (target == nums[mid]) {
                return true;
            }
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
