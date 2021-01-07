package com.rysis.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main_830
 * 较大分组的位置
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/1/7 11:50
 */
public class Main_830 {

    public static void main(String[] args) {
//        String s = "abc"; // []
//        String s = "aba"; // []
//        String s = "aaa"; // [[0,2]]
//        String s = "abbxxxxzzy"; // [[3,6]]
        String s = "abcdddeeeeaabbbcd"; // [[3,5],[6,9],[12,14]]
//        String s = ""; // []
        largeGroupPositions(s).forEach(lt -> System.out.print(lt.toString() + ", "));
    }

    // 使用LinkedList
    public static List<List<Integer>> largeGroupPositions1(String s) {
        // 返回结果
        LinkedList<List<Integer>> result = new LinkedList<>();
        // 上一次元素
        Character lastChar = null;
        // 内部元素的索引集合
        LinkedList<Integer> itemList = new LinkedList<>();
        for (int i = 0; i < s.toCharArray().length; i++) {
            char ch = s.charAt(i);
            // System.out.print(String.format("i=%d, ch=%s, lastChar=%s  ----", i, ch, lastChar));
            // 如果上一位的元素是空 or 元素不一致的时候，插入开始索引
            if (lastChar == null || lastChar != ch) {
                handle(itemList, result);
                itemList.clear(); // 清空元素
                itemList.addFirst(i);
            } else {
                itemList.addLast(i);
            }
            // System.out.println(itemList.toString());
            lastChar = ch;
        }
        handle(itemList, result);
        return result;
    }

    /**
     * 如果列表中大于等于2，则记录起始和结束的位置
     *
     * @param itemList
     * @param result
     */
    private static void handle(LinkedList<Integer> itemList, LinkedList<List<Integer>> result) {
        if (itemList.size() >= 2) {
            Integer first = itemList.getFirst();
            Integer last = itemList.getLast();
            int count = last - first;
            if (count >= 2) {
                result.addLast(Arrays.asList(first, last));
            }
        }
    }

    // 使用了游标来记录字符组的起始和结束位置
    public static List<List<Integer>> largeGroupPositions2(String s) {
        // 返回结果
        List<List<Integer>> result = new LinkedList<>();
        // 字符组的起始位置
        int start = 0;
        // 移动结束位置的游标
        for (int end = 1; end < s.toCharArray().length; end++) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);
            if (startChar != endChar) { // 字符不一致=字符变换的位置
                if (end - start >= 3) { // 当前字符（新的字符）距离上一组字符起始位置 >= 3
                    result.add(Arrays.asList(start, end - 1));
                }
                // 当前字符位置就是新一组字符的起始位置
                start = end;
            }
        }
        // 最后一组字符要额外判断一次长度
        if (s.toCharArray().length - start >= 3) {
            result.add(Arrays.asList(start, s.toCharArray().length - 1));
        }
        return result;
    }

    // 从其他人那里看来的正则的方法
    public static List<List<Integer>> largeGroupPositions(String s) {
        // 返回结果
        List<List<Integer>> result = new LinkedList<>();
        // 匹配小写字符，且后续出现的次数大于2次的
        Pattern r = Pattern.compile("([a-z])\\1{2,}");
        Matcher matcher = r.matcher(s);
        while (matcher.find()) {
            result.add(Arrays.asList(matcher.start(), matcher.end() - 1));
        }
        return result;
    }
}
