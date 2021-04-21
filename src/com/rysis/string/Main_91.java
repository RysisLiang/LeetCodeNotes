package com.rysis.string;

import java.util.Arrays;

/**
 * 解码方法
 */
public class Main_91 {

    public static void main(String[] args) {
//        String s = "11106"; // 2
//        String s = "121212"; // 13
//        String s = "0"; // 0
//        String s = "1201234"; // 3
//        String s = "12001"; // 0
        String s = "230"; // 0

        System.out.println(Integer.parseInt("05"));

        System.out.println(numDecodings(s));
    }

    // 自己的dp 8ms 打败了5.93% + 5.04%
    public static int numDecodings(String s) {
        if (s.startsWith("0")) {
            return 0;
        }

        // 记录次数的
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2, len = s.length(); i <= len; i++) {
            // 当前的数字
            int i1 = Integer.parseInt(s.charAt(i - 1) + "");
            // 包含上一位的数字
            int i2 = Integer.parseInt(s.charAt(i - 2) + "" + s.charAt(i - 1));

            String substring = s.substring(0, i);
            System.out.printf("[%d] substr=%s i1=%d i2=%d ", i, substring, i1, i2);

            if (i1 == 0) { // 当前是0 =》 必须与上一个元素合并破解
                if (i1 == i2 || i2 > 26) {
                    return 0;
                }
                dp[i] = dp[i - 1] = dp[i - 2];
                System.out.printf(" -> 1 dp[%d]=%d %n", i, dp[i]);
            } else if (i2 > 26) { // 超出字幕范围 =》只能拆开分别破解
//                dp[i] = dp[i - 2];
                dp[i] = dp[i - 1];
                System.out.printf(" -> 2 dp[%d]=%d %n", i, dp[i]);
            } else if (i1 == i2) { // 上一位是0 =》只能自己破解
                dp[i] = dp[i - 1];
                System.out.printf(" -> 3 dp[%d]=%d %n", i, dp[i]);
            } else { // 上一位不是0 =》可以拆开可以合并
                dp[i] = dp[i - 1] + dp[i - 2];
                System.out.printf(" -> 4 dp[%d]=%d %n", i, dp[i]);
            }

            System.out.println(Arrays.toString(dp));
        }

        return dp[s.length()];
    }
}
