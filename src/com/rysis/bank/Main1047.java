package com.rysis.bank;

import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Main_1047
 * 删除字符串中的所有相邻重复项
 *
 * @author rysis
 * @version 1.00
 * @date 2021/3/9 15:16
 */
public class Main1047 {

    public static void main(String[] args) {
        String s = "abbaca"; // ca
        System.out.println(removeDuplicates1(s));
        System.out.println(removeDuplicates(s));
    }

    // 使用栈
    public static String removeDuplicates(String S) {
        // 字符串栈
        Stack<Character> stack = new Stack<>();
        stack.push(S.charAt(0));

        for (int i = 1; i < S.length(); i++) {
            char c = S.charAt(i);
            // 比较当前元素与栈顶元素
            if (!stack.isEmpty() && c == stack.peek()) {
                // 栈顶的元素弹出
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        // 返回栈内的字符内容
        String result = stack.stream().map(c -> c.toString()).collect(Collectors.joining());
        return result;
    }

    // 使用数组模拟栈
    public static String removeDuplicates1(String S) {
        // 栈的顶部
        int index = 0;
        // 字符串栈
        char[] stack = new char[S.length()];
        stack[0] = S.charAt(0);

        for (int i = 1; i < S.length(); i++) {
            char c = S.charAt(i);
            // 比较当前元素与栈顶元素
            if (index >= 0 && c == stack[index]) {
                // 栈顶的元素弹出
                index--;
            } else {
                stack[++index] = c;
            }
        }
        // 返回栈内的字符内容
        return new String(stack, 0, index + 1);
    }
}
