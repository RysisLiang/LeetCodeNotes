package com.rysis.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    // 由于是查看关联关系的结果，所以先试试并查集
    public static int getImportance(List<Employee> employees, int id) {
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
    static class Union {
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
