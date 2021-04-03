package com.rysis.dp;

import java.util.Arrays;

/**
 * Main_1143
 * 最长公共子序列
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/4/3 21:59
 */
public class Main_1143 {

    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace"; // 3
//        String text1 = "abc", text2 = "abc"; // 3
//        String text1 = "abc", text2 = "def"; // 0

        System.out.println(longestCommonSubsequence(text1, text2));
    }

    // 减少了循环次数
    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1.equals(text2)) {
            return text1.length();
        }
        int len1 = text1.length(), len2 = text2.length();
        // 要多包含一个空字符串的情况，就是length = 0
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int l = 1; l <= len1; l++) {
            for (int s = 1; s <= len2; s++) {
                char c1 = text1.charAt(l - 1);
                char c2 = text2.charAt(s - 1);
                if (c1 == c2) {
                    dp[l][s] = dp[l - 1][s - 1] + 1;
                } else {
                    dp[l][s] = Math.max(dp[l - 1][s], dp[l][s - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    // dp
    // 子序列的定义是顺序相同，但是可以不连续的。找出最长的子序列，那么最长的上限就是短字符串的长度。
    // 那么两个字符串的子序列 拆解如下：
    // 比较下最后一位是否相同？相同，那么两个串都缩短一位后，再次进行递归处理，并计数+1：不同，则长串缩短一位，再进行递归处理。或者短串缩短一位，在进行递归处理；
    // 状态方程：dp[l][s]：是两者长度字符串下的最长公共子序列的个数；l：是长字符串长度；s：是短字符串长度；
    // 相同：dp[l][s] = dp[l - 1][s - 1] + 1
    // 不相同：dp[l][s] = Max(dp[l - 1][s], dp[l][s - 1])
    // 结束状态：当s = 0 时，dp[n][0] = 0。l同理
    public static int longestCommonSubsequence1(String text1, String text2) {
        if (text1.equals(text2)) {
            return text1.length();
        }
        int len1 = text1.length(), len2 = text2.length();
        // 要多包含一个空字符串的情况，就是length = 0
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int l = 0; l <= len1; l++) {
            for (int s = 0; s <= len2; s++) {
                if (l == 0 || s == 0) {
                    dp[l][s] = 0;
                    continue;
                }

                char c1 = text1.charAt(l - 1);
                char c2 = text2.charAt(s - 1);
                if (c1 == c2) {
                    dp[l][s] = dp[l - 1][s - 1] + 1;
                } else {
                    dp[l][s] = Math.max(dp[l - 1][s], dp[l][s - 1]);
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[len1][len2];
    }
}
