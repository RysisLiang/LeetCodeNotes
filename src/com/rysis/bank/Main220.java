package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 *  存在重复元素 III
 */
public class Main220 {

    public static void main(String[] args) {
//        String nums = "[1,2,3,1]"; // true
//        int k = 3, t = 0;
        String nums = "[1,5,9,1,5,9]"; // false
        int k = 2, t = 3;
        System.out.println(containsNearbyAlmostDuplicate(ArrayUtil.handleToIntArray(nums), k, t));
    }

    // 官方桶算法
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        // 桶列表。key是桶的id
        Map<Long, Long> map = new HashMap<Long, Long>();
        long w = (long) t + 1; // 桶的大小
        for (int i = 0; i < n; i++) {
            // 计算当前元素值属于哪个桶
            long id = getID(nums[i], w);
            // 如果该桶已经存在，说明肯定有元素符合题意
            if (map.containsKey(id)) {
                return true;
            }
            // 桶不存在。但是-1桶不存在，且两桶相差小于等于t
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {
                return true;
            }
            // 桶不存在。但是+1桶不存在，且两桶相差小于等于t
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {
                return true;
            }
            // 一个桶只存一个元素就够了，因为有两个元素同时属于一个桶时，可以直接返回true
            map.put(id, (long) nums[i]);
            if (i >= k) {
                map.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }

    /**
     * 计算该元素属于哪个桶
     *
     * @param x 元素
     * @param w 桶大小
     * @return
     */
    private static long getID(long x, long w) {
        if (x >= 0) {
            return x / w;
        }
        return (x + 1) / w - 1;
    }

    // 官方解法。使用结果和元素去找答案
    public static boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (k == 0 || nums.length <= 1) {
            return false;
        }

        // 因为会超出int长度
        TreeSet<Long> treeSet = new TreeSet<>();
        // 没有到结尾时
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                // 将超出窗口范围移除
                treeSet.remove((long) nums[i - k - 1]);
            }

            // 通过两个公式推导出判断公式
            // nums[i] - ceiling <= t => ceiling >= nums[i] - t
            // ceiling - nums[i] <= t => ceiling <= nums[i] + t
            Long ceiling = treeSet.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }

            treeSet.add((long) nums[i]);
        }

        return false;
    }

    // 两个下标的差值小于等于k，说明这是一个最大为 `k + 1` 的窗口
    // 且这两个位置的元素差值，小于等于t。
    // 双指针滑动窗口，满足k条件。然后查找窗口内元素（使用一个有序列表）两两相邻的比较最小是否有满足t条件的。
    public static boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if (k == 0 || nums.length <= 1) {
            return false;
        }

        // 因为会超出int长度
        TreeSet<Long> treeSet = new TreeSet<>();
        // 没有到结尾时
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                // 将超出窗口范围移除
                treeSet.remove((long) nums[i - k - 1]);
            }

            // 大一号的兄弟
            Long ceiling = treeSet.ceiling((long) nums[i]);
            if (ceiling != null && ceiling - nums[i] <= t) {
                return true;
            }
            // 小一号的兄弟
            Long floor = treeSet.floor((long) nums[i]);
            if (floor != null && nums[i] - floor <= t) {
                return true;
            }

            treeSet.add((long) nums[i]);
        }

        return false;
    }
}
