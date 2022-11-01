package com.rysis.string;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Main_387
 * 字符串中的第一个唯一字符
 *
 * @author rysis
 * @version 1.00
 * @date 2021/2/25 16:04
 */
public class Main387 {

    public static void main(String[] args) {
        String s = "leetcode"; // 0
//        String s = "loveleetcode"; // 2
        System.out.println(firstUniqChar2(s));
        System.out.println(firstUniqChar(s));
    }

    // 利用字符码减少循环次数
    public static int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        // 最小的索引定义
        int min = s.length();
        // 只从'a'遍历到'z'，这样训话的次数是固定的26次，而不会随s的长度而增加
        for (int i = 'a', end = 'z'; i <= end; i++) {
            // 第一次出现的索引
            int index = s.indexOf(i);
            // 如果该字符串出现 & 且该字符比上一个字符串先出现 & 该字符头部和尾部出现的索引一致（只有一次）
            if (index > -1 && index < min && s.lastIndexOf(i) == index) {
                min = index; // 更新唯一字符出现的最小索引
            }
        }
        // 如果该最小索引没变 则返回-1
        return min == s.length() ? -1 : min;
    }

    // 哈希表优化
    public static int firstUniqChar2(String s) {
        if (s == null || "".equals(s)) {
            return -1;
        }
        Map<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
        }
        return hashMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(1)) // 筛选出唯一出现的字符
                .map(entry -> s.indexOf(entry.getKey()))// 获取出现的索引
                .min(Comparator.comparingInt(i -> i))// 找到最小的索引
                .orElse(-1);
    }

    // 暴力循环
    public static int firstUniqChar1(String s) {
        if (s == null || "".equals(s)) {
            return -1;
        }

        Map<Character, Integer> hashMap = new LinkedHashMap<>();
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
        }
        for (int i = 0, len = s.length(); i < len; i++) {
            if (hashMap.get(s.charAt(i)).equals(1)) {
                return i;
            }
        }
        return -1;
    }
}
