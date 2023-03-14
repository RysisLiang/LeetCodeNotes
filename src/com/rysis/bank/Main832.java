package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;

/**
 * Main_832
 * 翻转图像
 *
 * @author rysis
 * @version 1.00
 * @date 2021/2/24 10:59
 */
public class Main832 {

    public static void main(String[] args) {
        String a = "[[1,1,0],[1,0,1],[0,0,0]]"; // [[1,0,0],[0,1,0],[1,1,1]]
//        String a = "[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]"; // [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
        System.out.println(Arrays.deepToString(flipAndInvertImage1(ArrayUtil.handleToNestedIntArray(a))));
        System.out.println(Arrays.deepToString(flipAndInvertImage(ArrayUtil.handleToNestedIntArray(a))));
    }

    // 模拟操作。循环可以减半的操作
    public static int[][] flipAndInvertImage(int[][] A) {
        int size = A.length;
        for (int i = 0; i < size; i++) {
            // 这里需要等于除2的商，因为即使不参与翻转，也要参与反转
            for (int j = 0; j < size / 2d; j++) {
                int temp = A[i][size - j - 1];
                A[i][size - j - 1] = A[i][j] ^ 1;
                A[i][j] = temp ^ 1;
            }
        }
        return A;
    }

    // 模拟操作
    public static int[][] flipAndInvertImage1(int[][] A) {
        int size = A.length;
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][size - j - 1] = A[i][j] ^ 1;
            }
        }
        return result;
    }
}
