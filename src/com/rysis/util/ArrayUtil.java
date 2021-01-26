package com.rysis.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ArrayUtil
 *
 * @author kunda-liang
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
    public static int[] handleToArray(String str) {
        return Arrays.stream(str.substring(1, str.length() - 1).split(","))
                .filter(s -> !"".equals(s))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    /**
     * 转嵌套数组
     *
     * @param str "[[1,3],[5,4],[7,2]]"
     * @return
     */
    public static int[][] handleToNestedArray(String str) {
        List<int[]> collect = Arrays.stream(str.substring(2, str.length() - 2).split("],\\["))
                .filter(s -> !"".equals(s))
                .map(s -> handleToArray("[" + s + "]"))
                .collect(Collectors.toList());
        int[][] ints = new int[collect.size()][2];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = collect.get(i);
        }
        return ints;
    }
}
