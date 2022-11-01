package com.rysis.dp;

import java.util.Arrays;

/**
 * Main_115
 * 不同的子序列
 *
 * @author rysis
 * @version 1.00
 * @date 2021/3/17 11:16
 */
public class Main115 {

    public static void main(String[] args) {
//        String s = "rabbbit", t = "rabbit"; // 3
        String s = "babgbag", t = "bag"; // 5

        System.out.println(numDistinct1(s, t));
        System.out.println(numDistinct(s, t));
    }

    // DP降维
    public static int numDistinct(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        // 这里只使用了一维数组老保存结果。这里主导因素是t的长度
        int[] dp = new int[tlen + 1];
        // 这里的i和j表示两个字符串的长度。从1开始
        for (int i = 1; i <= slen; i++) {
            int last = 1; // 这里就是为了初始化，当t=0时的结果
            for (int j = 1; j <= tlen; j++) {
                int temp = dp[j];
                // 如果此时，两个字符串的最后一个字符相等。
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] = dp[j] + last; // dp[j]:是s=i-1&t=j的结果。last是s=i-1&t=j-1时的结果
                }
                last = temp;
            }
        }
        return dp[tlen];
    }

    // 参考了题解大佬
    public static int numDistinct1(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        // 因为有空字符串的情况，所以个数的len +1
        int[][] dp = new int[slen + 1][tlen + 1];

        // 这里的i和j表示两个字符串的长度
        for (int i = 0; i <= slen; i++) {
            for (int j = 0; j <= tlen; j++) {
                if (j == 0) { // 如果是t串是空，那么s是多长都是1种匹配结果
                    dp[i][0] = 1;
                } else if (i == 0) { // 如果是s串是空，那么t是多长都是0种匹配结果
                    dp[0][j] = 0;
                } else {
                    // 比较上一个长度时尾部字符是否一致
                    if (s.charAt(i - 1) == t.charAt(j - 1)) { // 一致
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                    } else { // 不一致
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));

        return dp[slen][tlen];
    }
}
