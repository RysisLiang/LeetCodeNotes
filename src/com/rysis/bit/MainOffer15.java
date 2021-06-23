package com.rysis.bit;

/**
 * MainOffer15
 * 二进制中1的个数
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/6/23 23:17
 */
public class MainOffer15 {

    public static void main(String[] args) {
//        int i = 4; // 1
//        int i = -5; // 31
//        int i = -1; // 32
        int i = 5; // 2

        System.out.println(hammingWeight(i));
    }

    // 依次右移n，然后与1进行与运算
    public static int hammingWeight(int n) {
        int count = 0;
        // 知道n的高位全是0时则不再继续判断了
        while (n != 0) {
            // 每次只比较最低一位是否是1
            if ((n & 1) == 1) {
                count++;
            }
            // 无符号右移1位
            n >>>= 1;
        }
        return count;
    }

    // 依次右移n，然后与1进行与运算
    public static int hammingWeight1(int n) {
        int count = 0;
        // 依次从低到高检查二进制
        for (int i = 0; i < 32; i++) {
            // 每次对n进行位移
            if ((n >>> i & 1) == 1) {
                count++;
            }
        }
        return count;
    }
}
