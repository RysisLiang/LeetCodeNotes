package com.rysis.binarysearch;

import java.util.List;

/**
 * <b>功能名：Main50</b><br>
 * <b>说明：</b>Pow(x, n)<br>
 *
 * @date 2022/03/14
 */
public class Main50 {

    public static void main(String[] args) {
        List.of(
                new Bean(2D, 10),
                new Bean(2.1D, 3),
                new Bean(2, -2147483648),
                new Bean(2.D, -2)
        ).forEach(b -> System.out.printf("%s - result = %f %n", b, myPow(b.x, b.n)));
    }

    // 迭代方案 - 理论上所有递归都是可以转换成迭代的发难
    public static double myPow(double x, int n) {
        double result;
        if (n < 0) {
            // 这里的n可能是int的最小值（负数）。它无法直接变为int的正数。所以先将它转为long型以便能转为正数
            result = 1 / multi(x, Math.abs((long) n));
        } else {
            result = multi(x, n);
        }

        return result;
    }

    // 迭代方案。。fixme 这个需要好好理解
    private static double multi(double x, long n) {
        if (n == 0) {
            return 1;
        }
        // 相当于，二进制的最低一位不需要了
        double temp = x;
        // 声明一个栈用于存储中间变量
        double result = 1;

        while (n > 0) {
            // 这里相当于是判断二进制中，当前最低位是否是1
            if (n % 2 == 1) {
                result = result * temp;
            }
            temp = temp * temp;
            n = n / 2;
        }

        return result;
    }

    // 递归方案
    public static double myPow1(double x, int n) {

        double result;
        if (n < 0) {
            result = 1 / multi1(x, Math.abs(n));
        } else {
            result = multi1(x, Math.abs(n));
        }

        return result;
    }

    private static double multi1(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double res = multi1(x, n / 2);

        double result;
        if (n % 2 == 0) {
            result = res * res;
        } else {
            result = res * res * x;
        }

        return result;
    }

    private static final class Bean {
        double x;
        int n;

        public Bean(double x, int n) {
            this.x = x;
            this.n = n;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "x=" + x +
                    ", n=" + n +
                    '}';
        }
    }

}
