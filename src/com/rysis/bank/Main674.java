package com.rysis.bank;

import com.rysis.util.ArrayUtil;

/**
 * Main_674
 * 最长连续递增序列
 *
 * @author rysis
 * @version 1.00
 * @date 2021/1/24 13:44
 */
public class Main674 {

    public static void main(String[] args) {
//        String str = "[1,3,5,4,7]"; // 3
//        String str = "[]"; // 0
//        String str = "[2,2,2,2,2]"; // 1
        String str = "[1,3,5,7]"; // 4
        System.out.println(findLengthOfLCIS(ArrayUtil.handleToIntArray(str)));
    }

    // 答案的贪心算法，使用下角标进行个数计算
    public static int findLengthOfLCIS(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int startIndex = 0; // 当前联系递增的起始索引
        int maxCount = 0; // 最长的连续递增个数
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) { // 现在的元素小于等于上个元素
                // 计算当前到递增起始索引间的距离，取最大的连续递增个数
                maxCount = Math.max(i - startIndex, maxCount);
                startIndex = i;
                // 提前结束
                if (nums.length - i < maxCount) {
                    break;
                }
            }
        }
        return Math.max(nums.length - startIndex, maxCount);
    }

    // 自己做
    public static int findLengthOfLCIS1(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int count = 1; // 当前的连续递增个数
        int maxCount = count; // 最长的连续递增个数
        int lastItem = nums[0]; // 上一个数组元素
        for (int i = 1; i < nums.length; i++) {
            int item = nums[i];
            if (item > lastItem) { // 现在的元素大于上个元素
                count++; // 连续递增个数+1
            } else {
                maxCount = Math.max(count, maxCount); // 取最大的连续递增个数
                count = 1; // 连续递增个数=1

                // 提前结束
                if (nums.length - i < maxCount) {
                    break;
                }
            }
            lastItem = item;
        }
        return Math.max(count, maxCount);
    }
}
