package com.rysis.queue;

import java.util.Stack;

/**
 * Main_232
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/3/5 14:20
 */
public class Main_232 {

    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(12);
        System.out.println(obj.pop());
        System.out.println(obj.peek());
        System.out.println(obj.empty());
    }

    static class MyQueue {
        private Stack<Integer> in;
        private Stack<Integer> out;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            in = new Stack<>();
            out = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            in.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            // 将输入栈倒到输出栈上
            reverse();
            return out.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            // 将输入栈倒到输出栈上
            reverse();
            return out.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return out.isEmpty() && in.isEmpty();
        }

        private void reverse() {
            // 将输入栈倒到输出栈上
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
        }
    }
}
