package com.rysis.linkedlist;

import com.rysis.util.ArrayUtil;

import java.util.LinkedList;

/**
 * Main_82
 * 删除排序链表中的重复元素2
 *
 * @author rysis
 * @version 1.00
 * @date 2021/3/25 18:03
 */
public class Main82 {

    public static void main(String[] args) {
        String s = "[1,2,3,3,4,4,5]"; // [1,2,5]
//        String s = "[1,2,3,3,4,4,4]"; // [1,2]
        ListNode source = ListNode.build(ArrayUtil.handleToIntArray(s));
        System.out.println(source.toString("source = "));

        System.out.println(deleteDuplicates2(source).toString("result2 = "));

        ListNode source1 = ListNode.build(ArrayUtil.handleToIntArray(s));
        System.out.println(deleteDuplicates(source1).toString("result = "));
    }

    // 参考题解使用递归方式 todo
    // 给函数设置定义，就是删除所有head链表中所有有重复元素的节点
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 那么我们可以判断当前节点与子节点的值是否重复
        if (head.val != head.next.val) {
            // 继续遍历，剩下的子节点，然后递归使用函数
            head.next = deleteDuplicates(head.next);
        } else {
            // 如果重复则跳过该节点
            ListNode cur = head.next;
            // 判断当前节点的值，与目标节点的值是否一致
            while (cur != null && head.val == cur.val) {
                cur = cur.next;
            }
            // 该节点与当前节点（head）不一致，交给递归函数继续重复处理
            head = deleteDuplicates(cur);
        }
        return head;
    }

    // 重新实现一个
    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }
        // 包装，方便迭代判断
        ListNode wrapper = new ListNode(0, head);
        // 当前节点
        ListNode cur = wrapper;
        // 当前节点的子节点 & 子节点的子节点 都存在时.
        // 这里使用子节点与孙节点判断，是为了改变链表中节点的指向，改变cur的指向，只能改变遍历的节点的方向而不是head链表本身节点顺序
        while (cur.next != null && cur.next.next != null) {
            // 当子节点与孙节点的值相等时
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                // 找出值全部的相同的节点
                while (cur.next != null && val == cur.next.val) {
                    // 子节点指向孙节点
                    cur.next = cur.next.next;
                }
            } else {
                // 遍历下个节点
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
