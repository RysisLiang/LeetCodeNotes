package com.rysis.array;

/**
 * 所有奇数长度子数组的和
 */
public class Main1588 {

    public static void main(String[] args) {
//        int[] arr = {1, 4, 2, 5, 3}; // 58
//        int[] arr = {1, 2}; // 3
        int[] arr = {10,11,12}; // 66


        System.out.println(sumOddLengthSubarrays(arr));
    }

    // 前缀和
    public static int sumOddLengthSubarrays(int[] arr) {
        // 先计算出所有元素的前缀和
        int[] ints = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            ints[i + 1] = ints[i] + arr[i];
        }

        int sum = 0;

        // 然后在通过下角标确定每个窗口的位置，然后通过详见确定窗口内的元素和
        for (int x = 0; x < arr.length; x++) {
            // 在首个元素不变的情况下，控制窗口内的元素依次增加2个
            for (int y = 0; y < arr.length - x; y = y + 2) {
//                System.out.printf("x=%d, y=%d == sum=%d end=%d start=%d %n", x, y, sum, ints[x + y + 1], ints[x]);
                sum = sum + ints[x + y + 1] - ints[x];
            }
        }

        return sum;
    }

    // 暴力破解
    public static int sumOddLengthSubarrays1(int[] arr) {
        // 判断最大的奇数子数组长度
        int size = arr.length;
        if (arr.length % 2 == 0) {
            size = arr.length - 1;
        }

        int sum = 0;

        // 数组中元素个数去遍历，i只能是奇数
        for (int i = 1; i <= size; i += 2) {
            // 遍历数组，为了防止越界，需要提前预留出子数组长度
            for (int x = 0; x <= arr.length - i; x++) {
                // 控制窗口内元素的个数
                for (int y = 0; y < i; y++) {
                    sum = sum + arr[x + y];
                }
            }
        }
        return sum;
    }
}
