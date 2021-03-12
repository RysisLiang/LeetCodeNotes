package com.rysis.queue;

/**
 * Main_227
 * 基本计算器2
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/3/11 17:03
 */
public class Main_227 {

    public static void main(String[] args) {
//        String s = " 3+5 / 2 "; // 5
//        String s = "3+2*2"; // 7
//        String s = " 3/2 "; // 1
//        String s = " 1-3*10+33 "; // 4
        String s = " -1-3*10+33 "; // 2
        System.out.println(calculate1(s));
        System.out.println(calculate(s));
    }


    // 代码简化一下
    public static int calculate(String s) {
        // 定义栈
        int stackTop = -1;
        int[] numStack = new int[s.length()];

        char symbol = '+'; // 运算符号
        int num = 0; // 缓存运算符前的数字
        // 优先计算乘除
        for (int i = 0; i < s.length(); i++) {
            char cc = s.charAt(i);
            if (cc >= '0' && cc <= '9') {
                num = num * 10 + cc - '0';
            }
            if (i == s.length() - 1 || cc != ' ' && (cc < '0' || cc > '9')) {
                if (symbol == '+') {
                    numStack[++stackTop] = num;
                } else if (symbol == '-') {
                    numStack[++stackTop] = -num;
                } else if (symbol == '*') {
                    numStack[stackTop] = numStack[stackTop] * num;
                } else if (symbol == '/') {
                    numStack[stackTop] = numStack[stackTop] / num;
                }
                // 将当前的运算符记录下来
                symbol = cc;
                num = 0;
            }
        }
        int result = 0;
        // 累加栈中的数字
        for (; stackTop >= 0; stackTop--) {
            result += numStack[stackTop];
        }
        return result;
    }

    // 1. 运算符仅有加减乘除组成
    // 2. 没有了括号
    // 3. 都是非负整数，不用考虑符号问题
    // 把 元素 都压进栈里？然后优先算乘除，栈中留下加减的操作和数字？然后所有相减的操作，减数都可以看做是负数，然后最后栈中进行相加操作？
    public static int calculate1(String s) {
        // 定义栈
        int stackTop = -1;
        int[] numStack = new int[s.length()];

        int symbol = 1; // 加减的符号
        int compute = 0; // 乘除的符号

        // 优先计算乘除
        for (int i = 0; i < s.length(); i++) {
            char cc = s.charAt(i);

            if (cc == ' ') {
                // skip
            } else if (cc == '+') {
                symbol = 1;
            } else if (cc == '-') {
                symbol = -1;
            } else if (cc == '*') {
                compute = 1;
            } else if (cc == '/') {
                compute = -1;
            } else { // 数字
                int num = cc - '0';
                char temp;
                while (i + 1 < s.length() && (temp = s.charAt(i + 1)) >= '0' && temp <= '9') {
                    num = num * 10 + temp - '0';
                    i++;
                }
                // 计算乘除
                int res = num;
                if (compute < 0) {
                    res = numStack[stackTop--] / num; // 操作后替换原位置数据
                    compute = 0; // 消耗了乘除
                } else if (compute > 0) {
                    res = numStack[stackTop--] * num; // 操作后替换原位置数据
                    compute = 0; // 消耗了乘除
                }
                // 加减符号
                numStack[++stackTop] = res * symbol;
                symbol = 1; // 消耗了正负号
            }
        }
        int result = 0;
        // 累加栈中的数字
        for (; stackTop >= 0; stackTop--) {
            result += numStack[stackTop];
        }
        return result;
    }
}

