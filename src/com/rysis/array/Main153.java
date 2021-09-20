package com.rysis.array;

import java.util.Arrays;
import java.util.List;

public class Main153 {

    public static void main(String[] args) {
        List<int[]> p = Arrays.asList(
                new int[]{3, 4, 5, 1, 2},
                new int[]{11, 13, 15, 17},
                new int[]{4, 5, 6, 7, 0, 1, 2},
                new int[]{2, 1},
                new int[]{2, 3, 4, 5, 1},
                new int[]{5, 1, 2, 3, 4}
        );

        // 1,11,0,1,1,1
        p.forEach(nums -> System.out.println(findMin(nums)));
    }

    public static int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0, right = nums.length - 1;
        while (left < right) {
            // 取中间值
            int mid = left + (right - left) / 2;
            // 判断两侧的顺序数组
            int l = nums[left], r = nums[right], m = nums[mid];
            if (l < r) { // 连续递增
                return l;
            } else if (l > m) { // 旋转序列
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return nums[right];
    }
}
