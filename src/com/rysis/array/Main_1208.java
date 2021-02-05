package com.rysis.array;

/**
 * Main_1208
 * 尽可能使字符串相等
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/5 22:19
 */
public class Main_1208 {

    public static void main(String[] args) {
//        String s = "abcd";
//        String t = "bcdf";
//        int cost = 3; // 3
//        String s = "abcd";
//        String t = "cdef";
//        int cost = 3; // 1
//        String s = "abcd";
//        String t = "acde";
//        int cost = 0; // 1
//        String s = "abcd";
//        String t = "bcde";
//        int cost = 0; // 0
        String s = "krpgjbjjznpzdfy";
        String t = "nxargkbydxmsgby";
        int cost = 14; // 4
        System.out.println(equalSubstring(s, t, cost));
    }

    // 滑动窗口
    public static int equalSubstring(String s, String t, int maxCost) {
        char[] charsss = s.toCharArray();
        char[] charstt = t.toCharArray();
        int total = 0; // 窗口内的开销
        int len = 0; // 窗口宽度
        int max = len;
        for (int i = 0; i < s.length(); i++) {
            if (total > maxCost) { // 如果当前开销总和大于最大开销
                // 减去左边界开销
                total -= Math.abs(charsss[i - len] - charstt[i - len]);
                // 左边界右移
                i--;
                len--;
            } else { // 如果当前开销总和小于等于最大开销
                // 记录当前最大的窗口
                max = Math.max(max, len);
                // 加上有边界开销
                total += Math.abs(charsss[i] - charstt[i]);
                // 右边界右移
                len++;
            }
        }

        if (total <= maxCost) {
            max = Math.max(max, len);
        }
        return max;
    }
}
