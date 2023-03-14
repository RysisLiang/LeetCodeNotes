package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.LinkedList;

/**
 * Main_456
 * 132模式
 *
 * @author rysis
 * @version 1.00
 * @date 2021/3/24 22:49
 */
public class Main456 {

    public static void main(String[] args) {
//        String s = "[3,1,4,2]"; // [1, 4, 2]
//        String s = "[1,2,3,4]"; // false
//        String s = "[-1,3,2,0]"; // [-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0]
        String s = "[1,0,1,-4,-3]"; // false

        System.out.println(find132pattern(ArrayUtil.handleToIntArray(s)));
    }

    public static boolean find132pattern2(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        // 满足2的数
        int m2 = Integer.MIN_VALUE;

        // 从右到左遍历数组
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            // 1小于2，角标关系也符合，栈中的元素都比m2大
            if (num < m2) {
                return true;
            }

            // 检查栈顶，如果小于则弹出来赋予给m2，这样m2会越来越大，但会小于栈中的所有元素
            while (!linkedList.isEmpty() && linkedList.peek() < num) {
                m2 = linkedList.pop();
            }

            // 元素压入栈
            linkedList.push(num);
        }

        return false;
    }

    // 暴力迭代。会超时
    public static boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        // 1
        int m1 = nums[0];

        // 找出3
        for (int i = 1, len = nums.length; i < len; i++) {
            // 找出2
            for (int j = len - 1; j > i; j--) {
                if (m1 < nums[j] && nums[j] < nums[i]) {
                    return true;
                }
            }
            // 保持m1一直是3的左侧最小值
            m1 = Math.min(m1, nums[i]);
        }
        return false;
    }

    // 双栈处理，这个不行
    public static boolean find132pattern1(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        // 有序栈
        LinkedList<Integer> linkedList = new LinkedList<>();
        // 缓存栈
        LinkedList<Integer> buff = new LinkedList<>();

        for (int num : nums) {
            System.out.println(linkedList);

            if (linkedList.isEmpty()) {
                linkedList.push(num);
            } else {
                Integer peek = linkedList.peek(); // 栈顶元素
                if (num == peek) { // 等于栈顶元素，跳过处理
                    continue;
                }
                if (num > peek) { // 大于栈顶元素，num压入栈
                    linkedList.push(num);
                } else { // 小于栈顶元素，找出顺序的位置，放入栈中
                    Integer temp = num;
                    // 将栈顶元素一次比较，直到找出小于num的元素
                    while (!linkedList.isEmpty() && (temp = linkedList.pop()) >= num) {
                        buff.push(temp);
                    }
                    // 只有栈顶元素（已弹出）小于num时，栈顶元素重新压入栈
                    if (temp < num) {
                        return true;
                    }
                    if (temp > num) { // 一样的大小就不压入栈了
                        // 将num压入栈
                        linkedList.push(num);
                    }
                    // 将大于num的元素从缓存中放回栈中
                    while (!buff.isEmpty()) {
                        linkedList.push(buff.pop());
                    }
                }
            }
        }

        System.out.println(linkedList);

        return false;
    }
}
