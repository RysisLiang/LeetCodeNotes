package com.rysis.tree;

import java.util.ArrayList;

/**
 * Main_783
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/4/13 23:05
 */
public class Main_783 {

    public static void main(String[] args) {
        // [90,69,null,49,89,null,52]
        TreeNode root = new TreeNode(90,
                new TreeNode(69,
                        new TreeNode(49,
                                null,
                                new TreeNode(52)),
                        new TreeNode(89)),
                null);
        System.out.println(minDiffInBST(root));
        System.out.println(minDiffInBST1(root));
    }

    // 递归-任意节点-中序遍历
    public static int minDiffInBST(TreeNode root) {
        d(root);
        return res;
    }

    static int res = Integer.MAX_VALUE;
    static Integer last; // 记录上个节点的值

    // 中序遍历
    private static void d(TreeNode node) {
        if (node == null) {
            return;
        }
        d(node.left);
        // 中序处理
        // 初次记录节点的值（也是树的最小值）
        if (last != null) {
            res = Math.min(res, node.val - last);
        }
        last = node.val;
        d(node.right);
    }

    private static ArrayList<Integer> arr = new ArrayList<Integer>();

    // 递归-相邻节点-放入数组中，然后在数组中进行比较
    public static int minDiffInBST1(TreeNode root) {

        // 遍历二叉树，并放入list中
        ff(root);
        // 将list中进行排序，然后依次两两比较差值的大小
        arr.sort(Integer::compare);
        // 遍历
        System.out.println(arr);
        int res1 = Integer.MAX_VALUE;
        for (int i = 1; i < arr.size(); i++) {
            res1 = Math.min(res1, arr.get(i) - arr.get(i - 1));
        }
        return res1;
    }

    private static void ff(TreeNode root) {
        arr.add(root.val);
        TreeNode left = root.left;
        if (left != null) {
            ff(left);
        }
        TreeNode right = root.right;
        if (right != null) {
            ff(right);
        }
    }


}
