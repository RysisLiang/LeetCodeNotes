package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.LinkedList;

/**
 * Main_1052
 * 爱生气的书店老板
 *
 * @author rysis
 * @version 1.00
 * @date 2021/2/23 14:18
 */
public class Main1052 {

    public static void main(String[] args) {
//        String customers = "[1,0,1,2,1,1,7,5]";
//        String grumpy = "[0,1,0,1,0,1,0,1]";
//        int X = 3; // 16
//        String customers = "[1,0,1,2,1,1,7,5,0,0,1,3,56,2]";
//        String grumpy = "[0,1,0,1,0,1,0,1,0,1,1,1,0,0]";
//        int X = 3; // 74
        String customers = "[22,609,498,467,957,156,897,839,136,382,43,395,910,662,496,472,582,573,355,849,174,77,900,751,487,530,566,350,15,351,793,166,698,583,858,895,907,942,2,512,895,30,270,585,838,271,905,476,217,536]";
        String grumpy = "[1,0,1,1,0,0,0,1,0,1,1,0,0,1,1,1,0,1,1,0,1,0,1,0,0,0,0,1,1,0,1,1,1,0,0,0,1,1,1,0,0,1,0,1,1,0,0,0,0,0]";
        int X = 26; // 22176

        System.out.println(maxSatisfied2(ArrayUtil.handleToIntArray(customers), ArrayUtil.handleToIntArray(grumpy), X));
        System.out.println(maxSatisfied(ArrayUtil.handleToIntArray(customers), ArrayUtil.handleToIntArray(grumpy), X));
    }

    // 题解2ms，为什么那么快？我单次循环比他两次循环还慢？
    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int total = 0; // 自然满意顾客
        int max = 0; // 最大挽留顾客
        int window = 0; // 窗口内的挽留顾客
        // 时间轴
        for (int i = 0, len = customers.length; i < len; i++) {
            // 检查窗口移动抛弃的数据
            if (i - X >= 0) {
                window -= customers[i - X]; // 抛弃时生气，减去挽留顾客数
            }
            if (grumpy[i] == 1) { // 是否生气状态
                window += customers[i];
                max = Math.max(max, window);
            } else {
                total += customers[i];
                customers[i] = 0;
            }
        }
        return total + max;
    }

    // 简化下代码和逻辑。这里题目的思路和之前是一样的。但是技能的持续时间是固定的，所以这是一个固定的窗口，我们可以抛弃掉链表.
    // 窗口固定，使用单次数组循环。每次窗口移动时，都会检查是否需要减去窗口内挽留顾客数；且新增顾客应该加到total还是window中
    public static int maxSatisfied2(int[] customers, int[] grumpy, int X) {
        int total = 0; // 自然满意顾客
        int max = 0; // 最大挽留顾客
        int window = 0; // 窗口内的挽留顾客
        // 时间轴
        for (int i = 0; i < customers.length; i++) {
            int customer = customers[i]; // 当前的顾客数目
            // 检查窗口移动抛弃的数据
            if (i - X >= 0 && grumpy[i - X] == 1) {
                window -= customers[i - X]; // 抛弃时生气，减去挽留顾客数
            }
            if (grumpy[i] == 1) { // 是否生气状态
                window += customer;
                max = Math.max(max, window);
            } else {
                total += customer;
            }
        }
        return total + max;
    }

    // 滑动窗口来模拟技能发动的时间。
    // 不生气时的顾客数目是固定的，要想释放技能达到最大值，只能是当窗口内的挽回顾客（生气转不生气）达到最大值时，这时的整体顾客也会达到最大值
    public static int maxSatisfied1(int[] customers, int[] grumpy, int X) {
        // 技能释放点
        LinkedList<Integer> list = new LinkedList<Integer>();
        int total = 0; // 自然满意顾客数目
        int max = 0;  // 最大挽回顾客数目
        int window = 0;  // 窗口顾客数目

        // 滚动时间列表
        for (int i = 0; i < customers.length; i++) {
            int customer = customers[i]; // 当前的顾客数目
            boolean isGrumpy = grumpy[i] == 1; // 是否生气状态


            if (isGrumpy) { // 生气
                // 累加挽回的顾客
                window += customer;
                list.offer(i); // 记录生气时间
//                int mins = i - list.peek() + 1; // 这里应该循环缩减窗口左边界，而不是一次性缩小
                while(i - list.peek() + 1 > X) { // 当前释放时间大于目标释放时间
                    Integer poll = list.poll();
                    window -= customers[poll];
                }
                // 更新满意顾客数目
                max = Math.max(max, window);
            } else {
                // 累加自然满意的顾客
                total += customer;
            }
//            System.out.printf("i=%d, customer=%d [%b] | total=%d max=%d window=%d %s%n", i, customer, isGrumpy, total, max, window, list);
        }
        return total + max;
    }
}
