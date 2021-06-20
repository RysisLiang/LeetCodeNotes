package com.rysis.array;

import java.util.*;

/**
 * Main_1239
 * 串联字符串的最大长度
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/6/19 19:57
 */
public class Main_1239 {

    public static void main(String[] args) {
//        String[] s = {"un", "iq", "ue"}; // 4 uniq/ique
//        String[] s = {"abcdefghijklmnopqrstuvwxyz"}; // 26
//        String[] s = {"cha", "r", "act", "ers"}; // 6
        String[] s = {"a", "abc", "d", "de", "def"}; // 6

        System.out.println(maxLength(Arrays.asList(s)));
    }

    // 迭代遍历发
    public static int maxLength(List<String> arr) {
        // 符合题的结果集合
        ArrayList<String> result = new ArrayList<>();

        w:
        for (String s : arr) {
            System.out.println(s);
            // 如果元素本身就已经存在重复字符了则跳过
            for (int i = 0; i < s.length(); i++) {
                if (i != s.lastIndexOf(s.charAt(i))) {
                    continue w;
                }
            }
            // 检查结果集中符合的元素并进行拼接
            for (int i = 0; i < result.size(); i++) {
                String item = result.get(i);
                if (!checkStr(s, item)) {
//                    result.remove(i);
                    result.add(item + s);
                }
            }
            // 将本元素本身添加进结果集合中
            result.add(s);
        }
        System.out.println(result);
        Optional<String> max = result.stream().max(Comparator.comparingInt(String::length));
        return max.orElse("").length();
    }

    // 比较两个字符串是否有重复的字符
    private static boolean checkStr(String source, String target) {
        for (int i = 0; i < source.length(); i++) {
            if (target.indexOf(source.charAt(i)) > -1) {
                return true;
            }
        }
        return false;
    }
}
