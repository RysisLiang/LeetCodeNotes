package com.rysis.number;

/**
 * Main_7
 *
 * @author rysis
 * @version 1.00
 * @date 2021/2/3 14:33
 */
public class Main7 {

    public static void main(String[] args) {
//        int x = 123; //321
//        int x = -123; //-321
//        int x = -1234; //-4321
//        int x = 120; //21
//        int x = 0; // 0
        int x = 1534236469; // 0
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(x);
        System.out.println(reverse(x));
    }

    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int y = x % 10;
            x = x / 10;
            System.out.printf("y=%d, x=%d, result=%d ", y, x, result);
            int next = result * 10 + y;
            System.out.printf(", next=%d\n", next);
            // 如果左移后的在除十不等于原来的数，则表示溢出了
            if (next / 10 != result) {
                return 0;
            }
            result = next;
        }

        return result;
    }
}
