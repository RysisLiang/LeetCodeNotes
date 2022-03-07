package com.rysis.number;

import java.util.List;

/**
 * <b>功能名：Main_504</b><br>
 * <b>说明：</b>七进制数<br>
 *
 * @date 2022/03/07
 */
public class Main_504 {

    public static void main(String[] args) {
        List.of(100, // 202
                8, // 11
                9, // 12
                0, // 0
                -7, // -10
                -9 // -12
        ).forEach(x -> System.out.printf("%d convertToBase7 = %s %n", x, convertToBase7(x)));
    }

    // 普通方法
    public static String convertToBase7(int num) {
        if (num >= 0 && num < 7) {
            return num + "";
        }

        final StringBuilder sb = new StringBuilder();
        String f = "";
        if (num < 0) {
            f = "-";
        }

        num = Math.abs(num);

        while (num > 0) {
            if (sb.length() > 0) {
                sb.insert(0, num % 7);
            } else {
                sb.append(num % 7);
            }
//            System.out.println(a);
            num = num / 7;
        }

        return sb.insert(0, f).toString();
    }

}
