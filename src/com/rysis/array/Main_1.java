package com.rysis.array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Main_1
 *
 * @author liang
 * @version 1.00
 * @date 2021/1/6 14:41
 */
public class Main_1 {
    public static void main(String[] args) {
//        int[] nums = {2, 7, 11, 15};
//        int target = 9;
        int[] nums = {3, 2, 4}; // 1,2
        int target = 6;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    // 暴力破解
    public static int[] twoSum1(int[] nums, int target) {
        int i = 0;
        int max = nums.length - 1;
        while (i < max) {
            for (int j = i + 1; j <= max; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
            i++;
        }
        return new int[]{};
    }

    // 查看解题思路后，发现可以用hash
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0, len = nums.length; i < len; i++) {
            Integer index = map.get(nums[i]);
            if (index != null) {
                return new int[]{index, i};
            }
            map.put(target - nums[i], i);
        }
        return new int[]{};
    }
}
