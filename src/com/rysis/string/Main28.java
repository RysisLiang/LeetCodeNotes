package com.rysis.string;

import java.util.LinkedList;

/**
 * Main_28
 * 实现strStr
 *
 * @author rysis
 * @version 1.00
 * @date 2021/4/20 14:08
 */
public class Main28 {

    public static void main(String[] args) {
//        String haystack = "hello", needle = "ll";// 2
//        String haystack = "hello", needle = "lc";// -1
//        String haystack = "hello", needle = "";// 0
//        String haystack = "aaa", needle = "aaaa";// -1
//        String haystack = "mississippi", needle = "issip";// 4
//        String haystack = "mississippi", needle = "sippia";// -1
        String haystack = "babba", needle = "bbb";// -1

        System.out.println(strStr(haystack, needle));
    }

    // 不使用队列，直接暴力迭代。
    public static int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        if ("".equals(haystack) || haystack.length() < needle.length()) {
            return -1;
        }

        for (int i = 0; i < haystack.length(); i++) {
            // 找到首字符的匹配索引
            if (needle.charAt(0) != haystack.charAt(i)) {
                continue;
            }
            // 如果后面剩余字符串的长度不足子串的长度
            if (i + needle.length() > haystack.length()) {
                break;
            }
            // 开始匹配后续的字符。首字符一致，从第二个字符开始
            int j = 1;
            for (; j < needle.length() && i + j < haystack.length(); j++) {
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    // 存在不匹配的结束
                    break;
                }
            }
            // 查看结果
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }

    // 使用队列去维护首字符的位置
    public static int strStr1(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        if ("".equals(haystack) || haystack.length() < needle.length()) {
            return -1;
        }
        // 匹配的字符索引栈
        LinkedList<Integer> queue = new LinkedList<>();
        // 子串的指针
        int needleIndex = 0;
        for (int i = 0; i < haystack.length(); i++) {
            char cc = haystack.charAt(i);
            haystack.indexOf("abc");
            // 首字符匹配结果
            if (cc == needle.charAt(0)) {
                queue.offer(i);
            }
            // 超出子串上限直接结束
            if (needleIndex >= needle.length()) {
                break;
            }
            // 检查字符是否一致
            if (cc == needle.charAt(needleIndex)) {
                needleIndex++;
            } else if (!queue.isEmpty()) {
                queue.poll();
                Integer peek = queue.peek();
                if (peek != null) {
                    i = peek;
                    needleIndex = 1;
                } else {
                    needleIndex = 0;
                }
            }
        }
        return needleIndex == needle.length() && queue.peek() != null ? queue.peek() : -1;
    }
}
