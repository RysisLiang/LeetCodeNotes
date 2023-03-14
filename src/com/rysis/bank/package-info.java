/**
 * 普通题库的内的题目
 */
package com.rysis.bank;

/**
 * 树形结构
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 构建二叉树
     *
     * @param list
     * @return
     */
    static TreeNode build(Integer[] list) {
        TreeNode[] arr = new TreeNode[list.length];
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                continue;
            }
            TreeNode node = new TreeNode(list[i]);
            // 创建节点
            arr[i] = node;
            // 上级节点存在
            TreeNode parent = arr[(i - 1) / 2];
            if (parent != null) {
                if ((i - 1) % 2 == 0) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
            }

        }
        return arr[0];
    }

}

/**
 * 链表结构
 */
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
