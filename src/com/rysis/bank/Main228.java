package com.rysis.bank;

import java.util.*;

/**
 * Main_228
 * 汇总区间
 *
 * @author rysis
 * @version 1.00
 * @date 2021/1/10 20:16
 */
public class Main228 {

    public static void main(String[] args) {
//        int[] nums = {0, 1, 2, 4, 5, 7}; // [0->2, 4->5, 7]
        int[] nums = {0, 2, 3, 4, 6, 8, 9}; // [0, 2->4, 6, 8->9]
//        int[] nums = {}; // []
//        int[] nums = {-1}; // [-1]
//        int[] nums = {-2147483648, -2147483647, 2147483647}; // [-1]
        System.out.println(summaryRanges(nums));
    }

    // 链表方法
    public static List<String> summaryRanges(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        if (nums.length == 1) {
            return Collections.singletonList(nums[0] + "");
        }
        // 返回的列表
        ArrayList<String> result = new ArrayList<>();

        // 用于临时存储的链表，头大尾小
        LinkedList<Integer> linkedList = new LinkedList<>();
        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!linkedList.isEmpty()) {
                // 取出头部大数（也是上一个元素）
                Integer first = linkedList.getFirst();
                // 这里需要注意 num - first > 1 可能会超出int范围的情况
                if (first + 1 != num) { // 与上一个元素不连续
                    String item = linkedList.getLast().toString(); // 尾巴小数
                    if (linkedList.size() >= 2) {
                        item = item + "->" + linkedList.getFirst();
                    }
                    result.add(item);
                    linkedList.clear();
                }
            }
            // 将当前元素添加进链表头部
            linkedList.addFirst(num);
        }
        // 单独处理下链表的剩余数据
        String item = linkedList.getLast().toString(); // 尾巴小数
        if (linkedList.size() >= 2) {
            item = item + "->" + linkedList.getFirst();
        }
        result.add(item);
        return result;
    }
}
