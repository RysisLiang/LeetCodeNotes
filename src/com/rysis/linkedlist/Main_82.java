package com.rysis.linkedlist;

import com.rysis.util.ArrayUtil;

import java.util.LinkedList;

/**
 * Main_82
 * 删除排序链表中的重复元素2
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/3/25 18:03
 */
public class Main_82 {

    public static void main(String[] args) {
//        String s = "[1,2,3,3,4,4,5]"; // [1,2,5]
        String s = "[1,2,3,3,4,4,4]"; // [1,2]
        ListNode source = ListNode.build(ArrayUtil.handleToIntArray(s));
        System.out.println(source.toString("source = "));

        System.out.println(deleteDuplicates1(source).toString("result1 = "));
        System.out.println(deleteDuplicates(source).toString("result2 = "));
    }

    // 重新实现一个
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        // 包装
        ListNode wrapper = new ListNode(0, head);
        ListNode cur = wrapper;
        // 1 = 2 -> 2 = 3
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                // 找出全部的相同的节点
                while (cur.next != null && val == cur.next.val) {
                    // 每次都缩进一个节点
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return wrapper.next;
    }

    // 写的太乱了
    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null) {
            return null;
        }
        // 包装
        ListNode wrapper = new ListNode(head.val - 1, head);
        // 栈
        LinkedList<ListNode> linkedList = new LinkedList<>();
        ListNode last = wrapper;
        ListNode cur = new ListNode(head.val - 1, head); // 当前节点
        linkedList.push(cur);
        while (cur.next != null) {

            if (linkedList.peek().val != cur.next.val) { //
                if (linkedList.size() == 1) {
                    last = last.next = new ListNode(linkedList.pop().val);
                } else {
                    linkedList.clear();
                }
            }
            // 当前节点压入栈中
            linkedList.push(cur.next);
            cur = cur.next;
        }

        if (linkedList.size() == 1) {
            last.next = new ListNode(linkedList.pop().val);
        }
        return wrapper.next.next;
    }
}
