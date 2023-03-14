package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;

/**
 * Main_27
 * 移除元素
 *
 * @author rysis
 * @version 1.00
 * @date 2021/4/19 17:55
 */
public class Main27 {

    public static void main(String[] args) {
        String nums = "[3,2,2,3]";
        int val = 3; // 2
//        String nums = "[2]";
//        int val = 3; // 1

//        int[] array = new int[]{};
//        int val = 0; // 2

//        String nums = "[1]";
//        int val = 1; // 0

//        String nums = "[4,5]";
//        int val = 5; // 1

        int[] array = ArrayUtil.handleToIntArray(nums);
        System.out.println(removeElement2(array, val));
        System.out.println(Arrays.toString(array));
    }

    // 双指针-顺序该变-需要唯一的元素偏少
    // 官方题解
    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int left = 0; // 左指针
        int right = nums.length - 1; // 右指针

        while (left <= right) {
            if (nums[left] == val) { // 如果左侧指针的元素与val一致，则将right元素替换过来，并且right-1
                nums[left] = nums[right];
                right--;
            } else { // 如果left元素不一致，则left++继续判断下一个元素
                left++;
            }
        }
        return left;
    }

    // 双指针-顺序该变-需要唯一的元素偏少
    // 元素不一致的原地不动，只改变元素一致的位置
    // 由于最终的结果是让元素都排列到数组的前段，后端是什么无所谓，所以我们可以从后去遍历。
    // 前后双指针同时移动，前指针先移动到一个相同元素的位置等待后指针，找到第一个不同元素的位置，然后把后指针的元素，替换到前指针上。
    public static int removeElement2(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int left = 0; // 左指针
        int right = nums.length - 1; // 右指针

        while (left <= right) {
            // 左指针先找出相同的元素
            while (left <= right && val != nums[left]) {
                left++;
            }
            // 右指针找出不相同的元素
            while(left <= right && val == nums[right]){
                right--;
            }
            if (left < right) {
                // 左指针的元素被右指针的元素替代
                nums[left] = nums[right--];
            }
        }
        return left;
    }

    // 双指针-顺序不变-需要唯一的元素偏多
    public static int removeElement1(int[] nums, int val) {
        int slow = 0; // 慢指针
        // 快指针
        for (int i = 0; i < nums.length; i++) {
            if (val != nums[i]) {
                nums[slow++] = nums[i];
            }
        }
        return slow;
    }
}
