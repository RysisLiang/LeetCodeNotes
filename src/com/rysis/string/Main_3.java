package com.rysis.string;

import java.util.HashMap;

/**
 * Main_3
 * 无重复字符的最长子串
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/28 22:44
 */
public class Main_3 {

    public static void main(String[] args) {
//        String s = "abcabcbb"; // 3
//        String s = "bbbbb"; // 1
//        String s = "pwwkew"; // 3
//        String s = ""; // 0
        String s = "abba"; // 2

        System.out.println(lengthOfLongestSubstring(s));
    }

    // 滑动窗口-双指针法-优化索引
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int max = 0;
        // 左边界
        int left = 0;
        // 用于记录字符，和最后一次出现的位置
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) { // 检查字符是否存在
                // 已经存在则将left移动到存在的位置+1，如果left大于字符上次出现的位置，则left不动
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            // 记录最后一次出现的位置
            map.put(s.charAt(i), i);
            // 计算窗口的大小
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    // 滑动窗口-双指针法
    public static int lengthOfLongestSubstring1(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int max = 0;
        // 左边界
        int left = 0;
        // 用于记录字符，出现的次数
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            // 计数一次
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

            while (map.get(s.charAt(i)) > 1) { // 字符重复
                // 删除左边界元素
                map.computeIfPresent(s.charAt(left), (k, v) -> v -= 1);
                // 左边界右移
                left++;
            }
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
