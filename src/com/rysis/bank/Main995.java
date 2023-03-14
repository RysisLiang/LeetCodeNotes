package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.LinkedList;

/**
 * Main_995
 * K 连续位的最小翻转次数
 *
 * @author rysis
 * @version 1.00
 * @date 2021/2/18 15:43
 */
public class Main995 {

    public static void main(String[] args) {
//        String a = "[0,1,0]";
//        int k = 1; // 2
        String a = "[0,0,0,1,0,1,1,0]";
        int k = 3; // 3
        System.out.println(minKBitFlips(ArrayUtil.handleToIntArray(a), k));
    }

    // 题解 负雪明烛 的解法实现。
    // 是在没有想到如何解决
    public static int minKBitFlips(int[] A, int K) {
        // 用于记录需要翻转的元素索引，其中尾部元素相当于滑动窗口的左边界
        LinkedList<Integer> queue = new LinkedList<>();
        // 翻转的次数
        int count = 0;
        // 滑动窗口右边界
        for (int i = 0; i < A.length; i++) {
            // 当前右边界超出左边界的窗口范围，则左边界弹出
            if (queue.size() > 0 && i > queue.peekLast() + K - 1) {
                queue.removeLast();
            }
            if (queue.size() % 2 == A[i]) { // 核心战力来了！！
                if (i + K > A.length) { // 超出范围了
                    return -1;
                }
                queue.addFirst(i);
                count++;
            }
        }
        return count;
    }
}
