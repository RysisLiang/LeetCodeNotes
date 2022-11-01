package com.rysis.tree;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Main773
 * 滑动谜题
 *
 * @author rysis
 * @version 1.00
 * @date 2021/6/26 21:56
 */
public class Main773 {

    public static void main(String[] args) {
//        int[][] board = {{1, 2, 3}, {4, 0, 5}}; // 1
//        int[][] board = {{4, 1, 2}, {5, 0, 3}}; // 5
        int[][] board = {{1, 2, 3}, {5, 4, 0}}; // -1

        System.out.println(slidingPuzzle(board));
    }

    static StringBuilder sb = new StringBuilder();
    // 6个位置气的索引
    static int[][] qi = {
            {1, 3}, {0, 2, 4}, {1, 5},
            {0, 4}, {1, 3, 5}, {2, 4}
    };

    // 穷举法+BFS这个可以扩展到m*n上
    public static int slidingPuzzle(int[][] board) {
        // 层级
        int count = 0;
        // 初始化数据
        String boardStr = flatArray(board);
        // 目标
        int[][] target = {{1, 2, 3}, {4, 5, 0}};
        String targetStr = flatArray(target);
        System.out.println("targetStr=" + targetStr);
        if (targetStr.equals(boardStr)) {
            return count;
        }

        // 用于记录移动后的结果
        HashSet<String> set = new HashSet<>();
        set.add(boardStr);
        // 双向队列
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.offer(boardStr);

        while (!linkedList.isEmpty()) {
            // 层级+1
            count++;
            // 把队列中同一层的的元素全部弹出
            for (int x = 0, size = linkedList.size(); x < size; x++) {
                String pop = linkedList.pop();
                // 空格所在的位置
                int zero = pop.indexOf("0");
//                // 每个元素与后一个元素进行交换的次数
//                int row = board.length;
//                int col = board[0].length;
//                int a = zero / col + 1;
//                int b = zero % col + 1;
                // 循环气的位置，然后依次尝试位移
                int[] qiArr = qi[zero];
                for (int j = 0, qilen = qiArr.length; j < qilen; j++) {
                    int qiJ = qiArr[j];
                    // 拼接字符串
                    for (int i = 0, len = pop.length(); i < len; i++) {
                        if (i == zero) {
                            sb.append(pop.charAt(qiJ));
                        } else if (i == qiJ) {
                            sb.append(pop.charAt(zero));
                        } else {
                            sb.append(pop.charAt(i));
                        }
                    }
                    String ss = sb.toString();
                    if (targetStr.equals(ss)) {
                        return count;
                    }
                    // 未被使用过的添加进队列中和使用哈希表中
                    if (!set.contains(ss)) {
                        set.add(ss);
                        linkedList.offer(ss);
                    }
                    // 置空字符串
                    sb.setLength(0);
                }
            }
        }
        System.out.println(set);
        return -1;
    }// 穷举法+BFS

    // 我们可以先把board只移动一次的方法都列出来，如果没有结果，在再次递归把每个方法的在移动一次的方法列出来。
    // 直到找到目标情况，或者所有情况都已经记录了进行结束
    public static int slidingPuzzle1(int[][] board) {
        // 层级
        int count = 0;
        // 初始化数据
        String boardStr = flatArray(board);
        // 目标
        int[][] target = {{1, 2, 3}, {4, 5, 0}};
        String targetStr = flatArray(target);
        System.out.println("targetStr=" + targetStr);
        if (targetStr.equals(boardStr)) {
            return count;
        }

        // 用于记录移动后的结果
        HashSet<String> set = new HashSet<>();
        set.add(boardStr);
        // 双向队列
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.offer(boardStr);

        while (!linkedList.isEmpty()) {
            // 层级+1
            count++;
            // 把队列中同一层的的元素全部弹出
            for (int x = 0, size = linkedList.size(); x < size; x++) {
                String pop = linkedList.pop();
                // 空格所在的位置
                int zero = pop.indexOf("0");
                // 循环气的位置，然后依次尝试位移
                int[] qiArr = qi[zero];
                for (int j = 0, qilen = qiArr.length; j < qilen; j++) {
                    int qiJ = qiArr[j];
                    // 拼接字符串
                    for (int i = 0, len = pop.length(); i < len; i++) {
                        if (i == zero) {
                            sb.append(pop.charAt(qiJ));
                        } else if (i == qiJ) {
                            sb.append(pop.charAt(zero));
                        } else {
                            sb.append(pop.charAt(i));
                        }
                    }
                    String ss = sb.toString();
                    if (targetStr.equals(ss)) {
                        return count;
                    }
                    // 未被使用过的添加进队列中和使用哈希表中
                    if (!set.contains(ss)) {
                        set.add(ss);
                        linkedList.offer(ss);
                    }
                    // 置空字符串
                    sb.setLength(0);
                }
            }
        }
        System.out.println(set);
        return -1;
    }

    // 打平数组转字符串
    private static String flatArray(int[][] board) {
        for (int[] ints : board) {
            for (int anInt : ints) {
                sb.append(anInt);
            }
        }
        String s = sb.toString();
        sb.setLength(0);
        return s;
    }


}
