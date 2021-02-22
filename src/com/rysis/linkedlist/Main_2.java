package com.rysis.linkedlist;

import com.rysis.util.ArrayUtil;

/**
 * Main_2
 * 两数相加
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/22 15:12
 */
public class Main_2 {

    public static void main(String[] args) {
//        String l1 = "[2,4,3]"; // [7,0,8]
//        String l2 = "[5,6,4]";
        String l1 = "[9,9,9,9,9,9,9]"; // [8,9,9,9,0,0,0,1]
        String l2 = "[9,9,9,9]";

        ListNode listNode1 = ListNode.build(ArrayUtil.handleToIntArray(l1));
        ListNode listNode2 = ListNode.build(ArrayUtil.handleToIntArray(l2));
        System.out.println(listNode1.toString("listNode1 = "));
        System.out.println(listNode2.toString("listNode2 = "));
        System.out.println(addTwoNumbers(listNode1, listNode2).toString("result = "));
    }

    //
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 用于记录返回值的根node
        ListNode root = new ListNode(-1);
        // 用于暂存的临时头部node
        ListNode head = root;
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0) {
            int val1 = 0;
            int val2 = 0;
            if (l1 != null) {
                val1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                val2 = l2.val;
                l2 = l2.next;
            }
            int sum = val1 + val2 + carry;
            // 计算进位
            carry = sum / 10;
            // 当前的位数
            int current = sum % 10;

            ListNode node = new ListNode(current);
            head.next = node; // 绑定到头部node下
            head = node; // 记录到头部node
        }
        return root.next;
    }
}