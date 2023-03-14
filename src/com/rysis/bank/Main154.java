package com.rysis.bank;

import java.util.Arrays;
import java.util.List;

/**
 * 寻找旋转排序数组中的最小值2
 */
public class Main154 {

    public static void main(String[] args) {
        List<int[]> p = Arrays.asList(
                new int[]{1, 3, 5}, // 1
                new int[]{2, 2, 2, 0, 1}, // 0
                new int[]{2, 2, 2} // 2
        );

        p.forEach(nums -> System.out.println(findMin(nums)));
    }

    public static int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            int m = nums[mid];
            int l = nums[left];
            int r = nums[right];
            // 如果 l < r 则说明 lr区间一定连续递增，min在
            // 如果 l < m 则说明 lm区间一定连续递增，min在 [m,max,min,r]
            // 如果 l > m 则说明 mr区间一定连续递增，min在 [min,m,r]
            // 如果 r > m 则说明 mr区间一定连续递增，min在 [l,max,min,m]
            // 如果 r < m 则说明 lm区间一定连续递增，min在 [m,max,min,r]
            // 相等的情况不一定

            if (l < r) {
                // 整体有序，直接返回开头即可
                return l;
            }

            // 整体断开
            if (l < m || r < m) {
                // [l,m,r] -> [m,max,min,r]
                // 因为 最小值，肯定不会是m，要么是min 要么是r，所以可以在下圈把mid排除在外了
                left = mid + 1;
            } else if (r > m) {
                // [l,m,r] -> [l,max,min,m]，有可能min=m，所以m不能排除
                right = mid;
            } else {
//                right--;
                left++;
            }
        }

        return nums[left];
    }
}
