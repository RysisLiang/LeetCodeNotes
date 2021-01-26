package com.rysis.graph;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Main_959
 * 由斜杠划分区域
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/1/25 11:31
 */
public class Main_959 {

    public static void main(String[] args) {
//        String[] grid = {" /", "/ "}; // 2
//        String[] grid = {"\\/", "/\\"}; // 4
        String[] grid = {"//", "/ "}; // 3
        System.out.println(regionsBySlashes(grid));
    }

    static int[] topArray = null; // 每个区域所属的老大id
    static int[] rank = null; // 深度

    /*
    这题真的是没什么思路，还是看了评论第一的大佬的思路，才豁然开朗。思路如下：
    每个方块分成四个区域，1、2、3、4.
    - 然后无论何时，1和上3相连、2和右4相连、3和下1相连、4和左2相连；
    - 然后根据内部标记不同，内部区域也有相连。
        - \：1和2相连，3和4相连；
        - /：1和4相连，2和3相连；
        - ' '：全部相连；
     */
    public static int regionsBySlashes(String[] grid) {
        // 初始化数据
        topArray = new int[grid.length * grid[0].length() * 4]; // 所有方块*4个区域的老大数组
        rank = new int[grid.length * grid[0].length() * 4];
        for (int i = 0; i < topArray.length; i++) {
            topArray[i] = i;
            rank[i] = 1;
        }

        System.out.println("init topArray = " + Arrays.toString(topArray));
        System.out.println("init rank = " + Arrays.toString(rank));

        // 进行元素的比较合并
        for (int i = 0; i < grid.length; i++) {
            char[] chars = grid[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                char char1 = chars[j];
                // 当前元素的根索引。
                int root = i * chars.length * 4 + j * 4;
                // 进行元素内的合并操作
                if ('/' == char1) { // 14合并、23合并
                    merge(root, root + 3);
                    merge(root + 1, root + 2);
                } else if ('\\' == char1) {  // 12合并、34合并
                    merge(root, root + 1);
                    merge(root + 2, root + 3);
                } else { // 四个区域全部合并
                    merge(root, root + 1);
                    merge(root, root + 2);
                    merge(root, root + 3);
                }
                // 进行元素间的合并操作
                if (j > 0) { // 向左侧合并，左2永远与4合并
                    merge(root + 3, i * chars.length * 4 + (j - 1) * 4 + 1);
                }
                if (i > 0) { // 向上侧合并，上3永远与1合并
                    merge(root, (i - 1) * chars.length * 4 + j * 4 + 2);
                }
            }
        }

        System.out.println(Arrays.toString(topArray));

        // 然后找出所有的数的top个数
        HashSet<Integer> topSet = new HashSet<>();
        for (int i : topArray) {
            topSet.add(findTop(i));
        }

        System.out.println("topSet = " + topSet);

        // 返回树的个数
        return topSet.size();
    }

    // 查找指定位置的老大
    private static int findTop(int i) {
        return topArray[i] == i ? i : (topArray[i] = findTop(topArray[i]));
    }

    // 同集合元素进行合并
    private static void merge(int x, int y) {
        int topX = findTop(x);
        int topY = findTop(y);
        if (rank[topX] <= rank[topY]) {
            topArray[topX] = topY;
        } else {
            topArray[topY] = topX;
        }
        // 如果深度一致，rank[topY]下面又增加了一层rank[topX]的最低层
        if (rank[topX] == rank[topY] && topX != topY) {
            rank[topY]++;
        }
    }
}
