package com.rysis.bit;

/**
 * Main_389
 * 找不同
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/1/7 14:08
 */
public class Main_389 {
    public static void main(String[] args) {
        String s = "abcd";
        String t = "abcde";
        System.out.println(findTheDifference(s, t));
    }

    // 替换方法
    public static char findTheDifference1(String s, String t) {
        if (s.length() <= 0) {
            return t.charAt(0);
        }
        char[] arr = t.toCharArray();
        for (char c : arr) {
            int i = s.indexOf(c);
            if (i == -1) {
                return c;
            }
            // 进行替换
            int slen = s.replace(c + "", "").length();
            int tlen = t.replace(c + "", "").length();
            // 替换前两个字符串长度不一致，如果替换后长度一致，则说明该字符是不同的那个字符
            if (slen == tlen) {
                return c;
            }
        }
        return t.charAt(0);
    }

    // 参考了评论，利用了char-int的转换
    public static char findTheDifference2(String s, String t) {
        if (s.length() <= 0) {
            return t.charAt(0);
        }
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int sSum = 0;
        int tSum = 0;
        for (char c : sArr) {
            sSum = sSum + (int)c;
        }
        for (char c : tArr) {
            tSum = tSum + (int)c;
        }

        return (char) (tSum - sSum);
    }

    // 也是参考答案的位运算法
    public static char findTheDifference(String s, String t) {
        if (s.length() <= 0) {
            return t.charAt(0);
        }
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int sSum = 0;
        int tSum = 0;
        for (char c : sArr) {
            sSum = sSum + (int) c;
        }
        for (char c : tArr) {
            tSum = tSum + (int) c;
        }

        return (char) (tSum - sSum);
    }
}
