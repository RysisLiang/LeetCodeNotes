package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;

/**
 * Main_303
 * 区域和检索 - 数组不可变
 *
 * @author rysis
 * @version 1.00
 * @date 2021/3/1 11:23
 */
public class Main303 {

    public static void main(String[] args) {
        String s = "[-2,0,3,-5,2,-1]";
        String x = "[[0,2],[2,5],[0,5]]"; // [null, 1, -1, -3]

        NumArray obj = new NumArray(ArrayUtil.handleToIntArray(s));
        for (int[] ints : ArrayUtil.handleToNestedIntArray(x)) {
            System.out.println(Arrays.toString(ints) + " :: " + obj.sumRange(ints[0], ints[1]));
        }
    }

    static class NumArray {

        // 所有元素到起始点的和
        int[] sumArr;

        public NumArray(int[] nums) {
            sumArr = new int[nums.length];
            // 计算所有元素到起始点的和
            for (int i = 0; i < nums.length; i++) {
                if (i == 0) {
                    sumArr[i] = nums[i];
                    continue;
                }
                sumArr[i] = nums[i] + sumArr[i - 1];
            }
        }

        public int sumRange(int i, int j) {
            if (i == 0) {
                return sumArr[j];
            }
            return sumArr[j] - sumArr[i - 1];
        }
    }
}


