package com.rysis.bit;

import com.rysis.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main_1178
 * 猜字谜
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/26 12:02
 */
public class Main_1178 {

    public static void main(String[] args) {
        String words = "[\"aaaa\",\"asas\",\"able\",\"ability\",\"actt\",\"actor\",\"access\"]";
        String puzzles = "[\"aboveyz\",\"abrodyz\",\"abslute\",\"absoryz\",\"actresz\",\"gaswxyz\"]";
        System.out.println(findNumOfValidWords(ArrayUtil.handleToStringArray(words), ArrayUtil.handleToStringArray(puzzles)));
    }

    // 这道题真的不会没思路，看题解复刻吧，就当学习位运算方法了
    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        System.out.println(Arrays.toString(words));
        System.out.println(Arrays.toString(puzzles));



        return new ArrayList<>();
    }
}
