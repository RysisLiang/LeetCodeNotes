package com.rysis.tree;

import java.util.*;

/**
 * Main_102
 * 二叉树的层次遍历
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/5/23 22:13
 */
public class Main_102 {

    public static void main(String[] args) {
        Integer[] ints = new Integer[]{3, 9, 20, null, null, 15, 7}; // 3
//        Integer[] ints = new Integer[]{1, 2, 3, 4, null, null, 5}; // 3

        TreeNode root = TreeNode.build(ints);
        System.out.println(levelOrder(root));
    }

    // bfs层次遍历
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // 使用双向队列
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        // 初始化队列数据
        linkedList.offer(root);

        // 检查队列
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            ArrayList<Integer> list = new ArrayList<>();
            // 循环队列中该元素个数的次数，目的是为了把该层的元素全部弹出
            for (int i = 0; i < size; i++) {
                // 弹出队列头节点
                TreeNode node = linkedList.pop();
                // 保存元素（有顺序）
                list.add(node.val);
                // 检查子节点
                if (node.left != null) {
                    linkedList.offer(node.left);
                }
                if (node.right != null) {
                    linkedList.offer(node.right);
                }
            }
            result.add(list);
        }

        return result;
    }

    // dfs层次遍历
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> rows = new ArrayList<>();
        return dfs(root, 0, rows);
    }

    /**
     * dfs按层级遍历节点
     *
     * @param root 节点
     * @param level 层级
     * @param rows 结果集数组
     * @return
     */
    private static List<List<Integer>> dfs(TreeNode root, int level, List<List<Integer>> rows) {
        if (root == null) {
            return rows;
        }
        // 如果当前的结果集层级与当前层级一致，则表示当前层级属于第一次遍历到，需要新建层级
        if (rows.size() == level) {
            rows.add(level, new ArrayList<>(Arrays.asList(root.val)));
        } else { // 反之则表示该层级已经存在结果集，那么只需追加层级元素即可
            List<Integer> list = rows.get(level);
            list.add(root.val);
        }
        // 先左后右的深层次递归
        dfs(root.left, level + 1, rows);
        dfs(root.right, level + 1, rows);

        return rows;
    }

}
