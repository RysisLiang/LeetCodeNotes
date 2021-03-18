package com.rysis.linkedlist;

import com.rysis.util.ArrayUtil;

/**
 * Main_92
 * 反转链表2
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/3/18 00:16
 */
public class Main_92 {

    public static void main(String[] args) {
//        String l = "[1,2,3,4,5]";
//        int m = 2, n = 4;
        String l = "[3,5]";
        int m = 1, n = 2;

        ListNode head = ListNode.build(ArrayUtil.handleToIntArray(l));
        ListNode result = reverseBetween(head, m, n);
        System.out.println(result.toString("result = "));
    }

    // 自己写的栈版本，比较麻烦
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        // 索引记录
        int index = 1;
        // 头部加个套
        ListNode wrapper = new ListNode(0, head);
        // 反转暂存
        ListNode[] stack = new ListNode[right - left + 1];
        // 头
        ListNode t = null;
        // 尾
        ListNode w = null;
        // 索引节点
        ListNode indexNode = wrapper;
        // 开始遍历链表
        while (indexNode.next != null) {
            ListNode next = indexNode.next;
            if (index == left) {
                t = indexNode;
            }
            if (index == right) {
                w = next.next;
            }
            // 范围内的节点放入栈中
            if (index >= left && index <= right) {
                stack[index - left] = next;
            }
            // 索引累计+1
            index++;
            indexNode = next;
        }

        // 取出栈
        ListNode tmp = t;
        for (int i = stack.length - 1; i >= 0; i--) {
            tmp.next = stack[i];
            tmp = stack[i];
        }
        tmp.next = w;
        return wrapper.next;
    }
}
