package com.rysis.number;

import java.util.ArrayList;

/**
 * <b>功能名：Main_9</b><br>
 * <b>说明：</b>回文数<br>
 *
 * @date 2022/03/06
 */
public class Main9 {

    public static void main(String[] args) {
//        int x = 121; // true
//        int x = 1213; // false
//        int x = 10; // false
        int x = 100; // false
//        int x = 1010; // false

        System.out.println(isPalindrome(x));
    }

    // 数学法-只反转一半
    public static boolean isPalindrome(int x) {
        if (x >= 0 && x < 10) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }


        // 取出全部的数字
        int temp = 0;
        while (x > temp) {
            // 取出个位，然后与之前的数进行拼接。相当于把之前的低位变成了高位
            temp = temp * 10 + x % 10;
            x = x / 10;
        }

        System.out.println(x);
        System.out.println(temp);

        // 原x正好是偶数个数，所以可以对半开 | 原x是奇数个。temp比x长一位，所以需要除去一位
        return x == temp  || x == temp / 10;
    }

    // 数学法
    public static boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }

        if (x < 10) {
            return true;
        }

        // 取出全部的数字
        final ArrayList<Integer> list = new ArrayList<>();
        int temp = x;
        int yu;
        while ((yu = temp % 10) > -1 && (temp = temp / 10) > 0) {
            list.add(yu);
        }
        list.add(yu);

        // 然后遍历去比较
        for (int i = 0; i < list.size() / 2; i++) {
            if (!list.get(i).equals(list.get(list.size() - 1 - i))) {
                return false;
            }
        }

        return true;
    }

    // 转字符串法
    public static boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        }

        if (x < 10) {
            return true;
        }

        String s = x + "";
        int mid = s.length() / 2;

        for (int i = 0; i < mid; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }

}
