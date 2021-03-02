package com.rysis.array;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;

/**
 * Main_304
 * 二维区域和检索-矩阵不可变
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/3/2 11:03
 */
public class Main_304 {

    public static void main(String[] args) {
        String matrix = "[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]";
        String x = "[[2,1,4,3],[1,1,2,2],[1,2,2,4]]"; // 8-11-12
//        String matrix = "[[-1]]";
//        String x = "[[0,0,0,0]]"; // -1

        NumMatrix obj = new NumMatrix(ArrayUtil.handleToNestedIntArray(matrix));
        for (int[] ints : ArrayUtil.handleToNestedIntArray(x)) {
            System.out.println(obj.sumRegion(ints[0], ints[1], ints[2], ints[3]));
        }
    }

    // 根据303题扩展出来的-自行实现
    static class NumMatrix {
        int rowLen = 0;
        int colLen = 0;
        // 每个元素到左上角元素的和的数组
        int[] sums;

        public NumMatrix(int[][] matrix) {
            rowLen = matrix.length;
            if (rowLen > 0 && (colLen = matrix[0].length) > 0) {
                sums = new int[rowLen * colLen];
            } else {
                sums = new int[0];
            }
            // 二维数组预处理，转为一维数组，并记录每个元素到起始元素之间（二维）的和
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < colLen; j++) {
                    // 计算总和
                    sums[i * colLen + j] = compute(matrix, i, j);
                }
            }
            System.out.println("sums=" + Arrays.toString(sums));
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sums[row2 * colLen + col2] - computeAround(row2, col1, row1, col2);
        }

        /**
         * 计算元素到起始元素之间（二维）的和
         *
         * @param i 纵
         * @param j 横
         * @return
         */
        private int compute(int[][] matrix, int i, int j) {
            return matrix[i][j] + computeAround(i, j, i, j);
        }

        /**
         * 计算夹角的范围的和
         *
         * @param x1 左下
         * @param y1 左下
         * @param x2 右上
         * @param y2 右上
         * @return
         */
        private int computeAround(int x1, int y1, int x2, int y2) {
            // = 左侧元素的总和 + 上面相邻元素的总和 - 左上角相邻元素的和 （因为这个区域的元素被左侧和上面的和都包含了）
            int top = 0;
            int left = 0;
            int leftTop = 0;
            if (x2 > 0) {
                top = sums[(x2 - 1) * colLen + y2];
            }
            if (y1 > 0) {
                left = sums[x1 * colLen + (y1 - 1)];
            }
            if (x2 > 0 && y1 > 0) {
                leftTop = sums[(x2 - 1) * colLen + (y1 - 1)];
            }
            return top + left - leftTop;
        }

    }
}
