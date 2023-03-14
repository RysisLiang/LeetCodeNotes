package com.rysis.bank;

import java.util.Arrays;

/**
 * 解码方法
 */
public class Main91 {

    public static void main(String[] args) {
//        String s = "11106"; // 2
        String s = "121212"; // 13
//        String s = "0"; // 0
//        String s = "1201234"; // 3
//        String s = "12001"; // 0
//        String s = "230"; // 0

        System.out.println(numDecodings1(s));
        System.out.println(numDecodings(s));
    }

    // 参考题解优化下自己的dp
    public static int numDecodings(String s) {
        if (s.startsWith("0")) {
            return 0;
        }

        // 当前位置字符的次数，初始都是0，需要通过计算得来
        int dp0 = 0;
        // 上一个位置字符的次数。最开始如果是空字符串的话，只有一个解码方案。
        int dp1 = 1;
        // 上两个位置字符的次数
        int dp2 = 0;
        // 从字符长度为1的开始计算
        for (int i = 1, len = s.length(); i <= len; i++) {
            // 开始初始化当前位置字符的次数
            dp0 = 0;
            // 先判断当前字符是否符合解码要求。只要不等于0肯定有且只有一个解码方案
            if (s.charAt(i - 1) != '0') {
                // 所以与上一个长度的字符串解码次数相同
                dp0 = dp1;
            }
            // 再判断当前数组是否符合解码要求。
            if (i >= 2 && s.charAt(i - 2) != '0'
                    && Integer.parseInt(s.charAt(i - 2) + "" + s.charAt(i - 1)) <= 26) {
                dp0 = dp0 + dp2;
            }
            // 然后将上一位的变成上两位，当前的变成上一位
            dp2 = dp1;
            dp1 = dp0;
        }

        return dp0;
    }

    // 自己的dp 8ms 打败了5.93% + 5.04%
    /*
     首先我们从左往右进行数字的解读，每次的解读方案有两种，读一位和读两位；
     读一位：有两种情况：0和[1-9]，如果是0，那么它必须与上一位进行两位的共同解读；否则它可以直接被解读出字符；
     读两位：有三种情况：00、[01-26]、[27:]。00则表示这组是无效字符、1-26则表示是有效字符、超出范围也是无效字符
     那么状态方程：
        当前的数字n1，包含上一位的数字n2
        - n1 != 0 & 0 < n2 <= 26：dp[i] = dp[i - 1] + dp[i - 2]
        - n1 == 0 & 0 < n2 <= 26：dp[i] = dp[i - 1] = dp[i - 2]
        - n1 != 0 & 0 == n2 || n2 > 26：dp[i] = dp[i - 1]
        - n1 == 0 & 0 == n2 || n2 > 26：dp[i] = 0

     */
    public static int numDecodings1(String s) {
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

//            String substring = s.substring(0, i);
//            System.out.printf("[%d] substr=%s i1=%d i2=%d ", i, substring, i1, i2);

            if (i1 == 0) { // 当前是0 =》 必须与上一个元素合并破解
                if (i1 == i2 || i2 > 26) {
                    return 0;
                }
                dp[i] = dp[i - 1] = dp[i - 2];
//                System.out.printf(" -> 1 dp[%d]=%d %n", i, dp[i]);
            } else if (i2 > 26) { // 超出字幕范围 =》只能拆开分别破解
                dp[i] = dp[i - 1];
//                System.out.printf(" -> 2 dp[%d]=%d %n", i, dp[i]);
            } else if (i1 == i2) { // 上一位是0 =》只能自己破解
                dp[i] = dp[i - 1];
//                System.out.printf(" -> 3 dp[%d]=%d %n", i, dp[i]);
            } else { // 上一位不是0 =》可以拆开可以合并
                dp[i] = dp[i - 1] + dp[i - 2];
//                System.out.printf(" -> 4 dp[%d]=%d %n", i, dp[i]);
            }

            System.out.println(Arrays.toString(dp));
        }

        return dp[s.length()];
    }
}
