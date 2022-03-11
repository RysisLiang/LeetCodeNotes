package com.rysis.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * <b>功能名：Main2049</b><br>
 * <b>说明：</b>统计最高分的节点数目<br>
 *
 * @date 2022/03/11
 */
public class Main2049 {

    public static void main(String[] args) {

        List.of(
                new int[]{-1, 2, 0, 2, 0}
        ).forEach(parents -> System.out.println(countHighestScoreNodes(parents)));
    }

    // 保存树的结构
    // key: 父节点的值；value：子节点的列表
    final static HashMap<Integer, List<Integer>> tree = new HashMap<>();

    static int[] rootMapper;

    public static int countHighestScoreNodes(int[] parents) {
        if (parents.length <= 3) {
            return 2;
        }

        rootMapper = new int[parents.length];

        for (int i = 1; i < parents.length; i++) {
            final int parent = parents[i];
            if (parent >= 0) {

//                rootMapper[i] = parent;

                // 有效的父级
                if (tree.containsKey(parent)) {
                    tree.get(parent).add(i);
                } else {
                    final LinkedList<Integer> list = new LinkedList<>();
                    list.add(i);
                    tree.put(parent, list);
                }
            }
        }

        System.out.println(tree);

        // 然后要一次减去指定的节点，去计算分数
        for (int i = 0; i < parents.length; i++) {
            // 计算分数
        }

        return 0;
    }

    // 使用dfs去计算分数
    private int computeScore(int[] parents, int node) {

        final int[] root = new int[parents.length];

        // 分数暂存器
        int score = 1;
        // 计数器
        HashMap<Integer, Integer> counter = new HashMap<>();
        // 栈
        LinkedList<Integer> stack = new LinkedList<>();
        // 添加根节点（固定的）
        stack.push(0);

        while (!stack.isEmpty()) {
            final Integer _node = stack.pop();
            final int parent = parents[_node];


            // 记录树根
            if (parent >= 0) {
                if (root[parent] > 0) {
                    root[_node] = root[parent];
                } else {
                    root[_node] = parent;
                }
            }

            // 记录树的个数
            if (parent == node) {
                // 父级节点是被删除的节点
                // 计数器重置
                counter.put(_node, 1);
            } else if (_node != node) {
                // 上级节点的计数器
                final Integer parentCount = counter.getOrDefault(root[_node], 0);
                counter.put(root[_node], parentCount + 1);
            }

            // 查看当前节点是否包含子节点
            final List<Integer> children = tree.get(_node);
            if (children != null && !children.isEmpty()) {
                for (Integer child : children) {
                    stack.push(child);
                }
            }
        }


        for (Integer value : counter.values()) {
            score = score * value;
        }

        return score;
    }

}
