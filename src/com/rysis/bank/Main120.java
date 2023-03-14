package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Main_120
 * 三角形最小路径和
 *
 * @author rysis
 * @version 1.00
 * @date 2021/3/12 18:32
 */
public class Main120 {

    public static void main(String[] args) {
//        String s = "[[2],[3,4],[6,5,7],[4,1,8,3]]"; // 11
        String s = "[[-10]]"; // -10


        System.out.println(minimumTotal1(handler(s)));
        System.out.println(minimumTotal(handler(s)));
    }

    private static List<List<Integer>> handler(String s) {
        int[][] ints = ArrayUtil.handleToNestedIntArray(s);
        List<List<Integer>> list = new ArrayList<>();
        for (int[] anInt : ints) {
            List<Integer> in = new ArrayList<>();
            for (int i : anInt) {
                in.add(i);
            }
            list.add(in);
        }
        return list;
    }

    // dp
    // 元素上面的值，可以看做经过该节点的消耗值。每步都找出最小的走法，那么最后的值一定是最小的。
    // 每个元素只能到下面的相邻的两个元素。同时每个元素只能从其父节点的元素过来。
    // 转移方程（下到上）：d(i)(j) = min[d(i + 1)(j), d(i + 1)(j + 1)] + c(i)(j)
    // 从下到上吧，结果是一样的，其实就可以理解为，底部的哪个元素到顶端的消耗最小。最后累加到顶端的值就是最终的值
    public static int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        // 用于记录每个位置的消耗值
        int[][] arr = new int[len][len];
        // 初始化底部一层的消耗
        for (int i = 0; i < len; i++) {
            arr[len - 1][i] = triangle.get(len - 1).get(i);
        }

        // 最开始我们已知底部每一个元素的初始值，然后依次从下往上求。从倒数第二层开始
        for (int i = len - 1 - 1; i >= 0; i--) {
            List<Integer> inList = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                // 本元素是下层两个相邻元素的min + 自己的值（由于是下层，不存在边界的问题，a/b一定存在）
                int a = arr[i + 1][j];
                int b = arr[i + 1][j + 1];
                // 取最小值与自己的值相加，并计入数组中。转移方程
                arr[i][j] = Math.min(a, b) + inList.get(j);
            }
        }
        return arr[0][0];
    }

    // dp
    // 元素上面的值，可以看做经过该节点的消耗值。每步都找出最小的走法，那么最后的值一定是最小的。
    // 每个元素只能到下面的相邻的两个元素。同时每个元素只能从其父节点的元素过来。
    // 转移方程（上到下）：d(i)(j) = min[d(i - 1)(j - 1), d(i - 1)(j)] + c(i)(j)
    public static int minimumTotal1(List<List<Integer>> triangle) {
        int len = triangle.size();
        // 用于记录每个位置的消耗值
        int[][] arr = new int[len][len];
        // 初始化定点消耗的值
        arr[0][0] = triangle.get(0).get(0);

        int min = Integer.MAX_VALUE;

        // 最开始我们已知c(0)(0) 的值，然后依次从上往下求
        for (int i = 1; i < len; i++) {
            List<Integer> inList = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                // 上层两个相邻的元素累加值（需要排除掉边界上的元素）
                int a = j == 0 ? arr[i - 1][j] : arr[i - 1][j - 1];
                int b = j == i ? arr[i - 1][j - 1] : arr[i - 1][j];
                // 取最小值与自己的值相加，并计入数组中。转移方程
                arr[i][j] = Math.min(a, b) + inList.get(j);

                // 如果是最后一行了，找出最小值
                if (i == len - 1) {
                    min = Math.min(min, arr[i][j]);
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            return arr[0][0];
        }
        return min;
    }
}
