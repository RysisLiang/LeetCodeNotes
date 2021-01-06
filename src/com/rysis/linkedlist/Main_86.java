package com.rysis.linkedlist;

import java.util.LinkedList;

/**
 * Main_86
 * 分隔链表
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/1/6 17:22
 */
public class Main_86 {

    public static void main(String[] args) {
//        int[] vals = {1, 4, 3, 2, 5, 2};
//        int x = 3;
        int[] vals = {1, 4, 3, 2, 6, 2, 8, 4, 3, 10, 9, 2};
        int x = 5;
//        int[] vals = {2, 1};
//        int x = 2;
//        int[] vals = {1};
//        int x = 0;
//        int[] vals = {1, 1};
//        int x = 0;
        ListNode head = null;
        for (int i = vals.length - 1; i >= 0; i--) {
            head = new ListNode(vals[i], head);
        }
        printNode("source = ", head);
        printNode("result = ", partition(head, x));
    }

    private static void printNode(String prefix, ListNode head) {
        System.out.print(prefix);
        ListNode next = head;
        do {
            System.out.print(next.val + ", ");
        } while ((next = next.next) != null);
        System.out.println("");
    }

    // 需要一个游标+linkedList
    public static ListNode partition1(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        LinkedList<Integer> lt = new LinkedList<>();
        int highMinIndex = 0; // 高值区的最小索引
        ListNode next = head;
        do {
            int val = next.val;
            if (val >= x) { // 高值的处理
                lt.add(val);
            } else { // 低值的处理
                lt.add(highMinIndex, val);
                highMinIndex++;
            }
        } while ((next = next.next) != null);

        // 再次遍历排序后结果进行组装
        ListNode result = null;
        Integer val;
        while ((val = lt.pollLast()) != null) {
            result = new ListNode(val, result);
        }
        return result;
    }

    // 利用链表的特性原地遍历重组。一般就是拆成两段链表一个底、一个高，仅需记录两个链表的尾节点即可。
    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode low = new ListNode(0); // 低区间
        ListNode high = new ListNode(0); // 高区间
        ListNode lowLast = low; // 低区间的尾节点
        ListNode highLast = high; // 高区间的尾节点
        ListNode next = head;
        do {
            if (next.val < x) {
                // 当前节点接入低区间尾节点
                lowLast.next = next;
                lowLast = next;
            } else {
                // 当前节点接入高区间尾节点
                highLast.next = next;
                highLast = next;
            }
        } while ((next = next.next) != null);
        // 低区间尾巴链接高区间
        lowLast.next = high.next;
        // 高区间去掉多余的尾巴
        highLast.next = null;
        return low.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
