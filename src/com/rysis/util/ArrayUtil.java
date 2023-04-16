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

    private ArrayUtil() {
    }

    /**
     * 转数组
     *
     * @param str "[1,3,5,4,7]"
     * @return
     */
    public static int[] handleToIntArray(String str) {
        final var ripeStr = str.replace("\\s", "");
        return Arrays.stream(thinStr(ripeStr).split(","))
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
        final var ripeStr = str.replace("\\s", "");
        return Arrays.stream(thinStr(ripeStr).split(","))
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
        final var ripeStr = str.replace("\\s", "");
        List<Character> collect = Arrays.stream(thinStr(ripeStr).split(","))
                .filter(StringUtil::isDefined)
                .map(s -> s.charAt(1))
                .collect(Collectors.toUnmodifiableList());
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
        final var ripeStr = str.replace("\\s", "");
        List<int[]> collect = Arrays.stream(thinStr(ripeStr).split("],\\["))
                .filter(StringUtil::isDefined)
                .map(s -> handleToIntArray(s.trim()))
                .collect(Collectors.toUnmodifiableList());
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
        final var ripeStr = str.replace("\\s", "");
        List<char[]> collect = Arrays.stream(thinStr(ripeStr).split("],\\["))
                .filter(StringUtil::isDefined)
                .map(ArrayUtil::handleToCharArray)
                .collect(Collectors.toUnmodifiableList());
        char[][] ints = new char[collect.size()][2];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = collect.get(i);
        }
        return ints;
    }

    private static String thinStr(String str) {
        String rs = str;
        if (rs.startsWith("[")) {
            rs = rs.substring(1);
        }
        if (rs.endsWith("]")) {
            rs = rs.substring(0, rs.length() - 1);
        }

        return rs;
    }

}
