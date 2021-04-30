package com.rysis.bit;

import com.rysis.util.ArrayUtil;

import java.util.HashSet;

/**
 * Main_137
 * 只出现一次的数字2
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/4/30 21:26
 */
public class Main_137 {

    public static void main(String[] args) {
//        String nums = "[2,2,3,2]"; // 3
        String nums = "[0,1,0,1,0,1,99]"; // 99


        System.out.println(singleNumber(ArrayUtil.handleToIntArray(nums)));
    }

    // 哈希表
    public static int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        HashSet<Integer> hashSet = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();
        for (int num : nums) {
            // 临时哈希表中不存在 & 结果哈希表中已经存在
            if (!hashSet.contains(num) && !result.add(num)) {
                // 临时哈希表添加
                hashSet.add(num);
                // 结果哈希表删除
                result.remove(num);
            }
        }
        return result.iterator().next();
    }
}
