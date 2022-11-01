package com.rysis.queue;

/**
 * Main_224
 * 基本计算器
 *
 * @author rysis
 * @version 1.00
 * @date 2021/3/10 15:54
 */
public class Main224 {

    public static void main(String[] args) {
        String s = "(1+ (4+5+2) - 3) + (6+8)"; // 23
//        String s = " 2-1 + 2 "; // 3
//        String s = "123"; // 123
//        String s = "-(3+(4+5))"; // -12
//        String s = "-(3- (-4 + 5))"; // -2
//        String s = "(7)-(0)+(4)"; // 11
        System.out.println(calculate1(s));
        System.out.println(calculate(s));
    }

    // 优雅的解法，由于没有乘除，那么括号其实仅影响正负号的变换。可以先拆括号然后在统一计算
    public static int calculate(String s) {
        // 定义栈，用于记录过往的操作内容和结果
        int stackTop = 0;
        int sum = 0; // 和
        int[] symbolStack = new int[s.length()]; // 括号符号栈
        symbolStack[0] = 1; // 默认是正号
        int outSymbol = 1; // 当前数字的符号记录器
        for (int i = 0; i < s.length(); i++) {
            char cc = s.charAt(i);

            if (cc == ' ') {
                // skip
            } else if (cc == '-') { // 变为符号转换
                outSymbol = -symbolStack[stackTop]; // peek取反
            } else if (cc == '+') { // 变为符号不转换
                outSymbol = symbolStack[stackTop]; // peek
            } else if (cc == '(') {
                symbolStack[++stackTop] = outSymbol; // push
            } else if (cc == ')') {
                stackTop--; // pop
            } else { // 数字
                int num = cc - '0';
                // 连续数字处理
                char temp = '0';
                while (i + 1 < s.length() && (temp = s.charAt(i + 1)) >= '0' && temp <= '9') {
                    num = num * 10 + temp - '0';
                    i++;
                }
                // pop符号栈
                if (outSymbol < 0) {
                    sum -= num;
                } else {
                    sum += num;
                }
            }
        }
        return sum;
    }

    // 粗制滥造解法，不优雅。
    public static int calculate1(String s) {
        // 定义栈，用于记录过往的操作内容和结果
        int stackTopIndex = 0;
        String[] stack = new String[s.length()];

        StringBuilder numBuff = new StringBuilder();
        // 正向解析压入栈
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);


            if (c >= '0' && c <= '9') { // 数字放入缓存中
                numBuff.append(c);
            } else {
                if (numBuff.length() > 0) {
                    // 数字进行回溯计算
                    stackTopIndex = add(stack, stackTopIndex, Integer.parseInt(numBuff.toString()));
                    // 清空数组缓存
                    numBuff.setLength(0);
                }
                if (Character.isSpaceChar(c)) {
                    continue;
                }
                if (")".equals(c + "")) { // 括号内进行回溯计算
                    stackTopIndex = add(stack, stackTopIndex, 0) - 1;
                    stack[stackTopIndex - 1] = stack[stackTopIndex];
                } else {
                    stack[stackTopIndex++] = c + "";
                }
            }
        }
        // 如果还有多次操作再进行一次计算
        if (stackTopIndex > 0) {
            add(stack, stackTopIndex, numBuff.length() > 0 ? Integer.parseInt(numBuff.toString()) : 0);
            numBuff.setLength(0);
        }
        if (numBuff.length() > 0) {
            return Integer.parseInt(numBuff.toString());
        }
        return Integer.parseInt(stack[0]);
    }

    /**
     * 计算括号内的连续数字的结果
     *
     * @param stack 操作记录栈
     * @param stackTop 栈顶标记（+1）
     * @param initNum 初始数值
     * @return 返回新的栈顶标记
     */
    public static int add(String[] stack, int stackTop, int initNum) {
        int sum = initNum;
        StringBuilder buff = new StringBuilder();
        for (; stackTop > 0; stackTop--) {
            String str = stack[stackTop - 1]; // 弹出栈顶
            try {
                buff.append(Integer.parseInt(str));
            } catch (NumberFormatException e) {
                if ("-".equals(str)) { // 相减的处理
                    sum = (buff.length() > 0 ? -Integer.parseInt(buff.toString()) + sum : -sum);
                    buff.setLength(0);
                } else if ("+".equals(str)) {
                    sum = (buff.length() > 0 ? Integer.parseInt(buff.toString()) : 0) + sum;
                    buff.setLength(0);
                } else if ("(".equals(str)) {
                    break;
                }
            }
        }
        sum = sum + (buff.length() > 0 ? Integer.parseInt(buff.toString()) : 0);
        stack[stackTop++] = sum + "";
        return stackTop;
    }
}
