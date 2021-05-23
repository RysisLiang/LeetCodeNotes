package com.rysis.tree;

import java.util.Stack;

/**
 * Main_104
 * 二叉树的最大深度
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/5/23 22:13
 */
public class Main_104 {

    public static void main(String[] args) {
//        Integer[] ints = new Integer[]{3, 9, 20, null, null, 15, 7}; // 3
        Integer[] ints = new Integer[]{1, 2, 3, 4, null, null, 5}; // 3

        TreeNode root = TreeNode.build(ints);
        System.out.println(maxDepth(root));
    }

    // 非递归DFS
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 结果
        int res = 0;
        // 节点栈
        Stack<TreeNode> stack = new Stack<>();
        // 层数的栈
        Stack<Integer> stackLevel = new Stack<>();
        // 压入初始节点
        stack.push(root);
        stackLevel.push(1);
        // 检查栈中元素
        while (!stack.isEmpty()) {
            // 弹出栈顶上层节点信息
            TreeNode parentNode = stack.pop();
            Integer parentLevel = stackLevel.pop();
            // 检查是否存在左右节点。先右后左压入，先左后右弹出
            if (parentNode.right != null) {
                stack.push(parentNode.right);
                stackLevel.push(parentLevel + 1);
            }
            if (parentNode.left != null) {
                stack.push(parentNode.left);
                stackLevel.push(parentLevel + 1);
            }

            res = Math.max(res, stackLevel.isEmpty() ? 0 : stackLevel.peek());
        }
        return res;
    }

    // 递归方式DFS
    public static int maxDepth1(TreeNode root) {
        if (root != null) {
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
        return 0;
    }
}
