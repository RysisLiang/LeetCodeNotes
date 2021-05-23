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
