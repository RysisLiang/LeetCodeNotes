package com.rysis.array;

import com.rysis.util.ArrayUtil;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Main_1438
 * 绝对差不超过限制的最长连续子数组
 *
 * @author rysis
 * @version 1.00
 * @date 2021/2/21 23:32
 */
public class Main1438 {

    public static void main(String[] args) {
//        String nums = "[8,2,4,7]";
//        int limit = 4; // 2
//        String nums = "[4,2,2,2,4,4,2,2]";
//        int limit = 0; // 3
        String nums = "[10,1,2,4,7,2]";
        int limit = 5; // 4
        System.out.println(longestSubarray2(ArrayUtil.handleToIntArray(nums), limit));
        System.out.println(longestSubarray(ArrayUtil.handleToIntArray(nums), limit));
    }

    // 使用了两个PriorityQueue进行大根堆和小根堆的排序。且滑动窗口只会增大不会减小。
    public static int longestSubarray(int[] nums, int limit) {
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(((a, b) -> Integer.compare(b, a)));
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        int left = 0;
        int right = 0;
        for (; right < nums.length; right++) {
            maxQueue.add(nums[right]);
            minQueue.add(nums[right]);
            // 如果当前窗口超出limit时，则左边界右移。为什么不循环右移，窗口只会膨胀，即使满足limit但是窗口缩小了的数据是无意义的。
            if (maxQueue.peek() - minQueue.peek() > limit) {
                maxQueue.remove(nums[left]);
                minQueue.remove(nums[left]);
                left++;
            }
        }
        // 最后窗口的大小就是最大的连续数组的结果
        return right - left;
    }

    // 使用了treemap进行大小的排序。且滑动窗口只会增大不会减小。
    public static int longestSubarray2(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int left = 0;
        int right = 0;
        for (; right < nums.length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            // 如果当前窗口超出limit时，则左边界右移。为什么不循环右移，窗口只会膨胀，即使满足limit但是窗口缩小了的数据是无意义的。
            if (Math.abs(map.lastKey() - map.firstKey()) > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
        }
        // 最后窗口的大小就是最大的连续数组的结果
        return right - left;
    }

    // 该方法数组特别长时会超时
    public static int longestSubarray1(int[] nums, int limit) {
        LinkedList<Integer> list = new LinkedList<>();
//        int max = 0;
        int left = 0;
        int right = 0;
        for (; right < nums.length; right++) {
            list.offer(nums[right]);

            Integer max = list.stream().max((a, b) -> a - b).get();
            Integer min = list.stream().min((a, b) -> a - b).get();
            int abs = Math.abs(max - min);
            if (abs <= limit) {
//                max = Math.max(max, right - left + 1);
//                list.offer(nums[right]);
            } else {
                list.remove(new Integer(nums[left]));
                left++;
            }
        }
        return right - left;
    }
}
