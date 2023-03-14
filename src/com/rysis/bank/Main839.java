package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Main_839
 * 相似字符串组
 *
 * @author rysis
 * @version 1.00
 * @date 2021/1/31 20:08
 */
public class Main839 {

    public static void main(String[] args) {
//        String strs = "[\"tars\",\"rats\",\"arts\",\"star\"]"; // 2
//        String strs = "[\"omv\",\"ovm\"]"; // 1
//        String strs = "[\"abc\",\"abc\"]"; // 1
        String strs = "[\"ufixvnxsdxalinayfaappbmmj\",\"nxpxiaauvyjxasbfmfinmdpla\",\"ujimiyxsaxpavnanfapmlbxdf\",\"ufimvyxsaxpainanfapdlbxmj\",\"nxpyajaumxixalbfvpdnmasfi\",\"nxpxiaauvyjxpsbfmfinmdala\",\"ufimvyxspxaainanfapdlbxmj\",\"nxpyaiaumxjxapbfvlanmdsfi\",\"ufimvyxspxapinanfaadlbxmj\",\"nxpyaaauvxjxasbfmfinmdpli\",\"nxpyajaumxixapbfvlanmdsfi\",\"nxpyaaaumxjxasbfvfinmdpli\",\"ufixvnbsdxalinayfamppxamj\",\"ujimvyxsaxpainanfapdlbxmf\",\"ufixvyxsdxalinanfaappbmmj\",\"nxpyaaaumxjxapbfvlinmdsfi\",\"ufixvyxspxalinanfaadpbmmj\",\"nxpyaaaumxjxasbfvlinmdpfi\",\"ufixvyxspxapinanfaadlbmmj\",\"ufixvnbsdxalinayfaappxmmj\",\"ufiavnbsdxxlinayfamppxamj\",\"nxpyajaumxixapbfvldnmasfi\",\"ufiavnbsdxxlinayfamppxajm\",\"nxpyiaauvxjxasbfmfinmdpla\",\"ujimiyxsaxpavnanfapdlbxmf\"]"; // 2
        System.out.println(numSimilarGroups(ArrayUtil.handleToStringArray(strs)));
    }

    // 并查集
    public static int numSimilarGroups(String[] strs) {
        UnionFind unionFind = new UnionFind(strs.length);
        for (int i = 0; i < strs.length - 1; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                System.out.printf("strs[i]=%s, strs[j]=%s, [%d]-[%d]=%b%n", strs[i], strs[j], i, j, isLike(strs[i], strs[j]));
                if (isLike(strs[i], strs[j])) {
                    unionFind.merge(i, j);
                }
            }
        }

        System.out.println("topArr = " + Arrays.toString(unionFind.topArr));

        TreeSet<Integer> set = new TreeSet<>();
        int[] ints = new int[strs.length];
        for (int i = 0; i < unionFind.topArr.length; i++) {
            int top = unionFind.findTop(unionFind.topArr[i]);
            ints[i] = top;
            set.add(top);
        }
        System.out.println("ints = " + Arrays.toString(ints));
        System.out.println("set = " + set);

        return set.size();
    }

    /**
     * 比较字符串是否相似
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return
     */
    private static boolean isLike(String str1, String str2) {
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
            }
            if (count >= 3) {
                return false;
            }
        }
        return true;
    }

    static class UnionFind {
        public int[] topArr;
        public int[] ranks;

        public UnionFind(int size) {
            topArr = new int[size];
            ranks = new int[size];

            for (int i = 0; i < size; i++) {
                topArr[i] = i;
                ranks[i] = 1;
            }
        }

        public int findTop(int i) {
            return topArr[i] == i ? i : (topArr[i] = findTop(topArr[i]));
        }

        public void merge(int x, int y) {
            int topX = findTop(x);
            int topY = findTop(y);
            if (ranks[topX] <= ranks[topY]) {
                topArr[topX] = topY;
            } else {
                topArr[topY] = topX;
            }
            if (ranks[topX] == ranks[topY] && topX != topY) {
                ranks[topY]++;
            }
        }
    }

}
