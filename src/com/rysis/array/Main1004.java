package com.rysis.array;

import com.rysis.util.ArrayUtil;

import java.util.LinkedList;

/**
 * Main_1004
 * 最大连续1的个数 III
 *
 * @author rysis
 * @version 1.00
 * @date 2021/2/19 14:03
 */
public class Main1004 {

    public static void main(String[] args) {
//        String A = "[1,1,1,0,0,0,1,1,1,1]";
//        int K = 2; // 6
//        String A = "[1,1,1,0,0,0,1,1,1,1,0]";
//        int K = 2; // 6
//        String A = "[0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1]";
//        int K = 3; // 10
//        String A = "[1,1,1,0,0,0,1,1,1,1,0]";
//        int K = 0; // 4
        String A = "[1,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1,1,0,1,1]";
        String B = "[1,0,0,0,1,1,0,0,1,1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]";
        String C = "[0,0,0,0,1,1,1,1,1,1,1,1,1,1...]";
        int K = 8; // 25
        System.out.println(longestOnes1(ArrayUtil.handleToIntArray(A), K));
        System.out.println(longestOnes(ArrayUtil.handleToIntArray(A), K));
    }

    // 滑动窗口 -> 双指针法 -> 优化下代码
    public static int longestOnes(int[] A, int K) {
        int left = 0; // 左边界
        int right = 0; // 右边界
        int count = 0; // 转换的个数
        for (; right < A.length; right++) {
            // 只要是0的则记录转换的个数
            if (A[right] == 0) {
                count++;
            }
            if (count > K) {
                // 如果左边界右移时，排除掉的是0，则count-1
                if (A[left] == 0) {
                    count--;
                }
                left++; // 窗口左边界右移
            }
        }
        return right - left;
    }

    // 滑动窗口 -> 双指针法
    public static int longestOnes1(int[] A, int K) {
        // 用于记录转换的索引
        LinkedList<Integer> queue = new LinkedList<>();
        int left = 0; // 左边界
        int max = 0; // 最大连续长度记录
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                if (queue.size() < K) { // 还有剩余转换次数
                    // 如果转换的索引存在 & 小于left 时 = 更新left，否则left不变
                    left = queue.peek() != null && queue.peek() < left ? queue.peek() : left;
                } else {
                    // 弹出最先转换的元素，并+1设置为左边界。如果没有转换的元素，则设置为当前的位置为左边界
                    Integer first = queue.poll();
                    left = first != null ? first + 1 : i + 1;
                }
                if (K > 0) {
                    queue.offer(i); // 记录转换位置的索引
                }
            }
            max = Math.max(max, i - left + 1); // 记录最大连续长度
        }
        return max;
    }
}
