package com.rysis.bank;

import java.util.HashMap;

/**
 * Main_424
 * 替换后的最长重复字符
 *
 * @author rysis
 * @version 1.00
 * @date 2021/2/2 14:31
 */
public class Main424 {

    public static void main(String[] args) {
//        String s = "ABAB"; // 4
//        int k = 2;
//        String s = "ABABB"; // 5
//        int k = 2;
//        String s = "ABACBB"; // 5
//        int k = 2;
        String s = "ABBABCDA"; // 5
        int k = 2;
//        String s = "ABABCDAAADEEBABAAAB"; // 6
//        int k = 2;
        System.out.println(characterReplacement(s, k));
    }

    // 滑动窗口
    public static int characterReplacement(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxCount = 0; // 窗口长度内，出现最多次数的字符的个数
        int i = 0; // 右标
        int a = 0; // 左标
        for (; i < s.length(); i++) {
            char currentC = s.charAt(i);
            map.computeIfPresent(currentC, (kk, v) -> v + 1);
            map.putIfAbsent(currentC, 1);
            // 记录当前字符在当前长度内出现的次数
            maxCount = Math.max(maxCount, map.get(currentC));
            // 当前长度 > 长度内最多字符的个数 + 可以替换的个数时，则进行窗口右移
            if (i - a + 1 > maxCount + k) {
                map.computeIfPresent(s.charAt(a), (kk, v) -> v - 1);
                a++;
            }
        }
        return i - a;
    }
}
