package com.rysis.tree;

/**
 * TreeNode
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/4/13 23:04
 */
public class TreeNode {
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
}
