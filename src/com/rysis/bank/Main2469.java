
package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.List;
import java.util.Map;

/**
 * 数组中字符串的最大值
 */
public class Main2469 {

    public static void main(String[] args) {
        final var entry = List.of(
                "[\"alic3\",\"bob\",\"3\",\"4\",\"00000\"]", // 5
                "[\"1\",\"01\",\"001\",\"0001\"]" // 1
        );


        for (var stringEntry : entry) {
            System.out.printf("%d%n", maximumValue(ArrayUtil.handleToStringArray(stringEntry)));
        }
    }

    // 暴力方法-直接遍历循环
    public static int maximumValue(String[] strs) {
        int result = 0;
        for (String str : strs) {
            boolean hasStr = false;
            // 检查是否包含了字母
            for (char cc : str.toCharArray()) {
                // 包含了字母，则不需要转数字了
                if (cc >= 'a' && cc <= 'z') {
                    // 存在字母，则无需判断数字的十进制大小
                    hasStr = true;
                    break;
                }
            }

            if (hasStr) {
                result = Math.max(result, str.length());
            } else {
                result = Math.max(result, Integer.parseInt(str));
            }
        }


        return result;
    }


}
