package com.rysis.bank;

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
                new int[]{-1, 2, 0, 2, 0},
                new int[]{-1, 2, 0}
        ).forEach(parents -> System.out.println(countHighestScoreNodes(parents)));
    }

    // 又是超时
    static HashMap<Integer, List<Integer>> tree1 = new HashMap<>();
    public static int countHighestScoreNodes(int[] parents) {
        if (parents.length <= 3) {
            return 2;
        }

        for (int i = 1; i < parents.length; i++) {
            int parent = parents[i];
            if (parent >= 0) {
                // 有效的父级
                if (tree1.containsKey(parent)) {
                    tree1.get(parent).add(i);
                } else {
                    LinkedList<Integer> list = new LinkedList<>();
                    list.add(i);
                    tree1.put(parent, list);
                }
            }
        }

        System.out.println(tree1);
        for (int i = 0; i < parents.length; i++) {
            score = 1;
            dfs(parents, 0, i);
            if (score > max) {
                max = score;
                cnt = 1;
            } else if (score == max) {
                cnt += 1;
            }

            System.out.printf("%d score = %d %n", i, score);
        }

        return cnt;
    }


    static int score = 1;
    static int max = 0;
    static int cnt = 0;

    // 这回换一种思路。dfs从叶子节点开始计算
    // 这里由于是希望从叶子节点开始算。所以使用递归的方式去计数
    private static int dfs(int[] parents, int node, int rm) {

        int counter = 0;

        // 子节点列表
        List<Integer> childrenNode = tree1.getOrDefault(node, List.of());

        for (Integer integer : childrenNode) {
            int childrenCounter = dfs(parents, integer, rm);
            if (node == rm) {
                score = score * childrenCounter;
            } else {
                counter += childrenCounter;
            }

        }

        // 将自己加进去
        if (node != rm) {
            counter += 1;

            if (node == 0) {
                score = score * counter;
            }
        }

        return counter;
    }


    // 保存树的结构
    // key: 父节点的值；value：子节点的列表
    static HashMap<Integer, List<Integer>> tree = new HashMap<>();
    static int[] rootMapper;

    // 这个方案超时了
    public static int countHighestScoreNodes1(int[] parents) {
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

        LinkedList<Integer> stack = new LinkedList<>();

        // 然后要一次减去指定的节点，去计算分数
        for (int i = 0; i < parents.length; i++) {
            // 计算分数
            int score = computeScore(parents, i);
            if (stack.peek() != null) {
                if (score < stack.peek()) {
                    continue;
                } else if (score > stack.peek()) {
                    stack.clear();
                }
            }

            stack.push(score);

            System.out.printf("node = %d, score =%d %n", i, score);
        }

        return stack.size();
    }

    // 使用dfs去计算分数
    private static int computeScore(int[] parents, int node) {

        final int[] root = new int[parents.length];
        for (int i = 1; i < parents.length; i++) {
            root[i] = -1;
        }

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
                if (root[parent] >= 0) {
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
                // 根节点重置
                root[_node] = _node;
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


        System.out.println("counter = " + counter);

        for (Integer value : counter.values()) {
            score = score * value;
        }

        return score;
    }

}
