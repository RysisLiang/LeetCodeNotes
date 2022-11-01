package com.rysis.graph;

import com.rysis.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Main_130
 * 被围绕的区域
 *
 * @author rysis
 * @version 1.00
 * @date 2021/2/27 22:14
 */
public class Main130 {

    public static void main(String[] args) {
//        String board = "[[\"X\",\"X\",\"X\",\"X\"],[\"X\",\"O\",\"O\",\"X\"],[\"X\",\"X\",\"O\",\"X\"],[\"X\",\"O\",\"X\",\"X\"]]";
//        String board = "[[\"O\",\"O\",\"O\",\"O\"],[\"O\",\"O\",\"O\",\"O\"],[\"O\",\"O\",\"O\",\"O\"],[\"O\",\"O\",\"O\",\"O\"]]";
        String board = "[[\"X\",\"O\",\"X\"],[\"X\",\"O\",\"X\"],[\"X\",\"O\",\"X\"]]";
        char[][] chars = ArrayUtil.handleToNestedCharArray(board);
        System.out.println("before = " + Arrays.deepToString(chars));
        solve(chars);
        System.out.println("after = " + Arrays.deepToString(chars));
    }

    // 优化并查集。将边界上的所有'O'都连接成一个区域，并且用一个虚拟节点作为锚点
    public static void solve(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        // 增加一个虚拟结算作为边界'O'的锚点
        UnionFind unionFind = new UnionFind(row * col + 1);
        // 边界的虚拟锚点
        int borderTop = row * col;

        // 内部'O'的x/y坐标列表
        ArrayList<int[]> innerList = new ArrayList<>();
        // 进行一遍合并操作
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                        // 边界的'O'与虚拟锚点相关联
                        unionFind.merge(i * col + j, borderTop);
                    } else {
                        innerList.add(new int[]{i, j});
                    }
                    // 不是第一行时，与上侧元素相同则进行合并
                    if (i > 0 && board[i][j] == board[i - 1][j]) {
                        unionFind.merge(i * col + j, (i - 1) * col + j);
                    }
                    // 不是最后一列时，与右侧相同则进行合并
                    if (j > 0 && board[i][j] == board[i][j - 1]) {
                        unionFind.merge(i * col + j, i * col + (j - 1));
                    }
                }
            }
        }

        System.out.println("innerList = " + Arrays.deepToString(innerList.toArray()));
        System.out.println("unionFind.topsArr = " + Arrays.toString(unionFind.topsArr));
        System.out.println(unionFind.findTop(0) + " == " + unionFind.findTop(2));

        // 然后遍历内部'O'的xy列表，所有将所有不与边界相连的坐标找出来
        for (int[] ints : innerList) {
            // 内部元素不与边界元素相连则替换
            if (unionFind.findTop(ints[0] * col + ints[1]) != unionFind.findTop(borderTop)) {
                board[ints[0]][ints[1]] = 'X';
            }
        }
    }

    // 并查集
    public static void solve1(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        UnionFind unionFind = new UnionFind(row * col);

        // 边界'O'的x/y坐标列表
        ArrayList<int[]> borderList = new ArrayList<>();
        // 内部'O'的x/y坐标列表
        ArrayList<int[]> innerList = new ArrayList<>();
        // 进行一遍合并操作
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                        borderList.add(new int[]{i, j});
                    } else {
                        innerList.add(new int[]{i, j});
                    }
                }
                // 不是第一行时，与上侧元素相同则进行合并
                if (i > 0 && board[i][j] == board[i - 1][j]) {
                    unionFind.merge(i * col + j, (i - 1) * col + j);
                }
                // 不是最后一列时，与右侧相同则进行合并
                if (j > 0 && board[i][j] == board[i][j - 1]) {
                    unionFind.merge(i * col + j, i * col + (j - 1));
                }
            }
        }

        System.out.println("borderList = " + Arrays.deepToString(borderList.toArray()));
        System.out.println("innerList = " + Arrays.deepToString(innerList.toArray()));
        System.out.println("unionFind.topsArr = " + Arrays.toString(unionFind.topsArr));
        System.out.println(unionFind.findTop(0) + " == " + unionFind.findTop(2));

        // 然后遍历边界'O'的xy列表，所有将所有不与边界相连的坐标找出来
        for (int[] border : borderList) {
            int indexBor = border[0] * col + border[1];
            Iterator<int[]> iterator = innerList.iterator();
            while (iterator.hasNext()) {
                int[] next = iterator.next();
                int indexIn = next[0] * col + next[1];
                // 内部元素与边界元素相连，则删除内部元素
                if (unionFind.findTop(indexBor) == unionFind.findTop(indexIn)) {
                    iterator.remove();
                }
            }
        }
        // 剩余的内部元素就是需要转换的元素
        System.out.println("result = " + Arrays.deepToString(innerList.toArray()));

        for (int[] ints : innerList) {
            board[ints[0]][ints[1]] = 'X';
        }
    }

    // 并查集
    static class UnionFind {
        int[] topsArr;
        int[] rankArr;

        public UnionFind(int size) {
            this.topsArr = new int[size];
            this.rankArr = new int[size];
            for (int i = 0; i < size; i++) {
                topsArr[i] = i;
                rankArr[i] = 1;
            }
        }

        public int findTop(int i) {
            return topsArr[i] == i ? i : (topsArr[i] = findTop(topsArr[i]));
        }

        public void merge(int x, int y) {
            int topX = findTop(x);
            int topY = findTop(y);
            if (rankArr[topX] <= rankArr[topY]) {
                topsArr[topX] = topY;
            } else {
                topsArr[topY] = topX;
            }
            if (rankArr[topX] == rankArr[topY] && topX != topY) {
                rankArr[topY]++;
            }
        }
    }
}
