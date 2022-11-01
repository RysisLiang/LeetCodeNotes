package com.rysis.string;

/**
 * Main_395
 *
 * @author rysis
 * @version 1.00
 * @date 2021/2/27 17:14
 */
public class Main395 {

    public static void main(String[] args) {
//        String s = "aaabb";
//        int k = 3; // 3
//        String s = "ababbc";
//        int k = 2; // 5
        String s = "abadbbc";
        int k = 2; // 2

        System.out.println(longestSubstring1(s, k));
        System.out.println(longestSubstring(s, k));
    }

    // 优化一下逻辑
    public static int longestSubstring(String s, int k) {
        int len = s.length();
        if (len < k) {
            return 0;
        }
        // 计算每个字符出现的次数
        int[] count = new int[26];
        for (int i = 0; i < len; i++) {
            count[s.charAt(i) - 'a']++;
        }
        // 找出不符合字符的字符
        char noPassChar = '-';
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0 && count[i] < k) {
                noPassChar = (char) (i + 'a');
                break;
            }
        }
        // 如果没有找到，则返回当前字符串的长度
        if (noPassChar == '-') {
            return len;
        }
        // 拆分子串。一次性全部拆分
        int result = 0;
        for (String sub : s.split(String.valueOf(noPassChar))) {
            result = Math.max(result, longestSubstring(sub, k));
        }
        return result;
    }

    public static int longestSubstring1(String s, int k) {
        if (k == 1) {
            return s.length();
        }
        // 计算每个字符出现的次数
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        // 找出不符合字符的最小索引
        int minIndex = s.length();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0 && count[i] < k && s.indexOf(i + 'a') < minIndex) {
                minIndex = s.indexOf(i + 'a');
            }
        }
        // 如果没有找到，则返回当前字符串的长度
        if (minIndex == s.length()) {
            return minIndex;
        }
        // 拆分子串
        String sub1 = s.substring(0, minIndex);
        String sub2 = s.substring(minIndex + 1);
        // 将两个子串分别递归处理
        int sub1result = 0;
        int sub2result = 0;
        if (sub1.length() >= k) {
            sub1result = longestSubstring(sub1, k);
        }
        if (sub2.length() >= k) {
            sub2result = longestSubstring(sub2, k);
        }
        return Math.max(sub1result, sub2result);
    }
}
