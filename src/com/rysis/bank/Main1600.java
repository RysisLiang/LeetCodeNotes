package com.rysis.bank;

import java.util.*;

/**
 * Main_1600
 *
 * @author rysis
 * @version 1.00
 * @date 2021/6/20 12:29
 */
public class Main1600 {

    public static void main(String[] args) {
        ThroneInheritance obj = new ThroneInheritance("king");
        obj.birth("king", "andy");
        obj.birth("king", "bob");
        obj.birth("king", "catherine");
        obj.birth("andy", "matthew");
        obj.birth("bob", "alex"); // 继承顺序：king > andy > matthew > bob > alex > catherine
        obj.birth("bob", "asha"); // 继承顺序：king > andy > matthew > bob > alex > asha > catherine
        List<String> l1 = obj.getInheritanceOrder();// 返回 ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"]
        System.out.println(l1);
        obj.death("bob");
        List<String> l2 = obj.getInheritanceOrder();
        System.out.println(l2);
    }

    // 多叉树
    // 上面的顺序归根到底就是
    // 自己的长子优先继承，如果没有长子则第一个兄弟继承；
    // 其实就是DFS遍历结果就行
    static class ThroneInheritance {
        private String root;
        // 家族族谱
        private HashMap<String, List<String>> m = new HashMap<String, List<String>>();
        // 死亡清单
        private Set<String> deathSet = new HashSet<>();

        public ThroneInheritance(String kingName) {
            root = kingName;
            // 初始化族谱老大
            m.putIfAbsent(kingName, new ArrayList<String>());
        }

        public void birth(String parentName, String childName) {
            // 如果老爸不存在，则放入一个空
            m.putIfAbsent(parentName, new ArrayList<String>());
            // 给老爸添加孩子
            m.get(parentName).add(childName);
        }

        public void death(String name) {
            // 记录死亡名单
            deathSet.add(name);
        }

        public List<String> getInheritanceOrder() {
//            System.out.println("m=" + m);
//            System.out.println("deathSet=" + deathSet);
            return dfs();
        }

        // 使用深度优先遍历，并排除死亡名单
        private List<String> dfs() {
            ArrayList<String> result = new ArrayList<>();
            // 使用栈
            LinkedList<String> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                String pop = stack.pop();
                List<String> children = m.getOrDefault(pop, Collections.emptyList());
                // 这里应该把孩子从小打到压入栈中
                for (int i = children.size() - 1; i >= 0; i--) {
                    stack.push(children.get(i));
                }
                // 检查该人员是否死亡
                if (!deathSet.contains(pop)) {
                    result.add(pop);
                }
            }
            return result;
        }
    }
}
