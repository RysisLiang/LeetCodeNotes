package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.HashMap;

/**
 * Main_697
 * 数组的度
 *
 * @author rysis
 * @version 1.00
 * @date 2021/2/20 11:08
 */
public class Main697 {
    public static void main(String[] args) {
        String nums = "[1,2,2,3,1]"; // 2
//        String nums = "[1,2,2,3,1,4,2]"; // 6
        System.out.println(findShortestSubArray(ArrayUtil.handleToIntArray(nums)));
    }

    public static int findShortestSubArray(int[] nums) {
        // 元素的计数器 value = [count, start, end]
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int finalI = i;
            // 存在则计数器+1
            map.computeIfPresent(nums[i], (k, v) -> new int[]{v[0] + 1, v[1], finalI});
            // 不存在则初始化=1
            map.putIfAbsent(nums[i], new int[]{1, i, i});
        }
        // 找出“度”和度的元素
        int[] max = map
                .values()
                .stream()
                .max((a, b) -> {
                    if (a[0] == b[0]) {
                        // 比较最数组的长短，短的优先
                        return (b[2] - b[1]) - (a[2] - a[1]);
                    }
                    // 比较元素出现的频数，大的优先
                    return a[0] - b[0];
                })
                .get();

        return max[2] - max[1] + 1;
    }
}
