package com.rysis.linkedlist;

import com.rysis.util.ArrayUtil;

/**
 * Main_61
 *
 * @author rysis
 * @version 1.00
 * @date 2021/4/3 23:40
 */
public class Main61 {

    public static void main(String[] args) {
        String head = "[1,2,3,4,5]";
        int k = 2;
        ListNode sourceNode = ListNode.build(ArrayUtil.handleToIntArray(head));
        System.out.println(sourceNode.toString("source = "));

        System.out.println(rotateRight(sourceNode, k).toString("result = "));
    }

    // 官方题解
    // 先将链表变为闭环链表，然后找到切割节点即可
    public ListNode rotateRight2(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        // 计算链表的长度
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        // 计算实际位移长度，如果没有位移则直接返回
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        // 首尾相接，拼接成循环链表
        iter.next = head;
        // 再次遍历找到需要断开的节点
        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
    }

    // 优化下自己的题解
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        // 获取链表长度
        int count = 1;
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            count++;
        }
        System.out.printf("获取链表长度 = %d %n", count);

        // 计算实际位移
        int realK = k % count;
        System.out.printf("计算实际位移 = %d %n", realK);
        // 没有位移则直接返回
        if (realK == 0) {
            return head;
        }

        // 当前的temp是链表的最后一个节点，使原链表首位连接
        temp.next = head;
        // 临时节点，用于保存尾部链表
        while (count-- - realK > 0) {
            temp = temp.next;
        }
        // 获取头部
        ListNode result = temp.next;
        // 尾部截断
        temp.next = null;
        return result;
    }

    // 自己遍历的方式
    public static ListNode rotateRight1(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        // 获取链表长度
        int count = 1;
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            count++;
        }
        System.out.printf("获取链表长度 = %d %n", count);

        // 计算实际位移
        int realK = k % count;
        System.out.printf("计算实际位移 = %d %n", realK);
        // 没有位移则直接返回
        if (realK == 0) {
            return head;
        }

        // 包装要遍历的链表
        ListNode wrapper = new ListNode(0, head);
        ListNode cur = wrapper;
        // 临时节点，用于保存尾部链表
        ListNode temp2 = null;
        while (cur.next != null) {
            ListNode next = cur.next;
            if (count == realK) {
                // 另存尾部链表
                temp2 = new ListNode(0, next);
                // 将尾部截断
                cur.next = null;
            }
            cur = next;
            count--;
        }

        System.out.println(wrapper.toString("wrapper = "));
        System.out.println(temp2.toString("temp2 = "));

        // 重新拼接链表
        cur.next = wrapper.next;
        return temp2.next;
    }
}
