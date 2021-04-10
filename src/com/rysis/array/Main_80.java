package com.rysis.array;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;

/**
 * 删除有序数组中的重复项2
 */
public class Main_80 {
    public static void main(String[] args) {
        String s = "[0,0,1,1,1,1,2,3,3]"; //  [0,0,1,1,2,3,3]

        int[] nums = ArrayUtil.handleToIntArray(s);
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    // 官方双指针
    public static int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length <= 2) {
            return length;
        }
        // 慢指针
        int index1 = 2;
        // 遍历数组
        for (int i = 2; i < length; i++) {
            // 和慢指针的 - 2的元素进行比较（相当于计算重复次数了）
            if (nums[i] != nums[index1 - 2]) {
                nums[index1] = nums[i];
                index1++;
            }
        }
        return index1;
    }

    // 双指针
    public static int removeDuplicates1(int[] nums) {
        // 慢指针
        int index1 = 0;
        // nums[index1]的元素计数器
        int count = 1;
        // 遍历数组
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[index1]) {
                count++;
                if (count > 2) {
                    continue;
                }
            } else {
                count = 1;
            }
            nums[++index1] = nums[i];
        }
        return index1 + 1;
    }

}
