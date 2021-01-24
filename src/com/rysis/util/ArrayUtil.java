package com.rysis.util;

import java.util.Arrays;

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
}
