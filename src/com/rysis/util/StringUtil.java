package com.rysis.util;

/**
 * StringUtil
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/1/28 19:39
 */
public class StringUtil {

    /**
     * 是否为空
     *
     * @param str 字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim()) || "null".equals(str.trim().toLowerCase());
    }

    /**
     * 是否有效
     *
     * @param str 字符串
     * @return
     */
    public static boolean isDefined(String str) {
        return !isEmpty(str);
    }
}
