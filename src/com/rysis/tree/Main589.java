package com.rysis.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * <b>功能名：Main589</b><br>
 * <b>说明：</b>N叉树的前序遍历<br>
 *
 * @date 2022/03/10
 */
public class Main589 {

    public static void main(String[] args) {

    }

    // 迭代方法方法-前序遍历其实就是深度优先遍历=dfs
    public static List<Integer> preorder(Node root) {
        if (root == null) {
            return new LinkedList<>();
        }

        final LinkedList<Integer> result = new LinkedList<>();

        // 声明一个栈
        final LinkedList<Node> stack = new LinkedList<>();
        stack.push(root);
        // result.add(root.val); // 不需要，重复操作

        // 检查栈内元素
        while(!stack.isEmpty()) {
            // 获取上级节点
            final Node parent = stack.pop();
            result.add(parent.val);

            // 迭代子节点。由于是前序遍历，所以先迭代的元素要在栈顶，但是还不能从栈尾进入。所以必须反响迭代
            for (int i = parent.children.size() - 1; i >= 0; i--) {
                stack.push(parent.children.get(i));
            }
        }

        return result;
    }

    // 递归方法-该方法的定义就是获取节点下的前序列表
    public static List<Integer> preorder1(Node root) {
        if (root == null) {
            return new LinkedList<>();
        }

        final LinkedList<Integer> linkedList = new LinkedList<>();
        // 将当前的父节点，加入到列表中
        linkedList.add(root.val);

        // 遍历字节点
        for (Node child : root.children) {
            // 如果 子节点 还存在子节点，则递归获取该子节点下的全部前序元素
            linkedList.addAll(preorder(child));
        }

        return linkedList;
    }


    private static final class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}
