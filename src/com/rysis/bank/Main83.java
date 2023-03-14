package com.rysis.bank;

import com.rysis.util.ArrayUtil;

/**
 * Main_83
 * 删除排序链表中的重复元素
 *
 * @author rysis
 * @version 1.00
 * @date 2021/3/26 00:19
 */
public class Main83 {

    public static void main(String[] args) {
//        String s = "[1,1,2]"; // [1,2]
//        String s = "[1,2,3,3,4,4,5]"; // [1,2,3,4,5]
        String s = "[1,2,3,3,4,4,4]"; // [1,2,3,4]
        ListNode source = ListNode.build(ArrayUtil.handleToIntArray(s));
        System.out.println(source.toString("source = "));

        System.out.println(deleteDuplicates(source).toString("result = "));
    }

    // 递归法2
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 如果当前节点不等于下个节点的值，那么当前节点进行保留，下面节点继续递归进行重复操作
        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
            // 保留当前的head节点
            return head;
        } else { // 如果当前节点等于后续节点的值，那么跳过这些节点
            ListNode temp = head.next;
            while (temp.next != null && head.val == temp.next.val) {
                temp = temp.next;
            }
            // 不保留当前的head节点
            return deleteDuplicates(temp);
        }
    }

    // 递归法
    public static ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 找出当前节点的下一个节点的不重复序列，然后重新赋值给当前的节点的子节点
        head.next = deleteDuplicates(head.next);
        // 如果重复了则将重复的节点的最后一个返还给上级节点
        if (head.val != head.next.val) {
            return head;
        } else {
            return head.next;
        }
    }

    // 迭代法。使用了声明变量。
    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 包装一下
        ListNode wrapper = new ListNode(0, head);
        ListNode cur = wrapper;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                ListNode next = cur.next;
                ListNode nextNext = next.next;
                while (nextNext != null && next.val == nextNext.val) {
                    // 如果next == next.next 时，则保留next，让next.next = next.next.next
                    nextNext = nextNext.next;
                }
                // 把声明来的变量重新
                next.next = nextNext;
            }
            // 因为使用了声明的变量
            cur = cur.next; // 继续迭代下一个节点
        }
        return wrapper.next;
    }

    // 迭代法
    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 包装一下
        ListNode wrapper = new ListNode(0, head);
        ListNode cur = wrapper;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val != cur.next.next.val) {
                cur = cur.next; // 继续迭代下一个节点
            } else {
                while (cur.next.next != null && cur.next.val == cur.next.next.val) {
                    // 如果next == next.next 时，则保留next，让next.next = next.next.next
                    cur.next.next = cur.next.next.next;
                }
            }
        }
        return wrapper.next;
    }
}
