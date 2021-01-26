package com.rysis.array;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Main_1128
 * 等价多米诺骨牌对的数量
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/1/14 11:27
 */
public class Main_1128 {

    public static void main(String[] args) {
//        String dominoes = "[[1,2],[2,1],[3,4],[5,6]]"; // 1
        String dominoes = "[[1,2],[1,2],[1,1],[1,2],[2,2]]"; // 3
        System.out.println(numEquivDominoPairs(ArrayUtil.handleToNestedArray(dominoes)));

    }

    // 暴力遍历法
    public static int numEquivDominoPairs(int[][] dominoes) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < dominoes.length; i++) {
            int[] dominoe = dominoes[i];
            Arrays.sort(dominoe);
            String key = "" + dominoe[0] + dominoe[1];
            // 如果存在+1
            map.computeIfPresent(key, (k, v) -> v += 1);
            // 如果不存在就=1
            map.putIfAbsent(key, 1);
        }

        System.out.println("map = " + map);

        return map.values()
                .stream()
                .map(i -> i * (i - 1) / 2) // 数学法计算多点间的线段个数
                .reduce(0, (a, b) -> a + b);
    }

}
