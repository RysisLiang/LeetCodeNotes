package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Main_1631
 * 最小体力消耗路径
 *
 * @author rysis
 * @version 1.00
 * @date 2021/1/29 18:51
 */
public class Main1631 {

    public static void main(String[] args) {
//        String heights = "[[1,2,2],[3,8,2],[5,3,5]]"; // 2
//        String heights = "[[1,2,3],[3,8,4],[5,3,5]]"; // 1
//        String heights = "[[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]"; // 0
        String heights = "[[1,10,6,7,9,10,4,9]]"; // 0
        System.out.println(minimumEffortPath(ArrayUtil.handleToNestedIntArray(heights)));
    }

    // 并查集
    // 查看了解题思路。找出所有高度差关系，并且从小到大进行排序。然后找出连接关系，连通时即是高度差的值。
    public static int minimumEffortPath(int[][] heights) {
        // 用于存放高度差的集合。
        ArrayList<int[]> list = new ArrayList<int[]>();

        // 先初始化数据吧，这里先把高度差计算出来
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                int index = i * heights[0].length + j; // 每个方块的id
                // 里面的数组放三位：第一位是当前的元素的index、第二位是计算相邻元素的index、第三位是高度差
                if (j > 0) { // 如果不是第一列
                    int exp = Math.abs(heights[i][j] - heights[i][j - 1]); // 高度差
                    list.add(new int[]{index, index - 1, exp});
                }
                if (i > 0) { // 如果不是第一行
                    int exp = Math.abs(heights[i][j] - heights[i - 1][j]); // 高度差
                    list.add(new int[]{index, index - heights[0].length, exp});
                }
            }
        }

        // 然后把这些高度差关系，根据高度差进行正序排序
        list.sort((a, b) -> a[2] - b[2]);

        list.forEach(i -> System.out.println(Arrays.toString(i)));

        UnionFind unionFind = new UnionFind(heights.length * heights[0].length);
        for (int[] ints : list) {
            unionFind.merge(ints[0], ints[1]);
            if (unionFind.findTop(0) == unionFind.findTop(heights.length * heights[0].length - 1)) {
                return ints[2];
            }
        }

        return 0;
    }

    static class UnionFind {
        public int[] topArr;
        public int[] ranks;

        public UnionFind(int size) {
            topArr = new int[size];
            ranks = new int[size];

            // 初始化数据
            for (int i = 0; i < size; i++) {
                topArr[i] = i;
                ranks[i] = 1;
            }
        }

        public int findTop(int i) {
            return topArr[i] == i ? i : (topArr[i] = findTop(topArr[i]));
        }

        public void merge(int x, int y) {
            int topX = findTop(x);
            int topY = findTop(y);
            if (ranks[topX] <= ranks[topY]) {
                topArr[topX] = topY;
            } else {
                topArr[topY] = topX;
            }
            if (ranks[topX] == ranks[topY] && topX != topY) {
                ranks[topY]++;
            }
        }
    }


}