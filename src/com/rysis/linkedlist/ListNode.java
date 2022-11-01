package com.rysis.linkedlist;

/**
 * ListNode
 * 链表题的公共链表结构
 *
 * @author rysis
 * @version 1.00
 * @date 2021/2/22 15:13
 */
public class ListNode {
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

    /**
     * 打印方法
     *
     * @param prefix 前缀
     */
    public String toString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        ListNode next = this;
        do {
            sb.append(next.val).append(", ");
        } while ((next = next.next) != null);
        return sb.toString();
    }

    /**
     * 用户构建一个链表的方法
     *
     * @param vals 构建链表元素的数组
     * @return
     */
    public static ListNode build(int[] vals) {
        ListNode head = null;
        for (int i = vals.length - 1; i >= 0; i--) {
            head = new ListNode(vals[i], head);
        }

        return head;
    }
}
