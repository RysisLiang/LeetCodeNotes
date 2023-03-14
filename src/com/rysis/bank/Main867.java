package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;

/**
 * Main_867
 * 转置矩阵
 *
 * @author rysis
 * @version 1.00
 * @date 2021/2/25 15:10
 */
public class Main867 {

    public static void main(String[] args) {
        String matrix = "[[1,2,3],[4,5,6],[7,8,9]]"; // [[1,4,7],[2,5,8],[3,6,9]]
//        String matrix = "[[1,2,3],[4,5,6]]"; // [[1,4],[2,5],[3,6]]
        System.out.println(Arrays.deepToString(transpose(ArrayUtil.handleToNestedIntArray(matrix))));
    }

    public static int[][] transpose(int[][] matrix) {
        int[][] result = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

}
