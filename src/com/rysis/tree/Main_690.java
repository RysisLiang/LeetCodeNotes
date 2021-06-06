package com.rysis.tree;

import java.util.*;

/**
 * Main_690
 * 员工的重要性
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/5/1 23:01
 */
public class Main_690 {

    public static void main(String[] args) {
        // [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1 = 11
        List<Employee> employees = Arrays.asList(
                new Employee(1, 5, Arrays.asList(2, 3)),
                new Employee(2, 3, Collections.emptyList()),
                new Employee(3, 3, Collections.emptyList())
        );
//        // [[1,2,[2]], [2,3,[]]] 2 = 3
//        List<Employee> employees = Arrays.asList(
//                new Employee(1, 2, Arrays.asList(2)),
//                new Employee(2, 3, Collections.emptyList())
//        );

        System.out.println(getImportance(employees, 1));
    }

    // dfs-递归
    // 记录指定员工的重要度
    private static HashMap<Integer, Employee> map = new HashMap<>();

    public static int getImportance(List<Employee> employees, int id) {
        // 为了方便查找员工先把员工，根据id进行映射
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs(id);
    }

    // 栈的dfs
    private static int dfs(int id) {
        // 使用栈来存储节点
        LinkedList<Employee> linkedList = new LinkedList<>();

        Employee employee = map.get(id);
        linkedList.push(employee);
        int res = 0;
        // 检查栈
        while (!linkedList.isEmpty()) {
            // 弹出栈顶
            Employee pop = linkedList.pop();
            res += pop.importance;
            // 检查栈顶是否存在下属，并压入栈中
            for (Integer sub : pop.subordinates) {
                linkedList.push(map.get(sub));
            }
        }
        return res;
    }

    // 递归的dfs
    private static int dfs1(int id) {
        Employee employee = map.get(id);
        // 重要度
        int res = employee.importance;
        for (Integer subordinate : employee.subordinates) {
            res += dfs(subordinate);
        }
        return res;
    }

    // 由于是查看关联关系的结果，所以先试试并查集
    public static int getImportance1(List<Employee> employees, int id) {
        // 初始化并查集大小。由于题目限制至多2000个员工
        Union union = new Union(2001);
        // 储存员工重要性数据数组
        int[] importanceArray = new int[2001];
        // 录入数据
        for (Employee employee : employees) {
            // 记录员工重要性，方便根据id快速获取重要性
            importanceArray[employee.id] = employee.importance;
            // 遍历直属下属
            for (Integer subordinate : employee.subordinates) {
                // 如果下属员工是目标员工，那么最终的结果他就会使根员工，所以不让其添加其上司的下面
                if (subordinate != id) {
                    union.merge(employee.id, subordinate);
                }
            }
        }
//        System.out.println(Arrays.toString(union.topArr));
//        System.out.println(Arrays.toString(importanceArray));
        // 找出目标元素需要的
        int result = 0;
        for (int i = 0; i < union.topArr.length; i++) {
            if (union.findTop(union.topArr[i]) == id) {
                System.out.print(result + " = " + importanceArray[i]);
                result += importanceArray[i];
                System.out.println(" == " + result);
            }
        }

        return result;
    }

    // 并查集使用
    private static class Union {
        public int[] topArr;

        public Union(int size) {
            topArr = new int[size];
            for (int i = 0; i < size; i++) {
                topArr[i] = i;
            }
        }

        public int findTop(int x) {
            return topArr[x] == x ? x : (topArr[x] = findTop(topArr[x]));
        }

        /**
         * 因为这个就是有上下级顺序之分，所以一定是下级添加到上级的后面，就不存在ranks来缩减树的长度
         *
         * @param x 上司id
         * @param y 下属id
         */
        public void merge(int x, int y) {
            int topX = findTop(x);
            int topY = findTop(y);
            topArr[topY] = topX;
        }
    }
}

// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;

    public Employee() {
    }

    public Employee(int id, int importance, List<Integer> subordinates) {
        this.id = id;
        this.importance = importance;
        this.subordinates = subordinates;
    }
};
