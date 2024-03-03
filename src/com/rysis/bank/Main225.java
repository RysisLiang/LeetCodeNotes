package com.rysis.bank;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Main_1
 *
 * @author liang
 * @version 1.00
 * @date 2021/1/6 14:41
 */
public class Main225 {

    public static void main(String[] args) {
        MyStack obj = new MyStack();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        System.out.println("pop = " +obj.pop());
        System.out.println("top = " +obj.top());
        System.out.println("empty = " + obj.empty());
        System.out.println(obj);
        obj.push(4);
        obj.push(5);
        System.out.println(obj);
        System.out.println("top = " +obj.top());
        System.out.println("pop = " +obj.pop());
        System.out.println("empty = " + obj.empty());
        System.out.println(obj);

    }

    // 如何用两个队列模拟栈。
    // - 压入栈顶元素1。【1】【】
    // - 压入栈顶元素2。【1】【2】-》合并【】【21】
    // - 压入栈顶元素3。【3】【21】-》合并【321】【】
    // - 压入栈顶元素123。【123】【】-》翻转【321】【】
    // - 压入栈顶元素456。【321】【456】-》【321】翻转【654】-》【】【654321】
    // - 检查栈顶元素。【】peek头元素【654321】
    // - 弹出栈顶元素。【】弹出头元素【54321】
    // - 压入栈顶元素789。【789】【654321】-》翻转【987】【654321】-》【987654321】【】
    // - 压入栈顶元素7。【54321789】【】
    static class MyStack {
        // 新 -> 旧
        private final Queue<Integer> q1 = new LinkedList<>();
        // 旧 -> 新
        private final Queue<Integer> q2 = new LinkedList<>();

        public MyStack() {
            // init
        }

        public void push(int x) {
            final Queue<Integer> current;
            final Queue<Integer> other;
            // 1. 找到当前操作的队列。【】
            if (q1.isEmpty()) {
                // 不管q2是否有元素，都操作q1
                current = q1;
                other = q2;
            } else {
                // q1有元素，这里q2一定没有元素
                current = q2;
                other = q1;
            }

            // 2. 先添加元素。并且记录下，当前操作的队列。【456】
            current.add(x);

            // 3. 将本次添加的队列，进行翻转操作（批量时）。【654】

            // 4. 检查另一个队列，是否存在元素。如果存在元素进行元素合并。【654321】
            while (!other.isEmpty()) {
                current.add(other.poll());
            }
        }

        public int pop() {
            if (q1.isEmpty()) {
                if (q2.isEmpty()) {
                    return -1;
                }
                return q2.poll();
            } else {
                return q1.poll();
            }
        }

        public int top() {
            if (q1.isEmpty()) {
                if (q2.isEmpty()) {
                    return -1;
                }
                return q2.peek();
            } else {
                return q1.peek();
            }
        }

        public boolean empty() {
            return q1.isEmpty() && q2.isEmpty();
        }

        @Override
        public String toString() {
            return "MyStack{" +
                    "q1=" + q1 +
                    ", q2=" + q2 +
                    '}';
        }
    }

}
