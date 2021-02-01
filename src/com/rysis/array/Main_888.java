package com.rysis.array;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Main_888
 * 公平的糖果棒交换
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/1 11:34
 */
public class Main_888 {

    public static void main(String[] args) {
//        String A = "[1,1]", B = "[2,2]"; // [1,2]
//        String A = "[1,2]", B = "[2,3]"; // [1,2]
//        String A = "[2]", B = "[1,3]"; // [2,3]
        String A = "[1,2,5]", B = "[2,4]"; // [5,4]
        System.out.println(Arrays.toString(fairCandySwap(ArrayUtil.handleToIntArray(A), ArrayUtil.handleToIntArray(B))));
    }

    // fairCandySwap2 改进1
    public static int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int d = (sumA - sumB) / 2;

        // 之前的双数组循环浪费了大量时间，那么将其中一个数组放入哈希表中
        Set<Integer> set = Arrays.stream(B).boxed().collect(Collectors.toSet());
        for (int i : A) {
            if (set.contains(i - d)) {
                return new int[]{i, i - d};
            }
        }
        return new int[]{0, 0};
    }

    // fairCandySwap1 改进1
    public static int[] fairCandySwap2(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int d = (sumA - sumB) / 2;

        for (int i : A) {
            for (int j : B) {
                if (i - j == d) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }

    // 推导出公式 sumA - sumB = 2(i - j)
    public static int[] fairCandySwap1(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();

        for (int i : A) {
            for (int j : B) {
                if (2 * (i - j) == sumA - sumB) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }
}
