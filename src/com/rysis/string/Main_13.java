package com.rysis.string;

import java.util.Map;

/**
 * <b>功能名：Main_13</b><br>
 * <b>说明：</b><br>
 *
 * @date 2022/03/06
 */
public class Main_13 {

    public static void main(String[] args) {
        String s = "III"; // 3
//        String s = "IX"; // 9
//        String s = "MCMXCIV"; // 1994

        System.out.println(romanToInt(s));
    }

    // 字符于数字的映射
    private static Map<Character, Integer> mapper = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    // 数据有限，比哈希表查找更高效
    private static int get(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    // 普通方法
    public static int romanToInt1(String s) {
        int result = 0;

        int temp = 0;

        for (int i = 0; i < s.toCharArray().length; i++) {
            final char charAt = s.charAt(i);
//            final Integer num = mapper.get(charAt);
            // 使用switch case 替换哈希表
            final int num = get(charAt);

            if (num > temp) {
                result += num - (temp * 2);
            } else {
                result += num;
            }

            temp = num;
        }

        return result;
    }

    // 普通方法。 一次读两位
    public static int romanToInt(String s) {

        if (s.length() == 1) {
            return get(s.charAt(0));
        }

        int result = 0;
        int left = get(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int right = get(s.charAt(i));

            if (left < right) {
                result -= left;
            } else {
                result += left;
            }

            left = right;
        }

        result += left;

        return result;
    }

}
