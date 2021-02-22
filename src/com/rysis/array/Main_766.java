package com.rysis.array;

import com.rysis.util.ArrayUtil;

/**
 * Main_766
 * 托普利茨矩阵
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/22 14:39
 */
public class Main_766 {

    public static void main(String[] args) {
        String matrix = "[[1,2,3,4],[5,1,2,3],[9,5,1,2]]"; // true
//        String matrix = "[[1,2],[2,2]]"; // false
        System.out.println(isToeplitzMatrix(ArrayUtil.handleToNestedIntArray(matrix)));
    }

    // 进阶一
    public static boolean isToeplitzMatrix(int[][] matrix) {
        // 内存中的用于记录上一次出现的过的数组
        int[] temp = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            temp = matrix[i];
            if (i == 0) {
                continue;
            }
            for (int j = 1; j < temp.length; j++) {
                if (temp[j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 普通答案
    public static boolean isToeplitzMatrix1(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            int[] matrixi = matrix[i];
            for (int j = 1; j < matrixi.length; j++) {
                if (matrixi[j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
