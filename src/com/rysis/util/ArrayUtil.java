package com.rysis.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ArrayUtil
 *
 * @author rysis
 * @version 1.00
 * @date 2021/1/24 13:49
 */
public class ArrayUtil {

    /**
     * 转数组
     *
     * @param str "[1,3,5,4,7]"
     * @return
     */
    public static int[] handleToIntArray(String str) {
        return Arrays.stream(str.substring(1, str.length() - 1).split(","))
                .filter(StringUtil::isDefined)
                .mapToInt(s -> Integer.parseInt(s.trim()))
                .toArray();
    }

    /**
     * 转数组
     *
     * @param str "["tars","rats","arts","star"]"
     * @return
     */
    public static String[] handleToStringArray(String str) {
        return Arrays.stream(str.substring(1, str.length() - 1).split(","))
                .filter(StringUtil::isDefined)
                .map(s -> s.trim().replace("\"", ""))
                .toArray(String[]::new);
    }

    /**
     * 转数组
     *
     * @param str "["tars","rats","arts","star"]"
     * @return
     */
    public static char[] handleToCharArray(String str) {
        List<Character> collect = Arrays.stream(str.substring(1, str.length() - 1).split(","))
                .filter(StringUtil::isDefined)
                .map(s -> s.charAt(1))
                .collect(Collectors.toList());
        char[] result = new char[collect.size()];
        for (int i = 0; i < collect.size(); i++) {
            result[i] = collect.get(i);
        }
        return result;
    }

    /**
     * 转嵌套数组
     *
     * @param str "[[1,3],[5,4],[7,2]]"
     * @return
     */
    public static int[][] handleToNestedIntArray(String str) {
        List<int[]> collect = Arrays.stream(str.substring(2, str.length() - 2).split("],\\["))
                .filter(StringUtil::isDefined)
                .map(s -> handleToIntArray("[" + s.trim() + "]"))
                .collect(Collectors.toList());
        int[][] ints = new int[collect.size()][2];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = collect.get(i);
        }
        return ints;
    }

    /**
     * 转嵌套数组
     *
     * @param str "[[1,3],[5,4],[7,2]]"
     * @return
     */
    public static char[][] handleToNestedCharArray(String str) {
        List<char[]> collect = Arrays.stream(str.substring(2, str.length() - 2).split("],\\["))
                .filter(StringUtil::isDefined)
                .map(s -> handleToCharArray("[" + s + "]"))
                .collect(Collectors.toList());
        char[][] ints = new char[collect.size()][2];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = collect.get(i);
        }
        return ints;
    }
}
