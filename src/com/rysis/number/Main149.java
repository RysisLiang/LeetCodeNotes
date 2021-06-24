package com.rysis.number;

import com.rysis.util.ArrayUtil;

import java.util.HashMap;

/**
 * Main149
 * 直线上最多的点数
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/6/24 22:42
 */
public class Main149 {

    public static void main(String[] args) {
//        String points = "[[1,1],[2,2],[3,3]]"; // 3
//        String points = "[[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]"; // 4
//        String points = "[[1,1],[2,2],[3,3],[2,1],[3,2]]"; // 3
//        String points = "[[-6,-1],[3,1],[12,3]]"; // 3
        String points = "[[0,1],[0,0],[0,4],[0,-2],[0,-1],[0,3],[0,-4]]"; // 7

        System.out.println(maxPoints(ArrayUtil.handleToNestedIntArray(points)));
    }

    // maxPoints的基础上不求最大公约数
    public static int maxPoints(int[][] points) {
        if (points.length <= 2) {
            return points.length;
        }
        int result = 0;
        // 循环点，并求两点之间的斜率
        for (int i = 0; i < points.length; i++) {
            // 当前i点和其它点组成直线的斜率，其中在同一条直线上的最大个数
            HashMap<String, Integer> map = new HashMap<>();
            int oneMapMax = 0;
            for (int j = i + 1; j < points.length; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                // x的差值
                int dx = p1[0] - p2[0];
                // y的差值
                int dy = p1[1] - p2[1];
                // 求出斜率。由于题里没有碰到精度问题，所以double还可以，但是实际上如果碰到精度问题，还是应该先用最大公约数去约一下下
                String k;
                if (dy == 0) { // 这里说明线段一定是垂直x轴的，使用一个特殊标记
                    k = "chui";
                } else if (dx == 0) { // 这里说明线段一定是水平x轴的，使用一个特殊标记
                    k = "shui";
                } else  {
                    k = (dx / (double) dy) + "";
                }
                // 该斜率的点的个数计数+1。由于最少两个点连成线段，所以默认就是1
                map.put(k, map.getOrDefault(k, 1) + 1);
                oneMapMax = Math.max(oneMapMax, map.get(k));
                System.out.println(map);
            }
            result = Math.max(result, oneMapMax);
        }
        return result;
    }


    // 两两循环点，并计算之间的斜率
    public static int maxPoints1(int[][] points) {
        if (points.length <= 2) {
            return points.length;
        }
        int result = 0;
        // 循环点，并求两点之间的斜率
        for (int i = 0; i < points.length; i++) {
            // 当前i点和其它点组成直线的斜率，其中在同一条直线上的最大个数
            HashMap<String, Integer> map = new HashMap<>();
            int oneMapMax = 0;
            for (int j = i + 1; j < points.length; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                // x的差值
                int dx = p1[0] - p2[0];
                // y的差值
                int dy = p1[1] - p2[1];
                // 求出最大公约数
                int gcd = gcd(dx, dy);
                // 求出斜率，由于已经是使用了最大公约数去约完了，这里使用字符串保存公式
                String k = (dx / gcd) + "/" + (dy / gcd);
                // 该斜率的点的个数计数+1。由于最少两个点连成线段，所以默认就是1
                map.put(k, map.getOrDefault(k, 1) + 1);
                oneMapMax = Math.max(oneMapMax, map.get(k));

                System.out.println(map);
            }
            result = Math.max(result, oneMapMax);
        }
        return result;
    }

    // 求分子和分母的最大公约数
    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
