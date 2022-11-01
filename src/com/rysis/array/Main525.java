package com.rysis.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Main_525
 * 连续数组
 *
 * @author rysis
 * @version 1.00
 * @date 2021/6/3 22:40
 */
public class Main525 {

    public static void main(String[] args) {
//        int[] nums = {0}; // 0
//        int[] nums = {0, 1}; // 2
//        int[] nums = {0, 1, 0}; // 2
        int[] nums = {0, 0, 1}; // 2
//        int[] nums = {0, 0, 1, 0, 0, 0, 1, 1}; // 6

        System.out.println(findMaxLength(nums));
    }

    // 要求0和1的数目相同的连续元素长度
    // 0和1个数相同，那么这些元素相加的和一定是长度/2
    // sum[m]数index=m的前缀和（包含nums[m]自己）
    // (sum[i] - sum[j]) * 2 = i - j -> 2 * sum[i] - 2 * sum[j] = i - j -> 2 * sum[i] - i = 2 * sum[j] - j
    //
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // 如果 sum * 2 == 元素间的个数(i - j + 1) 那么当前的是成立的，因为是前缀和所以j=0
            if (sum * 2 == i + 1) {
                result = i + 1;
            } else if (map.containsKey(sum * 2 - i)) {
                // 取出 因为 2 * sum[i] - i = 2 * sum[j] - j，所以取出的value = j
                result = Math.max(result, i - map.get(sum * 2 - i));
            } else {
                // 相当于put: 2 * sum[j] - j
                map.put(sum * 2 - i, i);
            }
        }

        return result;
    }

    // 题解参考
    public static int findMaxLength1(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + (nums[i - 1] == 1 ? 1 : -1);
        }
        int ans = 0;

        System.out.println(Arrays.toString(sum));

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 2; i <= n; i++) {
            if (!map.containsKey(sum[i - 2])) {
                map.put(sum[i - 2], i - 2);
            }
            if (map.containsKey(sum[i])) {
                ans = Math.max(ans, i - map.get(sum[i]));
            }
        }
        return ans;
    }

}
