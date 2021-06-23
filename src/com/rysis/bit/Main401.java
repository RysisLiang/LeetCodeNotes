package com.rysis.bit;

import java.util.ArrayList;
import java.util.List;

/**
 * Main401
 * 二进制手表
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/6/21 22:43
 */
public class Main401 {

    public static void main(String[] args) {
        int turnedOn = 1;

        System.out.println(readBinaryWatch(turnedOn));
    }

    // 穷举
    public static List<String> readBinaryWatch(int turnedOn) {
        ArrayList<String> result = new ArrayList<>();
        // 循环12次，表示小时
        for (int i = 0; i < 12; i++) {
            // 循环60次，表示分钟
            for (int mins = 0; mins < 60; mins++) {
                // 计算小时和分钟中1的个数，看与参数相比是否相等，相等则记录下来
                if (Integer.bitCount(i) + Integer.bitCount(mins) == turnedOn) {
                    result.add(String.format("%d:%02d", i, mins));
                }
            }
        }
        return result;
    }
}
